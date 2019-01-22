package com.sinoyoo.familyfunds.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		//如果是登录页面则放行
		if(request.getRequestURI().indexOf("index.jsp")>=0
				|| request.getRequestURI().indexOf("user/regist")>=0){
			
				return true;
		}
		//如果是校验用户名是否被注册放行
		if(request.getRequestURI().indexOf("/user/check")>=0 ){
			
			return true;
		}
		//如果用户添加放行
		if(request.getRequestURI().indexOf("/user/add")>=0 ){
			
			return true;
		}
		//如果是登陆操作放行
		if(request.getRequestURI().indexOf("/user/login")>=0){
			
			String name = request.getParameter("name");
			if (name != null && !"".equalsIgnoreCase(name)) {
				return true;
			}
		}
		/**
		 * 小程序登陆
		 */
		if(request.getRequestURI().indexOf("/user/wlogin")>=0){
			return true;
		}
		if(request.getRequestURI().indexOf("/user/w")>=0){
			return true;
		}
		HttpSession session = request.getSession();
		//如果用户已登录也放行
		if(session.getAttribute("user")!=null){
			return true;
		}
		//用户没有登录跳转到登录页面
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
		return false;
	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}