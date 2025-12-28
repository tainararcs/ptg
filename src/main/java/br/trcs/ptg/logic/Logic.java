package br.trcs.ptg.logic;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interface base que define o contrato para todas as classes de lógica de negócio utilizadas pelo controlador principal {@link ControllerServlet} da aplicação.
 * <br>
 * Cada classe que implementa {@code Logic} representa uma ação específica da aplicação, responsável por processar requisições HTTP e definir o fluxo de navegação.
 */
public interface Logic {

    /**
     * Executa a lógica principal associada a uma requisição HTTP específica.
     *
     * @param request  o {@link HttpServletRequest} contendo parâmetros e atributos enviados pelo cliente.
     * @param response o {@link HttpServletResponse} utilizado para enviar respostas, mensagens ou redirecionamentos.
     * @return uma {@code String} indicando o caminho da página (JSP) a ser exibida ou uma constante que define o fluxo de navegação da aplicação.
     * 
     * @throws ServletException se ocorrer algum erro interno relacionado ao servlet.
     * @throws IOException      se ocorrer erro de entrada ou saída durante o processamento.
     */
    String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}