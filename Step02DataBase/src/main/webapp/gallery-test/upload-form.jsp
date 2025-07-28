<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/gallery-test/upload-form</title>
</head>
<body>
	<div class="container">
		<form action="${pageContext.request.contextPath }/gallery-test/upload" 
			method="post" enctype="multipart/form-data">
			<input type="text" name="caption" placeholder="설명입력..."/>
			<br>
			<input type="file" name="image"/>
			<button type="submit">저장</button>
		</form>
	</div>
</body>
</html>