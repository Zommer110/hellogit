<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String[] movieList = {"프리즌브레이크","스파르타쿠스","히어로즈"};
	request.setAttribute("movieList", movieList);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Example</title>
</head>
<body>
인기 미국 드라마 :
<ul>
	<c:forEach var="movie" items="${movieList}">
		<li>${movie}</li>
	</c:forEach>
</ul>
</body>
</html>