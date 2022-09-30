$(function() {
	$("#navbar").load("navbar.html");
	$("#footer").load("footer.html");
	$("#modal").load("modal-ocorrencias.html");
});

function limparForm() {
	document.getElementById('formSolicitacaoEntrega').reset();
	$('#msgs').hide();
}

function finalizarEntrega(id) {
	$.ajax({
		method: "PUT",
		url: "entregas/"+id+"/finalizacao",
		success: function(response) {
			alert("Entrega finalizada!");
			$("#msgs").html("<div class='alert alert-success'>Entrega finalizada!</div>");
			listarEntregas();
		}
	}).fail(function(xhr, status, errorThrown) {
		alert("Entrega já está finalizada!" + xhr.responseText);
		$("#msgs").html("<div class='alert alert-danger'>Entrega já está finalizada!</div>");
		listarEntregas();
	})
}

function listarEntregas() {
	$.ajax({
		method: "GET",
		url: "entregas",
		success: function(response) {
			document.getElementById('resultados').innerHTML = '';
			for (var i = 0; i < response.length; i++) {
				moment.locale('pt-br');
				$('#tabela > tbody').append('<tr id="' + response[i].id + '">');
				$('#tabela > tbody').append('<td>' + response[i].id + '</td>');
				$('#tabela > tbody').append('<td>' + response[i].cliente.nome + '</td>');
				$('#tabela > tbody').append('<td>' + response[i].destinatario.nome + '</td>');
				$('#tabela > tbody').append('<td>' + response[i].destinatario.logradouro + '</td>');
				$('#tabela > tbody').append('<td>' + response[i].destinatario.bairro + '</td>');
				$('#tabela > tbody').append('<td>' + response[i].destinatario.numero + '</td>');
				$('#tabela > tbody').append('<td>' + response[i].destinatario.complemento + '</td>');
				$('#tabela > tbody').append('<td>' + response[i].taxa + '</td>');
				$('#tabela > tbody').append('<td>' + moment(response[i].dataPedido).format('DD-MM-YY') + '</td>');
				$('#tabela > tbody').append('<td>' + moment(response[i].dataFinalizacao).format('DD-MM-YY') + '</td>');
				$('#tabela > tbody').append('<td>' + response[i].status + '</td>');
				//$('#tabela > tbody').append('<td><button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#modalPesquisar"><i class="fa-solid fa-square-check" data-toggle="tooltip" data-placement="top" title="Visualizar ocorrencias"></i></button></td>');
				//$('#tabela > tbody').append('<td><button type="button" class="btn btn-info" onClick="finalizarEntrega(' + response[i].id + ')""><i class="fa-solid fa-square-check" data-toggle="tooltip" data-placement="top" title="Finalizar entrega"></i></button></td>');
				$('#tabela > tbody').append('</tr>');
			}
		}
	}).fail(function(xhr, status, errorThrown) {
		alert("Erro ao buscar clientes" + xhr.responseText);
	})	
}

function excluirSolicitacaoEntrega(id) {

	if (confirm('Deseja realmente excluir?')) {
		$.ajax({
			method: "DELETE",
			url: "entregas/" + id,
			data: "id=" + id,
			success: function(response) {
				//pesquisarCliente();
				alert("Solicitação excluida!");
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao excluir solicitação de entrega!." + xhr.responseText);
		})
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
			document.getElementById('formSolicitacaoEntrega').reset();
		}
	}).fail(function(xhr, status, errorThrown) {
		alert("Erro ao solicitar: " + xhr.responseText);
		$('#msgs').html("<div class='alert alert-danger'>" + xhr.responseText + "</div>");
		$('#msgs').show();
	})
}


