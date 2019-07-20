package com.spring.myapp.boardtest;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.board.repository.IBoardDAO;
import com.spring.myapp.commons.paging.Criteria;

//주입할 객체 boardDAO의 정보를 아래 사용해서 가져와야함
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/mvc-config.xml"})
public class BoardDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardPagingTest.class);
	
	@Inject
	private IBoardDAO boardDAO;
	
	/*
	@Test
	public void insertTest() throws Exception{
		for(int i=1; i<=3000; i++) {
			BoardVO vo = new BoardVO();
			vo.setTitle(i + "번째 테스트 게시물!!");
			vo.setContent(i + "번째 게시물 내용입니다~~");
			vo.setWriter("user" + i);
			boardDAO.insert(vo);
		}
	}
	*/
	
	
	//게시물 개별 조회 테스트
	/*@Test
	public void selectTest() throws Exception{
		//4번게시물을 조회
		logger.info(boardDAO.getArticle(4).toString() + "\n");
		
	}*/
	
	//게시물 수정 테스트 : vo의 세터를 사용하여 수정내용(글제목, 글내용)을 입력하고
	/*@Test
	public void updateTest() throws Exception{
		BoardVO vo = new BoardVO();
		vo.setBoardNo(3);
		vo.setTitle("hojin");
		vo.setContent("testcontent");
		boardDAO.update(vo);
		logger.info(boardDAO.getArticle(3).toString() + "\n");
			
	}*/
	
	//게시물 삭제 테스트 : 게시글 번호를 통한 삭제 확인
	/*@Test	
	public void deleteTest() throws Exception{
		boardDAO.delete(5);
	}*/
	
	/*@Test
	public void selectAllTest() throws Exception{
		List<BoardVO> articles = boardDAO.getAllArticles();
		for (BoardVO article : articles) {
			logger.info(article.toString());
			
		}
	}
	*/
	
	//페이징 테스트
	@Test
	public void testListPaging() throws Exception{
		
		Criteria c = new Criteria();
		List<BoardVO> articles = boardDAO.listPaging(c);
		
		
		c.setCountPerPage(30);
		
		for(BoardVO article : articles) {
			logger.info(article.toString());
		}
	}
	
	
	
}
