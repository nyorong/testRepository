package com.spring.myapp.reply.repository;

import java.util.List;

import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.reply.model.ReplyVO;

public interface IReplyDAO {
	
	//댓글 목록 불러오기
	List<ReplyVO> list(int boardNo) throws Exception;
	
	//댓글 쓰기 기능
	void insert(ReplyVO reply) throws Exception;
	
	//댓글 수정 기능
	void update(ReplyVO reply) throws Exception;
	
	//댓글 삭제 기능
	void delete(int replyNo) throws Exception;
	
	//원본 게시물 댓글 전체 삭제
	void deleteAll(int boardNo) throws Exception;
	
	//페이징 처리된 게시글 목록 불러오기 기ㅡㄴㅇ
	//게시글번호과 한페이지에 몇개나 가져올지 정할 criteria
	List<ReplyVO> listPaging(int boardNo, Criteria cri) throws Exception;
	
	//특정 게심ㄹ의 총 댓글 수 불러오기 기능
	int countReplies(int boardNo) throws Exception;

	
}
