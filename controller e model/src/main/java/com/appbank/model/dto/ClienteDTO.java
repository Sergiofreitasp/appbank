package com.appbank.model.dto;

import lombok.Data;

@Data
public class ClienteDTO {
	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private String email;

	private boolean ativo;
}
