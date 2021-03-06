package cn.action.modules.equip.entity;


import cn.action.common.persistence.DataEntity;
/**
 * 设备故障上报处理实例类
 * @author Administrator
 *
 */
public class EquipFaultReport extends DataEntity<EquipFaultReport>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String equipId;//设备id
	private String equipNo;//设备编号
	private String equipType;//设备类型
	private String equipLoc;//设备所在产线
	private String faultDesc;//故障描述
	private String status;//状态信息
	private String reportPerson;//上报人
	
	private String assignTime;//派工时间
	private String maintenanceWorker;//维修工人
	private EquipRepair equipRepair;
	
	public EquipRepair getEquipRepair() {
		return equipRepair;
	}
	
	public void setEquipRepair(EquipRepair equipRepair) {
		this.equipRepair = equipRepair;
	}
	
	public  EquipFaultReport(){
		super();
		this.equipRepair=new EquipRepair();
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEquipNo() {
		return equipNo;
	}
	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
	}
	public String getEquipType() {
		return equipType;
	}
	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}
	public String getEquipLoc() {
		return equipLoc;
	}
	public void setEquipLoc(String equipLoc) {
		this.equipLoc = equipLoc;
	}
	public String getFaultDesc() {
		return faultDesc;
	}
	public void setFaultDesc(String faultDesc) {
		this.faultDesc = faultDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReportPerson() {
		return reportPerson;
	}
	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}
	public String getAssignTime() {
		return assignTime;
	}
	public void setAssignTime(String assignTime) {
		this.assignTime = assignTime;
	}
	public String getMaintenanceWorker() {
		return maintenanceWorker;
	}
	public void setMaintenanceWorker(String maintenanceWorker) {
		this.maintenanceWorker = maintenanceWorker;
	}
	


}
