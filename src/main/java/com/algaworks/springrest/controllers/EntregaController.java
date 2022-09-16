package com.algaworks.springrest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.springrest.common.EntregaAssembler;
import com.algaworks.springrest.model.Entrega;
import com.algaworks.springrest.model.DTO.DestinatarioDTO;
import com.algaworks.springrest.model.DTO.EntregaDTO;
import com.algaworks.springrest.repositories.EntregaRepository;
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
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO solicitar(@Valid @RequestBody Entrega entrega) {
		return entregaAssembler.toDTO(solicitacaoEntregaService.solicitar(entrega));
	}
	
	@GetMapping
	public List<EntregaDTO> listar(){
		return entregaAssembler.toCollectionDTO(entregaRepository.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EntregaDTO> buscar(@PathVariable Long id) {
		return entregaRepository.findById(id)
				.map(entrega -> {
					EntregaDTO entregaDTO = entregaAssembler.toDTO(entrega);
					return ResponseEntity.ok(entregaDTO);
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
				})
				.orElse(ResponseEntity.notFound().build());
	}

}