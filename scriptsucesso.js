let currentProtocol = 0;

function submitReport() {
  if (currentProtocol >= 500) {
    alert("Limite de protocolos atingido.");
    return;
  }
  currentProtocol++;

  const protocolStr = String(currentProtocol).padStart(3, '0');
  document.getElementById("protocolNumber").innerText = protocolStr;
  document.getElementById("formContainer").style.display = 'none';
  document.getElementById("confirmationContainer").style.display = 'block';
}

function goBack() {
  document.getElementById("confirmationContainer").style.display = 'none';
  document.getElementById("formContainer").style.display = 'block';
} 
