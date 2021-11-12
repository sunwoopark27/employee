<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>회원</title>
</head>
<body>
<h1>회원(JSP + JSP 액션태그 + EL + JSTL)</h1>
<p><a href='form'>새 회원</a></p>
<table border='1'>
<thead>
<tr>
<th>번호</th> <th> </th><th>이름</th> <th>이메일</th> <th>전화</th>
</tr>
</thead>
<tbody>

<c:forEach items="${list}" var="m">
	<tr> 
	  <td><fmt:formatNumber minIntegerDigits="3" value="${m.no}" type="number"/></td>
	  <%-- <td>${m.no}</td>  --%>
	  <td><a href='detail?no=${m.no}'>${m.name}</a></td>
	  <td>${m.position}</td>
	  <td>${m.email}</td> 
	  <td>${m.tel}</td> 
	</tr>
</c:forEach>

</tbody>
</table>

<form action='list' method='get'>
<input type='search' name='keyword' value='${param.keyword}'> 
<button>검색</button>
</form>
</body>
</html>

