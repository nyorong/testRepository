package com.spring.myapp.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.spring.myapp.board.controller.BoardController;
import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;
import com.spring.myapp.user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	
	@Autowired
	private IUserService service;
	
	/*
	//회원가입 처리 요청
	//@RequestMapping(value="", method=RequestMethod.POST)
	//위,아래 둘다가능. 아래가 더 최신인데 위에쓰는고도있음
	//public String register(@RequestBody UserVO user) throws Exception{
	@PostMapping("")
	public ModelAndView register() throws Exception{
		
		logger.info("/user/join 요청발생 : Get");
		
		//return "joinSuccess";
		return new ModelAndView("user/join");
	}*/
	   //회원가입 처리요청
//  @RequestMapping(value="", method=RequestMethod.POST)
	
	
  @PostMapping("")
  public String register(@RequestBody UserVO user) throws Exception {
     
     logger.info("/user/ 요청 발생: POST");
     logger.info("Param: " + user);
     service.register(user);
     
     return "joinSuccess";
  }
  
  //회원가입 페이지 열기 요청
  @GetMapping("/join")
  public ModelAndView register() throws Exception {
     logger.info("/user/join 요청 발생: GET");
     return new ModelAndView("user/join");
  }

  //ID중복확인 체크 요청
  @PostMapping("/idCheck")
  public Map<String, Object> confirmId(@RequestBody String userId) throws Exception{
	  System.out.println("중복확인 요청 ID:" + userId);
	  Map<String, Object> data = new HashMap<>();
	  
	  //서비스에 id줄테니까 중복확인해보고 리턴해봐라
	  int result = service.isDuplicateId(userId);
	  if(result == 0) {
		  	System.out.println("아이디 사용가능");
		  data.put("confirm", "OK");
	  }
	  else {
		  System.out.println("아이디 중복");
		  data.put("confirm", "NO");
	  }
	  return data;
  }
	
  //로그인 페이지 요청
  @GetMapping("/login")
  public ModelAndView login() throws Exception{
	  
	  logger.info("/user/login 요청! : GET");;
	  return new ModelAndView("user/login");
  }
	
//로그인 검증 요청
  @PostMapping("/loginCheck")
  public String login(@RequestBody LoginVO login,
		  HttpSession session, HttpServletResponse res) throws Exception {
     
	  //1. 세션객체를 얻는방법 - httpservletrequest객체 사용
	  //세션을 request객체에서 얻어오는거임
	  //request.getSession();
	  
	  //2. HttpSession객체 사용
	  //세션을 직접 얻는것
	  
	  
     /*
      # 클라이언트가 전송한 id값과 pw값을 가지고 DB에서 회원의 정보를
        조회해서 불러온다음 값 비교를 통해
        1. 아이디가 없을 경우 클라이언트측으로 문자열 "idFail" 전송
        2. 비밀번호가 틀렸을 경우 문자열 "pwFail"전송
        3. 로그인 성공시 문자열 "loginSuccess" 전송
      */
     logger.info("/user/loginCheck 요청! : POST");
     logger.info("parameters : " + login);
     
     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
     UserVO user = service.login(login);
     logger.info("로그인 시도 회원정보: " + user);
     
     String result = null;
     
     if(user != null) {
        //아이디 존재
        if(encoder.matches(login.getUserPw(), user.getUserPw())) {
           //비밀번호 일치 -> 로그인 성공
           result = "loginSuccess";
           //세션에 login라는 이름으로 user의 모든 정보를 보관
           session.setAttribute("login", user);
           session.setMaxInactiveInterval(60*60);	//세션만료 1시간으로 증가
           
           //자동로그인 쿠키 생성, 자동로그인되었을때만 쿠키생성하도록
           if(login.isAutoLogin()) {
        	   logger.info("자동로그인 체크함");
        	   long limitTime = 60 * 60 * 24 * 90;
        	   
        	   //현재시간 읽은다음에 제한시간인 3개월(limittime)을 넣음
        	   Date sessionLimit = new Date(System.currentTimeMillis() + (limitTime * 1000));
        	   
        	   //쿠키생성 , 세션의 고유id를 loginCookie에 저장, 고유id란 f12 어플에 있는 value값
        	   Cookie loginCookie = new Cookie("loginCookie", session.getId());
        	   //쿠키 속성 설정
        	   loginCookie.setMaxAge((int)limitTime);
        	   loginCookie.setPath("/");
        	   //생성된 쿠키를 response 객체에 실어서 전송
        	   res.addCookie(loginCookie);
        	   
        	   service.keepLogin(user.getUserId(), session.getId(), sessionLimit);
        	   
        	   
           }
           
           
        } else {
           //비밀번호 틀림
           result = "pwFail";
        }
     } else {
        //아이디 없음
        result = "idFail";
     }
     
     return result;
  }
  
  //로그아웃 요청
  @GetMapping("/logout")
  public ModelAndView logout(HttpServletRequest request,
        HttpServletResponse response,
        HttpSession session) throws Exception {

     Object object = session.getAttribute("login");
     if (object != null) {
        UserVO userVO = (UserVO) object;
        session.removeAttribute("login");
        session.invalidate();
        Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
        if (loginCookie != null) {
           loginCookie.setPath("/");
           loginCookie.setMaxAge(0);
           response.addCookie(loginCookie);
           service.keepLogin(userVO.getUserId(), "none", new Date());
        }
     }

     return new ModelAndView("redirect:/");
  }

  
  
  
}
