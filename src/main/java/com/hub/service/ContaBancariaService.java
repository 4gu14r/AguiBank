package com.hub.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hub.model.ContaBancaria;
import com.hub.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService {
    @Autowired
    private ContaBancariaRepository repository;

    public ContaBancaria criarContaParaUsuario(String userId) {
        ContaBancaria conta = new ContaBancaria(userId);
        return repository.save(conta);
    }

    public BigDecimal consultarSaldo(String userId) {
        BigDecimal saldo = repository.findSaldoByUserId(userId);
        if (saldo == null) {
            throw new RuntimeException("Conta não encontrada para o userId: " + userId);
        }
        return saldo;
    }

}
