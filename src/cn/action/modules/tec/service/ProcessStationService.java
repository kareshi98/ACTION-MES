package cn.action.modules.tec.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.tec.dao.ProcessStationDao;
import cn.action.modules.tec.entity.ProcessStation;

@Service
@Transactional(readOnly=true)
public class ProcessStationService extends CrudService<ProcessStationDao, ProcessStation>{

}
