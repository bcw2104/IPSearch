<%@page import="com.ipsearch.util.GlobalPath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar bg-primary navbar-dark">
    <div class="navbar-header w-100">
        <div class="px-2">
            <a href="<%=GlobalPath.root%>" class="navbar-brand font-24">
            	<img class="mr-1" alt="logo" src="<%=GlobalPath.root%>resources/images/logo.png" style="width:30px;">
            	<span>IP Search</span>
            </a>
			<c:if test="${requestScope.clientIp != null}">
            	<span class="text-white">접속 IP : ${requestScope.clientIp}</span>
            </c:if>
            <a href="https://www.lifencoding.com/" class="navbar-brand float-right mt-1">Life &amp; Coding</a>
        </div>
    </div>
</nav>