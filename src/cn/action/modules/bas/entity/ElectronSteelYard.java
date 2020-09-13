package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.sys.entity.Office;

public class ElectronSteelYard extends DataEntity<ElectronSteelYard>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String qrCode;
	private String type;
	private String spec;
	private WorkCell workCell=new WorkCell();//工位外键对象
	private WorkStationInfos workStationInfos=new WorkStationInfos();//工站外键对象
	private String supplier;
	private String manufacturer;
	private String factoryNumber;
	private String purpose;
	private String buyDate;
	private String person;
	private Office organization;//部门外键对象
	private String ip;
	private String comBaudrate;
	private String comDataseat;
	private String comBegin;
	private String comEnd;
	private String checkMode;
	private String sysParam;
	private String eSName;//名称
	private String eSRange;//称重范围
	private String eSWeight;//重量
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public WorkCell getWorkCell() {
		return workCell;
	}
	public void setWorkCell(WorkCell workCell) {
		this.workCell = workCell;
	}
	public WorkStationInfos getWorkStationInfos() {
		return workStationInfos;
	}
	public void setWorkStationInfos(WorkStationInfos workStationInfos) {
		this.workStationInfos = workStationInfos;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getFactoryNumber() {
		return factoryNumber;
	}
	public void setFactoryNumber(String factoryNumber) {
		this.factoryNumber = factoryNumber;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public Office getOrganization() {
		return organization;
	}
	public void setOrganization(Office organization) {
		this.organization = organization;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getComBaudrate() {
		return comBaudrate;
	}
	public void setComBaudrate(String comBaudrate) {
		this.comBaudrate = comBaudrate;
	}
	public String getComDataseat() {
		return comDataseat;
	}
	public void setComDataseat(String comDataseat) {
		this.comDataseat = comDataseat;
	}
	public String getComBegin() {
		return comBegin;
	}
	public void setComBegin(String comBegin) {
		this.comBegin = comBegin;
	}
	public String getComEnd() {
		return comEnd;
	}
	public void setComEnd(String comEnd) {
		this.comEnd = comEnd;
	}
	public String getCheckMode() {
		return checkMode;
	}
	public void setCheckMode(String checkMode) {
		this.checkMode = checkMode;
	}
	public String getSysParam() {
		return sysParam;
	}
	public void setSysParam(String sysParam) {
		this.sysParam = sysParam;
	}
	public String geteSName() {
		return eSName;
	}
	public void seteSName(String eSName) {
		this.eSName = eSName;
	}
	public String geteSRange() {
		return eSRange;
	}
	public void seteSRange(String eSRange) {
		this.eSRange = eSRange;
	}
	public String geteSWeight() {
		return eSWeight;
	}
	public void seteSWeight(String eSWeight) {
		this.eSWeight = eSWeight;
	}
	
	
}
