package ec.com.smx.auth.ws.controller.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Response {
	
	@JsonInclude(value = Include.NON_EMPTY)
	private Integer code;
	@JsonInclude(value = Include.NON_EMPTY)
	private String message;
	@JsonInclude(value = Include.NON_EMPTY)
	private Boolean status;

	public Response(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Response(String message, Boolean status) {
		this.message = message;
		this.status = status;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

}
