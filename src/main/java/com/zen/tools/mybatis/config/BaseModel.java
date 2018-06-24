package com.zen.tools.mybatis.config;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseModel implements Serializable {

  private static final long serialVersionUID = -2864104564817210962L;
  private String id;
  private String createdBy;
  private String updatedBy;
  private LocalDateTime dtCreated;
  private LocalDateTime dtUpdated;
  private Integer version;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public LocalDateTime getDtCreated() {
    return dtCreated;
  }

  public void setDtCreated(LocalDateTime dtCreated) {
    this.dtCreated = dtCreated;
  }

  public LocalDateTime getDtUpdated() {
    return dtUpdated;
  }

  public void setDtUpdated(LocalDateTime dtUpdated) {
    this.dtUpdated = dtUpdated;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
}
