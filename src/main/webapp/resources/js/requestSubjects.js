document.addEventListener("DOMContentLoaded", () => {
    fetch(CONTEXT_PATH + "/action/listSubject", {
        headers: { "X-Requested-With": "XMLHttpRequest" }
    })
    .then(res => res.json())
    .then(subjects => {
        const select = document.querySelector("select[name='subjectId']");
        select.innerHTML = "";

        if (!subjects || subjects.length === 0) {
            select.innerHTML = "<option>Nenhuma disciplina cadastrada</option>";
            return;
        }

        subjects.forEach(s => {
            const opt = document.createElement("option");
            opt.value = s.id;
            opt.textContent = s.name;
            select.appendChild(opt);
        });

        // Dispara um evento de change automaticamente.
        select.dispatchEvent(new Event("change"));
    })
    .catch(err => console.error(err));
});