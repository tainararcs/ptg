package br.trcs.ptg.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.trcs.ptg.logic.Logic;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class ControllerServlet {
    
    private final Map<String, Logic> logics;
    
    @Autowired
    public ControllerServlet(Map<String, Logic> logicMap) {
        this.logics = logicMap;
    }

    @RequestMapping("/{page}")
    public void handlePage(HttpServletRequest request, HttpServletResponse response, @PathVariable("page") String page) throws ServletException, IOException {
        if (!page.endsWith(".jsp")) page += ".jsp";
        
        if (request.getServletContext().getResource("/WEB-INF/views/" + page) == null) {
            // JSP não existe: redireciona para error.jsp.
            request.getRequestDispatcher("/error").forward(request, response);
            return;
        }
        
        request.getRequestDispatcher("/WEB-INF/views/" + page).forward(request, response);
    }
    
    private void handleRedirect(HttpServletRequest request,  HttpServletResponse response, String redirectValue ) throws IOException {
        String target = redirectValue.substring("redirect:".length());

        // redirect para ação.
        if (target.startsWith("action:")) {
            String action = target.substring("action:".length());
            response.sendRedirect(request.getContextPath() + "/action/" + action);
            return;
        }

        // redirect para página
        response.sendRedirect(request.getContextPath() + "/" + target);
    }
    
    @RequestMapping("/action/{action}")
    public void handle(HttpServletRequest request, HttpServletResponse response, @PathVariable("action") String action) throws ServletException, IOException {
        Logic logic = logics.get(action);

        if (logic == null) {
            request.getRequestDispatcher("/error").forward(request, response);
            return;
        }

        String nextPage = logic.service(request, response);

        if (response.isCommitted() || nextPage == null)  return;
        
        if (nextPage.startsWith("redirect:")) 
            handleRedirect(request, response, nextPage);
        else 
            handlePage(request, response, nextPage);
    }
}