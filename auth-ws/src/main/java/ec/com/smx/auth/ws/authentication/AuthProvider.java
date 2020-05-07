package ec.com.smx.auth.ws.authentication;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 
 * @author acachiguango
 *
 */
@Component
public class AuthProvider implements AuthenticationProvider {

	@Autowired
	private AuthLoginService auhtLoginService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
			String userName = authentication.getName();
			String password = authentication.getCredentials().toString();
			
			if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
				throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
			}
			SecureUser user = (SecureUser) auhtLoginService.userLogin(userName, password);
			if (user == null) {
				throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
			}
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		} 
		SecureUser secureUser = (SecureUser) auth.getPrincipal();
		return new UsernamePasswordAuthenticationToken(secureUser, null, secureUser.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}
	/*
	 * public boolean supports(Class<?> authentication) { return
	 * UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication
	 * ); }
	 */

}
