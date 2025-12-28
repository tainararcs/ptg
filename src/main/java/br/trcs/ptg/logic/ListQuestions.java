package br.trcs.ptg.logic;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.trcs.ptg.dao.DAO;
import br.trcs.ptg.model.Question;
import br.trcs.ptg.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Componente Spring responsável por listar todas as questões do sistema.
 * Implementa a interface {@link Logic}.
 */
@Component(Consts.LIST_QUESTION_LOGIC)
public class ListQuestions implements Logic {

    /**
     * Método principal que processa a requisição para listar todas as questões.
     * 
     * @param request  HttpServletRequest usado para processar a requisição.
     * @param response HttpServletResponse usado para enviar a resposta JSON.
     * @return null, pois a resposta JSON é escrita diretamente no {@link HttpServletResponse}.
     * 
     * @throws ServletException em caso de erro de servlet.
     * @throws IOException em caso de erro de I/O.
     */
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var questionsList = new DAO<>(Question.class).list();

        // Configura a resposta HTTP como JSON.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Converte a lista para JSON e escreve no response.
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(questionsList));

        return null; // A resposta JSON é enviada diretamente.
    }
}