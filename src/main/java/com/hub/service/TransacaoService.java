package com.hub.service;

import com.hub.model.Transacao;
import com.hub.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    /**
     * Gera o extrato bancário para uma conta de origem específica, listando todas
     * as transações
     * ordenadas do mais recente para o mais antigo.
     *
     * @param contaId O ID da conta de origem (chave primária da ContaBancaria)
     * @return Lista de transações associadas à conta de origem
     */

    public List<Transacao> extratoBancario(Long contaId) {
        List<Transacao> transacoes = transacaoRepository.findByContaOrigemContaOrderByDataHoraCriacaoDesc(contaId);
        List<Transacao> recebimento = transacaoRepository.findByContaDestinoContaOrderByDataHoraCriacaoDesc(contaId);


        List<Transacao> todasTransacoes = new ArrayList<>();
        todasTransacoes.addAll(transacoes);
        todasTransacoes.addAll(recebimento);

        if (todasTransacoes.isEmpty()) {
            throw new RuntimeException("Nenhuma transação encontrada para a conta " + contaId);
        }

        todasTransacoes.sort((t1, t2) -> t2.getDataHoraCriacao().compareTo(t1.getDataHoraCriacao()));

        return todasTransacoes;
    }

    public Transacao salvarTransacao(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }
}