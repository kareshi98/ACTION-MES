package cn.action.modules.equip.web;

import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.equip.entity.EquipFaultReport;
import cn.action.modules.equip.entity.EquipRepair;
import cn.action.modules.equip.service.EquipRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 此处填写说明
 *
 * @author Administrator
 * @createDate 2020/9/18
 */
@Controller
@RequestMapping(value="${adminPath}/equip/repair")
public class EquipRepairController  extends BaseController {
  	@Autowired
	private EquipRepairService equipRepairService;
	
	@ModelAttribute("equipRepair")
	public EquipRepair get(@RequestParam(required=false) String id,String mid) {
		if(StringUtils.isNotBlank(id)) {
			return equipRepairService.get(id);
		}
		EquipRepair repair=new EquipRepair();
		repair.setMid(mid);
		return repair;
	}
	@RequestMapping(value="save")
	public String save(MultipartFile file, EquipRepair equipRepair, Model model, RedirectAttributes redirectAttributes)throws IOException {
		   /**
         * 上传图片
         */
        //图片上传成功后，将图片的地址写到数据库
        String filePath = "D:\\EMC_IDEA\\ACTION-MES2\\WebContent\\static\\images";//保存图片的路径
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        //新的文件名字
        String newFileName = UUID.randomUUID()+originalFilename;
         //封装上传文件位置的全路径
        File targetFile = new File(filePath,newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        equipRepair.setFaultImgs(newFileName);
		    equipRepairService.saveRepair(equipRepair);
		    this.addMessage(redirectAttributes,"维修报告添加成功");
		    return "redirect:"+adminPath+"/equip/report/repairList";
	}
	@RequestMapping(value="form")
	public String form(EquipRepair equipRepair, Model model){
	  model.addAttribute("equipRepair",equipRepair);
	 return "modules/equip/maintenanceReportForm";
  }
}
