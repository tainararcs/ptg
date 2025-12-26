<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	    
	    <!-- Formulário de geração do teste -->
	    <c:if test="${empty sessionScope.showResults}">
			<div class="form-container">
				<form action="${pageContext.request.contextPath}/${Consts.ACTION_GENERATE_TEST}" method="post">
				    <h3>Série</h3>
				    <label><input type="radio" name="grade" value="FIRST_GRADE" ${sessionScope.grade == 'FIRST_GRADE' ? 'checked' : ''} required>Primeiro ano</label>
				    <label><input type="radio" name="grade" value="SECOND_GRADE" ${sessionScope.grade == 'SECOND_GRADE' ? 'checked' : ''}>Segundo ano</label>
				    <label><input type="radio" name="grade" value="THIRD_GRADE" ${sessionScope.grade == 'THIRD_GRADE' ? 'checked' : ''}>Terceiro ano</label>
				
				    <h3>Disciplina</h3>
					<select name="subjectId" required>
					    <option>Carregando...</option>
					</select>
					
				    <h3>Bimestre</h3>
					<label><input type="radio" name="bimester" value="FIRST_BIMESTER" ${sessionScope.bimester == 'FIRST_BIMESTER' ? 'checked' : ''} required>Primeiro Bimestre</label>
					<label><input type="radio" name="bimester" value="SECOND_BIMESTER" ${sessionScope.bimester == 'SECOND_BIMESTER' ? 'checked' : ''}>Segundo Bimestre</label>
					<label><input type="radio" name="bimester" value="THIRD_BIMESTER" ${sessionScope.bimester == 'THIRD_BIMESTER' ? 'checked' : ''}>Terceiro Bimestre</label>
					<label><input type="radio" name="bimester" value="FOURTH_BIMESTER" ${sessionScope.bimester == 'FOURTH_BIMESTER' ? 'checked' : ''}>Quarto Bimestre</label>
				
				    <h3>Matéria</h3>
					<select name="topicId" required>
					    <option>Selecione uma disciplina</option>
					</select>
				
				    <button type="submit"><i class="bi bi-pencil-square"></i> Gerar Teste </button>
				</form>
	        </div>
		</c:if>
		
	    <!-- Questões do teste -->
	    <c:if test="${not empty sessionScope.selectedQuestions}">
	        <c:choose>
	            <c:when test="${sessionScope.showResults}">
	                <!-- Modo resultado -->
	                <div class="test-results-container">
	                    <c:forEach var="q" items="${sessionScope.selectedQuestions}" varStatus="status">
	                        <div class="question result-question">
	                            <p><strong>${status.index + 1}. ${q.statement}</strong></p>
	                            
	                            <c:set var="userAnswerId" value="${sessionScope.userAnswers[q.id]}"/>
	                            
	                            <c:forEach var="opt" items="${q.optionsList}">
	                                <c:set var="isUserAnswer" value="${opt.id == userAnswerId}"/>
	                                <c:set var="isCorrect" value="${opt.correct}"/>
	                                
	                                <div class="option-result 
	                                    ${isCorrect ? 'correct-answer' : ''} 
	                                    ${isUserAnswer && !isCorrect ? 'wrong-answer' : ''}">
	                                    
	                                    <c:choose>
	                                        <c:when test="${isCorrect}"><i class="bi bi-check-circle-fill"></i></c:when>
	                                        <c:when test="${isUserAnswer && !isCorrect}"><i class="bi bi-x-circle-fill"></i></c:when>
	                                        <c:otherwise><i class="bi bi-circle"></i></c:otherwise>
	                                    </c:choose>
	                                    
	                                    <span>${opt.text}</span>
	                                    
	                                    <c:if test="${isUserAnswer && !isCorrect}"><span class="badge-wrong">Sua resposta</span></c:if>
	                                    <c:if test="${isCorrect}"><span class="badge-correct">Resposta correta</span></c:if>
	                                </div>
	                            </c:forEach>
	                        </div>
	                    </c:forEach>
	                    
	                    <!-- Score final -->
	                    <div class="final-score">
	                        <h3>
	                        	<i class="bi bi-trophy-fill"></i>
	                        	Você acertou ${sessionScope.score} de ${fn:length(sessionScope.selectedQuestions)} questões
	                        </h3>
	                        
	                        <p class="score-percentage">${(sessionScope.score * 100) / fn:length(sessionScope.selectedQuestions)}%</p>
	                        
	                        <a href="${pageContext.request.contextPath}/${Consts.ACTION_PREPARE_TESTS}" class="btn-new-test">
	                        	<i class="bi bi-arrow-repeat"></i> Fazer outro teste
	                        </a>
	                    </div>
	                </div>
	            </c:when>
	            
	            <c:otherwise>
	                <!-- Modo responder -->
	                <div class="form-container">
					    <form action="${pageContext.request.contextPath}/${Consts.ACTION_ADD_TEST}" method="post">
					        <input type="hidden" name="subjectId" value="${sessionScope.subjectId}">
					        <input type="hidden" name="bimester" value="${sessionScope.bimester}"> 
					        
					        <h2>Perguntas</h2>
					        <c:forEach var="q" items="${sessionScope.selectedQuestions}" varStatus="status">
					            <input type="hidden" name="questionIds" value="${q.id}"/>
					            
					            <div class="question">
					                <p><strong>${status.index + 1}. ${q.statement}</strong></p>
					                <c:forEach var="opt" items="${q.optionsList}">
					                    <div>
					                        <input type="radio" name="answer_${q.id}" value="${opt.id}" required/> ${opt.text}
					                    </div>
					                </c:forEach>
					            </div>
					        </c:forEach>
					
					        <button type="submit" class="btn-correct-test"><i class="bi bi-check-lg"></i> Corrigir Teste</button>
					    </form>
					</div>
	            </c:otherwise>
	        </c:choose>
	    </c:if>
	    
	    <script>
		    const CONTEXT_PATH = "${pageContext.request.contextPath}";
		</script>
		<script src="${pageContext.request.contextPath}/resources/js/requestTopic.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/requestSubject.js"></script>
	</body>
</html>