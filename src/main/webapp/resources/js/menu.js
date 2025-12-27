// Função para abrir/fechar o menu mobile.
function toggleMenu() {
    const nav = document.querySelector('nav');
    const body = document.body;
    const menuToggle = document.querySelector('.menu-toggle');
    
    nav.classList.toggle('active');
    body.classList.toggle('menu-open');
    
    // Muda o ícone do botão
    const icon = menuToggle.querySelector('i');
    if (nav.classList.contains('active')) {
        icon.className = 'bi bi-x-lg';
    } else {
        icon.className = 'bi bi-list';
    }
}

// Fecha o menu ao clicar no overlay (fundo escuro).
document.addEventListener('DOMContentLoaded', function() {
    document.body.addEventListener('click', function(e) {
        const nav = document.querySelector('nav');
        const menuToggle = document.querySelector('.menu-toggle');
        
        // Se o menu está aberto e clicou fora do menu e do botão.
        if (nav.classList.contains('active') && 
            !nav.contains(e.target) && 
            !menuToggle.contains(e.target)) {
            toggleMenu();
        }
    });
    
    // Fecha o menu ao clicar em qualquer link.
    const menuLinks = document.querySelectorAll('nav a');
    menuLinks.forEach(link => {
        link.addEventListener('click', function() {
            const nav = document.querySelector('nav');
            if (nav.classList.contains('active')) {
                toggleMenu();
            }
        });
    });
});

// Fecha o menu ao redimensionar para desktop.
window.addEventListener('resize', function() {
    if (window.innerWidth > 768) {
        const nav = document.querySelector('nav');
        const body = document.body;
        nav.classList.remove('active');
        body.classList.remove('menu-open');
        
        const icon = document.querySelector('.menu-toggle i');
        if (icon) {
            icon.className = 'bi bi-list';
        }
    }
});