package br.trcs.ptg.logic;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.trcs.ptg.dao.DAO;
import br.trcs.ptg.model.Topic;
import br.trcs.ptg.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Componente Spring responsável por listar todos os tópicos (Topics) de uma disciplina específica.
 * Implementa a interface {@link Logic.
 */
@Component(Consts.LIST_TOPIC_LOGIC)
public class ListTopic implements Logic {

    /**
     * Método principal que processa a requisição para listar tópicos de uma disciplina.
     * 
     * @param request  HttpServletRequest contendo o parâmetro "subjectId".
     * @param response HttpServletResponse usado para enviar a resposta JSON.
     * @return null, pois a resposta JSON é escrita diretamente no {@link HttpServletResponse}.
     * 
     * @throws ServletException em caso de erro de servlet.
     * @throws IOException em caso de erro de I/O.
     */
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String subjectParam = request.getParameter("subjectId");

        // Valida o parâmetro, se inválido, retorna lista vazia.
        if (subjectParam == null || !subjectParam.matches("\\d+")) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("[]");
            return null;
        }

        Integer subjectId = Integer.valueOf(subjectParam);

        DAO<Topic> dao = new DAO<>(Topic.class);

        // Consulta tópicos da disciplina especificada.
        var topics = dao.findByQuery(
            "SELECT t FROM Topic t WHERE t.subjectId.id = :id",
            Map.of("id", subjectId)
        );

        // Envia resposta JSON.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(topics));

        return null;
    }
}