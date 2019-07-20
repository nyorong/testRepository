<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id= "reply-box">


</div>

<p>
		# 댓글 작성자: <input id="newReplyWriter" type="text" name="replyWriter"><br>
		# 댓글 내용 : <textarea id="newReplyText" rows="3" name="replyText"></textarea><br>
			<input id="replyAddBtn" type="button" value="댓글 쓰기">
			
		
</p>



<!-- jquery 라이브러리 가져오기 -->
<jsp:include page="../include/plugin-js.jsp" />

<script type="text/javascript">

$(document).ready(function () {
	
	let boardNo = 997; // 댓글이 많은 게시글번호
	
	/*
		# Rest방식은 클라이언트와 서버간에 JSON데이터를 주고받기 떄문에 
		서버가 전달해준 JSON 데이터를 클라이언트측에서 받을수 있어야 합니다
		#JQUERY라이브러리가 제공하는 $.getJSON() 함수를 사용하면
		서버가 넘겨준 JSON데이터를 받아낼 수 있습니다.
		# getJSON()함수의 첫번째 매개값으로 요청URI, 두번째 매개값으로
		받은 JSON데이터를 실행할 익명 함수를 정의합니다.
	*/
	
	// 첫번쨰 매개변수(/mvc/replies/all/" + boardNo)에서 받은걸 두번쨰 매개변수(function(Data)의 data로 보냄)

	$.getJSON("/mvc/replies/all/" + boardNo, function(data){
		
		let str = "";
		
		for(let i=0; i<3; i++){
			str += "<p>댓글 내용 : " + data[i].replyText + "</p>" + 
			"<p>댓글 작성자: " + data[i].replyWriter + "</p>";
		}
		//html 태그를 추가하면 jQuery의 html()함수를 사용합니다.
		//id가 replxy-box인 태그안에  str값을 넣어라.
		$("#reply-box").html(str);
		
		
		//data가 가리키는 내용에서 뽑아옴.
		$(data).each(function(){
			str += "<p>댓글번호 : " + this.replyNo + "댓글내용 : " + this.replyText + "<br>" + "댓글작성자 : " + this.replyWriter + "</p><hr>";
		});
		
		$("#reply-box").html(str);
		
		
		
		
		//console로 찍은건  f12누르고 console창에서 데이터확인가능
		/*
		console.log("1번째 댓글 내용 : " + data[0].replyText);
		console.log("1번째 댓글 작성자: " + data[0].replyWriter);
		
		for(let i=0; i<10; i++){
			console.log((i+1) + "번째 댓글 내용: " + data[i].replyText);
			console.log((i+1) + "번쨰 댓글 작성자: " + data[i].replyWriter);
			console.log("================================================");
		}
		
		console.log(data);
		*/
	});
	
	//댓글쓰기 버튼 클릭 이벤트 처리
	$("#replyAddBtn").on("click", function(){
		console.log("등록 버튼 클릭!")
		//id가 newReplyText인 요소의 값을 상수  text에 저장
		const text = $("#newReplyText").val()	
		//id가 newReplyWriter인 요소의 값을 상수 writer에 저장
		const writer =  $("#newReplyWriter").val();
		
		//POST요청 비동기 통신 시작, ajax라는 함수에 전달하는 객체
		$.ajax({
			type: "POST", //http 요청 메서드
			url: "/mvc/replies", 
			
			//구형브라우저를 위한처리. 전송이안되면 post방식으로 전송
			headers: { 
				"Content-type" : "application/json",
            	"X-HTTP-Method-Override" : "POST"
			},
			 //들어오는게 등록요청의 리턴값인 regsuccess이므로 스트링을 받아줌
			dataType: "text",
			
			//서버로 전송할 json!!데이터
			//JSON.stringify : 매개값으로 자바스크립트의 객체를 전달하면
			//JSON형식으로 파싱하여 전송
			data: JSON.stringify({
				boardNo: 44,
				replyText: text, 
				replyWriter: writer
			}),
			//통신 성공시 실행할 문장 작성
			//익명함수의 매개값으로 서버가 리턴한 데이터가 들어옵니다.(성공시 regsuccess 텍스트가 들어옴)
			success: function(result){
				if(result == "regSuccess"){
					alert("댓글 등록 완료");
				}
				//성공했을떄 아래 칸을 빈칸으로 바꿔라
				$("#newReplyText").val("");
				$("#newReplyWriter").val("");
			}
		});
		
	});
	
});



</script>



</body>
</html>