package com.spring.myapp.user.model;

import java.util.Date;

import lombok.Data;


@Data
public class UserVO {
	private String userId;
	private String userPw;
	private String userName;
	private Date userRegDate;

	private String sessionId;
	private Date sessionLimit;
}
