<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="vo" class="jstl.MemberVO"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Example</title>
</head>
<body>
<c:set target="${vo}" property="name" value="홍길동"></c:set>
<c:set target="${vo}" property="email">
hong@hanmail.net
</c:set>
<c:set var="age" value="30"></c:set>
<c:set target="${vo}" property="age" value="${age}"></c:set>
<h3>회원정보</h3>
<ul>
	<li>이름 : ${vo.name}</li>
	<li>메일 : ${vo.email}</li>
	<li>나이 : ${vo.age}</li>
</ul>
</body>
</html>