package com.algaworks.springrest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "tb_entrega")
public class Entrega implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private BigDecimal taxa;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataPedido;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;
	
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();
	
	@Valid 
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	@NotNull
	@ManyToOne 
	private Cliente cliente;
	
	@Valid
	@NotNull
	@Embedded
	private Destinatario destinatario;	
	
	public Entrega() {
	}
	
	public Entrega(Long id, BigDecimal taxa, StatusEntrega status, OffsetDateTime dataPedido,
			OffsetDateTime dataFinalizacao, Cliente cliente, Destinatario destinatario) {
		super();
		this.id = id;
		this.taxa = taxa;
		this.status = status;
		this.dataPedido = dataPedido;
		this.dataFinalizacao = dataFinalizacao;
		this.cliente = cliente;
		this.destinatario = destinatario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public StatusEntrega getStatus() {
		return status;
	}

	public void setStatus(StatusEntrega status) {
		this.status = status;
	}

	public OffsetDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(OffsetDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Destinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}
	
	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public Ocorrencia adicionarOcorrencia(String descricao) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(this);		
		
		this.getOcorrencias().add(ocorrencia);
		
		return ocorrencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrega other = (Entrega) obj;
		return Objects.equals(id, other.id);
	}

}
