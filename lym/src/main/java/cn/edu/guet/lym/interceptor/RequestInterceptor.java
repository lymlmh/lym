package cn.edu.guet.lym.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class RequestInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger=LoggerFactory.getLogger(RequestInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("拦截器获取请求uri:"+request.getRequestURI());
		if(handler instanceof HandlerMethod){
			//HandlerMethod hl=(HandlerMethod)handler;
		}
		return super.preHandle(request, response, handler);
	}
	
}
