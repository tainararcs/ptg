package br.trcs.ptg.logic;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.trcs.ptg.dao.DAO;
import br.trcs.ptg.model.Question;
import br.trcs.ptg.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component(Consts.LIST_QUESTION_LOGIC)
public class ListQuestions implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		var questionsList = new DAO<>(Question.class).list();

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	
	    ObjectMapper mapper = new ObjectMapper();
	    response.getWriter().write(mapper.writeValueAsString(questionsList));
	
	    return null; 
	}
}