package cn.action.modules.bas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.WorkStationInfosDao;
import cn.action.modules.bas.entity.WorkStationInfos;

@Service
@Transactional(readOnly=true)
public class WorkStationInfosService extends CrudService<WorkStationInfosDao, WorkStationInfos>{

}
