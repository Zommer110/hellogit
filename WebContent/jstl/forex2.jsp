<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList, jstl.MemberVO" %>
<%
	MemberVO vo1 = new MemberVO("손오공", "son@hanmail.net", 500);
	MemberVO vo2 = new MemberVO("사오정", "sao@hanmail.net", 50);
	MemberVO vo3 = new MemberVO("저팔계", "pal@hanmail.net", 100);
	ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
	memberList.add(vo1);
	memberList.add(vo2);
	memberList.add(vo3);
	request.setAttribute("memberList", memberList);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Example</title>
</head>
<body>
<h3>회원 정보</h3>
<table border="1" width="450">
	<tr>
		<th width="50">번호</th>
		<th width="100">이름</th>
		<th width="200">메일</th>
		<th width="100">나이</th>
	</tr>
	<c:forEach var="member" items="${memberList}" varStatus="num">
		<tr>
			<td align="center">${num.count}</td>
			<td align="center">${member.name}</td>
			<td align="center">${member.email}</td>
			<td align="center">${member.age}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>