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

    // Contas
    public ContaBancaria criarConta(String userId) {
        return repository.save(new ContaBancaria(userId));
    }

    public ContaBancaria atualizarConta(ContaBancaria conta) {
        return repository.save(conta);
    }
    
    // Consultas
    public BigDecimal consultarSaldoPorUserId(String userId) {
        return repository.findSaldoByUserId(userId);
    }

    public BigDecimal consultaSaldoPorNumConta(Long conta){
        return repository.findSaldoByConta(conta);
    }

    public Long consultarNumContaPorUserId(String userId){
        return repository.findContaByUserId(userId);
    }

}
