package com.appbank.model.service;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appbank.exception.CustomException;
import com.appbank.exception.EntityNotFoundException;
import com.appbank.model.dto.ClienteDTO;
import com.appbank.model.entity.Cliente;
import com.appbank.model.repository.ClienteRepository;
import com.appbank.util.CpfUtil;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	public ClienteDTO buscarClientePorCpf(String cpf) {
		  
		  if (!cpfEhValido(cpf)) {
			  throw new CustomException("CPF invalido");
		  }
		  
		  Cliente cli = clienteRepository.findByCpf(cpf);
		  
		  if (cli == null) {
			  throw new EntityNotFoundException("Clinete não encontada");
		  }
		  
		  ClienteDTO retorno = new ClienteDTO();
		  retorno.setEmail(cli.getEmail());
		  retorno.setNome(cli.getNome());
		  retorno.setCpf(cli.getCpf());
		  retorno.setId(cli.getId());
		  
		  return retorno;
	  }
	  
	  private boolean cpfEhValido(String cpf) {
		  return CpfUtil.validaCPF(cpf);
	  }

	  public List<ClienteDTO> buscarClientePorNome(String nome) {
		  List<Cliente> clientes = clienteRepository.findByNomeIgnoreCase(nome);
		  List<ClienteDTO> listaRetorno = new ArrayList<>();
		  if (clientes != null) {
			  for (Cliente cli : clientes) {
				  ClienteDTO dto = new ClienteDTO();
				  dto.setEmail(cli.getEmail());
				  dto.setNome(cli.getNome());
				  dto.setCpf(cli.getCpf());
				  dto.setId(cli.getId());
				  listaRetorno.add(dto);
			  }
		  }
		  return listaRetorno;
	  }
}
