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
import cn.action.modules.bas.entity.ElectronSteelYard;
import cn.action.modules.bas.entity.WorkCell;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.ElectronSteelYardService;
import cn.action.modules.bas.service.WorkCellService;
import cn.action.modules.bas.service.WorkStationInfosService;

@Controller
@RequestMapping(value="${adminPath}/bas/electronSteelYard")
public class ElectronSteelYardController extends BaseController{
	@Autowired
	private ElectronSteelYardService electronSteelYardService;
	@Autowired
	private WorkCellService workCellService;
	@Autowired
	private WorkStationInfosService workStationInfosService;
	
	@ModelAttribute("electronSteelYard")
	public ElectronSteelYard get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return electronSteelYardService.get(id);
		}
		return new ElectronSteelYard();
	}
	//多条件查询并分页
	@RequestMapping(value= {"list",""})
	public String list(ElectronSteelYard electronSteelYard,HttpServletRequest request,HttpServletResponse respone,Model model) {
		Page<ElectronSteelYard> page=electronSteelYardService.findPage(new Page<ElectronSteelYard>(request,respone), electronSteelYard);
		model.addAttribute("page", page);
		return "modules/bas/electronSteelYardList";
	}
	//保存
	@RequestMapping(value= "save")
	public String save(ElectronSteelYard electronSteelYard,Model model,RedirectAttributes redirectAttributes) {
		electronSteelYardService.save(electronSteelYard);
		this.addMessage(redirectAttributes, "保存电子秤设备成功！");
		return "redirect:"+adminPath+"/bas/electronSteelYard";
	}
	//删除
	@RequestMapping(value= "delete")
	public String delete(ElectronSteelYard electronSteelYard,Model model,RedirectAttributes redirectAttributes) {
		electronSteelYardService.delete(electronSteelYard);
		this.addMessage(redirectAttributes, "删除电子秤设备成功！");
		return "redirect:"+adminPath+"/bas/electronSteelYard";
	}
	//跳转
	@RequestMapping(value= "form")
	public String form(ElectronSteelYard electronSteelYard,Model model) {
		//获得所有工位
		List<WorkCell> workCellList=workCellService.findAllList(new WorkCell());
		//获得所有工站
		List<WorkStationInfos> workStationInfosList=workStationInfosService.findAllList(new WorkStationInfos());
		model.addAttribute("workCellList", workCellList);
		model.addAttribute("workStationInfosList", workStationInfosList);
		model.addAttribute("electronSteelYard",electronSteelYard);
		return "modules/bas/electronSteelYardForm";
	}
}
