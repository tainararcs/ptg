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

/**
 * Componente Spring responsável por listar todos os usuários do sistema.
 * Implementa a interface {@link Logic}. Apenas administradores podem acessar.
 */
@Component(Consts.LIST_USER_LOGIC)
public class ListUsers implements Logic {

	/**
     * Método principal que processa a requisição para listar usuários.
     * 
     * @param request  HttpServletRequest usado para acessar a sessão do usuário.
     * @param response HttpServletResponse usado para enviar a resposta JSON.
     * @return null, pois a resposta JSON é escrita diretamente no {@link HttpServletResponse.
     * 
     * @throws ServletException em caso de erro de servlet.
     * @throws IOException em caso de erro de I/O.
     */
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Valida permissão de administrador.
        SecurityUtils.validaPermission(request, response, "admin");

        var usersList = new DAO<>(User.class).list();

        // Configura resposta JSON.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Serializa e envia a lista.
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(usersList));

        return null;
    }
}