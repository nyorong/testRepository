package com.spring.myapp.reply.service;

import java.util.List;

import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.reply.model.ReplyVO;

public interface IReplyService {
	
			//댓글 목록 불러오기
			List<ReplyVO> list(int boardNo) throws Exception;
			
			//댓글 쓰기 기능
			void insert(ReplyVO reply) throws Exception;
			
			//댓글 수정 기능
			void update(ReplyVO reply) throws Exception;
			
			//댓글 삭제 기능
			void delete(int replyNo, int boardNo) throws Exception;
			
			List<ReplyVO> listPaging(int boardNo, Criteria cri) throws Exception;
			
			int countReplies(int boardNo) throws Exception;
	
}
