package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.sys.entity.Office;

public class CardReader extends DataEntity<CardReader>{

	/**
	 * 读卡器
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
	
	private String readerSpeed;//读取速度
	private String timeInterval;//打卡间隔
	private String distance;//感应距离
	private String readerType;//读卡类型
	private String frequency;//读卡频率
	
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
	public String getReaderSpeed() {
		return readerSpeed;
	}
	public void setReaderSpeed(String readerSpeed) {
		this.readerSpeed = readerSpeed;
	}
	public String getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getReaderType() {
		return readerType;
	}
	public void setReaderType(String readerType) {
		this.readerType = readerType;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
}
