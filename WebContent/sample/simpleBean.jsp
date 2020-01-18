<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="msg" class="sample.SimpleData"/>
<%-- SimpleData msg = new SimpleData(); --%>
<jsp:setProperty name="msg" property="message"/>
 <%-- msg.setMessge(?); --%>
 <!-- 주의점 : 기본자료형, String -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h1>간단한 빈즈 프로그램 결과</h1>
<hr color="red"></hr><br></br>
<font size="5">
메시지 : <jsp:getProperty property="message" name="msg"/>
<%--<%=message%> --%>
</font>
</body>
</html>