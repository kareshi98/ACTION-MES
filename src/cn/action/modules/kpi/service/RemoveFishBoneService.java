package cn.action.modules.kpi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.kpi.dao.RemoveFishBoneDao;
import cn.action.modules.kpi.entity.RemoveFishBone;

@Service
@Transactional(readOnly=true)
public class RemoveFishBoneService extends CrudService<RemoveFishBoneDao, RemoveFishBone>{

}
