package cn.action.modules.qc.dao;

import cn.action.common.persistence.CrudDao;
import cn.action.modules.qc.entity.Violaction;

import java.util.List;

public interface ViolactionDao extends CrudDao<Violaction>{
    // 查询某年某月每个员工的违规次数
    List<Violaction> findTimes(Violaction violaction);
}
