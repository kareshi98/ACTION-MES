package cn.action.modules.qc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.qc.dao.ViolactionDao;
import cn.action.modules.qc.entity.Violaction;
import cn.action.modules.tec.entity.ProcessStation;
import cn.action.modules.tec.service.ProcessStationService;

@Service
@Transactional(readOnly=true)
public class ViolactionService extends CrudService<ViolactionDao, Violaction>{
	
	@Autowired
	private ProcessStationService processStationService;
	/**
	 * 保存
	 * @param violaction
	 * @return
	 */
	public boolean saveViolaction(Violaction violaction) {
		//将违规记录中的工站对应的工序对象添加到该对象中
		ProcessStation processStation=processStationService.findProcessStationByStation(violaction.getWorkStationInfos());
		//如果对象为空，说明没有匹配的工序，就不能添加违规记录
		if(processStation==null) {
			return false;
		}
		violaction.setProcess(processStation.getProcess());
		this.save(violaction);
		return true;
	}
}
