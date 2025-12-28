document.addEventListener("DOMContentLoaded", () => {
    document.body.addEventListener("submit", event => {
        if (event.target.id === "answerTestForm") {
            event.preventDefault();
            correctTest(event.target);
        }
    });
});

function correctTest(form) {
    const formData = new FormData(form);
    const params = new URLSearchParams(formData); 

    fetch(CONTEXT_PATH + "/action/correctTest", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: params
    })
    .then(response => {
        if (!response.ok) throw new Error("Erro ao corrigir teste");
        return response.json();
    })
    .then(result => renderResult(result))
    .catch(err => console.error(err));
}

function renderResult(result) {
    const container = document.getElementById("questionsContainer");

    // Limpa qualquer resultado anterior.
    container.innerHTML = "";

    result.questions.forEach((q, index) => {
        const questionDiv = document.createElement("div");
        questionDiv.className = "question result-question";

        const p = document.createElement("p");
        const strong = document.createElement("strong");
        strong.textContent = `${index + 1}. ${q.statement}`;
        p.appendChild(strong);
        questionDiv.appendChild(p);

        q.options.forEach(opt => {
            const optionDiv = document.createElement("div");
            optionDiv.className = "option-result";

            const icon = document.createElement("i");
            if (opt.correct) {
                icon.className = "bi bi-check-circle-fill";
                optionDiv.classList.add("correct-answer");
            } else if (q.userAnswerId === opt.id) {
                icon.className = "bi bi-x-circle-fill";
                optionDiv.classList.add("wrong-answer");
            } else {
                icon.className = "bi bi-circle";
            }
            optionDiv.appendChild(icon);

            const spanText = document.createElement("span");
            spanText.textContent = opt.text;
            optionDiv.appendChild(spanText);

            if (q.userAnswerId === opt.id && !opt.correct) {
                const badge = document.createElement("span");
                badge.className = "badge-wrong";
                badge.textContent = "Sua resposta";
                optionDiv.appendChild(badge);
            }
            if (opt.correct) {
                const badge = document.createElement("span");
                badge.className = "badge-correct";
                badge.textContent = "Resposta correta";
                optionDiv.appendChild(badge);
            }

            questionDiv.appendChild(optionDiv);
        });
		
		// Resultado final.
		const percentage = Math.round((result.score * 100) / result.total);
	    let finalDiv = document.getElementById("finalScore");
	    if (!finalDiv) {
	        finalDiv = document.createElement("div");
	        finalDiv.id = "finalScore";
	        finalDiv.className = "final-score";
	        container.appendChild(finalDiv);
	    }
		
	    finalDiv.innerHTML = `
	        <h3><i class="bi bi-trophy-fill"></i> Você acertou ${result.score} de ${result.total} questões</h3>
	        <p class="score-percentage">${percentage}%</p>
	        <a href="${CONTEXT_PATH}/action/prepareAddTests" class="btn-new-test">
	            <i class="bi bi-arrow-repeat"></i> Fazer outro teste
	        </a>
	    `;
		
        container.appendChild(questionDiv);
		container.appendChild(finalDiv);
    });
}