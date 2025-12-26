package br.trcs.ptg.logic;

import java.io.IOException;

import org.springframework.stereotype.Component;

import br.trcs.ptg.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 */
@Component(Consts.PREPARE_TESTS_LOGIC)
public class PrepareAddTests implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Reinicia todo o ciclo do teste.
        request.getSession().removeAttribute("selectedQuestions");
        request.getSession().removeAttribute("showResults");
        request.getSession().removeAttribute("userAnswers");
        request.getSession().removeAttribute("score");
        request.getSession().removeAttribute("subjectId");
        request.getSession().removeAttribute("topicId");
        request.getSession().removeAttribute("bimester");
        request.getSession().removeAttribute("grade");
        
        return Consts.REDIRECT_ADD_TEST_PAGE;
	}
}