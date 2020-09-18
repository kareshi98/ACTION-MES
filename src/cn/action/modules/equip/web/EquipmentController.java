package cn.action.modules.equip.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.action.common.persistence.Page;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.equip.entity.Equipment;
import cn.action.modules.equip.service.EquipmentService;

@Controller
@RequestMapping(value="${adminPath}/equip/equip")
public class EquipmentController extends BaseController{
	@Autowired
	private EquipmentService equipmentService;
	
	@ModelAttribute("equipment")
	public Equipment get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return equipmentService.get(id);
		}
		return new Equipment();
	}
	//按多条件分页查询
	@RequestMapping(value= {"list",""})
	public String list(Equipment equipment,HttpServletResponse response,HttpServletRequest request,Model model) {
		Page<Equipment> page=equipmentService.findPage(new Page<Equipment>(request, response), equipment);
		model.addAttribute("page",page);
		return "modules/equip/equipList";
	}
}
