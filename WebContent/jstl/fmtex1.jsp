<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="en"/>
<fmt:bundle basename="bundle.testBundle">
<fmt:message key="TITLE" var="title"></fmt:message>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
</head>
<body>
<fmt:message key="NAME"></fmt:message>
<br>
<c:if test="${! empty param.id}">
<fmt:message key="MESSAGE">
	<fmt:param value="${param.id}"></fmt:param>
</fmt:message>
</c:if>
</body>
</html>
</fmt:bundle>