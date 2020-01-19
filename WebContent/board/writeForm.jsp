<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="view/color.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<body bgcolor="${bodyback_c}">
<div align="center"><b>글쓰기</b>
<br>
<form method="post" name="writeform" action="/myWeb/board/writePro.do" onsubmit="return writeSave()">
<input type="hidden" name="num" value="${num}">
<input type="hidden" name="ref" value="${ref}">
<input type="hidden" name="step" value="${step}">
<input type="hidden" name="depth" value="${depth}">
<table width="400" border="1" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="right" colspan="2" bgcolor="${value_c}">
			<a href="/myWeb/board/list.do">글목록</a>
		</td>
	</tr>
	<tr>
		<td width="70" bgcolor="${value_c}" align="center">이 름</td>
		<td><input type="text" size="10" maxlength="10" name="writer"></td>
	</tr>
	<tr>
		<td width="70" bgcolor="${value_c}" align="center">제 목</td>
		<td width="330">
			<c:if test="${num == 0}">
				<input type="text" size="40" maxlength="50" name="subject">
			</c:if>
			<c:if test="${num != 0}">
				<input type="text" size="40" maxlength="50" name="subject" value="[답변]">
			</c:if>
		</td>
	</tr>
	<tr>
		<td width="70" bgcolor="${value_c}" align="center">Email</td>
		<td width="330"><input type="text" size="40" maxlength="30" name="email"></td>
	</tr>
	<tr>
		<td width="70" bgcolor="${value_c}" align="center">내 용</td>
		<td width="330"><textarea name="content" rows="13" cols="40"></textarea></td>
	</tr>
	<tr>
		<td width="70" bgcolor="${value_c}" align="center">비밀번호</td>
		<td width="330"><input type="password" size="8" maxlength="12" name="pass"></td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="${value_c}" align="center">
			<input type="submit" value="글쓰기">
			<input type="reset" value="다시작성">
			<input type="button" value="목록보기" onclick="window.location='/myWeb/list.do'">
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>