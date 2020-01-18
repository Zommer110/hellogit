<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "boardone.BoardDAO" %>    
<%@ page import = "boardone.BoardVO" %>    
<%@ page import = "java.util.List" %>    
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file = "view/color.jsp" %>
<%!
	int pageSize=5;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>    
<%
	String pageNum = request.getParameter("pageNum");
	if(pageNum == null){
		pageNum = "1";
	}
	int currentPage = Integer.parseInt(pageNum);
	int startRow = (currentPage -1) * pageSize + 1;//1<(현재페이지-1)*5 + 1>  5 / 6  10 / 11  15... 밑에가 아니라 옆에 행에 대한 것..
	int endRow = currentPage * pageSize;
	int count = 0; //전체 글수
	int number = 0; //화면에 보여지는 글 번호
	List<BoardVO> articleList = null;
	BoardDAO dbPro = BoardDAO.getInstance();
	count = dbPro.getArticleCount();//전체 글수
	if(count > 0) {
		articleList = dbPro.getArticles(startRow,endRow);//수정<3>
	}
	number = count-(currentPage-1)*pageSize;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href = "style.css" rel = "stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c%>">
<center><b>글목록(전체 글:<%=count %>)</b>
<table width="700">
<tr>
	<td align="right" bgcolor="<%=value_c%>">
	<a href="writeForm.jsp">글쓰기</a>
	</td>
</tr>
</table>
<%
	if(count == 0){
%>
<table width="700" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td align="center">
	게시판에 저장된 글이 없습니다.
	</td>
</tr>
</table>
<% } else { %>
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center">
	<tr height="30" bgcolor="<%=value_c%>">
		<td align="center" width="50">번호</td>
		<td align="center" width="250">제목</td>
		<td align="center" width="100">작성자</td>
		<td align="center" width="150">작성일</td>
		<td align="center" width="50">조회</td>
		<td align="center" width="100">IP</td>
	</tr>
<%
	for(int i = 0; i < articleList.size(); i++){
		BoardVO article = (BoardVO)articleList.get(i);
%>
	<tr height="30">
		<td align="center" width=50><%=number-- %></td>
		<td width="250">
<%
	int wid=0;
	if(article.getDepth()>0){
		wid=10*(article.getDepth()); //답변 사용시 들여쓰기를 이미지로 처리하기 위해
%>
	<img src="images/level.gif" width="<%=wid%>" heigth="16">
	<img src="images/re.gif">
<%}else{ %>
	<img src="images/level.gif" width="<%=wid%>" height="16">
<%} %>		
		<a href="content.jsp?num=<%=article.getNum()%>&pageNum=<%=currentPage%>">			
			<%=article.getSubject() %></a>
			<% if(article.getReadcount() >= 10) { %>
			<img src="images/hot.gif" border="0" height="16"><%} %></td>
		<td align="center" width="100">
			<a href="mailto:<%=article.getEmail()%>">
			<%=article.getWriter()%></a></td>
		<td align="center" width="150">
			<%= sdf.format(article.getRegdate())%></td>
		<td align="center" width="50"><%=article.getReadcount() %></td>			
		<td align="center" width="100"><%=article.getIp() %></td>			
	</tr>
		<%} %>	
</table>
<%} %>
<%
	if(count>0){
		int pageBlock = 5;//한 블럭에 보여질 페이지의 수
		int imsi = count % pageSize == 0 ? 0 : 1;//밑에랑 한 줄로 쓰면 이상하게 소수점이 나옴. 그래서 따로 쓰는 것!
		int pageCount = count / pageSize + imsi;// 전체 페이지 수
		int startPage = (int)((currentPage-1)/pageBlock)*pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if(endPage > pageCount) endPage = pageCount;//전체 페이지가 12개라면 11페이지에서 시작페이지 11, 엔드페이지 15가 나오는 상황이 발생..
		//이와같이  엔드페이지가 페이지 수를 넘어가지 않게 처리..
		if(startPage>pageBlock) { //시작페이지가 페이지 블락보다 클때%>
		<a href="list.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a>
<%
		}
		for(int i = startPage; i<=endPage; i++){ %>
		<a href="list.jsp?pageNum=<%=i%>">[<%=i%>]</a>	
<%
		}
		if(endPage<pageCount) { %>
		<a href="list.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a>	
<%
		}
	}	
%>			
</center>
</body>
</html>