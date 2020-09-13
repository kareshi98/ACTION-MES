package cn.action.modules.tec.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.bas.entity.WorkStationInfos;

public class ProcessStation extends DataEntity<ProcessStation>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Process process;
	private WorkStationInfos workStationInfos;
	public ProcessStation() {
		// TODO Auto-generated constructor stub
		super();
		this.process=new Process();
		this.workStationInfos=new WorkStationInfos();
	}
	
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
	public WorkStationInfos getWorkStationInfos() {
		return workStationInfos;
	}
	public void setWorkStationInfos(WorkStationInfos workStationInfos) {
		this.workStationInfos = workStationInfos;
	}
	

}
