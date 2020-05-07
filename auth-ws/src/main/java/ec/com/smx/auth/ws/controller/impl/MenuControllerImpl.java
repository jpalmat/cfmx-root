package ec.com.smx.auth.ws.controller.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import ec.com.kruger.utilitario.loggin.util.AmbienteConstantes;
import ec.com.smx.auth.client.commons.CFMXMessage;
import ec.com.smx.auth.client.log.CfmxLogger;
import ec.com.smx.auth.ws.controller.IMenuController;
import ec.com.smx.auth.ws.controller.pojo.FavoriteReq;
import ec.com.smx.auth.ws.controller.pojo.FavoriteRes;
import ec.com.smx.auth.ws.controller.pojo.Menu;
import ec.com.smx.auth.ws.controller.pojo.MenuItem;
import ec.com.smx.auth.ws.controller.pojo.User;
import ec.com.smx.auth.ws.enums.HeaderType;
import ec.com.smx.auth.ws.exception.WSException;
import ec.com.smx.corpv2.ws.vo.VistaFuncionarioPerfilOpcionVO;

/**
 * 
 * @author acachiguango
 *
 */
@RequestMapping(value = "/menu")
@Controller
public class MenuControllerImpl extends SecurityController implements IMenuController {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("serial")
	@RequestMapping(value = "/tree", method = RequestMethod.POST, headers = HeaderType.ACCCEPT_APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<List<MenuItem>> menuTree() throws WSException {
		User user = this.getSessionUser();
		CfmxLogger.LOG.info("Desplegar menu, usuario: {}", user.getUserName());
		if (StringUtils.isBlank(user.getProfileId())) {
			return new ResponseEntity<List<MenuItem>>(HttpStatus.BAD_REQUEST);
		}
		// arma el path del servico web del menuTree
		String pathUrl = AmbienteConstantes.DOMINIO_ACTUAL.concat("/datosCorporativo/menu/menuTree.json");
		// configura el header con json/utf8
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		// crea el json
        String json = null;
        user.setSystemID(CFMXMessage.getSystemId());
		try {
			json = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			throw new WSException(e);
		}
		// ejecuta la peticion
		HttpEntity<String> request = new HttpEntity<>(json, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(pathUrl, request, String.class);
		// valida el resultado
		if (200 == response.getStatusCodeValue()) {
			Type type = new TypeToken<Menu>() {}.getType();
			Gson gson = new Gson();
			Menu menu = gson.fromJson(response.getBody(), type);
			if(menu.getRoots() == null) {
				CfmxLogger.LOG.info("No se encontraron registros para el usuario: {} ", user.getUserName());
			}
			return new ResponseEntity<List<MenuItem>>(menu.getRoots(), HttpStatus.OK);
		}
		CfmxLogger.LOG.info("Status menuTree: " + response.getStatusCode());
		return new ResponseEntity<List<MenuItem>>(HttpStatus.OK);
	}

	@RequestMapping(value = "/addFavorite", method = RequestMethod.POST, headers = HeaderType.ACCCEPT_APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<FavoriteRes> addFavorite(@RequestBody Collection<FavoriteReq> parameter) throws WSException {
		//armar json
		String json = getJsonAddRemoveFavorite(parameter);
		
		//configurar postMethod
		StringRequestEntity requestEntity = null;
		try {
			requestEntity = new StringRequestEntity(json,"application/json","UTF-8");
		} catch (UnsupportedEncodingException e) {
			CfmxLogger.LOG.info("catch 1 addFavorite usuario Id: {}, error:  {}", this.getSessionUser().getUserId(), e.getMessage());
			return new ResponseEntity<FavoriteRes>(new FavoriteRes("Error al agregar favorito, por favor intente nuevamente", Boolean.FALSE), HttpStatus.BAD_REQUEST);
		}
		PostMethod postMethod = new PostMethod(CFMXMessage.getPathAddFavorite());
		postMethod.setRequestEntity(requestEntity);
		
		//ejecutar web service
		org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		try {
			httpClient.executeMethod(postMethod);
		} catch (IOException e) {
			CfmxLogger.LOG.info("catch 2 addFavorite usuario Id: {}, error:  {}", this.getSessionUser().getUserId(), e.getMessage());
			return new ResponseEntity<FavoriteRes>(new FavoriteRes("Error al agregar favorito, por favor intente nuevamente", Boolean.FALSE), HttpStatus.BAD_REQUEST);
		}
		
		// capturar el result
		String result = null;
		try {
			result = postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			CfmxLogger.LOG.info("catch 3 addFavorite usuario Id: {}, error:  {}", this.getSessionUser().getUserId(), e.getMessage());
			return new ResponseEntity<FavoriteRes>(new FavoriteRes("Error al agregar favorito, por favor intente nuevamente", Boolean.FALSE), HttpStatus.BAD_REQUEST);
		}
		try {
			if(StringUtils.isNotBlank(result) && Boolean.valueOf(result)){
				FavoriteRes response = new FavoriteRes();
				response.setSuccess(Boolean.valueOf(result));
	    	  return new ResponseEntity<FavoriteRes>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<FavoriteRes>(new FavoriteRes("Error al agregar favorito, por favor intente nuevamente", Boolean.FALSE), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			CfmxLogger.LOG.info("catch 4 addFavorite usuario Id: {}, error:  {}", this.getSessionUser().getUserId(), e.getMessage());
			return new ResponseEntity<FavoriteRes>(new FavoriteRes("Error al agregar favorito, por favor intente nuevamente", Boolean.FALSE), HttpStatus.BAD_REQUEST);
		}
	}

	private String getJsonAddRemoveFavorite(Collection<FavoriteReq> options) {
		Collection<VistaFuncionarioPerfilOpcionVO> optionsToAdd = new ArrayList<>();
		VistaFuncionarioPerfilOpcionVO optionFromSmart = new VistaFuncionarioPerfilOpcionVO();
		User user = this.getSessionUser();
		user.setSystemID(CFMXMessage.getSystemId());
		for (FavoriteReq option : options) {
			optionFromSmart = new VistaFuncionarioPerfilOpcionVO();
			optionFromSmart.setCodigoCompania(1);
			optionFromSmart.setCodigoOpcion(option.getCodigoOpcion());
			optionFromSmart.setCodigoPerfil(user.getProfileId());
			optionFromSmart.setCodigoSistema(user.getSystemID());
			optionFromSmart.setUserId(user.getUserId());
			optionFromSmart.setCodigoFuncionario(user.getUsuarioFuncionario());
			optionsToAdd.add(optionFromSmart);
		}
		Gson gson = new Gson();
		String json = gson.toJson(optionsToAdd);
		return json;
	}

	@RequestMapping(value = "/removeFavorite", method = RequestMethod.POST, headers = HeaderType.ACCCEPT_APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<FavoriteRes> removeFavorite(@RequestBody Collection<FavoriteReq> parameter) throws WSException {
		//armar json
		String json = getJsonAddRemoveFavorite(parameter);
		
		//configurar postMethod
		StringRequestEntity requestEntity = null;
		try {
			requestEntity = new StringRequestEntity(json,"application/json","UTF-8");
		} catch (UnsupportedEncodingException e) {
			CfmxLogger.LOG.info("catch 1 removeFavorite usuario Id: {}, error:  {}", this.getSessionUser().getUserId(), e.getMessage());
			return new ResponseEntity<FavoriteRes>(new FavoriteRes("Error al eliminar favorito, por favor intente nuevamente", Boolean.FALSE), HttpStatus.BAD_REQUEST);
		}
		PostMethod postMethod = new PostMethod(CFMXMessage.getPathRemoveFavorite());
		postMethod.setRequestEntity(requestEntity);
		
		//ejecutar web service
		org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		try {
			httpClient.executeMethod(postMethod);
		} catch (IOException e) {
			CfmxLogger.LOG.info("catch 2 removeFavorite usuario Id: {}, error:  {}", this.getSessionUser().getUserId(), e.getMessage());
			return new ResponseEntity<FavoriteRes>(new FavoriteRes("Error al eliminar favorito, por favor intente nuevamente", Boolean.FALSE), HttpStatus.BAD_REQUEST);
		}
		
		// capturar el result
		String result = null;
		try {
			result = postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			CfmxLogger.LOG.info("catch 3 removeFavorite usuario Id: {}, error:  {}", this.getSessionUser().getUserId(), e.getMessage());
			return new ResponseEntity<FavoriteRes>(new FavoriteRes("Error al eliminar favorito, por favor intente nuevamente", Boolean.FALSE), HttpStatus.BAD_REQUEST);
		}
		try {
			if(StringUtils.isNotBlank(result) && Boolean.valueOf(result)){
				FavoriteRes response = new FavoriteRes();
				response.setSuccess(Boolean.valueOf(result));
	    	  return new ResponseEntity<FavoriteRes>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<FavoriteRes>(new FavoriteRes("Error al eliminar favorito, por favor intente nuevamente", Boolean.FALSE), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			CfmxLogger.LOG.info("catch 4 removeFavorite usuario Id: {}, error:  {}", this.getSessionUser().getUserId(), e.getMessage());
			return new ResponseEntity<FavoriteRes>(new FavoriteRes("Error al eliminar favorito, por favor intente nuevamente", Boolean.FALSE), HttpStatus.BAD_REQUEST);
		}
	}
}
