package com.hub.service;

import com.hub.model.ContaBancaria;
import com.hub.model.Transacao;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DepositoService {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @Autowired
    private TransacaoService transacaoService;

    @Transactional
    public void realizarDeposito(BigDecimal valorDeposito, Long contaId) {

        if (valorDeposito == null || valorDeposito.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
        }


        // Operação
        BigDecimal saldoAtual = contaBancariaService.consultaSaldoPorNumConta(contaId);
        BigDecimal novoSaldo = saldoAtual.add(valorDeposito);

        // Conta Bancaria
        ContaBancaria contaBancaria = contaBancariaService.pegarNumContaAtual(contaId);
        contaBancaria.setConta(contaId);
        contaBancaria.setSaldo(novoSaldo);
        contaBancariaService.atualizarConta(contaBancaria);

        // Transação
        Transacao transacao = new Transacao();
        transacao.setContaOrigem(contaBancaria);
        transacao.setValor(valorDeposito);
        transacao.setTipoTransacao(Transacao.TipoTransacao.DEPOSITO);
        transacao.setDescricao("Depósito Bancário");
        transacao.setSaldoAtual(novoSaldo);
        transacaoService.salvarTransacao(transacao);
    }
}