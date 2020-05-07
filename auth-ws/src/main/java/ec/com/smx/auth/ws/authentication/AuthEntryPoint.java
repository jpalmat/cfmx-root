package ec.com.smx.auth.ws.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import ec.com.smx.auth.client.log.CfmxLogger;

/**
 * 
 * @author acachiguango
 *
 */
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				"No tiene autorizaci\u00F3n para acceder a este recurso del sistema.");
		CfmxLogger.LOG.error("AuthenticationEntryPoint: " + authException.getMessage());
	}

}
