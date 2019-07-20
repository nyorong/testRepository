package com.spring.myapp.boardtest;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.myapp.board.repository.IBoardDAO;
import com.spring.myapp.commons.paging.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/mvc-config.xml"})

public class BoardPagingTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Inject
	private IBoardDAO boardDAO;
	
	/*
	 #1. 페이지 화면 모습
	 - 한 화면에서 페이지를 10개씩 보여줘야 한다면??
	 ex) 1 2 3 4 5 ... 9 10 [다음] 	//	[이전] 31 32 ... 39 40 [다음]
	 이전버튼은 비활성화
	 - 만약 총 게시물이 67개라면?
	 ex) 1 2 3 4 .... 7
	 - 총 게시물이 142개이고 현재 12페이지를 보고있다면?
	 ex) [이전] 11 12 13 14 15
	 
	 #2. 우선 총 게시물의 수를 알아야한다.
	 - 총 게시물 수를 DB로부터 조회하는 로직 작성
	 	  
	 # 3. 사용자가 현재 보고있는 페이지 기준 끝페이지 계산하기 (끝 페이지 숫자구하기 공식)
	 - 만약 현재 보고있는 페이지가 3번 한번에 보여줄페이지가 10페이지라면
	  -> 끝 페이지 번호는 10번이다.
	 - 현재 페이지가 37페이지고 한번에 보여줄 페이지가 20개라면
	  -> 끝 페이지 번호는 40
	  
	  -공식 : Math.ceil() : 올림 메서드
	  Math.ceil(현재 페이지번호 / 한 페이지당 보여줄 페이지 수) * 한 페이지당 보여줄 페이지 수;
	  
	  # 4. 시작 페이지 번호를 계산하기
	  - 현재 보고있는 페이지가 15번이고 한번에 보여줄 페이지가 10개라면??
	  -> 시작페이지번호 : 11, 끝페이지번호 : 20
	  
	  - 현재 보고있는 페이지가 73번이고 한번에 보여줄 페이지가 30개라면?
	  -> 시작페이지번호 : 61 , 끝페이지 번호 : 90
	  
	  - 공식: (끝 페이지 번호 - 한번에 보여주 페이지 수) + 1
	  
	  # 5. 시작페이지번호를 구해낸 후 끝 페이지값 보정
	  - 만약 총 게시물이 138개이고 한 화면당 10페이지를 보여준ㄷ면
	  현재 11페이지ㅣ를 보고있다면 실제 끝 페이지는  20번이아니라 14번이 나와야한다.
	  - 하지만 위 3번공식을 적용하면 끝 페이지는 20으로 계산되므로 20이라는 숫자는 
	  시작페이지를 구하는 로직에서만 활용하고 다시 14로 재보정해야한다.
	 
	  - 재보정 공식: math.ceil(총 게시물 수 / 한 패아자에 보여줄 게시물 수);
	  
	  
	 */
	
	@Test
	public void pageTest() throws Exception{
		//총 게시물 수 구하는 테스트
		logger.info("총 게시물수 : " + boardDAO.countArticles() + "개\n");
		
		//끝페이지 번호 구하가ㅣ 테스트
		Criteria c = new Criteria();
		c.setPage(37);
		int displayPage = 10;
		
		int endPage = (int)(Math.ceil(c.getPage() / (double)displayPage) * displayPage);
		logger.info(" 끗 페이지 번호: " + endPage);
		
		
		//시작페이지 번호 구하는 테스트
		c.setPage(1);
		displayPage = 10;
		endPage = (int)(Math.ceil(c.getPage() / (double)displayPage) * displayPage);
		
		int beginPage = (endPage - displayPage) + 1;
		logger.info("시작 페이지 번호 : " + beginPage);
		logger.info(" 끝 페이지 번호 : " + endPage);
		
		//끝 페이지 보정
		int totalCnt = 57;
		endPage = (int)Math.ceil(totalCnt / (double)c.getCountPerPage());
		logger.info(" 보정 후 끝 페이지 번호 : " + endPage);
	
	}
}
