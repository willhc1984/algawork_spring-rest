function limparForm() {
	document.getElementById('formRegistrarOcorrencia').reset();
	$('#msgs').hide();
}

$(function() {
	$("#navbar").load("navbar.html");
	$("#footer").load("footer.html");
	$("#modal").load("modal-clientes.html");
});


function excluirOcorrencia(id) {

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

function salvarOcorrencia() {

	var idEntrega = $("#idEntrega").val();
	var descricao = $("#descricao").val();

	if (idEntrega !== '') {
		var data = {
			"descricao": descricao
		}

		$.ajax({
			method: "POST",
			url: "entregas/" + idEntrega + "/ocorrencias",
			data: JSON.stringify(data),
			contentType: "application/json; charset-utf-8",
			success: function(response) {
				$("#id").val(response.id);
				alert("Ocorrencia registrada!");
				$('#msgs').html("<div class='alert alert-success'>Ocorrencia registrada!</div>");
				limparForm();
				$('#msgs').show();
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao solicitar: " + xhr.responseText);
			$('#msgs').html("<div class='alert alert-danger'>" + xhr.responseText + "</div>");
			$('#msgs').show();
		})
	} else {
		$('#msgs').html("<div class='alert alert-danger'>Id da entrega inválido!</div>");
		$('#msgs').show();
	}


}
