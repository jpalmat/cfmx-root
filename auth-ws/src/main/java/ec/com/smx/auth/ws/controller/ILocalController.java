package ec.com.smx.auth.ws.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import ec.com.smx.auth.ws.controller.pojo.Local;
import ec.com.smx.auth.ws.exception.WSException;
/**
 * 
 * @author jpalma
 *
 */
public interface ILocalController {
	/**
	 * Obtiene los locales
	 * @return
	 * @throws WSException
	 */
	ResponseEntity<List<Local>> findLocals() throws WSException;
	
	/**
	 * Obtiene ip
	 * @param map
	 * @return
	 * @throws WSException
	 */
	ResponseEntity<Local> getLocalController(@RequestBody Map<String, Integer> map) throws WSException;
}
