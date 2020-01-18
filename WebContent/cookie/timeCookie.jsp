<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	Cookie cookie = new Cookie("hour","1time");
 	cookie.setMaxAge(60);//60초
 	response.addCookie(cookie);
 %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키유효시간설정</title>
</head>
<body>
유효시간이 1분인 hour 쿠키 생성.
</body>
</html>