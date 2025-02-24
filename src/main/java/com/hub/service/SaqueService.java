package com.hub.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.model.ContaBancaria;
import com.hub.model.Transacao;

import jakarta.transaction.Transactional;

@Service
public class SaqueService {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @Autowired
    private TransacaoService transacaoService;

    @Transactional
    public void realizarSaque(BigDecimal valorSaque, Long contaId) {
        if (valorSaque.compareTo(contaBancariaService.consultaSaldoPorNumConta(contaId)) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        } else if (valorSaque == null || valorSaque.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("valor inválido.");
        }

        // Operação
        BigDecimal saldoAtual = contaBancariaService.consultaSaldoPorNumConta(contaId);
        BigDecimal novoSaldo = saldoAtual.subtract(valorSaque);

        // Conta Bancaria
        ContaBancaria contaBancaria = contaBancariaService.pegarNumContaAtual(contaId);
        contaBancaria.setSaldo(novoSaldo);
        contaBancariaService.atualizarConta(contaBancaria);

        // Transação
        Transacao transacao = new Transacao();
        transacao.setContaOrigem(contaBancaria); 
        transacao.setValor(valorSaque);
        transacao.setTipoTransacao(Transacao.TipoTransacao.SAQUE);
        transacao.setDescricao("Saque Bancário");
        transacao.setSaldoAtual(novoSaldo);
        transacaoService.salvarTransacao(transacao);
    }

}
