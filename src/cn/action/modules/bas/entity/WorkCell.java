package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;

public class WorkCell extends DataEntity<WorkCell>{

	/**
	 * 工位
	 */
	private static final long serialVersionUID = 1L;
	private String cellName;
	private String cellNumber;
	private String cellMaster;
	private String cellDescription;
	private WorkStationInfos workStationInfos;//外键对象
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getCellNumber() {
		return cellNumber;
	}
	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}
	public String getCellMaster() {
		return cellMaster;
	}
	public void setCellMaster(String cellMaster) {
		this.cellMaster = cellMaster;
	}
	public String getCellDescription() {
		return cellDescription;
	}
	public void setCellDescription(String cellDescription) {
		this.cellDescription = cellDescription;
	}
	public WorkStationInfos getWorkStationInfos() {
		return workStationInfos;
	}
	public void setWorkStationInfos(WorkStationInfos workStationInfos) {
		this.workStationInfos = workStationInfos;
	}
	
	
}
