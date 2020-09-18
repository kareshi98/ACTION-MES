package cn.action.modules.equip.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.sys.entity.User;
/**
 * 设备保养计划实体类
 * @author Administrator
 *
 */
public class MaintenancePlan extends DataEntity<MaintenancePlan>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String equipType;//设备类型
	private String maintenance;//保养内容
	private String cycle;//保养周期
	private int warnTime;//预警时间，天数
	private User user;//保养人外键对象
	
	public MaintenancePlan() {
		super();
		this.user=new User();
	}
	
	public String getEquipType() {
		return equipType;
	}
	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}
	public String getMaintenance() {
		return maintenance;
	}
	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public int getWarnTime() {
		return warnTime;
	}
	public void setWarnTime(int warnTime) {
		this.warnTime = warnTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
}
