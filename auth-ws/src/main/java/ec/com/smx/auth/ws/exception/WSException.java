/**
 * 
 */
package ec.com.smx.auth.ws.exception;

/**
 * @author acachiguango
 *
 */
public class WSException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public WSException() {
	}

	/**
	 * 
	 * @param message
	 */
	public WSException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public WSException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public WSException(String message, Throwable cause) {
		super(message, cause);
	}

}
