package ec.com.smx.auth.ws.controller.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * POJO para response de generar token
 * 
 * @author jpalma
 *
 */
public class TokenRes {
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String token;
	private Response response;
	
	/**
	 * 
	 */
	public TokenRes() {}
	
	/**
	 * 
	 * @param message
	 * @param status
	 */
	public TokenRes(String message, Boolean status) {
		this.response = new Response(message, status);
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
}
