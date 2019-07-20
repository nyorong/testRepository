package com.spring.myapp.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		
		System.out.println("prehandle() 호출됨!");
		return true; // true -> 인터셉터 통과
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView mv) throws Exception {

		System.out.println("postHandle() 호출됨!");
		
		//인터셉터가 가로챘다면 모델 내부를 확인하기위해 사용
		Object data = mv.getModel().get("data");
		
		if(data != null) {
			request.getSession().setAttribute("data", data);
			response.sendRedirect("/myapp/inter/test1");
		}
		
	}
	
}
