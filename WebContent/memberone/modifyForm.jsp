<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="memberone.*"%>
<%
	StudentDAO dao = StudentDAO.getInstance();
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Form</title>
<link href="style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="script.js"></script>
</head>
<%
	String loginID = (String)session.getAttribute("loginID");
	StudentVO vo = dao.getMember(loginID);
%>
<body>
<form action="modifyProc.jsp" name="regForm" method="post">
<table border="1">
<tr>
	<td colspan="2" align="center">회원 수정 정보 입력</td>
</tr>
<tr>
	<td align="right">아이디 : </td>
	<td><%=vo.getId() %></td>
</tr>
<tr>
	<td align="right">패스워드 : </td>
	<td><input type="password" name="pass" value="<%=vo.getPass()%>"/></td>
</tr>
<tr>
	<td align="right">패스워드 확인 : </td>
	<td><input type="password" name="repass" value="<%=vo.getPass()%>"/></td>
</tr>
<tr>
	<td align="right">이름 : </td>
	<td><%=vo.getName() %></td>
</tr>
<tr>
	<td align="right">전화번호 : </td>
	<td>
		<input type="text" name="phone1" size="4" value="<%=vo.getPhone1()%>"/> -
		<input type="text" name="phone2" size="5" value="<%=vo.getPhone2()%>"/> -
		<input type="text" name="phone3" size="5" value="<%=vo.getPhone3()%>"/>
	</td>
</tr>
<tr>
	<td align="right">이메일 : </td>
	<td><input type="text" name="email" value="<%=vo.getEmail()%>"/></td>
</tr>
<tr>
	<td align="right">우편번호 : </td>
	<td><input type="text" name="zipcode" value="<%=vo.getZipcode()%>"/>
	<input type="button" value="찾기" onClick="zipCheck()"/></td>
</tr>
<tr>
	<td align="right">주소1 : </td>
	<td><input type="text" name="address1" size="50" value="<%=vo.getAddress1()%>"/></td>
</tr>
<tr>
	<td align="right">주소2 : </td>
	<td><input type="text" name="address2" size="50" value="<%=vo.getAddress2()%>"/></td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="button" value="정보수정" onclick="updateCheck()"/>&nbsp;&nbsp;
		<input type="button" value="취소" onClick="javascript:window.location.'login.jsp'"/>
	</td>
</tr>
</table>
</form>
</body>
</html>