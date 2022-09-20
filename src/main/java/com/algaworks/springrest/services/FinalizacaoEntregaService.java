package com.algaworks.springrest.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.springrest.model.Entrega;
import com.algaworks.springrest.model.StatusEntrega;
import com.algaworks.springrest.repositories.EntregaRepository;

@Service
public class FinalizacaoEntregaService {
	
	@Autowired
	private EntregaRepository entregaRepository;
	@Autowired
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public void finalizar(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		entrega.finalizar();
		
		entregaRepository.save(entrega);
		
	}

}
