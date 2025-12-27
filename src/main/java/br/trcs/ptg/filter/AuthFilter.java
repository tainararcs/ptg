package br.trcs.ptg.filter;

import java.io.IOException;

import br.trcs.ptg.utils.Consts;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Filtro responsável pelo controle de autenticação e autorização de acesso.
 * <br>Iintercepta todas as requisições da aplicação (mapeada para "/*") e verifica se o usuário possui uma sessão válida.
 * <ul>
 *   <li>Permite livre acesso ao login e à ação de autenticação.</li>
 *   <li>Permite acesso a recursos estáticos (CSS, JS, imagens, ícones, etc).</li>
 *   <li>Bloqueia qualquer tentativa de acesso sem usuário autenticado.</li>
 *   <li>Redireciona para a página de login com indicação de sessão expirada.</li>
 * </ul>
 */
@WebFilter("/*")
public class AuthFilter implements Filter {
    
	/**
     * Intercepta todas as requisições HTTP e decide se a requisição deve continuar ou ser bloqueada.
     *
     * @param req objeto {@link ServletRequest}.
     * @param res objeto {@link ServletResponse}.
     * @param chain cadeia de filtros.
     *
     * @throws IOException erro de I/O.
     * @throws ServletException erro de servlet.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        // Libera forwards internos e páginas de erro do container.
        DispatcherType dispatcherType = request.getDispatcherType();
        if (dispatcherType == DispatcherType.FORWARD || dispatcherType == DispatcherType.ERROR) {
            chain.doFilter(req, res);
            return;
        }

        // Verifica se o acesso deve ser permitido.
        if (shouldAllowAccess(request)) {
            chain.doFilter(req, res);
        } else {
            redirectToLogin(request, response);
        }
    }
    
    /**
    * Determina se a requisição atual pode prosseguir.
    * O acesso é permitido se:
    * - for um recurso público.
    * - o usuário estiver autenticado.
    *
    * @param request requisição HTTP.
    * @return true se o acesso for permitido, false caso contrário.
    */
    private boolean shouldAllowAccess(HttpServletRequest request) {
        return isPublicResource(request) || isUserLoggedIn(request);
    }
    
    /**
     * Verifica se é um recurso público (login, logout, auth) ou recurso estático (css, js e imagens).
     * 
     * @param request requisição HTTP.
     * @return true se for um recurso público.
     */
    private boolean isPublicResource(HttpServletRequest request) {
    	String uri = request.getRequestURI().substring(request.getContextPath().length());

    	// Libera página de login, autenticação, logout e todos os recursos estáticos.
        return uri.equals("/login")
            || uri.equals("/action/doLogin")
            || uri.equals("/doLogout")
            || uri.matches("^/(resources)/(css|js|img|fonts|images)/.*$"); 
    }
        
    /**
     * Verifica se existe um usuário autenticado na sessão.
     *
     * @param request requisição HTTP.
     * @return true se houver sessão válida com usuário logado.
     */
    private boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute(Consts.USER_LOGGED) != null;
    }
   
    /**
     * Trata acesso não autorizado.
     * Redireciona o usuário para a página de login, indicando que a sessão expirou ou não existe.
     *
     * @param request requisição HTTP.
     * @param response resposta HTTP.
     * @throws IOException erro de redirecionamento.
     */
    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/login?expired=true");
    }
}