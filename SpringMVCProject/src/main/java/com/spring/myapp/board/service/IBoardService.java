package com.spring.myapp.board.service;

import java.util.List;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.SearchCriteria;

public interface IBoardService {
	void insert(BoardVO article) throws Exception;
	BoardVO getArticle(int boardNo) throws Exception;
	void update(BoardVO article) throws Exception;
	void delete(int boardNo) throws Exception;
	
	List<BoardVO> getAllArticles() throws Exception;
	
	//페이징 처리
	List<BoardVO> listPaging(Criteria cri) throws Exception;
	
	//게시글의 총 게시물 수를 불러오기
	int countArticles() throws Exception;
	
	//제목으로 검색항 게시물 불러오기 	
	//List<BoardVO> listSearchByTitle(SearchCriteria cri) throws Exception;
	
	//작성자명으로 검색항 게시물 목록불러오기
	//목록 불러와서 리턴하기전에 new 붙일지 여부를 컨트롤러에 보내기전에 저장할수잇음
	List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	
	//검색 후 총 게시물 수 가져오기
	int countSearchedArticles(SearchCriteria cri) throws Exception;
}
