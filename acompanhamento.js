document.addEventListener("DOMContentLoaded", () => {
  const lista = document.getElementById("listaDenuncias");
  const filtro = document.getElementById("filtro");
  const buscaInput = document.getElementById("busca");

  const statusIcon = {
    "Aberta": "🟥",
    "Em Andamento": "🟡",
    "Resolvida": "✅"
  };

  function carregarDenuncias() {
    lista.innerHTML = "";
    const denuncias = JSON.parse(localStorage.getItem("denuncias") || "[]");
    const filtroStatus = filtro.value;
    const termoBusca = buscaInput.value.trim();

    const filtradas = denuncias.filter((d) => {
      const statusOk = filtroStatus === "todas" || d.status === filtroStatus;
      const buscaOk = termoBusca === "" || d.protocolo.includes(termoBusca);
      return statusOk && buscaOk;
    });

    filtradas.forEach((den) => {
      const div = document.createElement("div");
      div.classList.add("denuncia");

      const emojiStatus = `${statusIcon[den.status]} ${den.status}`;
      const statusClass = den.status.toLowerCase().replace(" ", "");

      div.innerHTML = `
        <h3>${den.titulo}</h3>
        <p>${den.descricao}</p>
        <p><strong>Local:</strong> ${den.endereco}</p>
        <p><strong>Data:</strong> ${den.data}</p>
        <p><strong>Protocolo:</strong> ${den.protocolo}</p>
        <p>${den.anonima ? "Denúncia Anônima" : "Por: " + den.nome}</p>
        <span class="status ${statusClass}">${emojiStatus}</span>
        <button class="alterarStatus" data-protocolo="${den.protocolo}">Alterar Status</button>
      `;

      const btn = div.querySelector(".alterarStatus");
      btn.addEventListener("click", () => {
        alterarStatus(den.protocolo);
      });

      lista.appendChild(div);
    });

    atualizarResumo(denuncias);
  }

  function alterarStatus(protocolo) {
    let denuncias = JSON.parse(localStorage.getItem("denuncias") || "[]");
    const denuncia = denuncias.find((d) => d.protocolo === protocolo);

    if (denuncia) {
      switch (denuncia.status) {
        case "Aberta":
          denuncia.status = "Em Andamento";
          break;
        case "Em Andamento":
          denuncia.status = "Resolvida";
          break;
        case "Resolvida":
          denuncia.status = "Aberta";
          break;
      }
      localStorage.setItem("denuncias", JSON.stringify(denuncias));
      carregarDenuncias();
    }
  }

  function atualizarResumo(denuncias) {
    const total = denuncias.length;
    const abertas = denuncias.filter((d) => d.status === "Aberta").length;
    const andamento = denuncias.filter((d) => d.status === "Em Andamento").length;
    const resolvidas = denuncias.filter((d) => d.status === "Resolvida").length;

    document.getElementById("total").textContent = total;
    document.getElementById("abertas").textContent = abertas;
    document.getElementById("andamento").textContent = andamento;
    document.getElementById("resolvidas").textContent = resolvidas;
  }

  filtro.addEventListener("change", carregarDenuncias);
  buscaInput.addEventListener("input", carregarDenuncias);

  carregarDenuncias();
});
