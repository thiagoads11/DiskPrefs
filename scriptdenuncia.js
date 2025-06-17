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

    // Campos renomeados para bater com o DTO
    let nomeUsuario = formData.get("nome");
    let telefoneUsuario = formData.get("telefone");
    let email = formData.get("email");

    if (isAnonima) {
      nomeUsuario = "";
      telefoneUsuario = "";
      email = "";
    }

    // Geração do número de protocolo (simulado no localStorage)
    let numeroProtocolo = parseInt(localStorage.getItem("ultimoProtocolo") || "0", 10);
    if (numeroProtocolo >= 500) {
      alert("Limite de denúncias atingido (500).");
      return;
    }

    numeroProtocolo += 1;
    const protocoloFormatado = numeroProtocolo.toString().padStart(3, '0');
    localStorage.setItem("ultimoProtocolo", numeroProtocolo);
    localStorage.setItem("protocoloAtual", protocoloFormatado);

    // Montar objeto para envio
    const denunciaData = {
      nomeUsuario,
      telefoneUsuario,
      email,
      tituloDenuncia: formData.get("titulo"),
      categoriaDenuncia: formData.get("categoria"),
      descricaoDenuncia: formData.get("descricao"),
      enderecoDenuncia: formData.get("endereco"),
      denunciaAnonima: isAnonima
    };

    // Enviar via fetch para o backend
    fetch("http://localhost:8080/denuncias", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(denunciaData)
    })
    .then(response => {
      if (!response.ok) {
        return response.text().then(text => {
          throw new Error(`Erro ${response.status}: ${text}`);
        });
      }
      return response.json();
    })
    .then(data => {
      console.log("Denúncia registrada com sucesso:", data);
      window.location.href = "sucesso.html";
    })
    .catch(error => {
      console.error("Erro ao registrar denúncia:", error);
      alert("Erro ao enviar denúncia. Verifique os dados e tente novamente.");
    });
  });
});