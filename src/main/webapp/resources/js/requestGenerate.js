document.addEventListener("DOMContentLoaded", () => {
	const form = document.getElementById("generateTestForm");

	if (form) {
       form.addEventListener("submit", event => {
           event.preventDefault();
           generateTest();
       });
	}
});

function generateTest() {
    const form = document.getElementById("generateTestForm");
    const formData = new FormData(form);
    
    // Converter FormData para URLSearchParams
    const params = new URLSearchParams(formData);
    
    console.log("Dados enviados:", params.toString());

    fetch(CONTEXT_PATH + "/action/generateTest", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: params
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erro ao gerar teste");
        }
        return response.json();
    })
    .then(data => {
        renderQuestions(data);
    })
    .catch(err => {
        console.error(err);
        alert("Erro ao gerar o teste");
    });
}

function renderQuestions(questions) {
    const container = document.getElementById("questionsContainer");
    container.innerHTML = "";

    if (!questions || questions.length === 0) {
        container.innerHTML = "<p>Nenhuma questão encontrada.</p>";
        return;
    }

    // Formulário.
    const form = document.createElement("form");
    form.id = "answerTestForm";

    // Título.
    const title = document.createElement("h2");
    title.textContent = "Perguntas";
    form.appendChild(title);

    // Perguntas.
    questions.forEach((q, index) => {
		// Hidden para cada questão.
	    const hiddenInput = document.createElement("input");
	    hiddenInput.type = "hidden";
	    hiddenInput.name = "questionIds";
	    hiddenInput.value = q.id;
	    form.appendChild(hiddenInput);
			
        const questionDiv = document.createElement("div");
        questionDiv.classList.add("question");

        // Enunciado.
        const statement = document.createElement("p");
        statement.innerHTML = `<strong>${index + 1}. ${q.statement}</strong>`;
        questionDiv.appendChild(statement);

        // Alternativas.
        q.options.forEach(opt => {
            const optionDiv = document.createElement("div");
            const input = document.createElement("input");
			
            input.type = "radio";
            input.name = `answer_${q.id}`;
            input.value = opt.id;
            input.required = true;

            optionDiv.appendChild(input);
            optionDiv.appendChild(document.createTextNode(` ${opt.text}`));

            questionDiv.appendChild(optionDiv);
        });

        form.appendChild(questionDiv);
    });

    // Botão para correção.
    const submitBtn = document.createElement("button");
    submitBtn.type = "submit";
    submitBtn.classList.add("btn-correct-test");
    submitBtn.innerHTML = `<i class="bi bi-check-lg"></i> Corrigir Teste`;

    form.appendChild(submitBtn);
    container.appendChild(form);
}