package cn.action.modules.qc.dao;

import cn.action.common.persistence.CrudDao;
import cn.action.modules.qc.entity.Violaction;

import java.util.List;

public interface ViolactionDao extends CrudDao<Violaction>{
    // ��ѯĳ��ĳ��ÿ��Ա����Υ�����
    List<Violaction> findTimes(Violaction violaction);
}
