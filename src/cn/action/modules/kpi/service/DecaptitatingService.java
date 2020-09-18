package cn.action.modules.kpi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.kpi.dao.DecaptitatingDao;
import cn.action.modules.kpi.entity.Decaptitating;

@Service
@Transactional(readOnly=true)
public class DecaptitatingService extends CrudService<DecaptitatingDao, Decaptitating>{

}
