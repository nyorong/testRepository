package com.spring.myapp.board.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.SearchCriteria;

@Repository
public class BoardDAO implements IBoardDAO{
	
	
	private final SqlSession sqlSession;
	
	//유지보수 효율을 위해 설정
	private static final String NAMESPACE = "BoardMapper";
	
	
	//mvc-config에서 미리 빈으로 등록해둔걸 주입함, 이거시 스프링. 
	//기존에는 필요할떄마다 new를 써서 넣어줘야했지만 스프링에서는 미리 만들어둔걸 주입시킨다
	@Autowired
	public BoardDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		
	}
	
	@Override
	public void insert(BoardVO article) throws Exception{
		sqlSession.insert(NAMESPACE+".insert", article);
	}

	@Override
	public BoardVO getArticle(int boardNo) throws Exception {
		System.out.println("게시글 번호: " + boardNo);

		return sqlSession.selectOne(NAMESPACE+".getArticle", boardNo);
	}

	@Override
	public void update(BoardVO article) throws Exception {
		System.out.println("업데이트할 게시글 번호: " + article.getBoardNo());
		sqlSession.update(NAMESPACE+".update", article);		
	}

	@Override
	public void delete(int boardNo) throws Exception {
		System.out.println("삭제될 게시글 번호 : " + boardNo);
		sqlSession.delete(NAMESPACE+".delete", boardNo);
	}

	
	@Override
	public List<BoardVO> getAllArticles() throws Exception {
		
		return sqlSession.selectList(NAMESPACE+".getAllArticles");
		
	}

	@Override
	public List<BoardVO> listPaging(Criteria cri) throws Exception {
		
		//page 변수에 1이 저장되어있다면? LIMIT 절의 시작값은 0
		//''	    2가 저장되어있다면? LIMIT 절의 시작값은 10
		//페이지 재계산: (페이지번호 - 1) * 한 페이지당 들어갈 게시물 수
		
		//criteria 내부로 옮겨가ㅣㅆ으므로 주석처리
		//page = (page-1) * 10;	
		
		//mybatis mapper.xml의 listpaging에 cri 전달 
		return sqlSession.selectList(NAMESPACE+".listPaging", cri);
	}

	
	@Override
	public int countArticles() throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+".countArticles");
	}

	@Override
	public int countSearchedArticles(SearchCriteria cri) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+".countSearchedArticles", cri);
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return sqlSession.selectList(NAMESPACE+".listSearch", cri);
	}

	@Override
	public int countSearchArticles(SearchCriteria cri) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".countSearchArticles", cri);
	}

	@Override
	public void updateViewCnt(int boardNo) throws Exception {
	
		sqlSession.update(NAMESPACE+".updateViewCnt", boardNo);
		
	}

	@Override
	public void updateReplyCnt(int boardNo, int count) throws Exception {
		Map<String , Object> datas = new HashMap<>();
		  System.out.println("원본 글 번호: " + boardNo);
		datas.put("boardNo", boardNo);
		datas.put("count", count);
		
		sqlSession.update(NAMESPACE+".updateReplyCnt", datas);
		
	}
	

	
}
