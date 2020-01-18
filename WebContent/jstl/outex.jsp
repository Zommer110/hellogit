<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.IOException, java.io.FileReader" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소스 보기</title>
</head>
<body>
<%
	FileReader reader = null;
	try{
		String path = request.getParameter("path");
		reader = new FileReader(getServletContext().getRealPath(path));
%>
<pre>
소스코드 = <%= path %>
<c:out value="<%= reader %>" escapeXml="true"></c:out>
</pre>
<%
	} catch(IOException ex){
%>
에러:<%=ex.getMessage() %>
<%
	} finally {
		if(reader != null)
			try{
				reader.close();
			} catch(IOException ex){}
	}	
%>
</body>
</html>