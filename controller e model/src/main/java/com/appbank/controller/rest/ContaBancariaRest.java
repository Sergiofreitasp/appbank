package com.appbank.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appbank.model.dto.ConsultaDeExtratoDTO;
import com.appbank.model.dto.DepositoDTO;
import com.appbank.model.dto.SaqueDTO;
import com.appbank.model.dto.TransacaoDTO;
import com.appbank.model.dto.TransferenciaBancariaDTO;
import com.appbank.model.entity.ContaBancaria;
import com.appbank.model.service.ContaBancariaService;
import com.appbank.model.service.TransacaoService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("rest/contas")
public class ContaBancariaRest {
	@Autowired
	private ContaBancariaService contaBancariaService;
	
	@Autowired
	private TransacaoService transacaoService;
	
	@RequestMapping(value = "/consultar-saldo/{agencia}/{conta}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Double> consultarSaldo(@PathVariable String agencia, 
			@PathVariable String conta){
		double saldo = contaBancariaService.consultarSaldo(agencia, conta);
		return new ResponseEntity<>(saldo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/consultar-contas-cliente/{cpf}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<ContaBancaria>> consultarContasPorCliente(@PathVariable String cpf){
		List<ContaBancaria> contasCliente = contaBancariaService.obterContas(cpf);
		return new ResponseEntity<>(contasCliente, HttpStatus.OK);
	}
	
	//---extrato
	@RequestMapping(value = "/extrato/{agencia}/{numeroConta}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<TransacaoDTO>> getExtrato(@PathVariable String agencia, 
			@PathVariable String numeroConta){
		List<TransacaoDTO> Transacoes = transacaoService.extrato(agencia, numeroConta);
		return new ResponseEntity<>(Transacoes, HttpStatus.OK);
	}
	//----Extrato
	@RequestMapping(value = "/consultarExtrato", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<TransacaoDTO>> extratoConsulta(@RequestBody ConsultaDeExtratoDTO dto) {
		List<TransacaoDTO> Transacoes = transacaoService.consultaExtrato(dto.getAgencia(), dto.getNumeroConta(),dto.getDataIncial(), dto.getDataFinal());
		
		return new ResponseEntity<>(Transacoes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deposito", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> depositar(@RequestBody DepositoDTO dto) {
		contaBancariaService.depositar(dto.getAgencia(), dto.getNumeroConta(), dto.getValor());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saque", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> sacar(@RequestBody SaqueDTO dto) {
		contaBancariaService.sacar(dto.getAgencia(), dto.getNumeroConta(), dto.getValor());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/transferencia", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> transferir(@RequestBody TransferenciaBancariaDTO dto) {
		contaBancariaService.transferir(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
