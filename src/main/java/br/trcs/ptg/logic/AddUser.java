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

/**
 * Componente Spring responsável por tratar a lógica de adição de um novo usuário (User) no sistema.
 * <br>
 * Este componente implementa a interface {@link Logic}.
 */
@Component(Consts.ADD_USER_LOGIC)
public class AddUser implements Logic {

    /**
     * Método principal que processa a requisição para adicionar um novo usuário.
     * 
     * @param request  HttpServletRequest contendo os parâmetros enviados pelo formulário.
     * @param response HttpServletResponse usado para redirecionamentos e validações.
     * @return String que representa a página a ser exibida após a execução.
     * 
     * @throws ServletException em caso de erro de servlet.
     * @throws IOException em caso de erro de I/O.
     */
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Valida se o usuário tem permissão de administrador.
        SecurityUtils.validaPermission(request, response, "admin");

        // Recupera os parâmetros da requisição.
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String profile = request.getParameter("profile");

        // Validação básica dos campos obrigatórios.
        if (name == null || name.isBlank() || password == null || password.isBlank() || profile == null || profile.isBlank()) {
            request.setAttribute(Consts.ERROR, "Preencha todos os campos");
            return Consts.ADD_USER_PAGE;
        }

        User user = new User();
        user.setName(name);
        user.setPassword(SecurityUtils.encryptPassword(password));
        user.setProfile(profile);

        DAO<User> dao = new DAO<>(User.class);
        dao.insert(user);

        request.setAttribute(Consts.MSG, "Usuário cadastrado com sucesso");
        return Consts.ADD_USER_PAGE;
    }
}