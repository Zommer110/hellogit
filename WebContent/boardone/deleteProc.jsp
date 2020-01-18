<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "boardone.BoardDAO" %>    
<%@ page import = "java.sql.Timestamp" %>
<% request.setCharacterEncoding("utf-8"); %>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	String pass = request.getParameter("pass");
	BoardDAO dbPro = BoardDAO.getInstance();
	int check = dbPro.deleteArticle(num, pass);
	if(check==1) {
%>   
	<meta charset="UTF-8" http-equiv="Refresh" content="0;url=list.jsp?pageNum=<%=pageNum%>">
<% } else { %>
	<script type="text/javascript">
	<!-- 
	alert("비밀번호가 맞지 않습니다.");
	history.go(-1);
	-->
	</script>	 
<%} %>	
