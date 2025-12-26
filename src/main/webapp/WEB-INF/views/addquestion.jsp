<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.ptg.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>PTG - Cadastrar Questão</title>
		
		<!-- Fonte Inter -->
	    <link rel="preconnect" href="https://fonts.googleapis.com">
	    <link rel="preconnect" href="https://fonts.gstatic.com">
	    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
	
	    <!-- CSS -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form.css">
		
	    <!-- Favicon -->
	    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/img/book.png">
	</head>
	
	<body>
		<jsp:include page="${Consts.MENU_JSP}"/>
		<jsp:include page="${Consts.HEADER_JSP}"/>
		<jsp:include page="${Consts.MESSAGE_JSP}"/>
	    
    	<h2>Cadastrar Questão</h2>
		
		<div class="form-container">
			<form action="${pageContext.request.contextPath}/${Consts.ACTION_ADD_QUESTION}" method="post">
	        	<h3>Disciplina</h3>
				<select name="subjectId" required>
				    <option>Carregando...</option>
				</select>
			
				<h3>Matéria</h3>
				<select name="topicId" required>
				    <option>Carregando...</option>
				</select>
			
				<h3>Enunciado</h3>
				<textarea name="statement" rows="4" cols="50" required></textarea>
				
				<span class="options">
				    <h3>Alternativas</h3>
				    <c:forEach begin="0" end="4" var="o">
					    <div>
					        <input type="radio" name="correctOption" value="${o}" required>
					        <input type="text" name="options" placeholder="Alternativa ${o + 1}" required>
					    </div>
					</c:forEach>
				</span>
				
				<h3>Observação</h3>
				<textarea name="obs" rows="4" cols="50"></textarea>
				
				<button type="submit">Cadastrar</button>
			</form>
		</div>
		
		<script>
		    const CONTEXT_PATH = "${pageContext.request.contextPath}";
		</script>
		<script src="${pageContext.request.contextPath}/resources/js/requestTopic.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/requestSubject.js"></script>
	</body>
</html>