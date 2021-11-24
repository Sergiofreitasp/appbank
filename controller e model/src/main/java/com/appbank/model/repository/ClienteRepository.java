package com.appbank.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.appbank.model.entity.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Cliente findByCpf(String cpf);

	List<Cliente> findByNomeIgnoreCase(String nome);
}
