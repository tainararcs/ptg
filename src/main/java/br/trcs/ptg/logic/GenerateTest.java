package br.trcs.ptg.logic;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.trcs.ptg.dao.QuestionDAO;
import br.trcs.ptg.enums.Bimester;
import br.trcs.ptg.model.Question;
import br.trcs.ptg.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Componente Spring responsável por gerar um teste selecionando questões específicas de acordo com disciplina, tópico e bimestre.
 * Implementa a interface {@link Logic}.
 */
@Component(Consts.GENERATE_TEST_LOGIC)
public class GenerateTest implements Logic {

    /**
     * Gera um teste aleatório com número fixo de questões, selecionadas a partir do banco de dados.
     * As questões são embaralhadas e armazenadas na sessão para correção futura.
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

        HttpSession session = request.getSession();

        Integer subjectId = Integer.valueOf(request.getParameter("subjectId"));
        Integer topicId = Integer.valueOf(request.getParameter("topicId"));
        Bimester bimester = Bimester.valueOf(request.getParameter("bimester"));

        // Consulta questões específicas.
        QuestionDAO dao = new QuestionDAO();
        List<Question> questions = dao.searchSpecifiQuestions(subjectId, topicId, bimester);

        // Verifica se há questões suficientes.
        if (questions.size() < Consts.NUMBER_QUESTIONS) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        // Seleção aleatória de questões.
        Collections.shuffle(questions);
        List<Question> selectedQuestions = questions.subList(0, Consts.NUMBER_QUESTIONS);

        // Armazena questões selecionadas na sessão para correção posterior.
        session.setAttribute("selectedQuestions", selectedQuestions);
        session.setAttribute("subjectId", subjectId);
        session.setAttribute("bimester", bimester);

        // Monta lista de questões e opções para envio ao front-end.
        List<Map<String, Object>> result = selectedQuestions.stream().map(q -> Map.of(
                "id", q.getId(),
                "statement", q.getStatement(),
                "options", q.getOptionsList().stream().map(o -> Map.of(
                        "id", o.getId(),
                        "text", o.getText()
                )).toList()
        )).toList();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        new ObjectMapper().writeValue(response.getWriter(), result);

        return null;
    }
}