<%@page import="com.ipsearch.util.GlobalPath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='content-language' content='ko'>

<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
<meta name="viewport" content="width=device-width, initial-scale=1" >
<meta name="author" content="bcw2104">
<meta name="description" content="도메인 또는 IP 주소를 검색하여 위치를 표시하는 지도와 관련 정보를 제공받으세요.">

<meta property="og:type" content="website">
<meta property="og:title" content="IP Search | 도메인 및 IP 위치 검색">
<meta property="og:description" content="도메인 또는 IP 주소를 검색하여 위치를 표시하는 지도와 관련 정보를 제공받으세요." >
<meta property="og:image" content="<%=GlobalPath.root%>resources/images/logo.png">
<meta property="og:url" content="https://www.lifencoding.com${requestScope['javax.servlet.forward.request_uri']}"/>
<meta property="og:site_name" content="IP Search">

<title>IP Search | 도메인 및 IP 위치 검색</title>

<!-- Bootstrap4 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<%=GlobalPath.root%>resources/css/common-min.css">
<link rel="stylesheet" href="<%=GlobalPath.root%>resources/css/font-min.css">

</head>
<body class="body-font">
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container my-5 py-5" style="height: 700px">
		<h1 class="text-danger font-weight-bold text-center" style="font-size: 200px;">${requestScope.status}</h1>
		<div class="mb-5 font-32 text-center">
		<c:if test="${requestScope.status == 400}">
			잘못된 요청이 발생했습니다.
		</c:if>
		<c:if test="${requestScope.status == 404}">
			존재하지 않는 페이지입니다.
		</c:if>
		<c:if test="${requestScope.status == 500}">
			서버에 오류가 발생했습니다.
		</c:if>
		</div>
		<div class="pt-5 text-center">
			<a href="/"><button type="button" class="btn btn-primary w-25 font-20" style="height: 50px;">홈으로</button></a>
		</div>
   	</div>
   	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>