package com.sinoyoo.familyfunds.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		//����ǵ�¼ҳ�������
		if(request.getRequestURI().indexOf("index.jsp")>=0
				|| request.getRequestURI().indexOf("user/regist")>=0){
			
				return true;
		}
		//�����У���û����Ƿ�ע�����
		if(request.getRequestURI().indexOf("/user/check")>=0 ){
			
			return true;
		}
		//����û���ӷ���
		if(request.getRequestURI().indexOf("/user/add")>=0 ){
			
			return true;
		}
		//����ǵ�½��������
		if(request.getRequestURI().indexOf("/user/login")>=0){
			
			String name = request.getParameter("name");
			if (name != null && !"".equalsIgnoreCase(name)) {
				return true;
			}
		}
		/**
		 * С�����½
		 */
		if(request.getRequestURI().indexOf("/user/wlogin")>=0){
			return true;
		}
		if(request.getRequestURI().indexOf("/user/w")>=0){
			return true;
		}
		HttpSession session = request.getSession();
		//����û��ѵ�¼Ҳ����
		if(session.getAttribute("user")!=null){
			return true;
		}
		//�û�û�е�¼��ת����¼ҳ��
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