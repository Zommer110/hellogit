<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP File</title>
</head>
<body>
	<h2>JSP Script 예제</h2>
	<% // Scriplet - 연산, 처리
			String scriplet = "스크립트릿 입니다.";
			String comment = "주석문 입니다.";
			out.println("내장객체를 이용한 출력 : " + declation + "<br></br>");
	%>
	선언문 출력하기(변수) : <%=declation%><br></br>
	선언문 출력하기(메소드) : <%=declationMethod()%><br></br>
	스크립트릿 출력하기 : <%=scriplet%><br></br>
	<%=comment %>
	<!-- JSP에서 사용하는 HTML 주석부분 -->
	<!-- HTML 주석 : <%=comment%> --><br></br>
	<%-- JSP 주석 : <%=comment%> --%><br></br>
	<%
		//자바주석
		/*
			여러줄주석
		*/
	%>
	<%!	//declation = 변수 선언
			String declation = "선언문 입니다.";			
	%>
	<%! //declation = 메소드 선언
			public String declationMethod(){
				return declation;
			}	
	%>
</body>
</html>