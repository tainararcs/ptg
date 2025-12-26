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