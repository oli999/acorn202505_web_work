<%@page import="org.apache.commons.text.StringEscapeUtils"%>
<%@page import="test.dao.BoardDao"%>
<%@page import="test.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//get 방식 파라미터로 전달되는 글번호 얻어내기
	int num=Integer.parseInt(request.getParameter("num"));
	//DB 에서 해당글의 자세한 정보를 얻어낸다.
	BoardDto dto=BoardDao.getInstance().getByNum(num);
	//로그인된 userName (null 일 가능성 있음)
	String userName=(String)session.getAttribute("userName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/view.jsp</title>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container pt-3">
		<nav>
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item">
		    	<a href="${pageContext.request.contextPath }/">Home</a>
		    </li>
		    <li class="breadcrumb-item">
		    	<a href="${pageContext.request.contextPath }/board/list.jsp">Board</a>
		    </li>
		    <li class="breadcrumb-item active">Detail</li>
		  </ol>
		</nav>
		<h1>게시글 상세보기</h1>
		<table class="table table-striped">
			<colgroup>
				<col class="col-2"/>
				<col class="col"/>
			</colgroup>
			<tr>
				<th>글번호</th>
				<td><%=num %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=dto.getWriter() %></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><%=StringEscapeUtils.escapeHtml4(dto.getTitle()) %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=dto.getViewCount() %></td>
			</tr>
		</table>
		<%--
			클라이언트가 작성한 글 제목이나 내용을 그대로 클라이언트에게 출력하는것은 javascript 주입 공격을 받을수 있다
			따라서 해당 문자열은 escape 해서 출력하는것이 안전하다 
		 --%>	
		<div><pre><%=StringEscapeUtils.escapeHtml4(dto.getContent()) %></pre></div>
		<div class="card mt-4">
		  <div class="card-header bg-light">
		    <strong>본문 내용</strong>
		  </div>
		  <div class="card-body p-1">
		    <pre class="mb-0" style="background-color: #f8f9fa; border-radius: 5px; padding: 1rem; white-space: pre-wrap; font-family: '맑은 고딕', 'Consolas', monospace;"><%=StringEscapeUtils.escapeHtml4(dto.getContent()) %></pre>
		  </div>
		</div>
		<%if(dto.getWriter().equals(userName)){ %>
			<div class="text-end pt-2">
				<a class="btn btn-warning btn-sm" href="edit.jsp?num=<%=dto.getNum()%>">Edit</a>
				<a class="btn btn-danger btn-sm" href="delete.jsp?num=<%=dto.getNum()%>">Delete</a>
			</div>
		<%} %>		
	</div><!-- .container -->
</body>
</html>






