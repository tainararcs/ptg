package br.trcs.ptg.logic;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.trcs.ptg.dao.DAO;
import br.trcs.ptg.model.User;
import br.trcs.ptg.utils.Consts;
import br.trcs.ptg.utils.SecurityUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component(Consts.LIST_USER_LOGIC)
public class ListUsers implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SecurityUtils.validaPermission(request, response, "admin");
		
		var usersList = new DAO<>(User.class).list();

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	
	    ObjectMapper mapper = new ObjectMapper();
	    response.getWriter().write(mapper.writeValueAsString(usersList));
	
	    return null; 
	}
}