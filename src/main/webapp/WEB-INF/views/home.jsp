<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IP Search</title>
</head>
<body>
	<form action="/" method="get">
		<input type="text" name="search" placeholder="IP 주소"/>
		<input type="submit" value="검색"/>
	</form>

	<c:if test="${requestScope.ipData != null}">
		<div>${requestScope.ipData}</div>
	</c:if>
</body>
</html>