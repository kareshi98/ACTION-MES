package cn.action.modules.tec.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.action.modules.tec.entity.Flow;
import cn.action.modules.tec.utils.excel.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.persistence.Page;
import cn.action.common.utils.StringUtils;
import cn.action.common.web.BaseController;
import cn.action.modules.tec.entity.Process;
import cn.action.modules.tec.service.ProcessService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping(value="${adminPath}/tec/process")
public class ProcessController extends BaseController{
	@Autowired
	private ProcessService processService;
	
	@ModelAttribute("process")
	public Process get(@RequestParam(required=false) String id) {
		if(StringUtils.isNotBlank(id)) {
			return processService.get(id);
		}
		return new Process();
	}
	//按条件分页查询
	@RequestMapping(value= {"list",""})
	public String list(Process process,HttpServletRequest request,HttpServletResponse response,Model model) {
		Page<Process> page=processService.findPage(new Page<Process>(request, response), process);
		model.addAttribute("page", page);
		return "modules/tec/processList";
	}
	//保存
	@RequestMapping(value="save")
	public String save(Process process,Model model,RedirectAttributes redirectAttributes) {
		processService.save(process);
		this.addMessage(redirectAttributes, "保存工序信息成功！");
		return "redirect:"+adminPath+"/tec/process";
	}
	//保存
	@RequestMapping(value="delete")
	public String delete(Process process,Model model,RedirectAttributes redirectAttributes) {
		processService.delete(process);
		this.addMessage(redirectAttributes, "删除工序信息成功！");
		return "redirect:"+adminPath+"/tec/process";
	}

	@RequestMapping(value="delmore")
	public String delmore(String[] idAr, Process process, Model model, RedirectAttributes redirectAttributes) {
		for(int i=0;i<idAr.length;i++){
			process.setId(idAr[i]);
			processService.delete(process);
		}
		this.addMessage(redirectAttributes, "删除工艺流程成功！");
		return "redirect:"+adminPath+"/tec/process";
	}

	//跳转页面
	@RequestMapping(value="form")
	public String form(Process process,Model model) {
		model.addAttribute("process", process);
		return "modules/tec/processForm";
	}

    @RequestMapping(value="/batchadd")
    @ResponseBody
    public String ajaxUploadExcel(@RequestParam("upfile") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        file = multipartRequest.getFile("upfile");
        System.out.println("file is "+file.getName());
        InputStream in =null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<String>> listob = null;
        try {

            listob= new ExcelUtils().readExcel(in,0).getContentList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listob.size(); i++) {
            List<String> lo = listob.get(i);
            Process process = new Process();
            process.setProCode(String.valueOf(lo.get(1)));
            process.setProName(String.valueOf(lo.get(2)));
            process.setProDesc(String.valueOf(lo.get(3)));
            processService.save(process);
        }
        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
		return "redirect:"+adminPath+"/tec/process";


    }
}
