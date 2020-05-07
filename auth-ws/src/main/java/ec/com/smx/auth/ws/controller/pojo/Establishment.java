/**
 * 
 */
package ec.com.smx.auth.ws.controller.pojo;

import java.util.Collection;

/**
 * @author gnolivos
 *
 */
public class Establishment {
	
	private String tipoServidor;
	private Collection<Integer> idCodigosEstablecimientos;
	
	/**
	 * @return the tipoServidor
	 */
	public String getTipoServidor() {
		return tipoServidor;
	}
	/**
	 * @param tipoServidor the tipoServidor to set
	 */
	public void setTipoServidor(String tipoServidor) {
		this.tipoServidor = tipoServidor;
	}
	/**
	 * @return the idCodigosEstablecimientos
	 */
	public Collection<Integer> getIdCodigosEstablecimientos() {
		return idCodigosEstablecimientos;
	}
	/**
	 * @param idCodigosEstablecimientos the idCodigosEstablecimientos to set
	 */
	public void setIdCodigosEstablecimientos(Collection<Integer> idCodigosEstablecimientos) {
		this.idCodigosEstablecimientos = idCodigosEstablecimientos;
	}
	
	

}
