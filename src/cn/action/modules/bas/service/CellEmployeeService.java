package cn.action.modules.bas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.CellEmployeeDao;
import cn.action.modules.bas.entity.CellEmployee;

@Service
@Transactional(readOnly=true)
public class CellEmployeeService extends CrudService<CellEmployeeDao, CellEmployee>{
	@Autowired
	private CellEmployeeDao cellEmployeeDao;
	/**
	 * 保存
	 * @param cellEmployee
	 * @return
	 */
	public CellEmployee saveRelation(CellEmployee cellEmployee) {
		//判断是否是添加，是添加就需要判断员工是否已经绑定工位了，其他正常
		if("".equals(cellEmployee.getId())) {
			//根据员工id判断是否绑定工位，如果绑定了，就返回关系对象
			CellEmployee relation=cellEmployeeDao.findByEmployeeId(cellEmployee);
			if(relation!=null) {
				//说明该员工已经绑定工位
				return relation;
			}
		}
		this.save(cellEmployee);
		return null;
	}
}
