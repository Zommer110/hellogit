<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setAttribute("PAGETITLE", "정보보기"); %>
<jsp:forward page="/temp/template/template.jsp">
	<jsp:param name="CONTENTPAGE" value="info_view.jsp"/>
</jsp:forward>