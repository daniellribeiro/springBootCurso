criarBotao("home","index.html");

criarBotao("clientes", "clientes.html");

criarBotao("produtos", "produtos.html");

criarBotao("pedidos","pedidos.html");

function criarBotao(nomeBotao, nomeHtmlTela) {
	let button = document.createElement("button");

	// Define o texto do botão
	button.textContent = nomeBotao;

	// Vincula o evento click ao botão
	button.addEventListener("click", function() {
		window.location.href = nomeHtmlTela;
	});
    
	// Adiciona o botão ao document
	document.body.appendChild(button);
   
   //Estilo botao
   button.style.width = "200px";
   button.style.fontSize = "30px";
   button.style.backgroundColor = "#ADD8E6";
}