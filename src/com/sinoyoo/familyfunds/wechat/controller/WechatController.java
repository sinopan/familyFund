package com.sinoyoo.familyfunds.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinoyoo.familyfunds.pojo.User;
import com.sinoyoo.familyfunds.result.LoginResult;
import com.sinoyoo.familyfunds.result.OperationResult;
import com.sinoyoo.familyfunds.service.UserService;
import com.sinoyoo.familyfunds.utils.FastJsonUtils;
import com.sinoyoo.familyfunds.utils.PageBean;

@Controller
@RequestMapping("/user")
public class WechatController {

	@Autowired
	private UserService userService;
	
	/**
	 * 以下接口专为微信小程序提供
	 */
	@RequestMapping("/wlogin")
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
	}
}
