<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="br.trcs.ptg.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
	    <meta charset="UTF-8">
	    <title>PTG - Usuários</title>
	
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
	
	   <h2>Usuários cadastrados</h2>
		
		<table id="usersTable" cellpadding="5">
		    <thead>
		        <tr>
		            <th>ID</th>
		            <th>Nome</th>
		            <th>Perfil</th>
		        </tr>
		    </thead>
		    <tbody></tbody>
		</table>
		
		<p id="emptyMsg" style="display:none;">Nenhum usuário cadastrado</p>
	
		<script>
		    const CONTEXT_PATH = "${pageContext.request.contextPath}";
		</script>
		<script src="${pageContext.request.contextPath}/resources/js/requestUsers.js"></script>
	</body>
</html>
