/**
 * 
 */
package ec.com.smx.auth.ws.controller.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ec.com.smx.auth.client.commons.CFMXMessage;
import ec.com.smx.auth.client.log.CfmxLogger;
import ec.com.smx.auth.ws.authentication.AuthManager;
import ec.com.smx.auth.ws.authentication.SecureUser;
import ec.com.smx.auth.ws.controller.IAuthController;
import ec.com.smx.auth.ws.controller.pojo.PasswordReq;
import ec.com.smx.auth.ws.controller.pojo.QuestionReq;
import ec.com.smx.auth.ws.controller.pojo.Response;
import ec.com.smx.auth.ws.controller.pojo.TokenReq;
import ec.com.smx.auth.ws.controller.pojo.TokenRes;
import ec.com.smx.auth.ws.controller.pojo.User;
import ec.com.smx.auth.ws.enums.HeaderType;
import ec.com.smx.auth.ws.exception.WSException;
import ec.com.smx.auth.ws.util.AuthUtil;
import ec.com.smx.corpv2.ws.vo.PreguntaSecreta;
import ec.com.smx.corpv2.ws.vo.UserFunctionariesGenericReqVO;
import ec.com.smx.corpv2.ws.vo.UserReqVO;
import ec.com.smx.corpv2.ws.vo.Usuario;
import ec.com.smx.mensajeria.vo.EventVO;

/**
 * @author acachiguango
 *
 */
@RequestMapping(value = "/auth")
@Controller
public class AuthControllerImpl implements IAuthController {

