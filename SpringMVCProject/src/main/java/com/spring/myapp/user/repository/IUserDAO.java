package com.spring.myapp.user.repository;

import java.util.Map;

import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;

public interface IUserDAO {

	//회원가입 처리
	void register(UserVO user) throws Exception;
	
	//아이디 중복확인 처리
	int isDuplicateId(String userId) throws Exception;
	
	//로그인 시도 회원정보 조회
	//일반객체로 login에 실어서 보내도되지만 자동로그인여유를위해 로그인 전용 vo생성
	UserVO login(LoginVO login) throws Exception;
	
	//자동로그인 유지 처리
	void keepLogin(Map<String, Object> datas) throws Exception;
	
	//세션아이디 검증 후 회원정보 불러오기
	UserVO getUserWithSessionId(String sessionId) throws Exception;
	
}
