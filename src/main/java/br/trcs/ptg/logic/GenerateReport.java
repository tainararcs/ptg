package br.trcs.ptg.logic;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.trcs.ptg.dao.TestDAO;
import br.trcs.ptg.model.Test;
import br.trcs.ptg.model.User;
import br.trcs.ptg.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 */
@Component(Consts.GENERATE_REPORT_LOGIC)
public class GenerateReport implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User userLogged = (User) request.getSession().getAttribute(Consts.USER_LOGGED);

		if (userLogged == null) {
            request.getSession().setAttribute(Consts.ERROR, "Sessão expirada");
            return Consts.REDIRECT_LOGIN_PAGE;
        }
		
        TestDAO dao = new TestDAO();
        List<Test> tests = null;
        
        if ("admin".equalsIgnoreCase(userLogged.getProfile()))
            tests = dao.searchAllTests();
        else
            tests = dao.searchUserTests(userLogged);
        
        request.getSession().setAttribute("tests", tests);
        return Consts.REDIRECT_SHOW_REPORT_PAGE;
	}
}