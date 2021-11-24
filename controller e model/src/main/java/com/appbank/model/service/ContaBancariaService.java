package com.appbank.model.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appbank.exception.CustomException;
import com.appbank.exception.EntityNotFoundException;
import com.appbank.model.dto.ClienteDTO;
import com.appbank.model.dto.TransferenciaBancariaDTO;
import com.appbank.model.entity.ContaBancaria;
import com.appbank.model.repository.ContaBancariaRepository;
@Service
public class ContaBancariaService {
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	@Autowired
	private TransacaoService transacaoService;
	
	@Autowired
	private ClienteService clienteService;
	
	
	public double consultarSaldo(String agencia, String conta) {
		ContaBancaria c = this.consultarConta(agencia, conta);
		return c.getSaldo();
	}

	public ContaBancaria consultarConta(String agencia, String numeroConta) {
		ContaBancaria c = contaBancariaRepository.findByAgenciaAndNumero(agencia, numeroConta);
		if (c == null) {
			throw new EntityNotFoundException("Conta não encontada");
		}
		return c;
	}

	public List<ContaBancaria> obterContas(String cpf) {
		ClienteDTO cli = clienteService.buscarClientePorCpf(cpf);
		List<ContaBancaria> contasDoCliente = contaBancariaRepository.buscarContasPorClienteSql(cli.getId());
		return contasDoCliente;
	}

	public void sacar(String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultarConta(agencia, numeroConta);
		
		if (conta.getSaldo() < valor) {
			throw new CustomException("Saldo inexistente");
		}
		conta.setSaldo(conta.getSaldo() - valor);
		contaBancariaRepository.saveAndFlush(conta);
		transacaoService.addTransacao("Saque", valor, conta);
	}

	public void depositar(String agencia, String numeroConta, double valor) {
		ContaBancaria conta = this.consultarConta(agencia, numeroConta);
		conta.setSaldo(conta.getSaldo() + valor);
		contaBancariaRepository.saveAndFlush(conta);
		transacaoService.addTransacao("Deposito", valor, conta);
	}

	@Transactional(rollbackFor = Exception.class)
	public void transferir(TransferenciaBancariaDTO dto) {
		if (dto.getAgenciaDestino().equals(dto.getAgenciaOrigem())
				&& dto.getNumeroContaDestino().equals(dto.getNumeroContaOrigem())) {
			throw new CustomException("Conta Invalida");
		}
		
		//---Saque
		ContaBancaria contaOrigem = this.consultarConta(dto.getAgenciaOrigem(), dto.getNumeroContaOrigem());
		if (contaOrigem.getSaldo() < dto.getValor()) {
			throw new CustomException("Saldo Inexistente");
		}
		contaOrigem.setSaldo(contaOrigem.getSaldo() - dto.getValor());
		contaBancariaRepository.saveAndFlush(contaOrigem);
		//---Deposito
		ContaBancaria contaDestino = this.consultarConta(dto.getAgenciaDestino(), dto.getNumeroContaDestino());
		contaDestino.setSaldo(contaDestino.getSaldo() + dto.getValor());
		contaBancariaRepository.saveAndFlush(contaDestino);
		
		//---Saque
		transacaoService.addTransacao("Transferiu para: "+ contaDestino.getCliente().getNome(), dto.getValor(), contaOrigem);
		//---Deposito
		transacaoService.addTransacao("Trasferencia de: " + contaOrigem.getCliente().getNome(), dto.getValor(), contaDestino);
		
	}
}
