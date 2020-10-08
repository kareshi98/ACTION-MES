package cn.action.modules.qc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.entity.Employee;
import cn.action.modules.bas.service.EmployeeService;
import cn.action.modules.qc.dao.ViolactionDao;
import cn.action.modules.qc.entity.Violaction;
import cn.action.modules.tec.entity.ProcessStation;
import cn.action.modules.tec.service.ProcessStationService;

@Service
@Transactional(readOnly = true)
public class ViolactionService extends CrudService<ViolactionDao, Violaction> {
	@Autowired
	private ProcessStationService processStationService;
	@Autowired
	private EmployeeService employeeService;

	/*
	 * ����
	 */
	public boolean saveViolaction(Violaction violaction) {
		// ��Υ���¼�еĹ�վ��Ӧ�Ĺ��������ӵ��ö�����
		ProcessStation processStation = processStationService
				.findProcessStationByStation(violaction.getWorkStationInfos());
		Employee employee = employeeService.get(violaction.getUserName());
		// ��Ϊ�գ�˵��û��ƥ��Ĺ��򣬲������Υ���¼
		if (processStation == null) {
			return false;
		}
		violaction.setProcess(processStation.getProcess());
		violaction.setUserName(employee.getEmployeeName());
		this.save(violaction);
		return true;

	}

	/**
	 * ��ѯĳ��ĳ��ÿ��Ա����Υ�����
	 *
	 * @param violaction
	 * @return
	 */
	public List<Violaction> findTimes(Violaction violaction) {
		// TODO Auto-generated method stub
		System.out.print(violaction.getViolationTime());
		return this.dao.findTimes(violaction);
	}
}
