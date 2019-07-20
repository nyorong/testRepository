package com.spring.myapp.reply.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.reply.model.ReplyVO;

@Repository
public class ReplyDAO implements IReplyDAO {

	private static final String NAMESPACE = "ReplyMapper";
	
	@Autowired
	private SqlSession sess;
	
	@Override
	public List<ReplyVO> list(int boardNo) throws Exception {

		return sess.selectList(NAMESPACE+".list", boardNo);
	}

	@Override
	public void insert(ReplyVO reply) throws Exception {
		sess.insert(NAMESPACE+".insert", reply);

	}

	@Override
	public void update(ReplyVO reply) throws Exception {
		sess.update(NAMESPACE+".update", reply);
	}

	@Override
	public void delete(int replyNo) throws Exception {
		sess.delete(NAMESPACE+".delete", replyNo);
	}

	
	
	@Override
	public List<ReplyVO> listPaging(int boardNo, Criteria cri) throws Exception {
		
		//mapper쪽으로 두개(boardNo, cri)를 보낼수가없음
		//그래서 map을 사용해서 보냄
		//mapper.xml에 여러 데이터를 보낼때는 Map데이터로 포장해서 전달한다.
		
		Map<String, Object> datas = new HashMap<>();
		datas.put("boardNo",  boardNo);
		datas.put("criteria",  cri);
		
		//return sess.selectList(NAMESPACE+".listPaging", boardNo, cri);
		return sess.selectList(NAMESPACE+".listPaging", datas);
	}

	@Override
	public int countReplies(int boardNo) throws Exception {
		
		return sess.selectOne(NAMESPACE+".countReplies", boardNo);
	}

	@Override
	public void deleteAll(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
