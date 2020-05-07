package ec.com.smx.auth.ws.controller.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author acachiguango
 *
 */
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6700859358930541581L;
	@JsonInclude(value = Include.NON_EMPTY)
	private String userId;
	@JsonInclude(value = Include.NON_EMPTY)
	private String userName;
	@JsonInclude(value = Include.NON_EMPTY)
	private String password;
	@JsonInclude(value = Include.NON_EMPTY)
	private String userCompleteName;
	@JsonInclude(value = Include.NON_EMPTY)
	private Integer companyId;
	@JsonInclude(value = Include.NON_EMPTY)
	private String profileId;
	@JsonInclude(value = Include.NON_EMPTY)
	private String systemID;
	@JsonInclude(value = Include.NON_EMPTY)
	private String usuarioFuncionario;
	private Boolean changePwd = Boolean.FALSE;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String tipoUsuario;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String userEmail;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String emailEmpresarial;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String emailPersonal;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Boolean puedeTenerPreguntaSecreta;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Collection<PreguntaSecreta> colPregSecretas;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String status;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Collection<UserDevice> userDeviceCol;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String userSuper;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Date userPasswordCaducityDate;
	private Response response;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer defaultLocalId;
	
	/**
	 * 
	 */
	public User() {}
	
	/**
	 * 
	 * @param message
	 * @param status
	 */
	public User(String message, Boolean status) {
		this.response = new Response(message, status);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getSystemID() {
		return systemID;
	}

	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}

	public String getUserCompleteName() {
		return userCompleteName;
	}

	public void setUserCompleteName(String userCompleteName) {
		this.userCompleteName = userCompleteName;
	}

	public String getUsuarioFuncionario() {
		return usuarioFuncionario;
	}

	public void setUsuarioFuncionario(String usuarioFuncionario) {
		this.usuarioFuncionario = usuarioFuncionario;
	}

	public Boolean getChangePwd() {
		return changePwd;
	}

	public void setChangePwd(Boolean changePwd) {
		this.changePwd = changePwd;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getEmailEmpresarial() {
		return emailEmpresarial;
	}

	public void setEmailEmpresarial(String emailEmpresarial) {
		this.emailEmpresarial = emailEmpresarial;
	}

	public String getEmailPersonal() {
		return emailPersonal;
	}

	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}

	public Boolean getPuedeTenerPreguntaSecreta() {
		return puedeTenerPreguntaSecreta;
	}

	public void setPuedeTenerPreguntaSecreta(Boolean puedeTenerPreguntaSecreta) {
		this.puedeTenerPreguntaSecreta = puedeTenerPreguntaSecreta;
	}

	public Collection<PreguntaSecreta> getColPregSecretas() {
		return colPregSecretas;
	}

	public void setColPregSecretas(Collection<PreguntaSecreta> colPregSecretas) {
		this.colPregSecretas = colPregSecretas;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Collection<UserDevice> getUserDeviceCol() {
		return userDeviceCol;
	}

	public void setUserDeviceCol(Collection<UserDevice> userDeviceCol) {
		this.userDeviceCol = userDeviceCol;
	}

	public String getUserSuper() {
		return userSuper;
	}

	public void setUserSuper(String userSuper) {
		this.userSuper = userSuper;
	}

	public Date getUserPasswordCaducityDate() {
		return userPasswordCaducityDate;
	}

	public void setUserPasswordCaducityDate(Date userPasswordCaducityDate) {
		this.userPasswordCaducityDate = userPasswordCaducityDate;
	}

	public Integer getDefaultLocalId() {
		return defaultLocalId;
	}

	public void setDefaultLocalId(Integer defaultLocalId) {
		this.defaultLocalId = defaultLocalId;
	}

}
