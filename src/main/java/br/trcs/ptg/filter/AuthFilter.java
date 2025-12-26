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
 * <br>Iintercepta todas as requisições da aplicação (mapeada para "/*") e valida se o administrador está autenticado antes de permitir o acesso. 
 * <ul>
 *   <li>Permite livre acesso ao login.</li>
 *   <li>Permite acesso a recursos estáticos (CSS, JS, imagens, ícones, etc).</li>
 *   <li>Bloqueia qualquer tentativa de acesso sem sessão válida de administrador.</li>
 *   <li>Redireciona para <b>login.jsp?expired=true</b> quando a sessão expira ou o usuário não está autenticado.</li>
 * </ul>
 */
@WebFilter("/*")
public class AuthFilter implements Filter {
    
	/**
     * Método principal do filtro. Intercepta todas as requisições HTTP.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        // Libera forwards e páginas de erro internos do container
        DispatcherType dispatcherType = request.getDispatcherType();
        if (dispatcherType == DispatcherType.FORWARD || dispatcherType == DispatcherType.ERROR) {
            chain.doFilter(req, res);
            return;
        }

        if (shouldAllowAccess(request)) {
            chain.doFilter(req, res);
        } else {
            redirectToLogin(request, response);
        }
    }
    
    /**
     * Verifica se a requisição deve ter acesso permitido.
     */
    private boolean shouldAllowAccess(HttpServletRequest request) {
        return isPublicResource(request) || isUserLoggedIn(request);
    }
    
    /**
     * Verifica se é um recurso público (login, logout, auth) ou recurso estático (css, js e imagens).
     */    
    private boolean isPublicResource(HttpServletRequest request) {
    	String uri = request.getRequestURI().substring(request.getContextPath().length());

    	// libera login, logout, auth e todos os recursos estáticos
        return uri.equals("/login")
            || uri.equals("/action/doLogin")
            || uri.equals("/doLogout")
            || uri.matches("^/(resources)/(css|js|img|fonts|images)/.*$"); 
    }
        
    /**
     * Verifica se usuário está logado.
     */
    private boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute(Consts.USER_LOGGED) != null;
    }
    
    /**
     * Trata acesso negado: redireciona para login.
     */    
    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/login?expired=true");
    }

}