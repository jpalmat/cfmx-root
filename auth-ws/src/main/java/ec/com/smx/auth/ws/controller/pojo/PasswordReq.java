/**
 * 
 */
package ec.com.smx.auth.ws.controller.pojo;

/**
 * @author gnolivos
 *
 */
public class PasswordReq {
	
	private String userId;
	private String oldPassword;
	private String newPassword;
	private String userPasswordCaducityDate;
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}
	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	/**
	 * @return the userPasswordCaducityDate
	 */
	public String getUserPasswordCaducityDate() {
		return userPasswordCaducityDate;
	}
	/**
	 * @param userPasswordCaducityDate the userPasswordCaducityDate to set
	 */
	public void setUserPasswordCaducityDate(String userPasswordCaducityDate) {
		this.userPasswordCaducityDate = userPasswordCaducityDate;
	}

}
