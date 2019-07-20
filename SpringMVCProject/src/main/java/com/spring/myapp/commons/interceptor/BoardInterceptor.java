package com.spring.myapp.commons.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BoardInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("게시판 인터셉트 발동");
		//글쓰기나 수정같은거 하기전에 로그인체크
		//즉 컨트롤러에 들어가기전에 점검, login 없으면  되돌아감
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			//간단하게 자바스크립트 하나 쓰려고 굳이 페이지 더 쓸ㅣㄹ요없으니까 아래처럼씀
			out.print("<script>alert('ㄹ그인을 해야해요!'); location.href='/myapp/user/login';</script>");
			out.flush();//출력버퍼 비움
			out.close();
			
			return false;
		}
		
		return true;
	}
	
}
