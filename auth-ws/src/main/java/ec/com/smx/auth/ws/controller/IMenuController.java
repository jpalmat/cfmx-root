package ec.com.smx.auth.ws.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import ec.com.smx.auth.ws.controller.pojo.FavoriteReq;
import ec.com.smx.auth.ws.controller.pojo.FavoriteRes;
import ec.com.smx.auth.ws.controller.pojo.MenuItem;
import ec.com.smx.auth.ws.exception.WSException;

/**
 * 
 * @author acachiguango
 *
 */
public interface IMenuController {
	/**
	 * Obtiene el menu del usuario
	 * 
	 * @param user
	 * @return
	 * @throws WSException
	 */
	ResponseEntity<List<MenuItem>> menuTree() throws WSException;
	
	/**
	 * Agrega favorito
	 * 
	 * @param parameter
	 * @return
	 * @throws WSException
	 */
	ResponseEntity<FavoriteRes> addFavorite(@RequestBody Collection<FavoriteReq> parameter) throws WSException;
	
	/**
	 * Elimina favorito
	 * 
	 * @param parameter
	 * @return
	 * @throws WSException
	 */
	ResponseEntity<FavoriteRes> removeFavorite(@RequestBody Collection<FavoriteReq> parameter) throws WSException;
}
