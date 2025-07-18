<%@page import="test.dao.BoardDao"%>
<%@page import="test.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//get 방식 파라미터로 전달되는 글번호 얻어내기
	int num=Integer.parseInt(request.getParameter("num"));
	//DB 에서 해당글의 자세한 정보를 얻어낸다.
	BoardDto dto=BoardDao.getInstance().getByNum(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/view.jsp</title>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
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
				<td><%=dto.getTitle() %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=dto.getViewCount() %></td>
			</tr>
		</table>	
		<div><pre><%=dto.getContent() %></pre></div>
		<div class="card mt-4">
		  <div class="card-header bg-light">
		    <strong>본문 내용</strong>
		  </div>
		  <div class="card-body p-1">
		    <pre class="mb-0" style="background-color: #f8f9fa; border-radius: 5px; padding: 1rem; white-space: pre-wrap; font-family: '맑은 고딕', 'Consolas', monospace;"><%= dto.getContent() %></pre>
		  </div>
		</div>
	</div>
</body>
</html>






