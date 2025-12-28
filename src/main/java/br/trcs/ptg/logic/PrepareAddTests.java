package br.trcs.ptg.logic;

import java.io.IOException;

import org.springframework.stereotype.Component;

import br.trcs.ptg.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Componente Spring responsável por preparar a aplicação para iniciar o cadastro de um novo teste. Implementa a interface {@link Logic}.
 * <br>
 * Esta classe reinicia o ciclo de teste, removendo da sessão quaisquer atributos relacionados a testes anteriores.
 */
@Component(Consts.PREPARE_TESTS_LOGIC)
public class PrepareAddTests implements Logic {

    /**
     * Limpa todos os atributos de sessão relacionados a testes e redireciona para a página de cadastro de teste.
     * 
     * @param request  HttpServletRequest usado para processar a requisição.
     * @param response HttpServletResponse.
     * @return a página a ser redirecionada.
     * 
     * @throws ServletException em caso de erro de servlet.
     * @throws IOException em caso de erro de I/O.
     */
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Remove dados da sessão referentes a testes anteriores
        request.getSession().removeAttribute("selectedQuestions");
        request.getSession().removeAttribute("showResults");
        request.getSession().removeAttribute("userAnswers");
        request.getSession().removeAttribute("score");
        request.getSession().removeAttribute("subjectId");
        request.getSession().removeAttribute("topicId");
        request.getSession().removeAttribute("bimester");
        request.getSession().removeAttribute("grade");

        // Redireciona para a página de adição de teste.
        return Consts.REDIRECT_ADD_TEST_PAGE;
    }
}