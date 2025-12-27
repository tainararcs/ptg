<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.ptg.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>PTG - Login</title>
	    
	    <!-- Fonte Inter -->
	    <link rel="preconnect" href="https://fonts.googleapis.com">
	    <link rel="preconnect" href="https://fonts.gstatic.com">
	    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700;900&display=swap" rel="stylesheet">
	
	    <!-- CSS -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/base.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
	
	    <!-- Favicon -->
	    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/img/book.png">
	</head>

	<body>
		<jsp:include page="${Consts.MESSAGE_JSP}"/>
		
	    <div class="login-wrapper">
	        <!-- Seção Visual Esquerda -->
	        <div class="login-visual">
	            <div class="visual-content">
	                <div class="logo-large">PTG</div>
	                <h1 class="visual-title">Estudo Inteligente</h1>
	                <p class="visual-subtitle">
	                	Transforme sua forma de estudar com testes personalizados e acompanhamento completo do seu progresso
	                </p>
	            </div>
	        </div>
	
	        <!-- Seção Formulário Direita -->
	        <div class="login-form-section">
	            <div class="login-container">
	                <h2>Bem-vindo de volta</h2>
	                <p class="login-subtitle">Entre com suas credenciais para continuar</p>
	                
	                <form action="${pageContext.request.contextPath}/${Consts.ACTION_LOGIN}" method="post">
	                    <div class="name">
	                        <label for="username">Nome de Usuário</label>
	                        <input type="text" id="username" name="username" placeholder="Digite seu nome" required autocomplete="username">
	                    </div>
	                    
	                    <div class="password">
	                        <label for="password">Senha</label>
	                        <input type="password" id="password"  name="password" placeholder="Digite sua senha" required autocomplete="current-password">
	                    </div>
	                    
	                    <button type="submit">Entrar</button>
	                </form>
	                
	                <jsp:include page="${Consts.FOOTER_JSP}"/>
	            </div>
	        </div>
	    </div>
	</body>
</html>