package ec.com.smx.auth.ws.authentication;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ec.com.smx.auth.client.log.CfmxLogger;
import ec.com.smx.auth.ws.controller.pojo.User;
import ec.com.smx.auth.ws.exception.WSException;
import ec.com.smx.auth.ws.util.AuthUtil;
import ec.com.smx.corpv2.ws.vo.UserPaswordRolTypeResVO;
import ec.com.smx.corpv2.ws.vo.VistaPerfil;
import ec.com.smx.framework.exception.FrameworkException;

/**
 * 
 * @author acachiguango
 *
 */
@Component
public class AuthLoginService implements UserDetailsService {

	protected GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
	protected List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(1);

	/**
	 * TeamSecurityLoginService
	 */
	public AuthLoginService() {
		authorities.add(authority);
	}

	/**
	 * Login
	 * ec.com.smx.external.ws.passwordstatus.vigente = 1
		ec.com.smx.external.ws.passwordstatus.porcaducar = 2
		ec.com.smx.external.ws.passwordstatus.caducado = 3
	 */
	public UserDetails userLogin(String userName, String password)
			throws UsernameNotFoundException {
		try {
			UserPaswordRolTypeResVO userDto = AuthUtil.loginUser(userName, password);
			if (userDto == null) {
				throw new BadCredentialsException("Datos incorrectos.");
			}
			User user = new User();
			if (StringUtils.isNotBlank(userDto.getUserPasswordStatus())) {
				String status = userDto.getUserPasswordStatus();
				switch (status) {
				case "3":
					//throw new BadCredentialsException("Contrasena caducada.");
					user.setChangePwd(true);
					break;
				case "4":
					throw new BadCredentialsException("Usuario inactivo.");
				default:
					break;
				}
			}
			// PRE ProfileId = 399
			try {
				PropertyUtils.copyProperties(user, userDto);
			} catch (IllegalAccessException e) {
				CfmxLogger.LOG.error("Error en copyProperties, userLogin()");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				CfmxLogger.LOG.error("Error en copyProperties, userLogin()");
			} catch (NoSuchMethodException e) {
				CfmxLogger.LOG.error("Error en copyProperties, userLogin()");
			}
			Collection<VistaPerfil> listaPerfiles = AuthUtil.getProfile(user.getUserId()); 
			VistaPerfil perfilDefault = getDefaultProfile(listaPerfiles);
			// valida si tiene un prefil por defecto
			if (perfilDefault == null) {
				CfmxLogger.LOG.error("El usuario {} no tiene un perfil asignado por defecto", userName);
				throw new BadCredentialsException("El usuario no tiene un perfil asignado por defecto.");
			}
			user.setProfileId(perfilDefault.getCodigoPerfil()); 
			user.setPassword(password);
			user.setCompanyId(1);
			user.setUsuarioFuncionario(AuthUtil.getCodigoFuncionario(user.getUserId()));
			return loadSecureUser(user);
		} catch (FrameworkException | WSException e) {
			CfmxLogger.LOG.error("Error en userLogin. {}", e.getMessage());
			throw new BadCredentialsException("Datos incorrectos.");
		} 
	}

	/**
	 * Obtiene el perfil por default de todos los perfiles de un usuario
	 * @param listaPerfiles
	 * @return
	 */
	private VistaPerfil getDefaultProfile(Collection<VistaPerfil> listaPerfiles) {
		return (VistaPerfil) CollectionUtils.find(listaPerfiles, new Predicate() {
			/**
			 * evaluate
			 */
			@Override
			public boolean evaluate(Object object) {
				return ((VistaPerfil) object).getEsPerfilPorDefecto() == true;
			}
		});
	}

	/**
	 * 
	 * @param userName
	 * @param loginViewDTO
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private SecureUser loadSecureUser(User user) {
		Boolean enabled = Boolean.TRUE;
		Boolean accountNonExpired = Boolean.TRUE;
		Boolean credentialsNonExpired = Boolean.TRUE;
		Boolean accountNonLocked = Boolean.TRUE;
		return new SecureUser(user.getUserName(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities, user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
