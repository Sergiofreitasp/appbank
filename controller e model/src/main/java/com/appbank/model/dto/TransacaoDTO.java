package com.appbank.model.dto;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TransacaoDTO {
	private LocalDate data;
	
	private String tipoTrasacao;
	
	private double valor;
}
