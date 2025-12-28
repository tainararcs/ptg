package br.trcs.ptg.logic;

import java.io.IOException;

import org.springframework.stereotype.Component;

import br.trcs.ptg.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Componente Spring responsável por tratar a lógica de logout de usuários no sistema.
 * <br>
 * Implementa a interface {@link Logic}.
 */
@Component(Consts.LOGOUT_LOGIC)
public class Logout implements Logic {

    /**
     * Método principal que processa a requisição de logout de um usuário.
     * 
     * @param request  HttpServletRequest usado para acessar a sessão do usuário.
     * @param response HttpServletResponse usado para redirecionamento.
     * @return String que representa a página de login após a execução.
     * 
     * @throws ServletException em caso de erro de servlet.
     * @throws IOException em caso de erro de I/O.
     */
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Recupera a sessão existente, sem criar uma nova.
        HttpSession session = request.getSession(false);

        // Se houver sessão, invalida-a para efetuar logout.
        if (session != null) session.invalidate();

        return Consts.REDIRECT_LOGIN_PAGE;
    }
}