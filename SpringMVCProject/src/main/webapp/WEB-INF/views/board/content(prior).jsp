<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<jsp:include page="../include/static-head.jsp" />

<body class="hold-transition skin-blue sidebar-mini layout-boxed">

<div class="wrapper">

    <!-- Main Header -->
    <jsp:include page="../include/main-header.jsp" />

    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="../include/side-bar.jsp" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                게시판
                <small>조회페이지</small>
            </h1>
            <ol class="breadcrumb">
                <li><i class="fa fa-edit"></i> article</li>
                <li class="active"><a href="<c:url value='/board/read'/>"> read</a></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">

            <div class="col-lg-12">
                <div class="box box-primary">
                
                   
                    <div class="box-header with-border">
                        <h3 class="box-title">글제목 : ${article.title}</h3>
                    </div>
                    <div class="box-body" style="height: 700px">
                        ${article.content}
                    </div>
               <div class="box-footer">
                        <div class="user-block">
                            <img class="img-circle img-bordered-sm" src="<c:url value='/dist/img/user1-128x128.jpg'/>" alt="user image">
                            <span class="username">
                                <a href="#">${article.writer}</a>
                            </span>
                            <span class="description"><fmt:formatDate pattern="yyyy-MM-dd a HH:mm" value="${article.regDate}"/></span>
                        </div>
                    </div>
                    
                    <div class="box-footer">
                        <form role="form" method="post">
                            <input type="hidden" name="boardNo" value="${article.boardNo}">
                        </form>
                        <button type="submit" class="btn btn-primary listBtn"><i class="fa fa-list"></i> 목록</button>
                        <div class="pull-right">
                            <button type="submit" class="btn btn-warning modBtn"><i class="fa fa-edit"></i> 수정</button>
                            <button type="submit" class="btn btn-danger delBtn"><i class="fa fa-trash"></i> 삭제</button>
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

</html>







