package cn.action.modules.kpi.service;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.WorkStationInfosService;
import cn.action.modules.kpi.dao.PerformStationDao;
import cn.action.modules.kpi.entity.PerformStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
public class PerformStationService extends CrudService<PerformStationDao, PerformStation>{
	@Autowired
	private WorkStationInfosService workStationInfosService;
	//根据是添加还是修改，提供不同的WorkStationInfos对象集合
	public List<WorkStationInfos> getStationsByIsAddOrUpdate(PerformStation performStation){
		List<WorkStationInfos> stations=workStationInfosService.findAllList(new WorkStationInfos());//有报错
		if(!performStation.getIsNewRecord()) {
			stations.add(performStation.getWorkStationInfos());
		}
		return stations;
	}
}
