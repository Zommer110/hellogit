<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "boardone.BoardDAO" %>    
<%@ page import = "java.sql.Timestamp" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="article" scope="page" class="boardone.BoardVO">
	<jsp:setProperty name="article" property="*"/>
</jsp:useBean>
<%
	String pageNum = request.getParameter("pageNum");
	BoardDAO dbPro = BoardDAO.getInstance();
	int check = dbPro.updateArticle(article);
	if(check==1){
%> 
	<meta http-equiv="Refresh" content="0;url=list.jsp?pageNum=<%=pageNum %>">
<% } else { %>
	<script type="text/javascript">
	<!--
	alert("비밀번호가 맞지 않습니다");
	history.go(-1);
	-->
	</script>	
<%
}
%>	
