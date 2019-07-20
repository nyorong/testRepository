package com.spring.myapp.board.model;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {
	
	//사용할 dataase table 컬럼과 1:1로 매핑되는 필드들을 캡슐화를 통해 프로퍼티화 시킨다.
	//db에서 가져온 데이터를 저장할 보따리
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private int viewCnt;
	
	private int replyCnt;
	
	private boolean newMark; 	// 신규게시물에 new 를띄울지 결정하는 논리필드
	
}
