<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pageTitle = (String)request.getAttribute("PAGETITLE");
	String contentPage = request.getParameter("CONTENTPAGE");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= pageTitle %></title>
</head>
<body>
<table width="400" border="1" cellpadding="2" cellspacing="0">
<tr>
	<td colspan="2">
		<jsp:include page="/temp/module/top.jsp" flush="false" />
	</td>
</tr>
<tr>
	<td width="100" valign="top">
		<jsp:include page="/temp/module/left.jsp" flush="false" />
	</td>
	<td width="300" valign="top">
		<jsp:include page="<%= contentPage %>" flush="false" />
	</td>
</tr>
<tr>
	<td colspan="2">
		<jsp:include page="/temp/module/bottom.jsp" flush="false" />
	</td>
</tr>
</table>
</body>
</html>