package ec.com.smx.auth.ws.controller.pojo;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserDevice
{
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Long userDeviceId;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String deviceUniqueIdentifier;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String codUserId;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String version;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String codeCatalogValue;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Integer codeCatalogType;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String trademark;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String model;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String documentNumber;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String status;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Collection<SystemDevice> systemDeviceLst;
  
  public String getCodUserId()
  {
    return this.codUserId;
  }
  
  public void setCodUserId(String codUserId)
  {
    this.codUserId = codUserId;
  }
  
  public Long getUserDeviceId()
  {
    return this.userDeviceId;
  }
  
  public void setUserDeviceId(Long userDeviceId)
  {
    this.userDeviceId = userDeviceId;
  }
  
  public String getDeviceUniqueIdentifier()
  {
    return this.deviceUniqueIdentifier;
  }
  
  public void setDeviceUniqueIdentifier(String deviceUniqueIdentifier)
  {
    this.deviceUniqueIdentifier = deviceUniqueIdentifier;
  }
  
  public String getVersion()
  {
    return this.version;
  }
  
  public void setVersion(String version)
  {
    this.version = version;
  }
  
  public String getCodeCatalogValue()
  {
    return this.codeCatalogValue;
  }
  
  public void setCodeCatalogValue(String codeCatalogValue)
  {
    this.codeCatalogValue = codeCatalogValue;
  }
  
  public Integer getCodeCatalogType()
  {
    return this.codeCatalogType;
  }
  
  public void setCodeCatalogType(Integer codeCatalogType)
  {
    this.codeCatalogType = codeCatalogType;
  }
  
  public String getTrademark()
  {
    return this.trademark;
  }
  
  public void setTrademark(String trademark)
  {
    this.trademark = trademark;
  }
  
  public String getModel()
  {
    return this.model;
  }
  
  public void setModel(String model)
  {
    this.model = model;
  }
  
  public String getDocumentNumber()
  {
    return this.documentNumber;
  }
  
  public void setDocumentNumber(String documentNumber)
  {
    this.documentNumber = documentNumber;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Collection<SystemDevice> getSystemDeviceLst()
  {
    return this.systemDeviceLst;
  }
  
  public void setSystemDeviceLst(Collection<SystemDevice> systemDeviceLst)
  {
    this.systemDeviceLst = systemDeviceLst;
  }
}
