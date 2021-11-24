package com.appbank.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.appbank.model.entity.Cliente;
import com.appbank.model.entity.ContaBancaria;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long>{
	ContaBancaria findByAgenciaAndNumero(String agencia, String numero);
	
	List<ContaBancaria> findByCliente(Cliente cli);
	
	@Query(value = "select c from ContaBancaria c where c.cliente = :cli")
	List<ContaBancaria> buscarContasDoClienteJpql (@Param("cli") Cliente cli);
	
	@Query(value = "select con.* from  clientes cli, contas con "
			+ "where cli.id=con.fk_cliente_id and cli.id = :idCliente", nativeQuery = true)
	List<ContaBancaria> buscarContasPorClienteSql (@Param("idCliente")Long idCliente);
}
