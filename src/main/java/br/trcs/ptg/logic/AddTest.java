package br.trcs.ptg.logic;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.trcs.ptg.dao.DAO;
import br.trcs.ptg.enums.Bimester;
import br.trcs.ptg.model.OptionsQuestion;
import br.trcs.ptg.model.Question;
import br.trcs.ptg.model.QuestionsTest;
import br.trcs.ptg.model.Subject;
import br.trcs.ptg.model.Test;
import br.trcs.ptg.model.User;
import br.trcs.ptg.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * 
 */
@Component(Consts.ADD_TEST_LOGIC)
public class AddTest implements Logic {
	 
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Integer subjectId = Integer.valueOf(request.getParameter("subjectId"));
		Bimester bimester = Bimester.valueOf(request.getParameter("bimester"));
		String[] questionIds = request.getParameterValues("questionIds");
		User user = (User) session.getAttribute(Consts.USER_LOGGED);
		
		// Recupera as questões da sessão.
		@SuppressWarnings("unchecked")
		List<Question> selectedQuestions = (List<Question>) session.getAttribute("selectedQuestions");
		
		// Validação simples.
		if (subjectId == null || bimester == null || questionIds == null) {
			request.setAttribute(Consts.ERROR, "Preencha todos os campos");
			return Consts.ACTION_ADD_TEST;
		}
		
		// Validação da sessão do usuário.
		if (user == null) {
			request.setAttribute(Consts.ERROR, "Sessão expirada. Faça login novamente");
			return Consts.LOGIN_PAGE;
		}
		
		// Sem abrir novas Sessions
		Subject subject = new DAO<>(Subject.class).findById(subjectId);
		
		// 🔥 NOVO: Armazenar respostas do usuário antes de corrigir
		Map<Integer, Integer> userAnswers = new HashMap<>();
		for (String key : request.getParameterMap().keySet()) {
			if (key.startsWith("answer_")) {
				// Extrair o ID da questão do parâmetro (answer_123 -> 123)
				Integer questionId = Integer.valueOf(key.substring(7));
				String[] values = request.getParameterValues(key);
				Integer optionId = Integer.valueOf(values[0]);
				userAnswers.put(questionId, optionId);
			}
		}
		
		// Contabiliza a quantidade de acertos.
		Integer correctAnswers = correctTest(request);
		
		Test test = new Test();
		test.setDate(LocalDate.now());
		test.setUserID(user);
		test.setSubjectId(subject);
		test.setBimester(bimester);
		test.setNumberCorrectAnswers(correctAnswers);
		
		// Salva o teste antes de armazenar as questões.
		DAO<Test> dao = new DAO<Test>(Test.class);
		dao.insert(test);
		
		// Salva o vínculo teste e questões.
		saveQuestions(test, questionIds);
		
		// Armazena dados na sessão para mostrar resultados.
		session.setAttribute("userAnswers", userAnswers);
		session.setAttribute("showResults", true);
		session.setAttribute("score", correctAnswers);
		session.setAttribute("selectedQuestions", selectedQuestions);
		
		return Consts.ADD_TEST_PAGE;
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
		
		// RECARREGA o Test na sessão atual
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