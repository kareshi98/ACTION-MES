package cn.action.modules.equip.entity;

import cn.action.common.persistence.DataEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 此处填写说明
 *
 * @author Administrator
 * @createDate 2020/9/18
 */
public class EquipRepair extends DataEntity<EquipRepair> {
  
  
  private static final long serialVersionUID = 1L;
  
  private String mid;
  private String faultType;
  private String faultReason;
  private String faultDesc;
  
  public MultipartFile getFile() {
    return file;
  }
  
  public void setFile(MultipartFile file) {
    this.file = file;
  }
  
  private String faultImgs;
   private MultipartFile file;
  public String getMid() {
    return mid;
  }
  
  public void setMid(String mid) {
    this.mid = mid;
  }
  
  public String getFaultType() {
    return faultType;
  }
  
  public void setFaultType(String faultType) {
    this.faultType = faultType;
  }
  
  public String getFaultReason() {
    return faultReason;
  }
  
  public void setFaultReason(String faultReason) {
    this.faultReason = faultReason;
  }
  
  public String getFaultDesc() {
    return faultDesc;
  }
  
  public void setFaultDesc(String faultDesc) {
    this.faultDesc = faultDesc;
  }
  
  public String getFaultImgs() {
    return faultImgs;
  }
  
  public void setFaultImgs(String faultImgs) {
    this.faultImgs = faultImgs;
  }
  
  
}
