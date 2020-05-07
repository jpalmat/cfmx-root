/**
 * 
 */
package ec.com.smx.auth.ws.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author acachiguango
 *
 */
public class AuthenticationToken extends UsernamePasswordAuthenticationToken {
	private String token;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationToken(Object principal, Object credentials, String token) {
		super(principal, credentials);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
