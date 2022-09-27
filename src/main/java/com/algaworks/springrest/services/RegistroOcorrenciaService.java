package com.algaworks.springrest.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.springrest.exceptions.BusinessException;
import com.algaworks.springrest.model.Entrega;
import com.algaworks.springrest.model.Ocorrencia;
import com.algaworks.springrest.repositories.EntregaRepository;

@Service
public class RegistroOcorrenciaService {

	@Autowired
	private BuscaEntregaService buscaEntregaService;

	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		return entrega.adicionarOcorrencia(descricao);
	}

}
