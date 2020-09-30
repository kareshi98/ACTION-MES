package cn.action.modules.equip.service;

import cn.action.common.service.CrudService;
import cn.action.common.utils.UserUtils;
import cn.action.modules.equip.dao.EquipRepairDao;
import cn.action.modules.equip.entity.EquipFaultReport;
import cn.action.modules.equip.entity.EquipRepair;
import cn.action.modules.equip.entity.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 此处填写说明
 *
 * @author Administrator
 * @createDate 2020/9/18
 */
@Service
@Transactional(readOnly=true)
public class EquipRepairService extends CrudService<EquipRepairDao, EquipRepair> {
	
  @Autowired
	private EquipFaultReportService equipFaultReportService;
  
  public void  saveRepair(EquipRepair equipRepair) {
		EquipFaultReport equipFaultReport=equipFaultReportService.get(equipRepair.getMid());
		equipFaultReport.setStatus("0004");
		equipFaultReportService.save(equipFaultReport);
		this.save(equipRepair);
	}
}

