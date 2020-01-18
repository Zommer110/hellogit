<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 폼</title>
</head>
<body>
<form action="processFileUpload.jsp" method="post" enctype="multipart/form-data">
파일1: <input type="file" name="file1"><br>
파일2: <input type="file" name="file2"><br>
파일3: <input type="file" name="file3"><br>
파라미터1: <input type="text" name="param1"><br>
파라미터2: <input type="text" name="param2"><br>
파라미터3: <input type="text" name="param3"><br>
<input type="submit" value="전송">
</form>
</body>
</html>