package com.spring.myapp.reply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.myapp.board.controller.BoardController;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.PageCreator;
import com.spring.myapp.reply.model.ReplyVO;
import com.spring.myapp.reply.service.IReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);


	@Autowired
	private IReplyService service;

	//rest-api-view.jsp  참조, src/view/test에있음

	//댓글등록처리
	/*
	 * @RequestBody: 클라이언트가 전송한 JSON 객체 데이터를 자바객체로 변환해주는 아노테이션
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	//@ResponseBody 
	//안쓰면 regSuccess.jsp를 찾으러감. 쓰면 화면에 바로 regSuccess 텍스트를띄움
	public String register(@RequestBody ReplyVO reply) throws Exception{

		logger.info("/replies : post요청발생");
		service.insert(reply);
		logger.info(reply.toString() + "댓글 등록 성공");

		//어느형식이든지 받은데이터를 받기위해 json으로 파싱해서 받아야함
		//@RequestBody 

		return "regSuccess";
	}


	//댓글 목록 가져오기
	/*
	 * @Pathvariable : rest방식의 uri경로에서 원하는 데이터를 추출할때 사용
	 */
	@RequestMapping(value="/all/{boardNo}", method=RequestMethod.GET)
	public List<ReplyVO> list(@PathVariable int boardNo) throws Exception{

		logger.info("/replies/all/" + boardNo + " : GET 요청발생");	

		return service.list(boardNo);
	}

	//페이징 처리된 댓글 목록 가져오기
	@RequestMapping(value="/{boardNo}/{page}", method=RequestMethod.GET)
	public Map<String, Object> listPaging(@PathVariable("boardNo") int boardNo,
			@PathVariable("page") int page) throws Exception {

		Criteria cri = new Criteria();
		cri.setPage(page);
		List<ReplyVO> replies = service.listPaging(boardNo, cri);

		PageCreator pc = new PageCreator();
		pc.setCriteria(cri);
		pc.setArticleTotalCount(service.countReplies(boardNo));

		Map<String, Object> datas = new HashMap<>();
		datas.put("replies", replies);
		datas.put("pageCreator", pc);

		return datas;
	}

	//댓글 삭제 요청
	@RequestMapping(value="/{replyNo}", method=RequestMethod.DELETE)
	public String delete(@PathVariable int replyNo,
			@RequestBody ReplyVO reply) throws Exception{

		service.delete(replyNo, reply.getBoardNo());
		return "delSuccess";
	}

	//댓글 수정 요청
	@RequestMapping(value="/{replyNo}", method=RequestMethod.PUT)
	public String update(@PathVariable int replyNo, 
			@RequestBody ReplyVO reply) throws Exception{

		logger.info("/replies/" + replyNo + " : put요청발생!");
		reply.setReplyNo(replyNo);
		service.update(reply);
		return "modSuccess";
	}



}
