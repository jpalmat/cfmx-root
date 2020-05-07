/**
 * 
 */
package ec.com.smx.auth.client.log;

import org.slf4j.Logger;

import ec.com.kruger.utilitario.loggin.KLogFactory;
import ec.com.kruger.utilitario.loggin.resources.LogUtilMessages;

/**
 * @author gnolivos
 *
 */
public final class CfmxLogger {
	
	public static final Logger LOG = KLogFactory
			.getLog(LogUtilMessages.MESSAGE_RESOLVER.getString("log.sistema.FLUXMOVIL"));

	private CfmxLogger() {
	}

}
