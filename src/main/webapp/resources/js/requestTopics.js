document.addEventListener("DOMContentLoaded", () => {

    const subjectSelect = document.querySelector("select[name='subjectId']");
    const topicSelect   = document.querySelector("select[name='topicId']");

    function loadTopics() {
        const subjectId = subjectSelect.value;
        if (!subjectId) return;

        topicSelect.innerHTML = "<option>Carregando...</option>";

        fetch(CONTEXT_PATH + "/action/listTopic?subjectId=" + subjectId, {
            headers: { "X-Requested-With": "XMLHttpRequest" }
        })
        .then(res => res.json())
        .then(topics => {
            topicSelect.innerHTML = "";

            if (!topics || topics.length === 0) {
                topicSelect.innerHTML = "<option>Nenhuma matéria cadastrada</option>";
                return;
            }

			topics.forEach((t, index) => { 
			    const opt = document.createElement("option");
			    opt.value = t.id;
			    opt.textContent = t.name;
			    topicSelect.appendChild(opt);
			    
			    // Seleciona a primeira automaticamente
			    if (index === 0) {
			        topicSelect.value = t.id;
			    }

            });
        })
        .catch(err => console.error(err));
    }

    // Listener normal.
    subjectSelect.addEventListener("change", loadTopics);

    // Garante carregamento inicial.
    if (subjectSelect.value) {
        loadTopics();
    }
});