document.addEventListener("DOMContentLoaded", () => {
    loadUsers();
});

function loadUsers() {
    fetch(CONTEXT_PATH + "/action/listUsers")
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao buscar usuários");
            }
            return response.json();
        })
        .then(usersList => renderUsers(usersList))
        .catch(err => console.error(err));
}

function renderUsers(usersList) {
    const tbody = document.querySelector("#usersTable tbody");
    const emptyMsg = document.getElementById("emptyMsg");

    tbody.innerHTML = "";

    if (!usersList || usersList.length === 0) {
        emptyMsg.style.display = "block";
        return;
    }

    emptyMsg.style.display = "none";

    usersList.forEach(user => {
        const tr = document.createElement("tr");

        tr.innerHTML = `
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.profile}</td>
        `;

        tbody.appendChild(tr);
    });
}