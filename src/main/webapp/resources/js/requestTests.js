document.addEventListener("DOMContentLoaded", () => {
    loadTests();
});

function loadTests() {
    fetch(CONTEXT_PATH + "/action/listTests")
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao buscar relatório");
            }
            return response.json();
        })
        .then(testsList => renderTests(testsList))
        .catch(err => console.error(err));
}

function renderTests(testsList) {
    const tbody = document.querySelector("#testsTable tbody");
    const emptyMsg = document.getElementById("emptyMsg");

    tbody.innerHTML = "";

    if (!testsList || testsList.length === 0) {
        emptyMsg.style.display = "block";
        return;
    }

    emptyMsg.style.display = "none";

    testsList.forEach(test => {
        const percentage = Math.round(
            (test.numberCorrectAnswers * 100) / TOTAL_QUESTIONS
        );

        const tr = document.createElement("tr");

        tr.innerHTML = `
            <td>${test.date}</td>
            <td>${test.user}</td>
            <td>${test.subject}</td>
            <td>${test.bimester}</td>
            <td>${test.numberCorrectAnswers}</td>
            <td class="percentage-cell">${percentage}%</td>
        `;

        tbody.appendChild(tr);
    });
}