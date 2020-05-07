package ec.com.smx.auth.ws.controller.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

public class SystemDevice
{
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String sysId;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Long userDeviceId;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String systemVersion;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String idPushMessage;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String endPoint;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String publicKey;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String auth;
  
  public String getSysId()
  {
    return this.sysId;
  }
  
  public void setSysId(String sysId)
  {
    this.sysId = sysId;
  }
  
  public Long getUserDeviceId()
  {
    return this.userDeviceId;
  }
  
  public void setUserDeviceId(Long userDeviceId)
  {
    this.userDeviceId = userDeviceId;
  }
  
  public String getSystemVersion()
  {
    return this.systemVersion;
  }
  
  public void setSystemVersion(String systemVersion)
  {
    this.systemVersion = systemVersion;
  }
  
  public String getIdPushMessage()
  {
    return this.idPushMessage;
  }
  
  public void setIdPushMessage(String idPushMessage)
  {
    this.idPushMessage = idPushMessage;
  }
  
  public String getEndPoint()
  {
    return this.endPoint;
  }
  
  public void setEndPoint(String endPoint)
  {
    this.endPoint = endPoint;
  }
  
  public String getPublicKey()
  {
    return this.publicKey;
  }
  
  public void setPublicKey(String publicKey)
  {
    this.publicKey = publicKey;
  }
  
  public String getAuth()
  {
    return this.auth;
  }
  
  public void setAuth(String auth)
  {
    this.auth = auth;
  }
}
