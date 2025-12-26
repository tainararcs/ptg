package br.trcs.ptg.logic;

import java.io.IOException;

import org.springframework.stereotype.Component;

import br.trcs.ptg.dao.UserDAO;
import br.trcs.ptg.model.User;
import br.trcs.ptg.utils.Consts;
import br.trcs.ptg.utils.SecurityUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 
 */
@Component(Consts.LOGIN_LOGIC)
public class Login implements Logic {
    
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validação básica.
	    if (username == null || password == null || username.isBlank() || password.isBlank()) {
	        request.setAttribute(Consts.ERROR, "Preencha todos os campos");
	        return Consts.LOGIN_PAGE;
	    }
	    
	    UserDAO dao = new UserDAO();
        User user = dao.findByNameAndPassword(username, SecurityUtils.encryptPassword(password));

        if (user != null) {
            request.getSession().setAttribute(Consts.USER_LOGGED, user);
            return Consts.REDIRECT_HOME_PAGE;
        }
        
        request.getSession().setAttribute(Consts.ERROR, "Nome ou senha incorretos");
        return Consts.REDIRECT_LOGIN_PAGE; 
    }
}