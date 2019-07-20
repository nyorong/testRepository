<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<style>
	.page-active{
		background-color: aqua;
	}
</style>

<jsp:include page="../include/static-head.jsp" />

<!--  count-per-page를 id로 갖고잇는 애들중에서 button속성있는애들한정-->
<style>
	#count-per-page input[type=button] {
		background-color: yellogreen;
		padding: 0px 12px;
	
	}
</style>

<body class="hold-transition skin-blue sidebar-mini layout-boxed">

<div class="wrapper">

    <!-- Main Header -->
    <jsp:include page="../include/main-header.jsp" />

    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="../include/side-bar.jsp" />

    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                   게시판
                <small>목록페이지</small>
            </h1>
            <ol class="breadcrumb">
                <li><i class="fa fa-edit"></i> article</li>
                <li class="active"><a href="#"> list</a></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">

            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                    
                    
                        <h3 class="box-title">게시글 목록</h3>
                        
                        <span id="count-per-page" style="float: right;">
                           <i class="fa fa-list">목록 보기</i>
                           <input class="btn" type="button" value="10">
                           <input class="btn" type="button" value="20">
                           <input class="btn" type="button" value="30">
                        </span>
                        
                    </div>
                    <div class="box-body">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <th style="width: 30px">#</th>
                                <th>제목</th>
                                <th style="width: 100px">작성자</th>
                                <th style="width: 150px">작성시간</th>
                                <th style="width: 60px">조회</th>
                            </tr>
                            
                            <%-- 게시물이 들어갈 공간 --%>
                            <c:if test="${articles.size() <= 0 }">
                            	<tr>
                            		<td align="center" colspan="5"><strong>검색 결과가 없습니다</strong></td>
                            	</tr>
                            </c:if>
                            <c:if test="${articles.size() > 0 }">
                            
                            	<c:forEach var="article" items="${articles}">
                            	<tr>
                                	<td>${article.boardNo}</td>
                                	<td><a href="<c:url value='/board/content${pageCreator.makePageURI(pageCreator.criteria.page)}&boardNo=${article.boardNo}' />">
                                	${article.title} [${article.replyCnt}]</a>
                                	<c:if test="${article.newMark}">
                             		   	<span class="label label-success">new</span>
                             		</c:if>
                                	</td>
                                	<td>${article.writer}</td>
                                	<td><fmt:formatDate value="${article.regDate}" pattern="yyyy-MM-dd a HH:mm"/> </td>
                                	<td>${article.viewCnt}</td>
                            	</tr>
                            	</c:forEach>
                            </c:if>
                            
                            </tbody>
                        </table>
                    </div>
                    
                    <div class="box-footer">
      					<div class="text-center"> 
          					<ul class="pagination">     
          					
          						<c:if test="${pageCreator.prev}">                                
              					<li><a href="<c:url value='/board/list${pageCreator.makeSearchURI(pageCreator.beginPage - 1)}'/>">이전</a></li>    
              					</c:if>
              					
              					<c:forEach var="idx" begin = "${pageCreator.beginPage}" end= "${pageCreator.endPage}">
              						<li <c:out value= "${pageCreator.criteria.page == idx ? 'class=active' : ''}"/>><a href="<c:url value='/board/list${pageCreator.makeSearchURI(idx)}'/>">${idx}</a></li>
              					</c:forEach>                            
                                
                                <c:if test="${pageCreator.next}">		                                
                                <li><a href="<c:url value='/board/list${pageCreator.makeSearchURI(pageCreator.endPage + 1)}'/>">다음</a></li>
                                </c:if>
                                
         					</ul>
      					</div>
					</div>
					<div class="box-footer">
                       <div class="col-sm-2"></div>
                        <div class="form-group col-sm-2">
                            <select id="condition" class="form-control" name="condition">
                                <option value="title"	<c:out value="${param.condition == 'title' ? 'selected' : '' }"/>>제목</option>
                                <option value="content" <c:out value="${param.condition == 'content' ? 'selected' : '' }"/>>내용</option>
                                <option value="writer"	<c:out value="${param.condition == 'writer' ? 'selected' : '' }"/>>작성자</option>
                                <option value="titleContent" <c:out value="${param.condition == 'titleContent' ? 'selected' : '' }"/>>제목+내용</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-6">
                            <div class="input-group">
                                <input type="text" class="form-control" name="keyword" id="keywordInput" value="${param.keyword}" placeholder="검색어">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-primary btn-flat" id="searchBtn">
                                        <i class="fa fa-search"></i> 검색
                                    </button>
                                </span>
                            </div>
                        </div>
                        <div class="col-sm-2 pull-right">
                            <button type="button" class="btn btn-success btn-flat" id="writeBtn">
                                <i class="fa fa-pencil"></i> 글쓰기
                            </button>
                        </div>
                    </div>
					
                    
                    
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <jsp:include page="../include/main-footer.jsp" />

</div>
<!-- ./wrapper -->

<jsp:include page="../include/plugin-js.jsp" />

</body>

<script type="text/javascript">
	const result="${message}";
	
	if(result ==="regSuccess"){
		alert("게시글 등록이 완료되었습니다.");
	}else if(result == "updateSuccess"){
		alert("게시글 수정이 완료되었습니다.");
	}
	
	//JQuery문의 시작
	//function같은걸 익명함수라부름.
	$(document).ready(function(){
		//목록 개수 표현하기
		//아래속성을 가진 버튼을 클릭했을떄 괄호안의내용  실행
		$("#count-per-page input[type=button]").on("click", function(){ 
				
			//console.log($(this).val());
			let count = $(this).val();
			self.location = "list?page=${pageCreator.criteria.page}&countPerPage=" + count;
		});
		
		//글쓰기버튼 클릭 이벤트
		$("#writeBtn").on("click", function(){
			self.location="/mvc/board/write";
		});
		
		//검색버튼 클릭 이벤트
		$("#searchBtn").on("click", function(){
			self.location = "/mvc/board/list${pageCreator.makePageURI(1)}" +
			"&condition=" + $("select option:selected").val() +
			"&keyword=" + $("#keywordInput").val();
		});
		
	});
	
	//엔터키 입력 이벤트
	$("#keywordInput").keydown(function (key){
		if(key.keyCode == 13){	//키가 13이면실행(엔터키가 13)
			$("#searchBtn").click();
		}
	})
		
		
</script>


</html>









<%-- 
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
	<h1>list.jsp hello</h1>
	
	<p> aaa: ${aaaa}</p>
	<p> bbb: ${bbbb}</p>
	
	<p>
	<c:forEach var="vo" items="${articles}">
		${vo.toString()} <br>
	</c:forEach>
	</p>
</body>
</html>

--%>