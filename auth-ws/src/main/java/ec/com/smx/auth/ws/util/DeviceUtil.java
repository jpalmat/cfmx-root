package ec.com.smx.auth.ws.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.google.gson.Gson;

import ec.com.smx.auth.client.commons.CFMXMessage;
import ec.com.smx.auth.client.log.CfmxLogger;
import ec.com.smx.auth.ws.exception.WSException;
import ec.com.smx.corpv2.ws.vo.MobileDeviceReqVO;

/**
 * 
 * @author jpalma
 *
 */
public class DeviceUtil {

	/**
	 * registra el dispositivo para el envío de mensajes PUSH
	 * @param userId
	 * @param sysId
	 * @param deviceId
	 * @param idPushMessage
	 * @param catalogType
	 * @param catalogValue
	 * @param trademark
	 * @param model
	 * @param osVersion
	 * @return
	 * @throws WSException
	 */
	public static Boolean registerDevice(String userId, String sysId, String deviceId, String idPushMessage, Integer catalogType, String catalogValue, String trademark, 
			String model, String osVersion) throws WSException {
		
			//armar json
			String json = loginLogoutJson(userId, sysId, deviceId, idPushMessage, catalogType, catalogValue, trademark, model, osVersion);
			
			//configurar postMethod
			StringRequestEntity requestEntity = null;
			try {
				requestEntity = new StringRequestEntity(json,"application/json","UTF-8");
			} catch (UnsupportedEncodingException e) {
				CfmxLogger.LOG.error("Error registerDevice catch1 {}", userId);
				throw new WSException(e);
			}
			PostMethod postMethod = new PostMethod(CFMXMessage.getUpdateDevice());
			postMethod.setRequestEntity(requestEntity);
			
			//ejecutar web service
			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			try {
				httpClient.executeMethod(postMethod);
			} catch (IOException e) {
				CfmxLogger.LOG.error("Error registerDevice catch2 {}", userId);
				throw new WSException(e);
			}
			
			// capturar el result
			String result = null;
			try {
				result = postMethod.getResponseBodyAsString();
			} catch (IOException e) {
				CfmxLogger.LOG.error("Error registerDevice catch3 {}", userId);
				throw new WSException(e);
			}
			
			return Boolean.parseBoolean(result);
		
	}
	
	/**
	 * arma el json para el registro y eliminacion de dispositivos
	 * @param userId
	 * @param sysId
	 * @param deviceId
	 * @param idPushMessage
	 * @param catalogType
	 * @param catalogValue
	 * @param trademark
	 * @param model
	 * @param osVersion
	 * @return
	 */
	private static String loginLogoutJson(String userId, String sysId, String deviceId, String idPushMessage, Integer catalogType, String catalogValue, String trademark, 
			String model, String osVersion) {
		MobileDeviceReqVO parameters = new MobileDeviceReqVO();
		parameters.setUserId(userId);
		parameters.setSystemId(sysId);
		parameters.setDeviceUniqueIdentifier(deviceId);
		parameters.setIdPushMessage(osVersion);
		parameters.setCodeCatalogType(catalogType);
		parameters.setCodeCatalogValue(catalogValue);
		parameters.setTrademark(trademark);
		parameters.setModel(model);
		parameters.setOsVersion(osVersion);
		
		// convertir en json la informacion
		Gson gson = new Gson();
		String json = gson.toJson(parameters);
		return json;
	}
}
