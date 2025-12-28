package br.trcs.ptg.logic;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

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
		System.out.println("subjectId: " + request.getParameter("subjectId"));
        System.out.println("topicId: " + request.getParameter("topicId"));
        
		HttpSession session = request.getSession();

        Integer subjectId = Integer.valueOf(request.getParameter("subjectId"));
        Integer topicId = Integer.valueOf(request.getParameter("topicId"));
        Bimester bimester = Bimester.valueOf(request.getParameter("bimester"));

        QuestionDAO dao = new QuestionDAO();
        List<Question> questions = dao.searchSpecifiQuestions(subjectId, topicId, bimester);

        if (questions.size() < Consts.NUMBER_QUESTIONS) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        Collections.shuffle(questions);
        List<Question> selectedQuestions = questions.subList(0, Consts.NUMBER_QUESTIONS);

        // Sessão só para correção posterior.
        session.setAttribute("selectedQuestions", selectedQuestions);
        session.setAttribute("subjectId", subjectId);
        session.setAttribute("bimester", bimester);

        List<Map<String, Object>> result = selectedQuestions.stream().map(q -> Map.of(
    	    "id", q.getId(),
    	    "statement", q.getStatement(),
    	    "options", q.getOptionsList().stream().map(o -> Map.of(
    	        "id", o.getId(),
    	        "text", o.getText()
    	    )).toList()
    	)).toList();

    	response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    	new ObjectMapper().writeValue(response.getWriter(), result);

        return null;
    }
}