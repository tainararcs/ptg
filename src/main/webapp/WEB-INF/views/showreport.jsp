<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="br.trcs.ptg.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
	    <meta charset="UTF-8">
	    <title>PTG - Relatório de Testes</title>
	
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
	
	    <h2>Relatório de Testes</h2>
	
	    <table id="testsTable" cellpadding="5">
	        <thead>
	            <tr>
	                <th>Data</th>
	                <th>Usuário</th>
	                <th>Disciplina</th>
	                <th>Bimestre</th>
	                <th>Acertos</th>
	                <th>Aproveitamento</th>
	            </tr>
	        </thead>
	        <tbody></tbody>
	    </table>
	
	    <p id="emptyMsg" style="display:none;">Nenhum teste realizado</p>
	
	    <a href="${pageContext.request.contextPath}/${Consts.ACTION_EXPORT_PDF}">
	        <button type="button">Exportar para PDF</button>
	    </a>
	
	    <script>
	        const CONTEXT_PATH = "${pageContext.request.contextPath}";
	        const TOTAL_QUESTIONS = ${Consts.NUMBER_QUESTIONS};
	    </script>
	    <script src="${pageContext.request.contextPath}/resources/js/requestTests.js"></script>
	</body>
</html>