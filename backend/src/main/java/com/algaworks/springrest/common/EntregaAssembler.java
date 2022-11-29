package com.algaworks.springrest.common;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.springrest.model.Entrega;
import com.algaworks.springrest.model.DTO.EntregaDTO;
import com.algaworks.springrest.model.DTO.EntregaPostDTO;

@Component
public class EntregaAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EntregaDTO toDTO(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDTO.class);
	}

	public EntregaAssembler(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}
	
	public List<EntregaDTO> toCollectionDTO(List<Entrega> entregas){
		return entregas.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaPostDTO entregaPostDTO) {
		return modelMapper.map(entregaPostDTO, Entrega.class);
	}
	
	
	
}
