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
<title>직원 리스트</title>
</head>
<body>
<h1>직원</h1>
<p><a href='form'>새 직원</a></p>
<table border='1'>
<thead>
<tr>
<th>번호</th> <th>이름</th><th>직급</th> <th>이메일</th> <th>전화</th>
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
<select name='item'>
   <option value='0' ${param.item == "0" ? "selected" : ""}>전체</option>
   <option value='1' ${param.item == "1" ? "selected" : ""}>번호</option>
   <option value='2' ${param.item == "2" ? "selected" : ""}>직급</option>
   <option value='3' ${param.item == "3" ? "selected" : ""}>이름</option>
   <option value='4' ${param.item == "4" ? "selected" : ""}>전화번호</option>
   <option value='5' ${param.item == "5" ? "selected" : ""}>이메일</option>
</select>
<input type='search' name='keyword' value='${param.keyword}'> 
<button>검색</button>
</form>

<form action='savecsv' method='post'>
<input type="hidden" name='keyword' value='${param.keyword}'>
<button>csv 다운</button>
</form>

<%-- <form action='list' method='get'>
<input type='search' name='keyword' value='${param.keyword}'> 
<button>검색</button>
</form> --%>
</body>
</html>

