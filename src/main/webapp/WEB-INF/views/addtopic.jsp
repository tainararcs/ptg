<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="br.trcs.ptg.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
	    <meta charset="UTF-8">
	    <title>PTG - Cadastrar Matéria</title>
	    
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
		
	    <h2>Cadastrar Matéria</h2>
		
	    <div class="form-container">
			<form action="${pageContext.request.contextPath}/${Consts.ACTION_ADD_TOPIC}" method="post">
				
                <h3>Nome da matéria</h3>
                <input type="text" name="name" required>

			    <h3>Série</h3>
			    <label><input type="radio" name="grade" value="FIRST_GRADE" required>Primeiro ano</label>
			    <label><input type="radio" name="grade" value="SECOND_GRADE">Segundo ano</label>
			    <label><input type="radio" name="grade" value="THIRD_GRADE">Terceiro ano</label>

			    <h3>Bimestre</h3>
			    <label><input type="radio" name="bimester" value="FIRST_BIMESTER" required>Primeiro Bimestre</label>
			    <label><input type="radio" name="bimester" value="SECOND_BIMESTER">Segundo Bimestre</label>
			    <label><input type="radio" name="bimester" value="THIRD_BIMESTER">Terceiro Bimestre</label>
			    <label><input type="radio" name="bimester" value="FOURTH_BIMESTER">Quarto Bimestre</label>
	
               	<h3>Disciplina</h3>
                <select name="subjectId" required>
				    <option>Carregando...</option>
				</select>

	            <button type="submit">Cadastrar</button>
	        </form>
	    </div>
	    
	    <script>
		    const CONTEXT_PATH = "${pageContext.request.contextPath}";
		</script>
		<script src="${pageContext.request.contextPath}/resources/js/requestSubjects.js"></script>
	</body>
</html>