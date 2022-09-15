package com.algaworks.springrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.springrest.model.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long	>{

}
