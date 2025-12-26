package br.trcs.ptg.logic;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interface base que define o contrato para todas as classes de lógica de negócio utilizadas pelo controlador principal ({@link ControllerServlet}) da aplicação.
 * <br>Cada classe que implementa {@code Logic} representa uma ação específica e retorna uma constante definida em {@link br.trcs.petshop.utils.Consts} que indica 
 * o próximo destino após a execução da ação.
 */
public interface Logic {

    /**
     * Executa a lógica principal associada a uma determinada requisição HTTP.
     * 
     * @param request  o objeto {@link HttpServletRequest} contendo os parâmetros e atributos da requisição feita pelo cliente.
     * @param response o objeto {@link HttpServletResponse} utilizado para enviar respostas ou redirecionamentos ao cliente.
     * @return uma {@code String} com o caminho da página (JSP) ou uma constante que indica para onde o fluxo da aplicação deve seguir.
     * 
     * @throws ServletException se ocorrer algum erro interno de servlet.
     * @throws IOException se ocorrer erro de entrada ou saída durante o processamento.
     */
	 String service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}