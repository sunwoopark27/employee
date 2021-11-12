<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 직원</title>
</head>
<body>
<h1>새 직원</h1>
<form action="add" method="post">
번호: <input type="number" name="no"><br>
이름: <input type="text" name="name"><br>
이메일: <input type="email" name="email"><br>
직급: <input type="text" name="position"><br>
전화번호: <input type="tel" name="tel"><br>
<input type="submit" value="등록">
</form>
</body>
</html>