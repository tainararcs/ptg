package br.trcs.ptg.logic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.trcs.ptg.dao.TestDAO;
import br.trcs.ptg.model.Test;
import br.trcs.ptg.model.User;
import br.trcs.ptg.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Componente Spring responsável por listar todos os testes (Tests) no sistema.
 * Implementa a interface {@link Logic}.
 */
@Component(Consts.LIST_TEST_LOGIC)
public class ListTests implements Logic {

    /**
     * Método principal que processa a requisição para listar testes.
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

    	// Recupera usuário logado.
        User userLogged = (User) request.getSession().getAttribute(Consts.USER_LOGGED);

        // Se não houver usuário logado, retorna 401.
        if (userLogged == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

        TestDAO dao = new TestDAO();
        List<Test> testsList;

        // Diferencia admin de usuário comum.
        if ("admin".equalsIgnoreCase(userLogged.getProfile())) 
            testsList = dao.searchAllTests();
        else 
            testsList = dao.searchUserTests(userLogged);

        // Mapeia dados relevantes do teste para JSON.
        List<Map<String, Object>> result = testsList.stream().map(t -> {
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("date", t.getDate() != null ? t.getDate().toString() : "");
            map.put("user", t.getUserID().getName());
            map.put("subject", t.getSubjectId().getName());
            map.put("bimester", t.getBimester());
            map.put("numberCorrectAnswers", t.getNumberCorrectAnswers());
            return map;
        }).toList();

        // Envia a resposta JSON.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(result));
        
        return null;
    }
}