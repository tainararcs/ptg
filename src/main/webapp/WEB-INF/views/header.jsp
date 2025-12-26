<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.ptg.utils.Consts" %>

<!DOCTYPE html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<!-- BootStrap -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>

<body>
	<c:set var="user" value="${sessionScope.userLogged}"/>
   	
   	<header class="main-header">
	    <div class="header-content">
	        <div class="header-title">
	            <h1>PTG - Estudo Inteligente</h1>
	            <p>Seu estudo, do seu jeito.</p>
	        </div>
	
	        <div class="header-actions">
	        	<div class="user-info">
	                <i class="bi bi-person-circle"></i>${user.name}
	            </div>
	            
	            <a href="${pageContext.request.contextPath}/${Consts.ACTION_LOGOUT}" class="logout-btn">
	                <i class="bi bi-box-arrow-right"></i> Sair
	            </a>
	        </div>
	    </div>
	</header>
</body>