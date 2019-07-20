package com.spring.myapp.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myapp.board.repository.IBoardDAO;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.reply.model.ReplyVO;
import com.spring.myapp.reply.repository.IReplyDAO;

@Service
public class ReplyService implements IReplyService {
   
   @Autowired
   private IReplyDAO replydao;
   
   @Autowired
   private IBoardDAO boardDao;

   @Override
   public List<ReplyVO> list(int boardNo) throws Exception {
      return replydao.list(boardNo);
   }

   @Override
   public void insert(ReplyVO reply) throws Exception {
      replydao.insert(reply);
      boardDao.updateReplyCnt(reply.getBoardNo(), 1);
   }

   @Override
   public void update(ReplyVO reply) throws Exception {
      replydao.update(reply);
   }

   @Transactional
   @Override
   public void delete(int replyNo, int boardNo) throws Exception {
	   replydao.delete(replyNo);
	   boardDao.updateReplyCnt(boardNo, -1);
      
   }

   	@Override
	public List<ReplyVO> listPaging(int boardNo, Criteria cri) throws Exception {
   		return replydao.listPaging(boardNo, cri);
}

   	@Override
   	public int countReplies(int boardNo) throws Exception {
   		return replydao.countReplies(boardNo);
   	}

}