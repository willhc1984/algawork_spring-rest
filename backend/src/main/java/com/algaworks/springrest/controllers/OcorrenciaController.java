package com.algaworks.springrest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.springrest.common.OcorrenciaAssembler;
import com.algaworks.springrest.model.Entrega;
import com.algaworks.springrest.model.Ocorrencia;
import com.algaworks.springrest.model.DTO.OcorrenciaDTO;
import com.algaworks.springrest.model.DTO.OcorrenciaPostDTO;
import com.algaworks.springrest.services.BuscaEntregaService;
import com.algaworks.springrest.services.RegistroOcorrenciaService;

@RestController
@RequestMapping(value = "/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {
	
	@Autowired
	private RegistroOcorrenciaService registroOcorrenciaService;
	
	@Autowired
	private OcorrenciaAssembler ocorrenciaAssembler;
	
	@Autowired
	private BuscaEntregaService buscaEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTO registrar(@PathVariable Long entregaId, 
			@Valid @RequestBody OcorrenciaPostDTO ocorrenciaPostDTO) {
		
		Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
				.registrar(entregaId, ocorrenciaPostDTO.getDescricao());
		
		return ocorrenciaAssembler.toDTO(ocorrenciaRegistrada);
	}
	
	@GetMapping
	public List<OcorrenciaDTO> listar(@PathVariable Long entregaId){
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		return ocorrenciaAssembler.toCollectionDTO(entrega.getOcorrencias());
	}

}
