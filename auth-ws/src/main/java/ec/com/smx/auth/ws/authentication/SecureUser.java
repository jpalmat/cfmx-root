package ec.com.smx.auth.ws.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import ec.com.smx.auth.ws.controller.pojo.User;

/**
 * 
 * @author acachiguango
 *
 */
@SuppressWarnings("serial")
public class SecureUser extends org.springframework.security.core.userdetails.User {

	private User user;


	public SecureUser(String username, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, User user) {
		super(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
