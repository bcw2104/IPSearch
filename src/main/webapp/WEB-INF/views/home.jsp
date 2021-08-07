<%@page import="com.ipsearch.util.GlobalPath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="addrExist" value="${requestScope.returnData != null && requestScope.returnData.addr != null}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='content-language' content='ko'>

<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
<meta name="viewport" content="width=device-width, initial-scale=1" >
<meta name="author" content="bcw2104">
<meta name="description" content="도메인 또는 IP 주소를 검색하고 위치를 표시하는 지도와 관련 정보를 제공받으세요.">

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

	<div class="container my-5">
		<form action="<%=GlobalPath.root%>" method="get">
			<div class="input-group">
				<input type="text" id="search" name="search" class="form-control col-8" placeholder="IPv4 또는 도메인"/>
				<div class="input-group-append p-0">
					<input type="submit" class="btn btn-primary" value="검색"/>
				</div>
			</div>
		</form>

		<div class="content mt-3">
		   		<div class="mb-3">검색 : ${requestScope.query}</div>
				<ul class="nav nav-tabs">
					<li class="nav-item"><a href="#map" id="tab-map" class="nav-link" data-toggle="tab">지도</a></li>
					<li class="nav-item"><a href="#info" id="tab-info" class="nav-link active" data-toggle="tab">정보</a></li>
				</ul>
				<div class="tab-content py-3">
					<div class="my-3">
						<small class="text-danger font-weight-bold">※ 주의사항 : 제공되는 위치 정보는 정확하지 않을 수 있습니다	.</small>
						<div class="mt-2">
							<span>위치:</span>
							<span id="position">${addrExist ? requestScope.returnData.addr : '위치 정보를 찾을 수 없습니다.'}</span>
						</div>
					</div>
					<div class="tab-pane container fade" id="map">
						<div id="map" style="width:100%;height:500px;"></div>
						<c:if test="${addrExist}">
						<!-- Naver Dynamic Map -->
						<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${requestScope.clientId}"></script>
						<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=${requestScope.clientId}&submodules=geocoder"></script>
						<script type="text/javascript">
							$(document).ready(function() {
								if (window.location.protocol == "http:") {
									var restOfUrl = window.location.href.substr(5);
									window.location.replace("https:" + restOfUrl);
								}
								else{
									var type = "${requestScope.returnType}";
									var address = "${requestScope.returnData.addr}"

									if(type == "domain"){
										naver.maps.Service.geocode({
									        address: address
									    }, function(status, response) {
									        if (status == naver.maps.Service.Status.OK) {
										        var latitude = response.result.items[0].point.y;
										        var longitude = response.result.items[0].point.x;

										        mapConfig(latitude,longitude);
									        }
									    });
									}
									else{
										var latitude = ${requestScope.returnData.latitude};
										var longitude = ${requestScope.returnData.longitude};
										mapConfig(latitude,longitude);
									}

									function mapConfig(latitude, longitude){
										var latlng = new naver.maps.LatLng(latitude, longitude);

										var map = new naver.maps.Map('map', {
											mapTypeId : naver.maps.MapTypeId.NORMAL,
										    center: latlng,
										    zoom: 16,
										    zoomOrigin : latlng,
										    zoomControl: true,
									        zoomControlOptions: {
									            style: naver.maps.ZoomControlStyle.LARGE,
									            position: naver.maps.Position.TOP_RIGHT
									        },
										    mapTypeControl : true,
										    scaleControl: true,
										    scaleControlOptions: {
										        position: naver.maps.Position.RIGHT_BOTTOM
										    },
										    logoControl: false,
										    mapDataControl: true,
									        mapDataControlOptions: {
									            position: naver.maps.Position.LEFT_BOTTOM
									        }

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

										$("#tab-map").trigger("click");
									}
								}
							});
						</script>
						</c:if>
					</div>
					<div class="tab-pane fade container show active p-0" id="info">
						<textarea readonly="readonly" wrap="off" disabled="disabled" class="w-100 p-4" style="min-height:500px">${requestScope.returnContent}</textarea>
					</div>
				</div>
	   	</div>
   	</div>
   	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>