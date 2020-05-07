/**
 * 
 */
package ec.com.smx.auth.ws.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import ec.com.smx.auth.client.commons.CFMXMessage;
import ec.com.smx.auth.client.log.CfmxLogger;
import ec.com.smx.auth.ws.controller.pojo.Establishment;
import ec.com.smx.auth.ws.controller.pojo.LocalsInformation;
import ec.com.smx.auth.ws.exception.WSException;

/**
 * @author gnolivos
 *
 */
public class LocalUtil {
	
	/**
	 * Servicio web para obtener los tipos de locales
	 * 
	 * @return Collection<Integer>
	 * @throws WSException
	 */
	@SuppressWarnings("serial")
	public static List<Integer> getTiposLocales() throws WSException {
		HttpClient client = new HttpClient();
		List<Integer> codesLocal = new ArrayList<>();
		 // agrega el path del servicio web
	    GetMethod method = new GetMethod(CFMXMessage.getPathTypeLocal());
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.OK.value()){
			    return null;
		    }
		    String responseBody = method.getResponseBodyAsString();
		    // valida el resultado
		    if(StringUtils.isNotBlank(responseBody)){
		    	Type type = new TypeToken<List<Integer>>() {}.getType();
				// transforma el json en una coleccion de objetos
				Gson googleJson = new Gson();
				codesLocal = googleJson.fromJson(responseBody, type);
				CfmxLogger.LOG.info("WS called from getTypeLocals");
			}
	      return codesLocal;
		} catch (IOException e) {
			CfmxLogger.LOG.error("Error al obtener los tipos de locales {}", e.getMessage());
			throw new WSException(e);
		} 
	}
	
	/**
	 * Servisio web para obtener locales activos y operacionales
	 * 
	 * @param codes
	 * @param typeServer
	 * @return
	 * @throws WSException
	 */
	@SuppressWarnings("serial")
	public static List<LocalsInformation> getLocalsEstablishment(Collection<Integer> codes, String typeServer) throws WSException {
		//armar json
		Establishment establecimiento = new Establishment();
		establecimiento.setIdCodigosEstablecimientos(codes);
		establecimiento.setTipoServidor(typeServer);
		// convertir en json la informacion
		Gson gson = new Gson();
		String json = gson.toJson(establecimiento);
		
		try {
			//configurar postMethod
			StringRequestEntity requestEntity = new StringRequestEntity(json,"application/json","UTF-8");
			PostMethod postMethod = new PostMethod(CFMXMessage.getPathLocalsEstablishment());
			postMethod.setRequestEntity(requestEntity);
			
			//ejecutar web service
			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			httpClient.executeMethod(postMethod);
			
			// capturar el result
			String result = postMethod.getResponseBodyAsString();
			if(StringUtils.isNotBlank(result)){
				Type type = new TypeToken<List<LocalsInformation>>() {}.getType();
				List<LocalsInformation> response = new ArrayList<>();
				// transforma el json en una coleccion de objetos
				Gson googleJson = new Gson();
				response = googleJson.fromJson(result, type);
				CfmxLogger.LOG.info("WS called from findLocalActYPublic");
				return response;
			}
		} catch (IOException e) {
			CfmxLogger.LOG.error("Error en getLocalsEstablishment {}", e);
			throw new WSException(e);
		}
		return null;
	}

}
