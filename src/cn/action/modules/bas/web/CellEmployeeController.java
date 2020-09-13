package cn.action.modules.bas.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.persistence.Page;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.bas.entity.CellEmployee;
import cn.action.modules.bas.entity.Employee;
import cn.action.modules.bas.entity.WorkCell;
import cn.action.modules.bas.service.CellEmployeeService;
import cn.action.modules.bas.service.EmployeeService;
import cn.action.modules.bas.service.WorkCellService;
@Controller
@RequestMapping(value="${adminPath}/bas/cellEmployee")
public class CellEmployeeController extends BaseController{
	@Autowired
	private CellEmployeeService cellEmployeeService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private WorkCellService workCellService;
	
	@ModelAttribute("cellEmployee")
	public CellEmployee get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return cellEmployeeService.get(id);
		}
		return new CellEmployee();
	}
	
	//��ҳ��ѯ
	@RequestMapping(value= {"list",""})
	public String list(CellEmployee cellEmployee,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<CellEmployee> page=cellEmployeeService.findPage(new Page<CellEmployee>(request,response), cellEmployee);
		model.addAttribute("page", page);
		return "modules/bas/cellEmployeeList";
	}
	//����
	@RequestMapping(value="save")
	public String save(CellEmployee cellEmployee,Model model,RedirectAttributes redirectAttributes) {
		String message=null;
		String url=null;
		CellEmployee relation=cellEmployeeService.saveRelation(cellEmployee);
		if(relation==null) {
			message="���湤λ��Ա����ϵ�ɹ���";
			url="redirect:"+adminPath+"/bas/cellEmployee";
		}else {
			message="һ��Ա��ֻ�ܶ�Ӧһ����λ��"+relation.getEmployee().getEmployeeName()+"�Ѿ���"+relation.getWorkCell().getCellName();
			url="redirect:"+adminPath+"/bas/cellEmployee/form";
		}
		this.addMessage(redirectAttributes, message);
		return url;		
	}
	//ɾ��
	@RequestMapping(value="delete")
	public String delete(CellEmployee cellEmployee,RedirectAttributes redirectAttributes) {
		cellEmployeeService.delete(cellEmployee);
		this.addMessage(redirectAttributes, "ɾ����λ��Ա����ϵ�ɹ���");
		return "redirect:"+adminPath+"/bas/cellEmployee";
	}
	//��תҳ��
	@RequestMapping(value="form")
	public String form(CellEmployee cellEmployee,Model model) {
		//�������Ա��
		List<Employee> employees=employeeService.findAllList(new Employee());
		//������й�λ
		List<WorkCell> cells=workCellService.findAllList(new WorkCell());
		model.addAttribute("employeeList", employees);
		model.addAttribute("workCellList", cells);
		model.addAttribute("cellEmployee", cellEmployee);
		return "modules/bas/cellEmployeeForm";
	}
}