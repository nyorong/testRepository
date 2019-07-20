package com.spring.myapp.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.PageCreator;

@RestController

@RequestMapping("/rest/test")

public class RestContest {
	
	@RequestMapping("/hello")
	
	public String sayHelloWorld() {
		System.out.println("/rest/test/hello 요청들어옴");
		return "Hello world";
	}
	
	@RequestMapping("/sendvo")
	public RestVO sendVO() {
		RestVO vo = new RestVO();	
		
		vo.setNumber(13);
		vo.setName("hojin");
		vo.setHobbys(Arrays.asList("운동", "음가감상", "독서"));
		
		Map<String, String> skills = new HashMap<String, String>();
		skills.put("언어",  "자바");
		skills.put("프레임워크",  "스프링");
		skills.put("운영체제",  "윈도우10");
		skills.put("DMBS",  "MYSQL");
		
		vo.setSkills(skills);
		
		return vo;
		
	}
	
	@RequestMapping("/page-cre")
	public PageCreator pageCreator() {
		
		PageCreator pc = new PageCreator();
		
		pc.setCriteria(new Criteria());
		
		return pc;
		
	}
	//////////////////////////////////////////////////
	
	//RestController에서 jsp파일열기
	@RequestMapping("/setview")
	public ModelAndView test() {
		/*
		ModelAndView mv = new ModelAndView();
		mv.setViewName("test/setview");
		mv.addObject("name", "홍길동");
		
		return mv;*/
		return new ModelAndView("test/setview", "name", "ㅁㅁㅁㅁ");
	}
	
}
