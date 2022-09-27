function limparForm(){
	document.getElementById('formSolicitacaoEntrega').reset();
	$('#msgs').hide();
}

$(function() {
	$("#navbar").load("navbar.html");
	$("#footer").load("footer.html");
	$("#modal").load("modaL-clientes.html");
});

function colocarEmEdicao(id) {
	$.ajax({
		method: "GET",
		url: "clientes/" + id,
		data: "id=" + id,
		success: function(response) {
			$("#id").val(response.id);
			$("#nome").val(response.nome);
			$("#email").val(response.email);
			$("#telefone").val(response.telefone);
			$("#modalPesquisar").modal('hide');
		}
	}).fail(function(xhr, status, errorThrown) {
		alert("Erro ao buscar cliente." + xhr.responseText);
	})
}

function pesquisarCliente() {

	var nome = $('#buscarPorNome').val();
	if (nome != null && nome.trim() != '') {
		$.ajax({
			method: "GET",
			url: "clientes/buscarPorNome",
			data: "nome=" + nome,
			success: function(response) {
				document.getElementById('resultados').innerHTML = '';
				for (var i = 0; i < response.length; i++) {
					$('#tabela > tbody').append('<tr id="' + response[i].id + '">');
					$('#tabela > tbody').append('<td>' + response[i].id + '</td>');
					$('#tabela > tbody').append('<td>' + response[i].nome + '</td>');
					$('#tabela > tbody').append('<td>' + response[i].email + '</td>');
					$('#tabela > tbody').append('<td>' + response[i].telefone + '</td>');
					$('#tabela > tbody').append('<td><a href="#" onClick="colocarEmEdicao(' + response[i].id + ')"><i class="fa-solid fa-file-pen"></i></a></td>');
					$('#tabela > tbody').append('<td><a href="#" onClick="excluirCliente(' + response[i].id + ')"><i class="fa-solid fa-trash"></i></a></td>');
					$('#tabela > tbody').append('</tr>');
				}
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao buscar cliente " + xhr.responseText);
		})
	}
}

function excluirSolicitacaoEntrega(id) {

	if (confirm('Deseja realmente excluir?')) {
		$.ajax({
			method: "DELETE",
			url: "entregas/" + id,
			data: "id=" + id,
			success: function(response) {
				//pesquisarCliente();
				alert("Cliente excluido!");
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao excluir solicitação de entrega!." + xhr.responseText);
		})
	}
}

function botaoDeletar() {
	var id = $("#id").val();
	if (id != null && id.trim() != '') {
		excluirCliente(id);
		$('#msgs').html("<div class='alert alert-success'>Cliente excluido!</div>");
		$('#msgs').show();
		document.getElementById('formCadCli').reset();
	}

}

function salvarSolicitacaoEntrega() {

	var id = $("#idDestinatario").val();
	var nome = $("#nomeDestinatario").val();
	var logradouro = $("#logradouroDestinatario").val();
	var bairro = $("#bairroDestinatario").val();
	var numero = $("#numeroDestinatario").val();
	var complemento = $("#complementoDestinatario").val();
	var taxa = $("#taxa").val();

	var data = {
		"cliente": {
			"id": id
		},
		"destinatario": {
			"nome": nome,
			"logradouro": logradouro,
			"numero": numero,
			"bairro": bairro,
			"complemento": complemento
		},
		"taxa": taxa
	}

	$.ajax({
		method: "POST",
		url: "entregas",
		data: JSON.stringify(data),
		contentType: "application/json; charset-utf-8",
		success: function(response) {
			$("#id").val(response.id);
			alert("Solicitação realizada!");
			$('#msgs').html("<div class='alert alert-success'>Solicitação realizada!</div>");
			$('#msgs').show();
		}
	}).fail(function(xhr, status, errorThrown) {
		alert("Erro ao solicitar: " + xhr.responseText);
		$('#msgs').html("<div class='alert alert-danger'>" + xhr.responseText + "</div>");
		$('#msgs').show();
	})
}
