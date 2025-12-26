package br.trcs.ptg.logic;

import java.io.IOException;

import org.springframework.stereotype.Component;

import br.trcs.ptg.dao.DAO;
import br.trcs.ptg.model.User;
import br.trcs.ptg.utils.Consts;
import br.trcs.ptg.utils.SecurityUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component(Consts.ADD_USER_LOGIC)
public class AddUser implements Logic {

	@Override
	public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SecurityUtils.validaPermission(request, response, "admin");
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String profile = request.getParameter("profile");

        // Validação simples.
        if (name == null || name.isBlank() || password == null || password.isBlank() || profile == null || profile.isBlank()) {
            request.setAttribute(Consts.ERROR, "Preencha todos os campos");
            return Consts.ADD_USER_PAGE;
        }
        
        User user = new User();
        user.setName(name);
        user.setPassword(SecurityUtils.encryptPassword(password));
        user.setProfile(profile);

        DAO<User> dao = new DAO<User>(User.class);
        dao.insert(user);

        request.setAttribute(Consts.MSG, "Usuário cadastrado com sucesso");
        return Consts.ADD_USER_PAGE;
	}
}