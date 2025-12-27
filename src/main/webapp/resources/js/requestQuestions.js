document.addEventListener("DOMContentLoaded", () => {
    loadQuestions();
});

function loadQuestions() {
    fetch(CONTEXT_PATH + "/action/listQuestions")
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao buscar questões");
            }
            return response.json();
        })
        .then(questionsList => renderQuestions(questionsList))
        .catch(err => console.error(err));
}

function renderQuestions(questionsList) {
    const tbody = document.querySelector("#questionsTable tbody");
    const emptyMsg = document.getElementById("emptyMsg");

    tbody.innerHTML = "";

    if (!questionsList || questionsList.length === 0) {
        emptyMsg.style.display = "block";
        return;
    }

    emptyMsg.style.display = "none";

    questionsList.forEach(question => {
        const tr = document.createElement("tr");

        tr.innerHTML = `
            <td>${question.id}</td>
            <td>${question.statement}</td>
            <td>${question.subjectId?.name ?? "-"}</td>
            <td>${question.topicId?.name ?? "-"}</td>
        `;

        tbody.appendChild(tr);
    });
}