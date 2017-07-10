package cn.edu.guet.lym.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.edu.guet.lym.domain.Employee;

public class UserContext {
	public final static String employee_session="employee_session";
	public static HttpSession getSession() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		return request.getSession();
	}
	
	public static void setCurrentUser(Employee employee) {
		getSession().setAttribute(employee_session, employee);
	}
	
	public static Employee getCurrentUser(){
		return (Employee) getSession().getAttribute(employee_session);
	}
}
