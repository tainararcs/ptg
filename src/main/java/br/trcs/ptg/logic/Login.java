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
 * Componente Spring responsável por tratar a lógica de login de usuários no sistema.
 * <be>
 * Implementa a interface {@link Logic}.
 */
@Component(Consts.LOGIN_LOGIC)
public class Login implements Logic {
    
    /**
     * Método principal que processa a requisição de login de um usuário.
     * 
     * @param request  HttpServletRequest contendo os parâmetros enviados pelo formulário.
     * @param response HttpServletResponse usado para redirecionamentos e validações.
     * @return String que representa a página a ser exibida após a execução.
     * 
     * @throws ServletException em caso de erro de servlet.
     * @throws IOException  em caso de erro de I/O..
     */
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Recupera os parâmetros da requisição.
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validação básica dos campos obrigatórios.
        if (username == null || password == null || username.isBlank() || password.isBlank()) {
            request.setAttribute(Consts.ERROR, "Preencha todos os campos");
            return Consts.LOGIN_PAGE;
        }

        UserDAO dao = new UserDAO();
        User user = dao.findByNameAndPassword(username, SecurityUtils.encryptPassword(password));

        if (user != null) {
            // Usuário autenticado com sucesso, armazena na sessão.
            request.getSession().setAttribute(Consts.USER_LOGGED, user);
            return Consts.REDIRECT_HOME_PAGE;
        }

        request.getSession().setAttribute(Consts.ERROR, "Nome ou senha incorretos");
        return Consts.REDIRECT_LOGIN_PAGE; 
    }
}