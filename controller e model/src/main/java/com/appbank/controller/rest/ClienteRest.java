package com.appbank.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appbank.model.dto.BuscaClienteDTO;
import com.appbank.model.dto.ClienteDTO;
import com.appbank.model.service.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("rest/clientes")
public class ClienteRest {
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/buscar-por-cpf/{cpf}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<ClienteDTO> buscarClientePorCpf(@PathVariable String cpf) {
		ClienteDTO retorno = clienteService.buscarClientePorCpf(cpf);
		return new ResponseEntity<>(retorno, HttpStatus.OK);
	}

//	@RequestMapping(value = "/buscar-por-nome", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
//	public @ResponseBody ResponseEntity<List<ClienteDTO>> buscarClientePorNome(@RequestBody BuscaClienteDTO dto) {
//
//		List<ClienteDTO> retorno = clienteService.buscarClientePorNome(dto.getNome());
//		
//		
//		if (retorno == null || retorno.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		
//		return new ResponseEntity<>(retorno, HttpStatus.OK);
//	}
}
