<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IP Search</title>

<!-- Bootstrap4 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>

<!-- Naver Dynamic Map -->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=k8dv3edpau"></script>

<link rel="stylesheet" href="/resources/css/common-min.css">
<link rel="stylesheet" href="/resources/css/font-min.css">

</head>
<body class="body-font">
	<nav class="navbar bg-primary navbar-dark">
	    <div class="navbar-header">
	        <div class="px-2">
	            <a href="/" class="navbar-brand font-24">
	            	<img class="mr-1" alt="logo" src="/resources/images/logo.png" style="width:30px;">
	            	<span>IP Search</span>
	            </a>
	            <span class="text-white">접속 IP : ${requestScope.clientIp}</span>
	        </div>
	    </div>
	</nav>

	<div class="container my-5">
		<form action="/" method="get">
			<div class="input-group">
				<input type="text" id="search" name="search" class="form-control col-4" placeholder="IPv4 주소"/>
				<div class="input-group-append p-0">
					<input type="submit" class="btn btn-primary" value="검색"/>
				</div>
			</div>
		</form>

		<div class="content mt-3">
			<c:choose>
		   		<c:when test="${requestScope.geoData != null}">
		   		<div class="mb-3">검색 IP : ${requestScope.searchIp}</div>
				<ul class="nav nav-tabs">
					<li class="nav-item"><a href="#map" class="nav-link active" data-toggle="tab">위치</a></li>
					<li class="nav-item"><a href="#info" class="nav-link" data-toggle="tab">정보</a></li>
				</ul>
				<div class="tab-content py-3">
					<div class="my-3">
						<small class="text-danger font-weight-bold">※ 주의사항 : 제공되는 위치 정보는 정확하지 않을 수 있습니다.</small>
						<div class="mt-2">위치: <span id="position">${requestScope.geoData.r1} ${requestScope.geoData.r2} ${requestScope.geoData.r3}</span></div>
					</div>
					<div class="tab-pane container fade show active" id="map">
						<div id="map" style="width:100%;height:500px;"></div>
						<script type="text/javascript">
							$(document).ready(function() {
								var latlng = new naver.maps.LatLng(${requestScope.geoData.latitude}, ${requestScope.geoData.longitude});
								var address = $("#position").text();
								var map = new naver.maps.Map('map', {
									mapTypeId : naver.maps.MapTypeId.NORMAL,
								    center: latlng,
								    zoom: 16,
								    zoomOrigin : latlng
								});

								var marker = new naver.maps.Marker({
								    position: latlng,
								    map: map,
								    animation : 2,
								    title : address
								});

								var circle = new naver.maps.Circle({
									center: latlng,
									radius : 200,
									fillColor : "#1833e5",
									fillOpacity : 0.05,
								    map: map
								});
							});
						</script>
					</div>
					<div class="tab-pane fade container" id="info">
						<table class="table table-borderless">
							<thead>
								<tr>
									<th>항목</th>
									<th>정보</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>국가 코드:</td>
									<td>${requestScope.geoData.country}</td>
								</tr>
								<tr>
									<td>행정구역 코드:</td>
									<td>${requestScope.geoData.code}</td>
								</tr>
								<tr>
									<td>위도:</td>
									<td>${requestScope.geoData.latitude}</td>
								</tr>
								<tr>
									<td>경도:</td>
									<td>${requestScope.geoData.longitude}</td>
								</tr>
								<tr>
									<td>통신사:</td>
									<td>${requestScope.geoData.net}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
		   		</c:when>
		   		<c:otherwise>
					<div>${requestScope.errorMsg}</div>
		   		</c:otherwise>
		   	</c:choose>
	   	</div>
   	</div>
</body>
</html>