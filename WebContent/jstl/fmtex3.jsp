<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL fmt 예제 - formatNuber, formatDate</title>
</head>
<body>
number : 
<fmt:formatNumber value="12345.678" type="number"></fmt:formatNumber><br>
currency :
<fmt:formatNumber value="12345.678" type="currency"></fmt:formatNumber><br>
percent :
<fmt:formatNumber value="12345.678" type="percent"></fmt:formatNumber><br>
pattern=".0" :
<fmt:formatNumber value="12345.678" pattern=".0"></fmt:formatNumber><br>
<c:set var="now" value="<%= new java.util.Date() %>"></c:set>
<c:out value="${now}"></c:out><br>
date : <fmt:formatDate value="${now}" type="date"/>
time : <fmt:formatDate value="${now}" type="time"/>
both : <fmt:formatDate value="${now}" type="both"/>
</body>
</html>