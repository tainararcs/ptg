<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="br.trcs.ptg.utils.Consts" %>

<head>
    <meta charset="UTF-8">
    <title>PTG - Home</title>

    <!-- Fonte Inter -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu.css">
	
	<!-- BootStrap -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
	
<body>
	<button class="menu-toggle" onclick="toggleMenu()">
	    <i class="bi bi-list"></i>
	</button>

	<nav class="sidebar">
    	<div class="sidebar-logo">PTG</div>

	    <ul class="menu">
	        <li class="menu-item">
	            <a href="${pageContext.request.contextPath}/${Consts.HOME_PAGE}">
	                <i class="bi bi-house-door"></i><span>Home</span>
	            </a>
	        </li>
	
	        <c:set var="user" value="${sessionScope.userLogged}" />
	
	        <!-- Funcionalidades apenas do admin -->
	        <c:if test="${user.profile != null and fn:toLowerCase(user.profile) eq 'admin'}">
	            <li class="menu-item">
	                <a href="${pageContext.request.contextPath}/${Consts.ADD_SUBJECT_PAGE}">
	                    <i class="bi bi-journals"></i><span>Cadastrar Disciplina</span>
	                </a>
	            </li>
	
	            <li class="menu-item">
	                <a href="${pageContext.request.contextPath}/${Consts.ADD_TOPIC_PAGE}">
	                    <i class="bi bi-journal-text"></i><span>Cadastrar Matéria</span>
	                </a>
	            </li>
	
	            <li class="menu-item">
	                <a href="${pageContext.request.contextPath}/${Consts.ADD_QUESTION_PAGE}">
	                    <i class="bi bi-question-square"></i><span>Cadastrar Questão</span>
	                </a>
	            </li>
	
	            <li class="menu-item">
	                <a href="${pageContext.request.contextPath}/${Consts.ADD_USER_PAGE}">
	                    <i class="bi bi-person-square"></i><span>Cadastrar Usuário</span>
	                </a>
	            </li>
	        </c:if>
	
	        <!-- Funcionalidades comuns -->
	        <li class="menu-item">
	            <a href="${pageContext.request.contextPath}/${Consts.ACTION_PREPARE_TESTS}">
	                <i class="bi bi-pencil-square"></i><span>Gerar Teste</span>
	            </a>
	        </li>
	
	        <li class="menu-item">
	            <a href="${pageContext.request.contextPath}/${Consts.ACTION_GENERATE_REPORT}">
	                <i class="bi bi-card-list"></i><span>Gerar Relatório</span>
	            </a>
	        </li>
	    </ul>
	
	    <jsp:include page="${Consts.FOOTER_JSP}" />
	</nav>
  	
  	<script src="${pageContext.request.contextPath}resources/js/menu.js"></script>
</body>
