package ec.com.smx.auth.ws.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import ec.com.smx.auth.ws.controller.pojo.User;

/**
 * 
 * @author acachiguango
 *
 */
public class AuthManager {

	@Autowired
	private AuthenticationManager authManager;

	/**
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	public Authentication login(String username, String password, HttpServletRequest request) {

		UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(username, password);
		// AuhtLoginService -->>
		Authentication authentication = this.authManager.authenticate(userToken);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		userToken.setDetails(new WebAuthenticationDetails(request));
		if (authentication.isAuthenticated()) {
			HttpSession session = request.getSession(Boolean.TRUE);
			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
			SecureUser userSecurity = (SecureUser) authentication.getPrincipal();
			User user = userSecurity.getUser();
			user.setPassword(null);
		}
		return authentication;
	}

	/**
	 * constructor
	 */
	public AuthManager() {
	}
}
