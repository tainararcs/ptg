<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="br.trcs.ptg.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
	    <meta charset="UTF-8">
	    <title>PTG - Questões</title>
	
	    <!-- Fonte -->
	    <link rel="preconnect" href="https://fonts.googleapis.com">
	    <link rel="preconnect" href="https://fonts.gstatic.com">
	    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
	
	    <!-- CSS -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form.css">
	
	    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/img/book.png">
	</head>
	
	<body>
	    <jsp:include page="${Consts.MENU_JSP}"/>
	    <jsp:include page="${Consts.HEADER_JSP}"/>
	
	   <h2>Questões</h2>
		
		<table id="questionsTable" cellpadding="5">
		    <thead>
		        <tr>
		            <th>ID</th>
		            <th>Enunciado</th>
		            <th>Disciplina</th>
		            <th>Matéria</th>
		        </tr>
		    </thead>
		    <tbody></tbody>
		</table>
		
		<p id="emptyMsg" style="display:none;">Nenhuma questão cadastrado</p>
	
		<script>
		    const CONTEXT_PATH = "${pageContext.request.contextPath}";
		</script>
		<script src="${pageContext.request.contextPath}/resources/js/requestQuestions.js"></script>
	</body>
</html>