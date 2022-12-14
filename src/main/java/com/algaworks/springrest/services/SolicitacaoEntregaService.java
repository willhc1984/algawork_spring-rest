package com.algaworks.springrest.services;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.springrest.model.Cliente;
import com.algaworks.springrest.model.Entrega;
import com.algaworks.springrest.model.StatusEntrega;
import com.algaworks.springrest.repositories.EntregaRepository;

@Service
public class SolicitacaoEntregaService {

	@Autowired
	private EntregaRepository entregaRepository;

	@Autowired
	private ClienteService clienteService;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {

		Cliente cliente = clienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);

	}

}
