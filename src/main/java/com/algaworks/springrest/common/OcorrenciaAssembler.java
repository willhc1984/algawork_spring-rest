package com.algaworks.springrest.common;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.springrest.model.Ocorrencia;
import com.algaworks.springrest.model.DTO.OcorrenciaDTO;

@Component
public class OcorrenciaAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public OcorrenciaDTO toDTO(Ocorrencia ocorrencia){
		return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
	}

	public OcorrenciaAssembler(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}
	
	public List<OcorrenciaDTO> toCollectionDTO(List<Ocorrencia> ocorrencias){
		return ocorrencias.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

}
