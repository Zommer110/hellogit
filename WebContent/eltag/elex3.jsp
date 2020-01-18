<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>간단한 표현언어(EL)예제</title>
</head>
<body>
<h3>간단한 표현언어(EL)예제</h3>
<p>연산자를 사용한 예와 내장객체의 사용한 예:</p>
<table border="1">
	<tr><td><b>표현식</b></td><td><b>값</b></td></tr>
	<tr><td>\${2+5}</td><td>${2+5}</td></tr>
	<tr><td>\${4/5}</td><td>${4/5}</td></tr>
	
	<tr><td>\${5 mod 7}</td><td>${5 mod 6}</td></tr>
	<tr><td>\${2 < 3}</td><td>${2 < 3}</td></tr>
	<tr><td>\${2 gt 3}</td><td>${2 gt 3}</td></tr>
	<tr><td>\${3.1 le 3.2}</td><td>${3.1 le 3.2}</td></tr>
	<tr><td>\${(5>3) ? 5:3}</td><td>${(5>3) ? 5:3}</td></tr>
	<tr><td>\${header["host"]}</td><td>${header["host"]}</td></tr>
	<tr><td>\${header["user-agent"]}</td><td>${header["user-agent"]}</td></tr>
</table>
</body>
</html>