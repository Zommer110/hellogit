<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String memberid = (String)session.getAttribute("MEMBERID");
	String name = (String)session.getAttribute("NAME");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션에 정보 불러오기</title>
</head>
<body>
세션에 정보를 로드하였습니다.
Member ID : <%=memberid %> <br></br>
Name : <%= name %>
</body>
</html>