/**
 * 
 */
package ec.com.smx.auth.ws.controller.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ec.com.smx.auth.ws.authentication.SecureUser;
import ec.com.smx.auth.ws.controller.pojo.User;

/**
 * @author acachiguango
 *
 */
public class SecurityController {
	/**
	 * Usuario en sesion
	 */
	private User sessionUser;

	/**
	 * Obtiene el usuario logeando
	 * 
	 * @return
	 */
	public User getSessionUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null
				&& !authentication.getPrincipal().equals("anonymousUser")) {
			SecureUser secureUser = (SecureUser) authentication.getPrincipal();
			return secureUser.getUser();
		}
		return sessionUser;
	}
}
