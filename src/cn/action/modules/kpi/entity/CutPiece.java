package cn.action.modules.kpi.entity;

import cn.action.common.persistence.DataEntity;
/**
 * 开片绩效实体类
 * @author Administrator
 *
 */
public class CutPiece extends DataEntity<CutPiece>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String time;//日期
	private String employeeName;//人员名称
	private String pieces;//总片数
	private String money;//薪资
	private String startTime;//开始时间
	private String endTime;//结束时间
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPieces() {
		return pieces;
	}
	public void setPieces(String pieces) {
		this.pieces = pieces;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
