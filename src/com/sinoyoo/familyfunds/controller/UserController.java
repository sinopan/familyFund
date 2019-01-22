package com.sinoyoo.familyfunds.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinoyoo.familyfunds.pojo.User;
import com.sinoyoo.familyfunds.pojo.UserExample;
import com.sinoyoo.familyfunds.pojo.UserExample.Criteria;
import com.sinoyoo.familyfunds.result.LoginResult;
import com.sinoyoo.familyfunds.result.OperationResult;
import com.sinoyoo.familyfunds.service.UserService;
import com.sinoyoo.familyfunds.utils.DateUtils;
import com.sinoyoo.familyfunds.utils.FastJsonUtils;
import com.sinoyoo.familyfunds.utils.PageBean;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/regist")
	public String regist(){
		
		return "regist";
	}
	
	/**
	 * 注册成功页面，点击返回首页
	 * @return
	 */
	@RequestMapping("/regist_success")
	public String registSuccess(){
		
		return "regist_success";
	}
	
	@RequestMapping("/index")//重定向到首页
	public String index(){
		
		return "redirect:/index.jsp";
	}
	
	/**
	 * ajax请求：用户名输入框失去焦点时判断用户名是否被注册
	 * @param name
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/check")
	@ResponseBody
	public void checkName(@RequestParam("name") String name, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		
		if (name!=null && name.length()>0) {
			criteria.andNameLike(name);
		}
		
		List<User> list = userService.selectByExample(example);
		if (list!=null && list.size()>0) {
			out.println("The user name has been registered!");
		}else {
			out.println("");
		}
		out.flush();
		out.close();
	}
	
	/**
	 * Ajax添加用户
	 * @param name
	 * @param password
	 * @param gender
	 * @param bithday
	 * @param age
	 * @param mobile
	 * @param response 响应Ajax请求
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/add")
	@ResponseBody
	public void registUser(
			@RequestParam("name") String name, 
			@RequestParam("password") String password, 
			@RequestParam("gender") String gender, 
			@RequestParam("bithday") String bithday, 
			@RequestParam("age") String age, 
			@RequestParam("mobile") String mobile,
			HttpServletResponse response) throws IOException{
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		User addUser = new User();
		addUser.setName(name);
		addUser.setPassword(password);
		addUser.setGender(Integer.parseInt(gender));
		try {
			addUser.setBithday(sdf.parse(bithday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		addUser.setAge(Integer.parseInt(age));
		addUser.setMobile(mobile);
		//如果添加成功，resultUser不为null
		User resultUser = null;
		try {
			resultUser = userService.addUser(addUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		OperationResult<User> operationResult = null;
		if (resultUser!=null) {
			operationResult = new OperationResult<User>(200, true, resultUser);
		} else {
			operationResult = new OperationResult<User>(500, false, resultUser);
		}
		
		out.print(FastJsonUtils.BeanToJson(operationResult));
		out.flush();
		out.close();
	}
	
	/**
	 * 登录功能
	 * @param user 接受pojo
	 * @param session 存放用户
	 * @param model 
	 * @return 登陆失败后转发（重定向拿不到）；成功后重定向到欢迎页（这样可以避免用户刷新页面时再次提交登录请求）
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(User user, HttpSession session, Model model) throws Exception{
		
		User existUser = userService.login(user.getName(), user.getPassword());
		if (existUser != null) {
			session.setAttribute("user", existUser);//放入session域
			session.setMaxInactiveInterval(900);//5分钟失效
			return "redirect:/user/welcome";
		}
		model.addAttribute("failedMsg", "用户名或密码错误！");
		model.addAttribute("name", user.getName());
		System.out.println(123);
		return "forward:/index.jsp";
	}
	
	/**
	 * 重定向防止了登陆后，用户刷新重复登陆
	 * @return
	 */
	@RequestMapping("/welcome")
	public String welcome(){
		return "/welcome";
	}
	
	/**
	 * 登出功能(重定向到webContent根目录下)
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/index.jsp";
	}
	
	
	@RequestMapping("/list/{currPage}")
	public String showUserList(@PathVariable("currPage") String currPage, Model model){
		
		PageBean<User> pagebean = null;
		try {
			pagebean = userService.getPageBean(Integer.parseInt(currPage), 5);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("pagebean", pagebean);
		
		return "user_list";
		
	}
	
	@RequestMapping("ajax_list")
	@ResponseBody
	public void getUserListAjax(HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//获取所有用户
		List<User> list = userService.selectByExample(new UserExample());
		
		out.print(FastJsonUtils.ArrayToJson((ArrayList<User>) list));
		out.flush();
		out.close();
		
	}
	
	@RequestMapping("/modify_page/{id}")
	public String toModifyMemberPage(@PathVariable("id") String id, Model model) throws Exception{
		//从数据库中查询
		User user = userService.getUserByPrimaryKey(id);
		model.addAttribute("user", user);
		
		return "user_modify";
	}
	
	@RequestMapping("/modify")
	public String modifyUser(User upateUser,Model model) throws Exception{
		Integer i = userService.modifyUser(upateUser);
		if (i==0) {
			model.addAttribute("msg", "修改失败");
		}
		model.addAttribute("msg", "修改成功");
		model.addAttribute("modifyFlag", "modifyopration");
		return "forward:/user/list/1";
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 以下接口专为微信小程序提供
	 */
	/*@RequestMapping("/wlogin")
	@ResponseBody
	public void wechat_login(@RequestParam("username") String username,
							@RequestParam("password") String password, 
							HttpSession session,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		User user = new User();
		user.setName(new String(username.getBytes("iso-8859-1"),"utf-8"));
		user.setPassword(password);
		
		LoginResult loginResult = new LoginResult(false, null, null);
		
		User existUser = userService.login(user.getName(), user.getPassword());
		if (existUser != null) {
			session.setAttribute("user", existUser);//放入session域
			session.setMaxInactiveInterval(300);//5分钟失效
			loginResult = new LoginResult(true, existUser, session.getId());
		}
		out.println(FastJsonUtils.BeanToJson(loginResult));
		
		out.flush();
		out.close();
	}
	
	//页面暂时为实现分页，后期再说
	@RequestMapping("/wlist/{currPage}")
	@ResponseBody
	public void getUserList(@PathVariable("currPage") String currPage,
							HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		PageBean<User> pagebean = null;
		try {
			pagebean = userService.getPageBean(Integer.parseInt(currPage), 15);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		out.println(FastJsonUtils.ArrayToJson((ArrayList<User>) pagebean.getList()));
		
		out.flush();
		out.close();
		
	}
	
	@RequestMapping("/wRegist")
	@ResponseBody
	public void addUser(
			@RequestParam("username") String name, 
			@RequestParam("password") String password, 
			@RequestParam("gender") String gender, 
			@RequestParam("bithday") String bithday, 
			@RequestParam("age") String age, 
			@RequestParam("mobile") String mobile,
			HttpServletResponse response) throws IOException{
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		User addUser = new User();
		addUser.setName(new String(name.getBytes("iso-8859-1"),"utf-8"));
		addUser.setPassword(password);
		addUser.setGender(Integer.parseInt(gender));
		try {
			addUser.setBithday(sdf.parse(bithday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		addUser.setAge(Integer.parseInt(age));
		addUser.setMobile(mobile);
		//如果添加成功，resultUser不为null
		User resultUser = null;
		try {
			resultUser = userService.addUser(addUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		OperationResult<User> operationResult = null;
		if (resultUser!=null) {
			operationResult = new OperationResult<User>(200, true, resultUser);
		} else {
			operationResult = new OperationResult<User>(500, false, resultUser);
		}
		
		out.print(FastJsonUtils.BeanToJson(operationResult));
		out.flush();
		out.close();
	}*/
	
}
