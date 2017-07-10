package cn.edu.guet.lym.controller;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
	public final String employee_session="employee_session";
	
	public void setSession(HttpServletRequest request,Object object){
		request.getSession().setAttribute(employee_session, object);
	}
	public Object getSession(HttpServletRequest request,String key){
		return request.getSession().getAttribute(key);
	}
}
