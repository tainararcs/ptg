<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- Mensagem de erro (request ou sessão) -->
<c:if test="${not empty requestScope.error}">
   <div class="error-message feedback">${requestScope.error}</div>
</c:if>

<c:if test="${not empty sessionScope.error}">
   <div class="error-message feedback">${sessionScope.error}</div>
   <c:remove var="error" scope="session"/>
</c:if>

<!-- Mensagem de sucesso (request ou sessão) -->
<c:if test="${not empty requestScope.msg}">
   <div class="success-message feedback">${requestScope.msg}</div>
</c:if>

<c:if test="${not empty sessionScope.msg}">
   <div class="success-message feedback">${sessionScope.msg}</div>
   <c:remove var="msg" scope="session"/>
</c:if>

<style>
	/* Mensagens de feedback (overlay centralizado) */
	.error-message,
	.success-message {
	    position: absolute;
	    top: 10px;
	    left: 43%;
	    transform: translateX(-50%);
	    display: flex;
	    align-items: center;
	    justify-content: center;
	    max-width: 700px;
	    width: calc(100% - 2rem);
	    padding: var(--spacing-md) var(--spacing-xl);
	    border-radius: var(--radius-lg);
	    z-index: 9999;
	    animation: fadeIn 0.4s ease-out;
	}
	
	/* Erro */
	.error-message {
	    background: rgba(239, 68, 68, 0.12);
	    color: #991b1b;
	    box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);
	}
	
	/* Texto erro */
	.error-message p {
	    font-weight: 600;
	    color: #991b1b;
	    text-align: center;
	}
	
	/* Sucesso */
	.success-message {
	    background: rgba(16, 185, 129, 0.12);
	    color: #065f46;
	    box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);
	}
	
	/* Texto sucesso */
	.success-message h3 {
	    font-weight: 700;
	    color: #065f46;
	    font-size: 1.1rem;
	    text-align: center;
	}
	
	
	/* Celular */
	@media (max-width: 768px) {
	    .error-message,
	    .success-message {
	        margin: var(--spacing-lg) var(--spacing-md);
	    }
	}
</style>

<script>
   const feedbacks = document.querySelectorAll('.feedback');

   feedbacks.forEach(feedback => {
       setTimeout(() => feedback.classList.add('show'), 100);
       setTimeout(() => {
           feedback.classList.remove('show');
           setTimeout(() => feedback.remove(), 500);
       }, 3000);
   });
</script>