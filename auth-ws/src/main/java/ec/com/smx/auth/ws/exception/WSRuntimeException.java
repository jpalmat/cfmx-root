/**
 * 
 */
package ec.com.smx.auth.ws.exception;

/**
 * @author acachiguango
 *
 */
public class WSRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1639715169983198210L;

	/**
	 * 
	 * @param message
	 */
	public WSRuntimeException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message
	 * @param ex
	 */
	public WSRuntimeException(String message, Throwable ex) {
		super(message, ex);
	}
}