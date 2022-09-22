package com.algaworks.springrest.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.springrest.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Optional<Cliente> findByEmail(String email);
	
	@Query("select c from Cliente c where c.nome like %?1%")
	List<Cliente> buscarPorNome(String nome);

}
