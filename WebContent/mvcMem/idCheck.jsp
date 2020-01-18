<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중복체크</title>
<link href="style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="#FFFFCC"><br>
	<center>
		<b>${id }</b>
		<c:if test="${check eq true}">
			는 이미 존재하는 ID입니다.</br></c:if>
		<c:if test="${check ne true}">는 사용 가능합니다.</br></c:if>
		<a href="#" onclick="javascript:self.close()">닫기</a>	
	</center>
</body>
</html>