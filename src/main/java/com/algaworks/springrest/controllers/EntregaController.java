package com.algaworks.springrest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.springrest.common.EntregaAssembler;
import com.algaworks.springrest.model.Entrega;
import com.algaworks.springrest.model.DTO.EntregaDTO;
import com.algaworks.springrest.model.DTO.EntregaPostDTO;
import com.algaworks.springrest.repositories.EntregaRepository;
import com.algaworks.springrest.services.FinalizacaoEntregaService;
import com.algaworks.springrest.services.SolicitacaoEntregaService;

@RestController
@RequestMapping(value = "/entregas")
public class EntregaController {
	
	@Autowired
	private SolicitacaoEntregaService solicitacaoEntregaService;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private EntregaAssembler entregaAssembler;
	
	@Autowired
	private FinalizacaoEntregaService finalizacaoEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO solicitar(@Valid @RequestBody EntregaPostDTO entregaPostDTO) {		
		Entrega novaEntrega = entregaAssembler.toEntity(entregaPostDTO);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);		
		return entregaAssembler.toDTO(entregaSolicitada);
	}
	
	@GetMapping
	public List<EntregaDTO> listar(){
		return entregaAssembler.toCollectionDTO(entregaRepository.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EntregaDTO> buscar(@PathVariable Long id) {
		return entregaRepository.findById(id)
				.map(entrega -> ResponseEntity.ok(entregaAssembler.toDTO(entrega)))
							.orElse(ResponseEntity.notFound().build());
					/*
					 * Codigo tradicional - BoilerPlate
					 * EntregaDTO entregaDTO = new EntregaDTO(); entregaDTO.setId(entrega.getId());
					 * entregaDTO.setNomeCliente(entrega.getCliente().getNome());
					 * entregaDTO.setDestinatario(new DestinatarioDTO());
					 * entregaDTO.getDestinatario().setNome(entrega.getDestinatario().getNome());
					 * entregaDTO.getDestinatario().setLogradouro(entrega.getDestinatario().
					 * getLogradouro());
					 * entregaDTO.getDestinatario().setBairro(entrega.getDestinatario().getBairro())
					 * ; entregaDTO.getDestinatario().setComplemento(entrega.getDestinatario().
					 * getComplemento());
					 * entregaDTO.getDestinatario().setNumero(entrega.getDestinatario().getNumero())
					 * ; entregaDTO.setTaxa(entrega.getTaxa());
					 * entregaDTO.setStatus(entrega.getStatus());
					 * entregaDTO.setDataFinalizacao(entrega.getDataFinalizacao());
					 * entregaDTO.setDataPedido(entrega.getDataPedido()); return
					 * ResponseEntity.ok(entregaDTO);
					 */
	}
	
	@PutMapping(value = "/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}

}
