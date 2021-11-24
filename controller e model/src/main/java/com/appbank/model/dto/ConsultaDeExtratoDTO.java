package com.appbank.model.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ConsultaDeExtratoDTO {
	private String agencia;
	
	private String numeroConta;
	
	private LocalDate dataIncial;
	
	private LocalDate dataFinal;
}
