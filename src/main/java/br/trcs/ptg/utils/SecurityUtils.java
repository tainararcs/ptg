package br.trcs.ptg.utils;

import java.io.IOException;

import br.trcs.ptg.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecurityUtils {
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param profilePermited
	 * 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public static void validaPermission(HttpServletRequest request, HttpServletResponse response, String profilePermited) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute(Consts.USER_LOGGED);
		
		if(user == null || !profilePermited.equalsIgnoreCase(user.getProfile())) {
            request.getRequestDispatcher("/error").forward(request, response);
            return;
		}
	}
	
	/**
     * Criptografa (de forma simples) a senha informada. Utiliza o {@link String#hashCode()}.
     * 
     * @param password senha original digitada pelo usuário.
     * @return valor hash da senha.
     */
	public static String encryptPassword(String password) {
		return String.valueOf(password.hashCode());
	}
}