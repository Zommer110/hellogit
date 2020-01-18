<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL core 예제 - set, out, remove</title>
</head>
<body>
browser변수값 설정
<c:set var="browser" value="${header['User-Agent']}"></c:set></br>
<c:out value="${browser}"></c:out><p>
browser변수값 제거 후
<c:remove var="browser"/>
<c:out value="${browser}"></c:out>
</body>
</html>