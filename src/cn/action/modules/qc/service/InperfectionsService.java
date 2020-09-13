package cn.action.modules.qc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.qc.dao.InperfectionsDao;
import cn.action.modules.qc.entity.Inperfections;

@Service
@Transactional(readOnly=true)
public class InperfectionsService extends CrudService<InperfectionsDao, Inperfections>{

}
