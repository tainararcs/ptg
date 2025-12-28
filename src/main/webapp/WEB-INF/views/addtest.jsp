<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="br.trcs.ptg.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
	    <meta charset="UTF-8">
	    <title>PTG - Teste</title>
	
	    <!-- Fonte Inter -->
	    <link rel="preconnect" href="https://fonts.googleapis.com">
	    <link rel="preconnect" href="https://fonts.gstatic.com">
	    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
	
	    <!-- CSS -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form.css">
	
	    <!-- Bootstrap Icons -->
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
	
	    <!-- Favicon -->
	    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/img/book.png">
	</head>
	
	<body>
	    <jsp:include page="${Consts.MENU_JSP}"/>
	    <jsp:include page="${Consts.HEADER_JSP}"/>
	    <jsp:include page="${Consts.MESSAGE_JSP}"/>
	
	    <h2>Teste</h2>
	
	    <!-- FORMULÁRIO GERAR TESTE -->
	    <div class="form-container" id="generateTestContainer">
	
	        <form id="generateTestForm">
	            <h3>Série</h3>
	            <label><input type="radio" name="grade" value="FIRST_GRADE" required> Primeiro ano</label>
	            <label><input type="radio" name="grade" value="SECOND_GRADE"> Segundo ano</label>
	            <label><input type="radio" name="grade" value="THIRD_GRADE"> Terceiro ano</label>
	
	            <h3>Disciplina</h3>
	            <select name="subjectId" id="subjectSelect" required>
	                <option value="">Carregando...</option>
	            </select>
	
	            <h3>Bimestre</h3>
	            <label><input type="radio" name="bimester" value="FIRST_BIMESTER" required> Primeiro Bimestre</label>
	            <label><input type="radio" name="bimester" value="SECOND_BIMESTER"> Segundo Bimestre</label>
	            <label><input type="radio" name="bimester" value="THIRD_BIMESTER"> Terceiro Bimestre</label>
	            <label><input type="radio" name="bimester" value="FOURTH_BIMESTER"> Quarto Bimestre</label>
	
	            <h3>Matéria</h3>
	            <select name="topicId" id="topicSelect" required>
	                <option value="">Selecione uma disciplina</option>
	            </select>
	
	            <button type="submit">
	                <i class="bi bi-pencil-square"></i> Gerar Teste
	            </button>
	        </form>
	    </div>
	
	    <!-- CONTAINER DAS QUESTÕES -->
	    <div id="questionsContainer" class="form-container"></div>
	
	    <script>
	        const CONTEXT_PATH = "${pageContext.request.contextPath}";
	        const TOTAL_QUESTIONS = ${Consts.NUMBER_QUESTIONS};
	    </script>
	
	    <script src="${pageContext.request.contextPath}/resources/js/requestSubjects.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/requestTopics.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/requestGenerate.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/js/requestCorrection.js"></script>
	</body>
</html>