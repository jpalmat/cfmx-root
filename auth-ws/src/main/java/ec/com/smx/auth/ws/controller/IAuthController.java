/**
 * 
 */
package ec.com.smx.auth.ws.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import ec.com.smx.auth.ws.controller.pojo.Response;
import ec.com.smx.auth.ws.controller.pojo.TokenReq;
import ec.com.smx.auth.ws.controller.pojo.TokenRes;
import ec.com.smx.auth.ws.controller.pojo.User;
import ec.com.smx.auth.ws.exception.WSException;
import ec.com.smx.corpv2.ws.vo.UserFunctionariesGenericReqVO;

/**
 * @author acachiguango
 *
 */
public interface IAuthController {

	/**
	 * Login
	 * 
	 * @param user
	 * @param request
	 * @return
	 * @throws WSException
	 */
	ResponseEntity<User> login(@RequestBody User user, HttpServletRequest request) throws WSException;

	/**
	 * Logout
	 * 
	 * @param request
	 * @return
	 * @throws WSException
	 */
	ResponseEntity<Response> logout(HttpServletRequest request) throws WSException;
	
	/**
	 * ForgotPassword
	 * 
	 * @param params
	 * @return
	 * @throws WSException
	 */
	ResponseEntity<User> forgotPassword(@RequestBody UserFunctionariesGenericReqVO params) throws WSException;
	
	ResponseEntity<TokenRes> getCorpTokenWs(@RequestBody TokenReq params) throws WSException;
}
