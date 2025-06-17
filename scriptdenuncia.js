document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("denunciaForm");
  const checkboxAnonima = document.getElementById("anonima");
  const contatoSection = document.getElementById("contato");
  const fotosInput = document.getElementById("fotos");

  // Mostrar ou esconder os campos de contato com base no checkbox
  checkboxAnonima.addEventListener("change", () => {
    contatoSection.style.display = checkboxAnonima.checked ? "none" : "block";
  });

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const fotos = fotosInput.files;
    if (fotos.length > 5) {
      alert("Você só pode enviar no máximo 5 fotos.");
      return;
    }

    const formData = new FormData(form);
    const isAnonima = checkboxAnonima.checked;

    if (isAnonima) {
      formData.set("nome", "");
      formData.set("telefone", "");
      formData.set("email", "");
    }

    // Controle do número de protocolo (001 a 500)
    let numeroProtocolo = parseInt(localStorage.getItem("ultimoProtocolo") || "0", 10);
    if (numeroProtocolo >= 500) {
      alert("Limite de denúncias atingido (500).");
      return;
    }

    numeroProtocolo += 1;
    const protocoloFormatado = numeroProtocolo.toString().padStart(3, '0');
    localStorage.setItem("ultimoProtocolo", numeroProtocolo);
    localStorage.setItem("protocoloAtual", protocoloFormatado);

    // Enviar os dados para o backend com fetch
    fetch("/denuncias", {
      method: "POST",
      body: formData
    })
    .then(res => {
      if (!res.ok) throw new Error("Erro ao enviar denúncia");
      return res.json();
    })
    .then(() => {
      // Redireciona para a página de confirmação
      window.location.href = "sucesso.html";
    })
    .catch(err => {
      alert("Falha no envio: " + err.message);
    });

    // window.location.href = "sucesso.html";
  });
});
