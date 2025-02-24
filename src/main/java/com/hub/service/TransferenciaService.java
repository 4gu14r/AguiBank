package com.hub.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.model.ContaBancaria;
import com.hub.model.Transacao;
import com.hub.repository.ContaBancariaRepository;

import jakarta.transaction.Transactional;

@Service
public class TransferenciaService {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private ContaBancariaService contaBancariaService;

    @Autowired
    private TransacaoService transacaoService;

    @Transactional
    public void realizarTransferencia(BigDecimal valor, Long contaOrigem, Long contaDestino) {
        
        // Pegar contas
        ContaBancaria destino = contaBancariaRepository.findById(contaDestino).orElseThrow(()->new RuntimeException("Conta Inexistente"));;
        ContaBancaria origem = contaBancariaRepository.findById(contaOrigem).get();

        // Verifica saldo suficiente
        if (origem.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente para transferência.");
        }

        // Realiza a transferência: subtrai da origem e adiciona na destino
        origem.setSaldo(origem.getSaldo().subtract(valor));
        destino.setSaldo(destino.getSaldo().add(valor));

        // Salva as atualizações
        contaBancariaService.atualizarConta(destino);
        contaBancariaService.atualizarConta(origem);

        // Transação
        Transacao transacao = new Transacao();
        transacao.setContaOrigem(origem);
        transacao.setContaDestino(destino);
        transacao.setValor(valor);
        transacao.setTipoTransacao(Transacao.TipoTransacao.TRANSFERENCIA);
        transacao.setDescricao("Transferência Bancaria");
        transacaoService.salvarTransacao(transacao);
    }
}
