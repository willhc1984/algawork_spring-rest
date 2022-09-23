package com.algaworks.springrest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.springrest.exceptions.IntegrityViolation;
import com.algaworks.springrest.model.Cliente;
import com.algaworks.springrest.repositories.ClienteRepository;
import com.algaworks.springrest.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> buscarTodos(){
		List<Cliente> clientes = clienteRepository.findAll();
		return ResponseEntity.ok(clientes);
	}
	
	@GetMapping(value = "/buscarPorNome")
	public ResponseEntity<List<Cliente>> buscarPorNome(@RequestParam(name = "nome") String nome){
		List<Cliente> clientes = clienteRepository.buscarPorNome(nome.trim().toUpperCase());
		return ResponseEntity.ok(clientes);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {		
		return clienteRepository.findById(id)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
		
		/* Codigo mais tradicional
		 * Optional<Cliente> cliente = clienteRepository.findById(id);
		 * if(cliente.isPresent()) { return ResponseEntity.ok(cliente.get()); } return
		 * ResponseEntity.notFound().build();
		 */
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@Valid @RequestBody Cliente cliente){
		return clienteService.salvar(cliente);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(id);
		clienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		if(!clienteRepository.existsById(id)){
			return ResponseEntity.notFound().build();
		}
		
		try {
			clienteService.excluir(id);
		} catch (DataIntegrityViolationException e) {
			throw new IntegrityViolation("Erro ao excluir: erro de integridade de dados.");
		}
		
		return ResponseEntity.noContent().build();
	}
	
}
