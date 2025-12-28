<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="br.trcs.ptg.utils.Consts" %>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>PTG - Erro 404</title>
        
        <!-- Fonte Inter -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700;900&display=swap" rel="stylesheet">

        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/error.css">
        
        <!-- Bootstrap Icons (opcional, para ícones nos botões) -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">

        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/img/book.png">
    </head>

    <body>
        <div class="error-container">
            <div class="error-box">
                <div class="error-code">404</div>
                
                <h1 class="error-title">Página não encontrada</h1>
                
                <p class="error-message">
                    A página ou ação que você tentou acessar não existe ou não está disponível
                </p>
                
                <a href="${pageContext.request.contextPath}/${Consts.HOME_PAGE}" class="error-button">
                    <i class="bi bi-house-door"></i> Voltar para Home
                </a>
            </div>
        </div>
    </body>
</html>