	@Autowired
	private AuthManager authManager;

	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = HeaderType.ACCCEPT_APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<User> login(@RequestBody User user, HttpServletRequest request) throws WSException {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (StringUtils.isBlank((user.getUserName())) || StringUtils.isBlank((user.getPassword()))) {
				return new ResponseEntity<User>(new User(), HttpStatus.UNAUTHORIZED);
			}
			authentication = authManager.login(user.getUserName(), user.getPassword(), request);
			if (authentication == null) {
				return new ResponseEntity<User>(new User(), HttpStatus.UNAUTHORIZED);
			}
			SecureUser secureUser = (SecureUser) authentication.getPrincipal();
			CfmxLogger.LOG.info("Usuario en sesion: {}", secureUser.getUser().getUserName());
			
			// consultar local por defecto
			User userResult = secureUser.getUser();
			Integer localId = AuthUtil.getLocalDefault(userResult.getUserId());
			userResult.setDefaultLocalId(localId);
			
			//registrar dispositivo en el corporativo
//			try {
//				Boolean ok = DeviceUtil.registerDevice(user.getUserId(), null, null, null, null, null, null, null, null);
//				CfmxLogger.LOG.info(ok ? "Dispositivo registrado del user " + user.getUserId() : "Dispositivo no registrado del user " + userResult.getUserId().concat(userResult.getUserName()));
//			} catch (WSException e) {
//				CfmxLogger.LOG.error("Error en registrar dispositivo, {}", e.getMessage());
//				return new ResponseEntity<User>(new User(e.getMessage(), Boolean.FALSE), HttpStatus.UNAUTHORIZED);
//			}
			return new ResponseEntity<User>(userResult, HttpStatus.OK);
		} catch (BadCredentialsException ex) {
			CfmxLogger.LOG.error("Error en login, {}", ex.getMessage());
			return new ResponseEntity<User>(new User(ex.getMessage(), Boolean.FALSE), HttpStatus.UNAUTHORIZED);
		} catch (UsernameNotFoundException ex) {
			CfmxLogger.LOG.error("Error en login, {}", ex.getMessage());
			return new ResponseEntity<User>(new User(ex.getMessage(), Boolean.FALSE), HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> logout(HttpServletRequest request) throws WSException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
		CfmxLogger.LOG.info("Sesion cerrada correctamente.");
		return new ResponseEntity<Response>(new Response(200, "Sesion cerrada correctamente."), HttpStatus.OK);
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, headers = HeaderType.ACCCEPT_APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<User> forgotPassword(@RequestBody UserFunctionariesGenericReqVO params)
			throws WSException {
		// valida si existe el usuario ingresado
		try {
			String userId = AuthUtil.getFuncionaryByName(params.getUserName());
			if (StringUtils.isNotBlank(userId)) {
				Usuario user = AuthUtil.getSecretQuestions(userId);
				User finalUser = new User();
				PropertyUtils.copyProperties(finalUser, user);
				return new ResponseEntity<User>(finalUser, HttpStatus.OK);
			}
			CfmxLogger.LOG.info("No existe el usuario ingresado, {}", params.getUserName());
			return new ResponseEntity<User>(new User("No existe el usuario ingresado", Boolean.FALSE), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			CfmxLogger.LOG.error("No existe el usuario ingresado, {}", params.getUserName());
			return new ResponseEntity<User>(new User("No existe el usuario ingresado", Boolean.FALSE), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/validateUserAnswer", method = RequestMethod.POST, headers = HeaderType.ACCCEPT_APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<Response> validateUserAnswer(@RequestBody QuestionReq params)
			throws WSException {
		
		// consultar las preguntas del usuario
		Usuario user = AuthUtil.getSecretQuestions(params.getUserId());
		if (user == null){
			CfmxLogger.LOG.error("No se encuentra en usuario, {}", params.getUserId());
			return new ResponseEntity<Response>(new Response(
					"No existe el usuario ingresado.",
					Boolean.FALSE), HttpStatus.BAD_REQUEST);
		}
		PreguntaSecreta question = (PreguntaSecreta) CollectionUtils.find(user.getColPregSecretas(), 
				new BeanPredicate("questionId", PredicateUtils.equalPredicate(params.getQuestionId())));
		// valida si la respuesta es correcta
		if (params.getAnswer().equals(question.getAnswer())) {
			// se genera una clave
			String tempPassword = this.generatePassword();
			// guarda la clave temporal
			Boolean savePwd = this.savePassword(user.getUserId(), tempPassword);
			// valida el envio de la clave temporal al correo
			if(savePwd) {
				String email = null;
				try {
					email = user.getEmailEmpresarial() != null ? user.getEmailEmpresarial() : user.getEmailPersonal();
					this.sendPasswordMail(email, tempPassword);
				} catch (Exception e) {
					CfmxLogger.LOG.error("Se produjo un error al enviar el correo, {}", e.getMessage());
					return new ResponseEntity<Response>(new Response(
							"Se produjo un error al enviar el correo, por favor intente m\u00E1s tarde.",
							Boolean.FALSE), HttpStatus.BAD_REQUEST);
				}
				CfmxLogger.LOG.info("Se ha enviado clave temporal al correo: {}", email);
				return new ResponseEntity<Response>(new Response("Se ha enviado una contrase\u00F1a temporal a su correo.",
						Boolean.TRUE), HttpStatus.OK);
			} else {
				CfmxLogger.LOG.error("No se actualiz\u00F3 el registro del usuario {}", params.getUserId());
				return new ResponseEntity<Response>(new Response("No se actualiz\u00F3 el registro del usuario.",
						Boolean.FALSE), HttpStatus.BAD_REQUEST);
			}
		} else {
			CfmxLogger.LOG.error("Respuesta incorrecta. Usuario: {}, preguntaId: {} ", 
					params.getUserId(), params.getQuestionId());
			return new ResponseEntity<Response>(new Response("Respuesta incorrecta.", Boolean.FALSE),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, headers = HeaderType.ACCCEPT_APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<Response> changePassword(@RequestBody PasswordReq params)
			throws WSException {
		try {
			Boolean success = AuthUtil.changePassword(params.getUserId(), params.getOldPassword(), params.getNewPassword());
			CfmxLogger.LOG.info("Se ha cambiado la contrase\\u00F1a correctamente, usuario: {}", params.getUserId());
			return new ResponseEntity<Response>(new Response("Se ha cambiado la contrase\u00F1a correctamente.", success),
					HttpStatus.OK);
		} catch (Exception e) {
			CfmxLogger.LOG.error("Se produjo un error al cambiar la contrase\u00F1a, {}", e.getMessage());
			return new ResponseEntity<Response>(new Response("Se produjo un error al cambiar la contrase\u00F1a.",
					Boolean.FALSE), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Servicio que valida si el token se encuentra activo 
	 * @return
	 * @throws WSException
	 */
	@RequestMapping(value = "/checkToken", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> checkToken() throws WSException {
		String result = "{}";
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	/**
	 * Guarda el password temporal
	 * 
	 * @param userId
	 * @param tempPassword
	 * @throws EPMException
	 */
	private Boolean savePassword(String userId, String tempPassword) throws WSException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar now = Calendar.getInstance();
		now.add(Calendar.YEAR, -1);
		UserReqVO user = new UserReqVO();
		user.setUserId(userId);
		user.setUserPassword(tempPassword);
		user.setUserPasswordCaducityDate(sdf.format(now.getTime()));
		return AuthUtil.updateUserPwd(user);
	}
	
	@RequestMapping(value = "/getCorpToken", method = RequestMethod.POST, headers = HeaderType.ACCCEPT_APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<TokenRes> getCorpTokenWs(@RequestBody TokenReq params)
			throws WSException {
		try {
			CfmxLogger.LOG.info("request getCorpToken , companyId {}, usuario {}", params.getCompanyId(), params.getUserId());
			params.setSystemId(CFMXMessage.getSystemId());
			CfmxLogger.LOG.info("modificado getCorpToken , companyId {}, usuario {}, systemId {}", params.getCompanyId(), params.getUserId(), params.getSystemId());
			TokenRes token = AuthUtil.getCorpToken(params);
			return new ResponseEntity<TokenRes>(token, HttpStatus.OK);
		}
		catch (WSException e) {
			CfmxLogger.LOG.error("error getCorpToken. Usuario: {}, error: {} ", 
					params.getUserId(), e);
			return new ResponseEntity<TokenRes>(new TokenRes("No existe el usuario ingresado", Boolean.FALSE), HttpStatus.BAD_REQUEST);
		}
	}
	/**
	 * Metodo para envio de mail con la contrasena temporal
	 * 
	 * @throws EPMException
	 */
	private void sendPasswordMail(String mail, String password) throws WSException {
		// obtener el evento del sistema
		EventVO eventVo = AuthUtil.getEventForEmail();
		// envia el correo correspondiente
		AuthUtil.sendEmail(mail, password, eventVo);
	}
	
	/**
	 * Genera una clave. Se agrega al final una letra mayuscula generada
	 * 
	 * @return
	 */
	private String generatePassword() {
		String ret = UUID.randomUUID().toString().substring(0, 8);
		char upper = (char) Math.floor(Math.random()*(26)+65);
		return ret + upper; 
	}


	/**
	 * @return the authManager
	 */
	public AuthManager getAuthManager() {
		return authManager;
	}

	/**
	 * @param authManager
	 *            the authManager to set
	 */
	public void setAuthManager(AuthManager authManager) {
		this.authManager = authManager;
	}
}
