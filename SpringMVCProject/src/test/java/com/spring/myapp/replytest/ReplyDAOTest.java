package com.spring.myapp.replytest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.reply.model.ReplyVO;
import com.spring.myapp.reply.repository.IReplyDAO;

//spring에 등록된 bean이 설정된 파일 불러주기
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:/spring/mvc-config.xml"})

public class ReplyDAOTest {
	@Autowired
	private IReplyDAO dao;
	
	/*
	@Test
	public void testReplyInsert() throws Exception{
		
		for (int i=1; i<=300; i++) {

			ReplyVO reply= new ReplyVO();
			reply.setBoardNo(997);
			reply.setReplyText("ㄷㄷㄷㄷㄷㄷㄷㄷ댓글입니다");
			reply.setReplyWriter("hojin");
			
			dao.insert(reply);			
		}
		
		System.out.println("댓글등록 성공");
	}*/
	
	/*
	@Test
	public void replyListTest() throws Exception{
		
		for(ReplyVO vo : dao.list(994)) {
			if(vo.getReplyNo() == 251) break;
			System.out.println(vo);
		}
	}
	*/
	
	/*
	@Test
	public void testReplyModify() throws Exception{
		ReplyVO reply = new ReplyVO();
		reply.setReplyNo(252);
		reply.setReplyText("수정된 댓글입니다.");
		
		dao.update(reply);
		
	}*/
	/*
	@Test
	public void testDeleteTest() throws Exception{
		
		
		dao.delete(255);
		
	}
	*/
	
	//댓글 페이징 테스트
	@Test
	public void pagingTest() throws Exception{
		
		Criteria cri = new Criteria();
		
		cri.setPage(2);
		cri.setCountPerPage(20);
		
		for(ReplyVO reply : dao.listPaging(997, cri)) {
			System.out.println(reply);
		}
		
	}
}
