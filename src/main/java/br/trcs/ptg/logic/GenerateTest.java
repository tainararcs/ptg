package br.trcs.ptg.logic;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import br.trcs.ptg.dao.QuestionDAO;
import br.trcs.ptg.enums.Bimester;
import br.trcs.ptg.model.Question;
import br.trcs.ptg.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * 
 */
@Component(Consts.GENERATE_TEST_LOGIC)
public class GenerateTest implements Logic {
	
	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Integer subjectId = Integer.valueOf(request.getParameter("subjectId"));
		Integer topicId = Integer.valueOf(request.getParameter("topicId"));
		Bimester bimester = Bimester.valueOf(request.getParameter("bimester"));
		
		// Validação simples
		if (subjectId == null || topicId == null || bimester == null) {
			request.setAttribute(Consts.ERROR, "Preencha todos os campos");
			return Consts.ADD_TEST_PAGE;
		}
		
		// Buscar questões da disciplina e bimestre.
		QuestionDAO dao = new QuestionDAO();
		List<Question> questions = dao.searchSpecifiQuestions(subjectId, topicId, bimester);
		
		if (questions.size() < Consts.NUMBER_QUESTIONS) {
			request.setAttribute(Consts.ERROR, "Não há questões suficientes para gerar o teste");
			return Consts.ADD_TEST_PAGE;
		}
		
		// Sorteio aleatório das questões.
		Collections.shuffle(questions);
		List<Question> selectedQuestions = questions.subList(0, Consts.NUMBER_QUESTIONS);
		
		// Armazena na sessão.
		session.setAttribute("selectedQuestions", selectedQuestions);
		session.setAttribute("subjectId", subjectId);
		session.setAttribute("topicId", topicId);
		session.setAttribute("bimester", bimester.name());
		session.setAttribute("grade", request.getParameter("grade"));
		session.setAttribute("selectedBimester", bimester);

		return Consts.ADD_TEST_PAGE;
	}
}