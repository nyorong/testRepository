package com.spring.myapp.usertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;
import com.spring.myapp.user.repository.IUserDAO;

//spring에 등록된 bean이 설정된 파일 불러주기
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:/spring/mvc-config.xml"})

public class UserDAOTest {

	@Autowired
	private IUserDAO dao;
	
	/*
	@Test
	public void testUserInsert() throws Exception{
		
		UserVO user = new UserVO();
		user.setUserId("hhj");
		user.setUserName("hojin");
		user.setUserPw("0814");
		
		dao.register(user);
		
	}
	*/
	
	/*
	@Test
	public void duplicateTest() throws Exception{
		int n=dao.isDuplicateId("apple");
		if(n == 1)
			System.out.println("아이디 중복");
		else
			System.out.println("아이디 사용가능");
		
	}*/
	
	@Test
	public void loginTest() throws Exception{
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		LoginVO login = new LoginVO();
		login.setUserId("nyorong");
		login.setUserPw("hwangho89!");
		
		UserVO user = dao.login(login);
		System.out.println("로그인 시도 회원정보: " + user);
		
		if(user != null) {
			String dbPw = user.getUserPw();
			System.out.println("DB password: " + dbPw);
			String inputPw = login.getUserPw();
			System.out.println("Input password: " + inputPw);
			
			//일반 문자열 비교로는 암호화된비밀번호 비교가 안됨
			//if(dbPw.contentEquals(inputPw)) {
			if(encoder.matches(inputPw, dbPw)) {
				System.out.println("로그인성공");
			} else {
				System.out.println("비밀번호가 틀렸습니다.");
			}
			
		}else {
			System.out.println("존재하지않는 회원입니다.");
		}
		
	}
	
}
