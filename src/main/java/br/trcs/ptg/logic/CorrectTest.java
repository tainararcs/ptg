package br.trcs.ptg.logic;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.trcs.ptg.dao.DAO;
import br.trcs.ptg.enums.Bimester;
import br.trcs.ptg.model.OptionsQuestion;
import br.trcs.ptg.model.Question;
import br.trcs.ptg.model.QuestionsTest;
import br.trcs.ptg.model.Subject;
import br.trcs.ptg.model.Test;
import br.trcs.ptg.model.User;
import br.trcs.ptg.utils.Consts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component(Consts.CORRECT_TEST_LOGIC)
public class CorrectTest implements Logic {

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(Consts.USER_LOGGED);
        @SuppressWarnings("unchecked")
        List<Question> questions = (List<Question>) session.getAttribute("selectedQuestions");
        Integer subjectId = (Integer) session.getAttribute("subjectId");
        Bimester bimester = (Bimester) session.getAttribute("bimester");

        if (user == null || questions == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        // Corrige o teste usando.
        int score = correctTest(request);

        // Cria o objeto Test.
        Subject subject = new DAO<>(Subject.class).findById(subjectId);
        Test test = new Test();
        test.setDate(LocalDate.now());
        test.setUserID(user);
        test.setSubjectId(subject);
        test.setBimester(bimester);
        test.setNumberCorrectAnswers(score);

        // Salva o teste antes de armazenar as questões.
        DAO<Test> testDao = new DAO<>(Test.class);
        testDao.insert(test);

        // Salva o vínculo Test e Questions.
        String[] questionIds = questions.stream().map(q -> String.valueOf(q.getId())) .toArray(String[]::new);
        saveQuestions(test, questionIds);

        // Cria lista detalhada de questões para envio ao front-end.
        List<Map<String, Object>> questionResults = new ArrayList<>();
        for (Question q : questions) {
            List<Map<String, Object>> options = new ArrayList<>();
            if (q.getOptionsList() != null) {
                for (OptionsQuestion opt : q.getOptionsList()) {
                    options.add(Map.of(
                        "id", opt.getId(),
                        "text", opt.getText(),
                        "correct", opt.getCorrect()
                    ));
                }
            }

            Integer userAnswerId = null;
            String ans = request.getParameter("answer_" + q.getId());
            if (ans != null) userAnswerId = Integer.valueOf(ans);

            questionResults.add(Map.of(
                "id", q.getId(),
                "statement", q.getStatement(),
                "userAnswerId", userAnswerId,
                "options", options
            ));
        }

        // Monta e envia JSON para o front-end.
        Map<String, Object> result = Map.of(
            "score", score,
            "total", questions.size(),
            "questions", questionResults
        );

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        new ObjectMapper().writeValue(response.getWriter(), result);

        return null;
    }

    /**
	 * Corrige o teste e retorna a quantidade de acertos.
	 * @param request
	 * @return número de questões corretas
	 */
	private Integer correctTest(HttpServletRequest request) {
		int score = 0;
		DAO<OptionsQuestion> dao = new DAO<>(OptionsQuestion.class);
		
		for (String key : request.getParameterMap().keySet()) {
			if (key.startsWith("answer_")) {
				String[] values = request.getParameterValues(key);
				Integer optionId = Integer.valueOf(values[0]);
				OptionsQuestion option = dao.findById(optionId);
				
				if (option != null && option.getCorrect()) {
					score++;
				}
			}
		}
		
		return score;
	}
    
    /**
	 * Salva as questões vinculadas ao teste.
	 * @param test
	 * @param questionIds
	 */
	private void saveQuestions(Test test, String[] questionIds) {
		DAO<QuestionsTest> questionTestDao = new DAO<>(QuestionsTest.class);
		DAO<Question> questionDao = new DAO<>(Question.class);
		DAO<Test> testDao = new DAO<>(Test.class);
		
		// Recarrega o Test na sessão atual.
		Test managedTest = testDao.findById(test.getId());
		
		for (String questionId : questionIds) {
			Question question = questionDao.findById(Integer.valueOf(questionId));
			QuestionsTest questionsTest = new QuestionsTest();
			questionsTest.setTestId(managedTest);
			questionsTest.setQuestionId(question);
			questionTestDao.insert(questionsTest);
		}
	}
}