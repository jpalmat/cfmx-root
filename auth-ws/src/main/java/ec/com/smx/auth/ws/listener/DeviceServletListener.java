package ec.com.smx.auth.ws.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ec.com.smx.auth.ws.exception.WSRuntimeException;
import ec.com.smx.frameworkv2.common.factory.FrameworkFactory;

public class DeviceServletListener implements ServletContextListener {

	/**
	 * 
	 */
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/**
	 * Inicializa los factory al subir el servidor
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			FrameworkFactory.getInstancia();
		} catch (Exception e) {
			throw new WSRuntimeException("Error al iniciar la aplicacion, FrameworkFactory {}", e);
		}
	}
}
