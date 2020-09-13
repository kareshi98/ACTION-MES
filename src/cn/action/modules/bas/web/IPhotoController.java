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
import cn.action.modules.bas.entity.IPhoto;
import cn.action.modules.bas.entity.WorkCell;
import cn.action.modules.bas.entity.WorkStationInfos;
import cn.action.modules.bas.service.IPhotoSevice;
import cn.action.modules.bas.service.WorkCellService;
import cn.action.modules.bas.service.WorkStationInfosService;

@Controller
@RequestMapping(value="${adminPath}/bas/iPhoto")
public class IPhotoController extends BaseController{
	@Autowired
	private IPhotoSevice iPhotoSevice;
	@Autowired
	private WorkCellService workCellService;
	@Autowired
	private WorkStationInfosService workStationInfosService;
	
	@ModelAttribute("iPhoto")
	public IPhoto get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return iPhotoSevice.get(id);
		}
		return new IPhoto();
	}
	//查询
	@RequestMapping(value= {"list",""})
	public String list(IPhoto iPhoto,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<IPhoto> page=iPhotoSevice.findPage(new Page<IPhoto>(request, response), iPhoto);
		model.addAttribute("page", page);
		return "modules/bas/iPhotoList";
	}
	//保存
	@RequestMapping(value="save")
	public String save(IPhoto iPhoto,Model model,RedirectAttributes redirectAttributes) {
		iPhotoSevice.save(iPhoto);
		this.addMessage(redirectAttributes, "保存红外对射枪设备成功！");
		return "redirect:"+adminPath+"/bas/iPhoto";
	}
	//删除
	@RequestMapping(value="delete")
	public String delete(IPhoto iPhoto,Model model,RedirectAttributes redirectAttributes) {
		iPhotoSevice.delete(iPhoto);
		this.addMessage(redirectAttributes, "删除红外对射枪设备成功！");
		return "redirect:"+adminPath+"/bas/iPhoto";
	}
	//跳转
	@RequestMapping(value="form")
	public String form(IPhoto iPhoto,Model model) {
		//获得所有工位
		List<WorkCell> workCellList=workCellService.findAllList(new WorkCell());
		//获得所有工站
		List<WorkStationInfos> workStationInfosList=workStationInfosService.findAllList(new WorkStationInfos());
		
		model.addAttribute("workCellList", workCellList);
		model.addAttribute("workStationInfosList", workStationInfosList);
		//model.addAttribute("iPhoto", iPhoto);
		return "modules/bas/iPhotoForm";
	}
}
