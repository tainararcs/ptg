<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

		<c:choose>
		    <c:when test="${empty tests}"><p>Nenhum teste realizado</p></c:when>
		    <c:otherwise>
		        <table cellpadding="5">
		            <thead>
		                <tr>
		                    <th>Data</th><th>Usuário</th><th>Disciplina</th><th>Bimestre</th><th>Acertos</th><th>Aproveitamento</th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach var="t" items="${tests}">
		                    <tr>
		                        <td>${t.date}</td>
		                        <td>${t.userID.name}</td>
		                        <td>${t.subjectId.name}</td>
		                        <td>${t.bimester}</td>
		                        <td>${t.numberCorrectAnswers}</td>
		                        <td class="percentage-cell">${(t.numberCorrectAnswers * 100) / 5}%</td>
		                    </tr>
		                </c:forEach>
		            </tbody>
		        </table>
		    </c:otherwise>
		</c:choose>
		
		<a href="${pageContext.request.contextPath}/${Consts.ACTION_EXPORT_PDF}">
			<button type="button" >Exportar para PDF</button>
		</a>
	</body>
</html>