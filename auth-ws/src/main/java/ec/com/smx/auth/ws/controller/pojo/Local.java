package ec.com.smx.auth.ws.controller.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author jpalma
 *
 */
public class Local {
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer id;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String name;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String city;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String type;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String status;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String cityCode;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer referenceCode;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Double latitude;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Double longitude;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String ipLocal;
	private Response response;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer portLocal;
	
	/**
	 * 
	 */
	public Local() {}
	
	/**
	 * 
	 * @param message
	 * @param status
	 */
	public Local(String message, Boolean status) {
		this.response = new Response(message, status);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode
	 *            the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the referenceCode
	 */
	public Integer getReferenceCode() {
		return referenceCode;
	}

	/**
	 * @param referenceCode
	 *            the referenceCode to set
	 */
	public void setReferenceCode(Integer referenceCode) {
		this.referenceCode = referenceCode;
	}

	/**
	 * @return the ipLocal
	 */
	public String getIpLocal() {
		return ipLocal;
	}

	/**
	 * @param ipLocal
	 *            the ipLocal to set
	 */
	public void setIpLocal(String ipLocal) {
		this.ipLocal = ipLocal;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	/**
	 * @return the portLocal
	 */
	public Integer getPortLocal() {
		return portLocal;
	}

	/**
	 * @param portLocal the portLocal to set
	 */
	public void setPortLocal(Integer portLocal) {
		this.portLocal = portLocal;
	}

}
