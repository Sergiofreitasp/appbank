package com.appbank.model.entity;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "tb_Transacao")
public class Transacao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private LocalDate data;
	
	@Column(name="Descrição")
	private String tipoTrasacao;
	
	@Column
	private double valor;
	
	@ManyToOne
	@JoinColumn(name = "fk_conta_id", nullable = false)
	private ContaBancaria conta;
}
