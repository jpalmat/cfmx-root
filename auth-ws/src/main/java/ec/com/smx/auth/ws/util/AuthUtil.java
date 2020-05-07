package ec.com.smx.auth.ws.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import ec.com.smx.auth.client.commons.CFMXMessage;
import ec.com.smx.auth.client.log.CfmxLogger;
import ec.com.smx.auth.ws.controller.pojo.PasswordReq;
import ec.com.smx.auth.ws.controller.pojo.TokenReq;
import ec.com.smx.auth.ws.controller.pojo.TokenRes;
import ec.com.smx.auth.ws.exception.WSException;
import ec.com.smx.corpv2.ws.vo.Funcionario;
import ec.com.smx.corpv2.ws.vo.FunctionaryResVO;
import ec.com.smx.corpv2.ws.vo.UserFunctionariesGenericReqVO;
import ec.com.smx.corpv2.ws.vo.UserPaswordRolTypeReqVO;
import ec.com.smx.corpv2.ws.vo.UserPaswordRolTypeResVO;
import ec.com.smx.corpv2.ws.vo.UserReqVO;
import ec.com.smx.corpv2.ws.vo.Usuario;
import ec.com.smx.corpv2.ws.vo.VistaPerfil;
import ec.com.smx.mensajeria.commons.resources.MensajeriaMessages;
import ec.com.smx.mensajeria.vo.EventIdVO;
import ec.com.smx.mensajeria.vo.EventVO;
import ec.com.smx.mensajeria.vo.MailMessageStructureVO;
import ec.com.smx.mensajeria.web.service.util.MessageWebServices;

public class AuthUtil {
	
