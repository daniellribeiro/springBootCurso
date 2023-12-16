criarBotao("home", "index.html");

criarBotao("clientes", "clientes.html");

criarBotao("produtos", "produtos.html");

criarBotao("pedidos", "pedidos.html");

function criarBotao(nomeBotao, nomeHtmlTela) {
	let botao = document.createElement("button");

	// Define o texto do botão
	botao.textContent = nomeBotao;

	// Vincula o evento click ao botão
	botao.addEventListener("click", function() {
		window.location.href = nomeHtmlTela;
	});

	// Adiciona o botão ao document
	document.body.appendChild(botao);

	//Estilo botao
	botao.style.width = "200px";
	botao.style.fontSize = "30px";
	botao.style.backgroundColor = "#ADD8E6";
}

function desabilitarTodosOsBotoes() {
	document.getElementById("btnBuscar").style.display = "none";

	document.getElementById("btnTelaCadastrar").style.display = "none";

	document.getElementById("btnEnviarCadastro").style.display = "none";

	document.getElementById("btnEnviarAlteracao").style.display = "none";

	document.getElementById("btnTelaAlterarCadastro").style.display = "none";

	document.getElementById("btnTelaDeletar").style.display = "none";

	document.getElementById("btnAlterarImagem").style.display = "none";
}
