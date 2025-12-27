package br.trcs.ptg.utils;

import java.io.IOException;

import br.trcs.ptg.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe utilitária responsável por funcionalidades relacionadas à segurança da aplicação, como:
 * - validação de permissões de acesso.
 * - criptografia (simples) de senhas.
 */
public class SecurityUtils {

    /**
     * Valida se o usuário logado possui permissão para acessar determinado recurso da aplicação.
     * Caso o usuário não esteja logado ou não possua o perfil permitido,
     * a requisição é encaminhada para a página de erro.
     *
     * @param request objeto {@link HttpServletRequest} da requisição atual.
     * @param response objeto {@link HttpServletResponse} da resposta atual.
     * @param profilePermited perfil necessário para acesso ao recurso.
     *
     * @throws ServletException caso ocorra erro ao encaminhar a requisição.
     * @throws IOException caso ocorra erro de I/O.
     */
    public static void validaPermission(HttpServletRequest request, HttpServletResponse response, String profilePermited) throws ServletException, IOException {

        // Recupera o usuário logado da sessão.
        User user = (User) request.getSession().getAttribute(Consts.USER_LOGGED);

        // Verifica se o usuário está logado e se possui o perfil exigido.
        if (user == null || !profilePermited.equalsIgnoreCase(user.getProfile())) 
            request.getRequestDispatcher("/error").forward(request, response);
    }

    /**
     * Criptografa (de forma simples) a senha informada. Utiliza o {@link String#hashCode()}.
     *
     * @param password senha original digitada pelo usuário.
     * @return hash da senha em formato {@link String}.
     */
    public static String encryptPassword(String password) {
        return String.valueOf(password.hashCode());
    }
}