	/**
	 * Obtiene codigo funcionario
	 * 
	 * @param userId
	 * @return
	 * @throws WSException
	 */
	public static String getCodigoFuncionario(String userId) throws WSException {
			//armar json
			String json = getJsonFuncionario(userId);
			
			//configurar postMethod
			StringRequestEntity requestEntity = null;
			try {
				requestEntity = new StringRequestEntity(json,"application/json","UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new WSException(e);
			}
			PostMethod postMethod = new PostMethod(CFMXMessage.getFuncionaryByUser());
			postMethod.setRequestEntity(requestEntity);
			
			//ejecutar web service
			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			try {
				httpClient.executeMethod(postMethod);
			} catch (HttpException e) {
				throw new WSException(e);
			} catch (IOException e) {
				throw new WSException(e);
			}
			
			// capturar el result
			String result = null;
			try {
				result = postMethod.getResponseBodyAsString();
			} catch (IOException e) {
				throw new WSException(e);
			}
			if(StringUtils.isNotBlank(result)){
			  FunctionaryResVO response = new FunctionaryResVO();
			  //transforma el json en una coleccion de objetos
	    	  Gson googleJson = new Gson();
	    	  response = googleJson.fromJson(result, FunctionaryResVO.class);
	    	  return response.getCodigoFuncionario();
			}
			
		return null;
	}
	
	@SuppressWarnings("serial")
	public static Collection<VistaPerfil> getProfile(String userId) throws WSException {
		//armar json
		String json = getJsonFuncionario(userId);
		
		//configurar postMethod
		StringRequestEntity requestEntity = null;
		try {
			requestEntity = new StringRequestEntity(json,"application/json","UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new WSException(e);
		}
		PostMethod postMethod = new PostMethod(CFMXMessage.getProfile());
		postMethod.setRequestEntity(requestEntity);
		
		//ejecutar web service
		org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		try {
			httpClient.executeMethod(postMethod);
		} catch (HttpException e) {
			throw new WSException(e);
		} catch (IOException e) {
			throw new WSException(e);
		}
		
		// capturar el result
		String result = null;
		try {
			result = postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			throw new WSException(e);
		}
		if(StringUtils.isNotBlank(result)){
			Type type = new TypeToken<Collection<VistaPerfil>>() {}.getType();
			Collection<VistaPerfil> response = new ArrayList<>();
		 //transforma el json en una coleccion de objetos
    	  Gson googleJson = new Gson();
    	  response = googleJson.fromJson(result, type);
    	  return response;
		}
		
	return null;
}

	/**
	 * Arma json de funcionario
	 * 
	 * @param userId
	 * @return
	 */
	private static String getJsonFuncionario(String userId) {
		UserFunctionariesGenericReqVO request = new UserFunctionariesGenericReqVO();
		request.setCompanyId(1);
		request.setUserId(userId);
		Gson gson = new Gson();
		String json = gson.toJson(request);
		return json;
	}
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws WSException
	 */
	public static UserPaswordRolTypeResVO loginUser(String userName, String password) throws WSException {
		//armar json
		String json = getJsonLogin(userName, password);
		
		//configurar postMethod
		StringRequestEntity requestEntity = null;
		try {
			requestEntity = new StringRequestEntity(json,"application/json","UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new WSException(e);
		}
		PostMethod postMethod = new PostMethod(CFMXMessage.getLogin());
		postMethod.setRequestEntity(requestEntity);
		
		//ejecutar web service
		org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		try {
			httpClient.executeMethod(postMethod);
		} catch (HttpException e) {
			throw new WSException(e);
		} catch (IOException e) {
			throw new WSException(e);
		}
		
		// capturar el result
		String result = null;
		try {
			result = postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			throw new WSException(e);
		}
		if(StringUtils.isNotBlank(result)){
			UserPaswordRolTypeResVO response = new UserPaswordRolTypeResVO();
		 //transforma el json en una coleccion de objetos
    	  Gson googleJson = new Gson();
    	  response = googleJson.fromJson(result, UserPaswordRolTypeResVO.class);
    	  return response;
		}
		
		return null;
	}

	/**
	 * Arma json de login
	 * @param userName
	 * @param password
	 * @return
	 */
	private static String getJsonLogin(String userName, String password) {
		UserPaswordRolTypeReqVO request = new UserPaswordRolTypeReqVO();
		request.setUser(userName);
		request.setPassword(password);
		Gson gson = new Gson();
		String json = gson.toJson(request);
		return json;
	}
	
	/**
	 * Metodo para obtener la informacion del funcionario
	 * 
	 * @param userName
	 * @return userId
	 * @throws WSException
	 */
	public static String getFuncionaryByName(String userName) throws WSException {
		// armar json
		String json = getJsonFunctionaryName(userName);
		// configurar postMethod
		StringRequestEntity requestEntity = null;
		try {
			requestEntity = new StringRequestEntity(json, "application/json", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new WSException(e);
		}
		PostMethod postMethod = new PostMethod(CFMXMessage.getFuncionary());
		postMethod.setRequestEntity(requestEntity);
		// ejecutar web service para obtener funcionario
		org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		try {
			httpClient.executeMethod(postMethod);
		} catch (HttpException e) {
			throw new WSException(e);
		} catch (IOException e) {
			throw new WSException(e);
		}
		// capturar el result
		String result = null;
		try {
			result = postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			throw new WSException(e);
		}
		if (StringUtils.isNotBlank(result)) {
			// transforma el json en una coleccion de objetos
			Funcionario response = new Funcionario();
			Gson googleJson = new Gson();
			response = googleJson.fromJson(result, Funcionario.class);
			return response.getUsuario().getUserId();
		}
		return null;
	}
	
	/**
	 * Arma json de nombre de funcionario
	 * 
	 * @param referenceCode
	 * @return
	 */
	 private static String getJsonFunctionaryName(String userName) {
		 UserFunctionariesGenericReqVO request = new UserFunctionariesGenericReqVO();
		 request.setUserName(userName);
		 Gson gson = new Gson();
		 String json = gson.toJson(request);
		 return json;
	 }
	 
	 /**
	  * Obtiene las preguntas secretas del usuario
	  * 
	  * @param userId
	  * @return Usuario
	  * @throws WSException
	  */
	 public static Usuario getSecretQuestions(String userId) throws WSException {
		// armar json
		String jsonUserId = getJsonFunctionaryId(userId);
		// configurar postMethod
		StringRequestEntity requestEntity = null;
		try {
			requestEntity = new StringRequestEntity(jsonUserId, "application/json", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new WSException(e);
		}
		PostMethod postMethod = new PostMethod(CFMXMessage.getSecretQuestions());
		postMethod.setRequestEntity(requestEntity);
		// ejecutar web service para obtener funcionario
		org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		httpClient = new org.apache.commons.httpclient.HttpClient();
		try {
			httpClient.executeMethod(postMethod);
		} catch (IOException e) {
			throw new WSException(e);
		}
		
		// capturar el result
		String result;
		try {
			result = postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			throw new WSException(e);
		}
		if (StringUtils.isNotBlank(result)) {
			// transforma el json en una coleccion de objetos
			Usuario user = new Usuario();
			Gson googleJson = new Gson();
			user = googleJson.fromJson(result, Usuario.class);
			return user;
		}
		return null;
	 }
	 
	 /**
	  * Arma json de usuario id de funcionario
	  * 
	  * @param userName
	  * @return
	  */
	 private static String getJsonFunctionaryId(String userId) {
		 UserFunctionariesGenericReqVO request = new UserFunctionariesGenericReqVO();
		 request.setUserId(userId);
		 Gson gson = new Gson();
		 String json = gson.toJson(request);
		 return json;
	 }
	 
	 /**
	  * Actualiza la clave temporal
	  * 
	  * @param user
	  * @return
	  * @throws WSException
	  */
	 public static Boolean updateUserPwd(UserReqVO user) throws WSException {
		 Boolean isUpdated = Boolean.FALSE;
		/////// armar json
		Gson gson = new Gson();
		 String json = gson.toJson(user);
		// configurar postMethod
		StringRequestEntity requestEntity = null;
		try {
			requestEntity = new StringRequestEntity(json, "application/json", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new WSException(e);
		}
		PostMethod postMethod = new PostMethod(CFMXMessage.getUpdateUserPwd());
		postMethod.setRequestEntity(requestEntity);
		// ejecutar web service para obtener funcionario
		org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		httpClient = new org.apache.commons.httpclient.HttpClient();
		try {
			httpClient.executeMethod(postMethod);
		} catch (IOException e) {
			throw new WSException(e);
		}
		
		// capturar el result
		String result;
		try {
			result = postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			throw new WSException(e);
		}
		if (StringUtils.isNotBlank(result)) {
			isUpdated = Boolean.valueOf(result);
		}
		return isUpdated;
	 }
	 
	 /**
	  * Metodo para obtener el evento del sistema
	  * 
	  * @return
	  * @throws WSException
	  */
	 @SuppressWarnings("static-access")
	public static EventVO getEventForEmail() throws WSException {
		 // se arma el objeto
		 EventIdVO eventIdRequest = new EventIdVO();
		 eventIdRequest.setCompanyId(CFMXMessage.getCompanyId());
		 eventIdRequest.setSystemId(CFMXMessage.getSystemId());
		 eventIdRequest.setEventCode(CFMXMessage.getEventCode());
		 // se obtiene el evento
		 MessageWebServices messageService = new MessageWebServices();
		 EventVO event = messageService.findEventoDtoById(eventIdRequest);
		 return event;
	 }
	 
	 /**
	  * Metodo para enviar un correo
	  * 
	  * @param mailMessageStructureVO
	  * @return
	  * @throws WSException
	  */
	 public static Boolean sendEmail(String mail, String password, EventVO eventVo) throws WSException {
		try {
			// se arma el objeto
			MailMessageStructureVO mailMessageStructureVO = new MailMessageStructureVO();
			mailMessageStructureVO.setEventVo(eventVo);
			mailMessageStructureVO.setHost(MensajeriaMessages.getString("mail.serverHost"));
			mailMessageStructureVO.setPort(MensajeriaMessages.getString("mail.puerto"));
			mailMessageStructureVO.setHtmlFormat(Boolean.TRUE);
			mailMessageStructureVO.setFrom(CFMXMessage.getEmailRecoverPwd());
			mailMessageStructureVO.setSubject(eventVo.getSubjectEvent());
			mailMessageStructureVO.setOmitEventDto(Boolean.FALSE);
			String[] mailsAgentes = new String[1];
			mailsAgentes[0] = mail;
			mailMessageStructureVO.setTo(mailsAgentes);
			int capacity = 60 + password.length();
			StringBuilder contenidoXml = new StringBuilder(capacity);
			contenidoXml.append("<?xml version=\"1.0\"?><datos><tempPass>").append(password)
					.append("</tempPass></datos>");
			mailMessageStructureVO.setXmlMessage(contenidoXml.toString());
			// se envia el correo con clave temporal
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<MailMessageStructureVO> entity = new HttpEntity<>(mailMessageStructureVO, headers);
			Boolean response = new RestTemplate().postForObject(CFMXMessage.getPathSendEmail(), entity, Boolean.class);
			return response;
			
		} catch (Exception e) {
			CfmxLogger.LOG.error("Se produjo un error al consumir el servicio web: sendEmail: {}", e);
			throw new WSException(e);
		}
	 }
	 
	 /**
	  * Actualiza la clave temporal
	  * 
	  * @param user
	  * @return
	  * @throws WSException
	  */
	 @SuppressWarnings("serial")
	 public static TokenRes getCorpToken(TokenReq request) throws WSException {
		 CfmxLogger.LOG.info("AuthUtil getCorpToken1 , companyId {}, usuario {}, systemId {}", request.getCompanyId(), request.getUserId(), request.getSystemId());
		 TokenRes res = new TokenRes();
		// convertir a json
		 Gson gson = new Gson();
		 String json = gson.toJson(request);
		 CfmxLogger.LOG.info("AuthUtil getCorpToken2 , {}", json);
		// configurar postMethod
		StringRequestEntity requestEntity;
		try {
			requestEntity = new StringRequestEntity(json, "application/json", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			CfmxLogger.LOG.error("AuthUtil error getCorpToken1 servicio , {}", e);
			throw new WSException(e);
		}
		PostMethod postMethod = new PostMethod(CFMXMessage.getCorpToken());
		CfmxLogger.LOG.info("AuthUtil getCorpToken3 servicio , {}", CFMXMessage.getCorpToken());
		postMethod.setRequestEntity(requestEntity);
		// ejecutar web service para obtener funcionario
		org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		httpClient = new org.apache.commons.httpclient.HttpClient();
		try {
			httpClient.executeMethod(postMethod);
		} catch (IOException e) {
			CfmxLogger.LOG.error("AuthUtil error getCorpToken2 servicio , {}", e);
			throw new WSException(e);
		}
		String result = null;
		// capturar el result
		try {
			result = postMethod.getResponseBodyAsString();
			CfmxLogger.LOG.info("AuthUtil getCorpToken4 respuesta , {}", result);
		} catch (IOException e) {
			CfmxLogger.LOG.error("AuthUtil error getCorpToken3 servicio , {}", e);
			throw new WSException(e);
		}
		if (StringUtils.isNotBlank(result)) {
			Type type = new TypeToken<TokenRes>() {}.getType();
			res = gson.fromJson(result, type);
			CfmxLogger.LOG.info("AuthUtil getCorpToken5 respuesta , {}", res.getToken());
		}
		return res;
	 }

	 /**
	  * 
	  * @param userId
	  * @param oldPassword
	  * @param newPassword
	  * @return
	  * @throws WSException
	  */
	 public static Boolean changePassword(String userId, String oldPassword, String newPassword) 
			 throws WSException {
		 
		 PasswordReq request = new PasswordReq();
		 request.setUserId(userId);
		 request.setOldPassword(oldPassword);
		 request.setNewPassword(newPassword);
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Calendar now = Calendar.getInstance();
		 now.add(Calendar.MONTH, 1);
		 request.setUserPasswordCaducityDate(sdf.format(now.getTime()));
		 // se envia el correo con clave temporal
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 HttpEntity<PasswordReq> entity = new HttpEntity<>(request, headers);
		 Boolean response = new RestTemplate().postForObject(CFMXMessage.getPathChangePassword(), entity, Boolean.class);
		 return response;
	 }
	 
	 /**
	  * Metodo que busca el local por defecto
	  * 
	  * @param userId
	  * @return
	  * @throws WSException
	  */
	 public static Integer getLocalDefault(String userId) throws WSException {
		 
		 StringBuilder path = new StringBuilder();
		 path.append(CFMXMessage.getPathLocalDefault()).append("?codigoCompania=")
		 .append(CFMXMessage.getCompanyId()).append("&userId=").append(userId);
		 
		// Create a method instance.
		GetMethod method = new GetMethod(path.toString());
		HttpClient client = new HttpClient();
		try {
			Integer statusCode = client.executeMethod(method);
			
			if (statusCode != HttpStatus.OK.value()){
				CfmxLogger.LOG.error("Error al obtener el local por defecto.");
				throw new WSException();
		  }
		  // Read the response body
		  String responseBody = method.getResponseBodyAsString();
		  Funcionario funcionario = new Funcionario();
		  if(StringUtils.isNotBlank(responseBody)){
				// transforma el json en un objeto
			  Gson googleJson = new Gson();
			  funcionario = googleJson.fromJson(responseBody, Funcionario.class);
			  CfmxLogger.LOG.info("Se obtiene el local por defecto {}", 
					  funcionario.getAreaTrabajo().getIdAreaTrabajo());
			}
		  // retorna el codigo del local por defecto
		  return funcionario.getAreaTrabajo().getIdAreaTrabajo();
		} catch (IOException e) {
			CfmxLogger.LOG.error("Error al obtener el local por defecto {}", e.getMessage());
			throw new WSException(e);
		} 
	 }
	 
}
