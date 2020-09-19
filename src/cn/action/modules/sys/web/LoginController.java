package cn.action.modules.sys.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.action.common.utils.*;
import cn.action.modules.sys.utils.RandomValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;

import cn.action.common.config.Global;
import cn.action.common.web.BaseController;
import cn.action.modules.sys.entity.Menu;
import cn.action.modules.sys.entity.User;
import cn.action.modules.sys.service.SystemService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController extends BaseController{


	@Autowired
	private SystemService systemService;
	String code = null;


	/**
	 * 管理登录
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
	public String login(HttpSession session, HttpServletResponse response, Model model) {

		// 如果已登录，再次访问主页，则退出原账号。
		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))){
			CookieUtils.setCookie(response, "LOGINED", "false");
		}
		User user =  (User)session.getAttribute(UserUtils.CURRENT_USER);
		// 如果已经登录，则跳转到管理首页
		if(user != null){
			return "redirect:" + adminPath;
		}
		return "modules/sys/sysLogin";
	}

	/**
	 * 获取生成验证码显示到 UI 界面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="${adminPath}/checkCode")
	public void checkCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置相应类型,告诉浏览器输出的内容为图片
		response.setContentType("image/jpeg");

		//设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);

		RandomValidateCode randomValidateCode = new RandomValidateCode();
		try {
			code = randomValidateCode.getRandcode(request, response);//输出图片方法
			System.out.println("code of right:"+code);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
	public String login(HttpSession session,String account,String pwd,Model model,HttpServletRequest request ,@RequestParam String inputcode){

		User user = systemService.login(account, pwd);

		System.out.println("code of user inputted:"+inputcode);
		if ((user!=null)&&(code.equalsIgnoreCase(inputcode))) {
			user.setRoleList(UserUtils.getRoleList());
			session.setAttribute(UserUtils.CURRENT_USER, user);
			//获取用户的菜单
			List<Menu> menus = systemService.getMenuByUserId(user.getId());
			session.setAttribute(UserUtils.MENU_LIST, menus);

			//if(code.equalsIgnoreCase(inputcode))
			//	{
			return "modules/sys/sysIndex";

			//addMessage(model,"验证码错误！");
			//return "redirect:" + adminPath;
			//return "modules/sys/sysIndex";
			//	}

			//addMessage(model, "验证码错误！");
			//return "modules/sys/sysIndex"
		}

		//addMessage(model, "用户名或者密码错误，请重新输入！");
		System.out.println("账户信息输入或者验证码错误！");
		return "redirect:" + adminPath;




	}


	/**
	 * 登录成功，进入管理首页
	 */

	@RequestMapping(value = "${adminPath}")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		User principal = UserUtils.getUser();

		// 登录成功后，验证码计算器清零
		//isValidateCodeLogin(principal.getLoginName(), false, true);
		if(principal!=null && principal.getId()==null) {
			return "redirect:" + adminPath + "/login";
		}
		//return "modules/sys/sysIndex";
		return null;
	}



	/**
	 * 获取主题方案
	 */
	@RequestMapping(value = "/theme/{theme}")
	public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request, HttpServletResponse response){
		if (StringUtils.isNotBlank(theme)){
			CookieUtils.setCookie(response, "theme", theme);
		}else{
			theme = CookieUtils.getCookie(request, "theme");
		}
		return "redirect:"+request.getParameter("url");
	}

	/**
	 * 是否是验证码登录
	 * @param useruame 用户名
	 * @param isFail 计数加1
	 * @param clean 计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){
		Map<String, Integer> loginFailMap = (Map<String, Integer>)EhCacheUtils.get("loginFailMap");
		if (loginFailMap==null){
			loginFailMap = Maps.newHashMap();
			EhCacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum==null){
			loginFailNum = 0;
		}
		if (isFail){
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean){
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}



	@RequestMapping(value = "${adminPath}/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute(UserUtils.CURRENT_USER);
		return "redirect:" + adminPath;
	}
}
