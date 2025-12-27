package br.trcs.ptg.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.trcs.ptg.logic.Logic;
import br.trcs.ptg.utils.Consts;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller central da aplicação, responsável por:
 * - Resolver requisições de páginas JSP diretamente.
 * - Resolver requisições de ações (actions) delegando a lógica para implementações da interface {@link Logic}.
 * Atua como um Front Controller, centralizando o fluxo de navegação e controle da aplicação.
 */
@Controller
@RequestMapping("/")
public class ControllerServlet {
    
	/**
     * Mapa de lógicas de negócio.
     * A chave representa o nome da action (string presente na URL),
     * e o valor é a implementação correspondente da interface {@link Logic}.
     * Esse mapa é injetado automaticamente pelo Spring, associando os beans do tipo Logic pelo nome.
     */
    private final Map<String, Logic> logics;

    /**
     * Construtor com injeção de dependência.
     *
     * @param logicMap mapa contendo todas as implementações de Logic disponíveis no contexto do Spring.
     */
    @Autowired
    public ControllerServlet(Map<String, Logic> logicMap) {
        this.logics = logicMap;
    }

    /**
     * Manipula requisições diretas para páginas JSP.
     * Caso a página não exista, redireciona para a página de erro.
     *
     * @param request requisição HTTP.
     * @param response resposta HTTP.
     * @param page nome da página solicitada na URL.
     * 
     * @throws ServletException em caso de erro no forward.
     * @throws IOException em caso de erro de I/O.
     */
    @RequestMapping("/{page}")
    public void handlePage(HttpServletRequest request, HttpServletResponse response, @PathVariable("page") String page) throws ServletException, IOException {
        if (!page.endsWith(".jsp")) page += ".jsp";
        
        if (request.getServletContext().getResource(Consts.VIEWS_PATH + page) == null) {
            // JSP não existe: redireciona para error.jsp.
            request.getRequestDispatcher("/error").forward(request, response);
            return;
        }
        
        request.getRequestDispatcher(Consts.VIEWS_PATH + page).forward(request, response);
    }
    
    /**
     * Processa redirecionamentos definidos pelas lógicas de negócio.
     * O formato esperado é:
     * - redirect:action:nomeDaAction
     * - redirect:nomeDaPagina
     *
     * @param request requisição HTTP.
     * @param response resposta HTTP.
     * @param redirectValue valor retornado pela lógica iniciando com "redirect:".
     * 
     * @throws IOException em caso de erro no sendRedirect.
     */
    private void handleRedirect(HttpServletRequest request,  HttpServletResponse response, String redirectValue ) throws IOException {
    	// Remove o prefixo "redirect:".
    	String target = redirectValue.substring("redirect:".length());

    	// Redirect para uma action.
        if (target.startsWith("action:")) {
            String action = target.substring("action:".length());
            response.sendRedirect(request.getContextPath() + "/action/" + action);
            return;
        }

        // Redirect direto para uma página.
        response.sendRedirect(request.getContextPath() + "/" + target);
    }
    
    /**
     * Manipula requisições de ações (actions).
     *
     * Exemplo de URL:
     *   /action/login
     *   /action/logout
     *
     * O método localiza a lógica correspondente no mapa, executa o método {@code service} e decide o próximo passo:
     * - forward para JSP
     * - redirect para página
     * - redirect para outra action
     *
     * @param request requisição HTTP.
     * @param response resposta HTTP.
     * @param action nome da action extraído da URL.
     * 
     * @throws ServletException em caso de erro no forward.
     * @throws IOException em caso de erro de I/O.
     */
    @RequestMapping("/action/{action}")
    public void handle(HttpServletRequest request, HttpServletResponse response, @PathVariable("action") String action) throws ServletException, IOException {
    	// Recupera a lógica associada à action.
    	Logic logic = logics.get(action);

        if (logic == null) {
            request.getRequestDispatcher("/error").forward(request, response);
            return;
        }

        // Executa a lógica de negócio.
        String nextPage = logic.service(request, response);

        if (response.isCommitted() || nextPage == null)  return;
        
        // Decide entre redirect ou forward.
        if (nextPage.startsWith("redirect:")) 
            handleRedirect(request, response, nextPage);
        else 
            handlePage(request, response, nextPage);
    }
}