<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="br.trcs.ptg.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>PTG - Cadastrar Disciplina</title>
		
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
		
		<h2>Cadastrar Disciplina</h2>
				
		<div class="form-container">
			<form action="${pageContext.request.contextPath}/${Consts.ACTION_ADD_SUBJECT}" method="post">
				<h3>Nome da disciplina</h3>
				<input type="text" name="name" required>
				
				<button type="submit">Cadastrar</button>
			</form>
		</div>
	</body>
</html>