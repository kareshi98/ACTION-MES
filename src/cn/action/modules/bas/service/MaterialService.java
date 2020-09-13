package cn.action.modules.bas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.MaterialDao;
import cn.action.modules.bas.entity.Material;

@Service
@Transactional(readOnly=true)
public class MaterialService extends CrudService<MaterialDao, Material>{

}
