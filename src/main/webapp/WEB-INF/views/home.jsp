<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="br.trcs.ptg.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
	    <meta charset="UTF-8">
	    <title>PTG - Home</title>
	
	    <!-- Fonte Inter -->
	    <link rel="preconnect" href="https://fonts.googleapis.com">
	    <link rel="preconnect" href="https://fonts.gstatic.com">
	    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
	
	    <!-- CSS -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
	
		<!-- BootStrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
		
	    <!-- Favicon -->
	    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/img/book.png">
	</head>
	
	<body>
		<jsp:include page="${Consts.HEADER_JSP}"/>
		
		<main class="container">
		    <section class="dashboard">
		    	<c:set var="user" value="${sessionScope.userLogged}" />
				
		    	<!-- Só admins tem acesso -->
				<c:if test="${fn:toLowerCase(user.profile) eq 'admin'}">
				
					<a href="${pageContext.request.contextPath}/${Consts.ADD_SUBJECT_PAGE}" class="card">
						<i class="bi bi-journals"></i>
		                <h3>Cadastrar Disciplina</h3>
		                <p>Registre as disciplinas do currículo escolar, como Matemática ou Português</p>
		            </a>
		            
		            <a href="${pageContext.request.contextPath}/${Consts.ADD_TOPIC_PAGE}" class="card">
		                <i class="bi bi-journal-text"></i>
		                <h3>Cadastrar Matéria</h3>
		                <p>Adicione matérias  relacionada a uma disciplina, como Geometria ou Genética</p>
		            </a>
		            
		            <a href="${pageContext.request.contextPath}/${Consts.ADD_QUESTION_PAGE}" class="card">
		                <i class="bi bi-question-square"></i>
		                <h3>Cadastrar Questão</h3>
		                <p>Crie e gerencie questões de múltipla escolha para compor os testes das matérias</p>
		            </a>
		            
		            <a href="${pageContext.request.contextPath}/${Consts.ADD_USER_PAGE}" class="card">
		                <i class="bi bi-person-square"></i>
		                <h3>Cadastrar Usuário</h3>
		                <p>Cadastre novos usuários e autorize o acesso à plataforma de estudos</p>
		            </a>
		            
	            </c:if>
	            
		        <a href="${pageContext.request.contextPath}/${Consts.ACTION_PREPARE_TESTS}" class="card">
		            <i class="bi bi-pencil-square"></i>
		            <h3>Gerar Teste</h3>
		            <p>Crie testes a partir de disciplinas, conteúdos e bimestres cadastrados</p>
		        </a>
		
		        <a href="${pageContext.request.contextPath}/${Consts.SHOW_REPORT_PAGE}" class="card">
		            <i class="bi bi-postcard-fill"></i>
		            <h3>Gerar Relatório</h3>
		            <p>Visualize o desempenho e progresso através de relatórios dos testes realizados</p>
		        </a>
		        
		        <c:if test="${user.profile != null and fn:toLowerCase(user.profile) eq 'admin'}">
			        <a href="${pageContext.request.contextPath}/${Consts.SHOW_USER_PAGE}" class="card">
			            <i class="bi bi-person-vcard"></i>
		                <h3>Listar Usuários</h3>
		                <p>Visualize todos os usuários cadastrados</p>
		            </a>
		        </c:if>
		        
		        <a href="${pageContext.request.contextPath}/${Consts.SHOW_QUESTION_PAGE}" class="card">
		            <i class="bi bi-card-list"></i>
	                <h3>Listar Questões</h3>
	                <p>Visualize todas as questões cadastradas</p>
	            </a>
		    </section>
		</main>
		
		<jsp:include page="${Consts.FOOTER_JSP}"/>
		<script src="${pageContext.request.contextPath}/js/home.js"></script>
	</body>
</html>