<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar bg-primary navbar-dark">
    <div class="navbar-header">
        <div class="px-2">
            <a href="/" class="navbar-brand font-24">
            	<img class="mr-1" alt="logo" src="/resources/images/logo.png" style="width:30px;">
            	<span>IP Search</span>
            </a>
			<c:if test="${requestScope.clientIp != null}">
            	<span class="text-white">접속 IP : ${requestScope.clientIp}</span>
            </c:if>
        </div>
    </div>
</nav>