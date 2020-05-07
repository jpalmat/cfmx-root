/**
 * 
 */
package ec.com.smx.auth.ws.controller.pojo;

import java.util.Collection;

/**
 * @author gnolivos
 *
 */
public class LocalsInformation {
	
	private Integer codigoAreaTrabajo;
	private String areaTrabajo;
	private String nombreDivGeoPol;
	private String nombreEstablecimiento;
	private String estadoAreaTrabajo;
	private String codigoCiudad;
	private Integer codigoReferencia;
	private Double latitud;
	private Double longitud;
	private String ipLocal;
	private Collection<ServerLocalCol> serverLocalCol;
	
	/**
	 * @return the codigoAreaTrabajo
	 */
	public Integer getCodigoAreaTrabajo() {
		return codigoAreaTrabajo;
	}
	/**
	 * @param codigoAreaTrabajo the codigoAreaTrabajo to set
	 */
	public void setCodigoAreaTrabajo(Integer codigoAreaTrabajo) {
		this.codigoAreaTrabajo = codigoAreaTrabajo;
	}
	/**
	 * @return the areaTrabajo
	 */
	public String getAreaTrabajo() {
		return areaTrabajo;
	}
	/**
	 * @param areaTrabajo the areaTrabajo to set
	 */
	public void setAreaTrabajo(String areaTrabajo) {
		this.areaTrabajo = areaTrabajo;
	}
	/**
	 * @return the nombreDivGeoPol
	 */
	public String getNombreDivGeoPol() {
		return nombreDivGeoPol;
	}
	/**
	 * @param nombreDivGeoPol the nombreDivGeoPol to set
	 */
	public void setNombreDivGeoPol(String nombreDivGeoPol) {
		this.nombreDivGeoPol = nombreDivGeoPol;
	}
	/**
	 * @return the nombreEstablecimiento
	 */
	public String getNombreEstablecimiento() {
		return nombreEstablecimiento;
	}
	/**
	 * @param nombreEstablecimiento the nombreEstablecimiento to set
	 */
	public void setNombreEstablecimiento(String nombreEstablecimiento) {
		this.nombreEstablecimiento = nombreEstablecimiento;
	}
	/**
	 * @return the estadoAreaTrabajo
	 */
	public String getEstadoAreaTrabajo() {
		return estadoAreaTrabajo;
	}
	/**
	 * @param estadoAreaTrabajo the estadoAreaTrabajo to set
	 */
	public void setEstadoAreaTrabajo(String estadoAreaTrabajo) {
		this.estadoAreaTrabajo = estadoAreaTrabajo;
	}
	/**
	 * @return the codigoCiudad
	 */
	public String getCodigoCiudad() {
		return codigoCiudad;
	}
	/**
	 * @param codigoCiudad the codigoCiudad to set
	 */
	public void setCodigoCiudad(String codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
	}
	/**
	 * @return the codigoReferencia
	 */
	public Integer getCodigoReferencia() {
		return codigoReferencia;
	}
	/**
	 * @param codigoReferencia the codigoReferencia to set
	 */
	public void setCodigoReferencia(Integer codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}
	/**
	 * @return the latitud
	 */
	public Double getLatitud() {
		return latitud;
	}
	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	/**
	 * @return the longitud
	 */
	public Double getLongitud() {
		return longitud;
	}
	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	/**
	 * @return the ipLocal
	 */
	public String getIpLocal() {
		return ipLocal;
	}
	/**
	 * @param ipLocal the ipLocal to set
	 */
	public void setIpLocal(String ipLocal) {
		this.ipLocal = ipLocal;
	}
	/**
	 * @return the serverLocalCol
	 */
	public Collection<ServerLocalCol> getServerLocalCol() {
		return serverLocalCol;
	}
	/**
	 * @param serverLocalCol the serverLocalCol to set
	 */
	public void setServerLocalCol(Collection<ServerLocalCol> serverLocalCol) {
		this.serverLocalCol = serverLocalCol;
	}

}
