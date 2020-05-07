package ec.com.smx.auth.ws.controller.pojo;

/**
 * response del servicio addFavorite
 * 
 * @author jpalma
 *
 */
public class FavoriteRes {
	
	private Boolean success;
	private Response response;
	/**
	 * 
	 */
	public FavoriteRes() {}
	
	/**
	 * 
	 * @param message
	 * @param status
	 */
	public FavoriteRes(String message, Boolean status) {
		this.response = new Response(message, status);
	}

	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
}
