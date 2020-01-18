<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="memberone.*"%>
<% request.setCharacterEncoding("utf-8"); %>
<%
	StudentDAO dao = StudentDAO.getInstance();
%>
<jsp:useBean id="vo" class="memberone.StudentVO">
	<jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<%
	String loginID = (String)session.getAttribute("loginID");
	vo.setId(loginID);
	dao.updateMember(vo);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="Refresh" content="3;url=login.jsp">
<title>Update Process</title>
</head>
<body>
<center>
<font size="5" face="바탕체">
입력하신 내용대로 <b>회원정보가 수정되었습니다.</b><br></br>
3초후에 Login Page로 이동합니다.
</font>
</center>
</body>
</html>