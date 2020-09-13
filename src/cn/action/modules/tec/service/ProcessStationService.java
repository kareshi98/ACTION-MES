package cn.action.modules.tec.service;


import cn.action.common.service.CrudService;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.tec.dao.ProcessStationDao;
import cn.action.modules.tec.entity.ProcessStation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
public class ProcessStationService extends CrudService<ProcessStationDao, ProcessStation>{
    /**
     * 根据工站对象获得对应的工序对象
     * @param workStationInfos
     * @return
     */
    public ProcessStation findProcessStationByStation(WorkStationInfos workStationInfos) {
        ProcessStation processStation=new ProcessStation();
        processStation.setWorkStationInfos(workStationInfos);
        List<ProcessStation> list=this.findList(processStation);
        if(list==null) {
            return null;
        }
        if(list.size()>0) {
            return list.get(0);
        }
        return null;
    }
}
