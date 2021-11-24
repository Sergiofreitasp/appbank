package com.appbank.model.repository;

import org.springframework.data.repository.CrudRepository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appbank.model.entity.ContaBancaria;
import com.appbank.model.entity.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
List<Transacao> findByConta(ContaBancaria conta);
	
	@Query(value = "Select tran.* from contas con, transacao tran "+
			"where con.id=tran.fk_conta_id and con.id = :idConta "+
			"and (tran.data BETWEEN :dInicial and :dFinal) ", nativeQuery = true)
	List<Transacao> buscarTransacaoSql(@Param("idConta")Long idConta, @Param("dInicial") String dataI, @Param("dFinal") String dataF );
}
