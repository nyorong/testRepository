package com.spring.myapp.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.board.service.IBoardService;
import com.spring.myapp.commons.paging.PageCreator;
import com.spring.myapp.commons.paging.SearchCriteria;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private IBoardService service;
	/*
	//게시글 목록페이지 열람요청 처리 메서드
	@RequestMapping(value="/list", method=RequestMethod.GET)
	//Model : 화면에 담을 객체를 보여주는용도
	public String list(Box b, Model model) throws Exception{
		//여기서 model에 추가해두면 el변수로 활용가능
		model.addAttribute("aaaa", b.getAaa());
		model.addAttribute("bbbb", b.getBbb());
		
		logger.info("/board/list request~~");
		List<BoardVO> articles = service.getAllArticles();
		
		
		model.addAttribute("articles", service.getAllArticles());
		
		return "board/list";
	}
	*/
	/*
	//위에 중간에넣었었음
	System.out.println("=================================");
	for(BoardVO vo : articles)
		System.out.println(vo);
	System.out.println("=================================");
	*/
	
	//페이징 처리 후 게시글 목록 요청
	/*
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String list(Criteria cri, Model model) throws Exception{
		logger.info("/board/list : Get요청 발생!");
		
		logger.info("목록 게시물 수: " + cri.getCountPerPage());
		
		PageCreator pc = new PageCreator();
		pc.setCriteria(cri);
		pc.setArticleTotalCount(service.countArticles());
		
		
		model.addAttribute("articles", service.listPaging(cri));
		model.addAttribute("pageCreator", pc);
		
		return "board/list";
	}*/
	
	//검색 처리 후 게시글 목록 불러오기
	//자식을 부모에 넣어도 다형성으로 인해 오류발생안함.(상속관계) SearchCriteria는 부모인 criteria모습을 가질수있음
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String list(SearchCriteria cri, Model model) throws Exception{
		logger.info("/board/list : Get요청 발생!");
		
		logger.info("목록 게시물 수: " + cri.getCountPerPage());
		
		PageCreator pc = new PageCreator();
		pc.setCriteria(cri);
		pc.setArticleTotalCount(service.countSearchedArticles(cri));
		
		
		/*
		if(cri.getCondition().equals("writer")) {
			model.addAttribute("articles", service.listSearchByWriter(cri));
		}else if(cri.getCondition().equals("content")) {
			model.addAttribute("articles", service.)
		}
		
		else{
			model.addAttribute("articles", service.listSearchByTitle(cri));
		}
		*/
		List<BoardVO> list = service.listSearch(cri);
		System.out.println("게시물 수(list): " + list.size());
		model.addAttribute("articles", list);
		model.addAttribute("pageCreator", pc);
		
		return "board/list";
	}
	
	//게시글 작성화면 열람 요청
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(HttpSession session) throws Exception{
		
		if(session.getAttribute("login") == null)
			return "redirect:/";
		
		return "board/write";
	}
	
	//게시글 
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(BoardVO article, RedirectAttributes redirectAttr) throws Exception{
		
		logger.info(article.toString());
		service.insert(article);
		//regsuccess란 문자를 message에 보낸다, el로 사용가능
		redirectAttr.addFlashAttribute("message",  "regSuccess");
		return "redirect:/board/list";
	}
	
	//게시물 상세 조회 페이지 요청
	@RequestMapping(value="/content", method=RequestMethod.GET)
	public String content(@RequestParam("boardNo") int boardNo, 
			@ModelAttribute("criteria") SearchCriteria cri
			, Model model) throws Exception{
		//@RequestParam("page") int page 
		// 아래 model.addAttribute("page", page);이랑 같이 써서 전송하려는걸 두개로 쓰기 귀찮으니까 하나로 줄인게 위  ㅡㅐㅇ딤ㅅㅅ갸ㅠㅕㅅㄷ
		model.addAttribute("article", service.getArticle(boardNo));
		//model.addAttribute("page", page);
		return "board/content";
	}
	
	//게시물 수정 페이지
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@RequestParam("boardNo") int boardNo, Model model) throws Exception{
		
		model.addAttribute("article", service.getArticle(boardNo));
		return "board/modify";
	}
	
	//페이징 처리전
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(BoardVO article, RedirectAttributes redirectAttr) throws Exception{
		
		service.update(article);
		redirectAttr.addFlashAttribute("message", "updateSuccess");
		return "redirect:/board/list";
	}
	
}
