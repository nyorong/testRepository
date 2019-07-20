package com.spring.myapp.user.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginVO {

	private String userId;
	private String userPw;
	private boolean autoLogin;
	
	
}
