package ec.com.smx.auth.ws.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import ec.com.smx.auth.client.commons.CFMXMessage;
import ec.com.smx.auth.client.log.CfmxLogger;
import ec.com.smx.auth.ws.controller.ILocalController;
import ec.com.smx.auth.ws.controller.pojo.InfoTypeServer;
import ec.com.smx.auth.ws.controller.pojo.Local;
import ec.com.smx.auth.ws.controller.pojo.LocalsInformation;
import ec.com.smx.auth.ws.exception.WSException;
import ec.com.smx.auth.ws.util.DroolsUtil;
import ec.com.smx.auth.ws.util.LocalUtil;
import ec.com.smx.corpv2.ws.vo.DatosLocal;
import ec.com.smx.corpv2.ws.vo.WorkAreaReqVO;

/**
 * 
 * @author jpalma
 *
 */
@RequestMapping(value = "/local")
@Controller
public class LocalControllerImpl implements ILocalController {

	/**
	 * Obtiene los locales
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Local>> findLocals() throws WSException {
		try {
			// busca los tipos de locales
			List<Integer> codesLocal = LocalUtil.getTiposLocales();
			// llama a Drools para obtener el tipo de servidor
			InfoTypeServer infoServer = new InfoTypeServer();
			DroolsUtil.applyRules(infoServer, CFMXMessage.getPathInfoTypeServer());
			// valida si tiene informacion
			if(CollectionUtils.isNotEmpty(codesLocal)) {
				List<LocalsInformation> locals = LocalUtil.getLocalsEstablishment(codesLocal, infoServer.getTypeServer());
				List<Local> locations = getLocals(locals);
				return new ResponseEntity<List<Local>>(locations, HttpStatus.OK);
			}
			return new ResponseEntity<List<Local>>(new ArrayList<>(), HttpStatus.OK);
		} catch (Exception e) {
			CfmxLogger.LOG.error("Error en findLocals {}", e.getMessage());
			return new ResponseEntity<List<Local>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/controller", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Local> getLocalController(@RequestBody Map<String, Integer> map) throws WSException {
		try {
			//armar json
			String json = getJsonLocalController(map.get("referenceCode"));
			
			//configurar postMethod
			StringRequestEntity requestEntity = new StringRequestEntity(json,"application/json","UTF-8");
			PostMethod postMethod = new PostMethod(CFMXMessage.getLocalController());
			postMethod.setRequestEntity(requestEntity);
			
			//ejecutar web service
			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			httpClient.executeMethod(postMethod);
			
			// capturar el result
			String result = postMethod.getResponseBodyAsString();
			if(StringUtils.isNotBlank(result)){
				DatosLocal response = new DatosLocal();
			 //transforma el json en una coleccion de objetos
	    	  Gson googleJson = new Gson();
	    	  response = googleJson.fromJson(result, DatosLocal.class);
	    	  Local controller = new Local();
	    	  controller.setIpLocal(response.getIpServicioNormal());
	    	  return new ResponseEntity<Local>(controller, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			CfmxLogger.LOG.error("No se obtiene el controller del local , {}", map.get("referenceCode"));
			return new ResponseEntity<Local>(new Local("No se puede obtener servidor del local", Boolean.FALSE), HttpStatus.BAD_REQUEST);
		}
		return null;
	}

	/**
	 * 
	 * @param referenceCode
	 * @return
	 */
	private String getJsonLocalController(Integer referenceCode) {
		WorkAreaReqVO request = new WorkAreaReqVO();
		request.setCodigoCompania(1);
		request.setCodigoReferencia(referenceCode);
		request.setTipoAreaTrabajoValor("LOC");
		Gson gson = new Gson();
		String json = gson.toJson(request);
		return json;
	}
	
	/**
	 * armar coleccion de objetos
	 * 
	 * @param localDTOs
	 * @return
	 * @throws WSException
	 */
	private List<Local> getLocals(List<LocalsInformation> localDTOs) throws WSException {
		List<Local> locals = new ArrayList<Local>();
		for (LocalsInformation localDTO : localDTOs) {
			if (localDTO.getLatitud() != null && localDTO.getLongitud() != null) {
				// arma estructura para retornar
				Local localVO = new Local();
				localVO.setId(localDTO.getCodigoAreaTrabajo());
				localVO.setName(localDTO.getAreaTrabajo());
				localVO.setCity(localDTO.getNombreDivGeoPol());
				localVO.setType(localDTO.getNombreEstablecimiento());
				localVO.setStatus("1");
				localVO.setCityCode(localDTO.getCodigoCiudad());
				localVO.setReferenceCode(localDTO.getCodigoReferencia());
				localVO.setLatitude(localDTO.getLatitud());
				localVO.setLongitude(localDTO.getLongitud());
				// si tiene un servidor se selecciona el primero
				if(CollectionUtils.isNotEmpty(localDTO.getServerLocalCol())) {
					localVO.setIpLocal(localDTO.getServerLocalCol().iterator().next().getIpHost());
					localVO.setPortLocal(localDTO.getServerLocalCol().iterator().next().getPort());
				} else {
					// TODO eliminar elementos por defecto
					localVO.setIpLocal("10.2.44.15");
					localVO.setPortLocal(8080);
				}
				locals.add(localVO);
			}
		}
		return locals;
	}
}
