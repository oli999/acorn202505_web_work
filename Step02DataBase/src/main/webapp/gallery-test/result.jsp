<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//request 객체에 담긴 내용 읽어오기 (원래는 DB 에서 읽어와야함)
	String caption=(String)request.getAttribute("caption");
	String saveFileName=(String)request.getAttribute("saveFileName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/gallery-test/result.jsp</title>
</head>
<body>
	<h3><%=caption %></h3>
	<%-- 아래의 /upload/저장된파일명  형태로 이미지를 보여주려면 ImageServlet 이 있어야함 --%>
	<img src="${pageContext.request.contextPath }/upload/<%=saveFileName %>" alt="업로드 이미지" />
</body>
</html>