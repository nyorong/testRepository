package com.spring.myapp.board.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.board.repository.IBoardDAO;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.SearchCriteria;

@Service
public class BoardService implements IBoardService {

	@Autowired
	private IBoardDAO dao;
	
	@Override
	public void insert(BoardVO article) throws Exception {
		dao.insert(article);

	}

	@Transactional
	@Override
	public BoardVO getArticle(int boardNo) throws Exception {
		BoardVO article = dao.getArticle(boardNo);
		
		//  '\n'을 사용하기위해 세팅함, 없으면 줄바꿈 적용안됨
		String content = article.getContent()
											.replace("\n", "<br>")
											.replace("\u0020", "&nbsp;");
		article.setContent(content);
		
		dao.updateViewCnt(boardNo);
		return article;
	}

	@Override
	public void update(BoardVO article) throws Exception {
		dao.update(article);

	}

	@Override
	public void delete(int boardNo) throws Exception {
		dao.delete(boardNo);

	}

	@Override
	public List<BoardVO> getAllArticles() throws Exception {
		
		
		
		return dao.getAllArticles();
	}

	@Override
	public List<BoardVO> listPaging(Criteria cri) throws Exception {
		
		return dao.listPaging(cri);
	}

	@Override
	public int countArticles() throws Exception {
		
		return dao.countArticles();
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		
		List<BoardVO> list = dao.listSearch(cri);
		//리턴하기전에 뉴마크를 트루로 변경해줘야함
		//1일이내에 쓰여진 게시물에만 new 붙이기
		//현재시간->게시물등록시간->둘을뺴서 1일미만인지 확인
		for(BoardVO boardVO : list) {
			//게시물 목록을 요청한 시간 - 게시물등록시간 < 1일 => newMark 변수를 true로
			
			//현재시간을 밀리초로반환해서 long 리턴
			//1979년부터 시작함
			long now = System.currentTimeMillis();	//현재시간
			//Date date = boardVO.getRegDate();		//, 게시물 등록시간 long이 아닌 date형 변수
			long regDate = boardVO.getRegDate().getTime();			//위 두 변수를 계산하게하도록 date형 타입을 일치시시키고 한줄로 줄임
		
			long oneDayMillis = 60 * 60 * 24 * 1000;		//하루의 밀리초
			if(now - regDate <= oneDayMillis) {		//하루이내에 등록된 게시물이라면
				boardVO.setNewMark(true);
			}
		}
		
		
		return list;
	}

	@Override
	public int countSearchedArticles(SearchCriteria cri) throws Exception {
		return dao.countSearchedArticles(cri);
	}



	
}
