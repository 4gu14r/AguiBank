package com.hub.utils;

import java.math.BigDecimal;
import java.util.List;

import com.hub.model.Transacao;

public class TransacaoUtils {
    public static void imprimirExtrato(List<Transacao> transacoes) {
        if (transacoes == null || transacoes.isEmpty()) {
            System.out.println("Nenhuma transação encontrada para esta conta.");
            return;
        }

        System.out.println("\n=== Extrato Bancário ===");
        System.out.println("Data/Hora\t\tTipo\t\tValor\t\tDescrição\t\tSaldo Atual");
        System.out.println("----------------------------------------------------------------");

        for (Transacao transacao : transacoes) {
            System.out.printf("%s\t%s\tR$ %.2f\t%s\tR$ %.2f%n",
                    transacao.getDataHoraCriacao(),
                    transacao.getTipoTransacao(),
                    transacao.getValor(),
                    transacao.getDescricao() != null ? transacao.getDescricao() : "-",
                    transacao.getSaldoAtual() != null ? transacao.getSaldoAtual() : BigDecimal.ZERO);
        }
        System.out.println("========================");
    }
}
