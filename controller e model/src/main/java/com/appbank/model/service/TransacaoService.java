package com.appbank.model.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appbank.exception.CustomException;
import com.appbank.exception.EntityNotFoundException;
import com.appbank.model.dto.TransacaoDTO;
import com.appbank.model.entity.ContaBancaria;
import com.appbank.model.entity.Transacao;
import com.appbank.model.repository.TransacaoRepository;

@Service
public class TransacaoService {
	@Autowired
	public ContaBancariaService contaBancariaService;
	@Autowired
	public TransacaoRepository transacaoRepository;
	
	public void addTransacao(String tipoTransacao, double valor, ContaBancaria conta) {
		LocalDate data = LocalDate.now();
		Transacao t = new Transacao();
		t.setData(data);
		t.setTipoTrasacao(tipoTransacao);
		t.setValor(valor);
		t.setConta(conta);
		transacaoRepository.saveAndFlush(t);
	}
	
	public List<TransacaoDTO> extrato(String agencia, String numeroConta) {
		ContaBancaria conta = contaBancariaService.consultarConta(agencia, numeroConta);
		List<Transacao> Lista = transacaoRepository.findByConta(conta);
		if(Lista == null) {
			throw new EntityNotFoundException("NENHUM REGISTRO ENCONTRADO");
		}
		List<TransacaoDTO> listaRetorno = new  ArrayList<>();
		for (Transacao t : Lista) {
			TransacaoDTO retorno = new TransacaoDTO();
			retorno.setData(t.getData());
			retorno.setTipoTrasacao(t.getTipoTrasacao());
			retorno.setValor(t.getValor());
			listaRetorno.add(retorno);
		}
		
		return listaRetorno;
	}
	
	public List<TransacaoDTO> consultaExtrato(String agencia, String numeroConta, LocalDate dataI,  LocalDate dataF){
		ContaBancaria conta = contaBancariaService.consultarConta(agencia, numeroConta);
		List<Transacao> Lista = transacaoRepository.buscarTransacaoSql(conta.getId(), dataI.toString(), dataF.toString());
		if(Lista == null) {
			throw new EntityNotFoundException("NENHUM REGISTRO ENCONTRADO");
		}
		List<TransacaoDTO> listaRetorno = new  ArrayList<>();
		for (Transacao t : Lista) {
			TransacaoDTO retorno = new TransacaoDTO();
			retorno.setData(t.getData());
			retorno.setTipoTrasacao(t.getTipoTrasacao());
			retorno.setValor(t.getValor());
			listaRetorno.add(retorno);
		}
		return listaRetorno;
	}
}
