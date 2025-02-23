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
        return repository.save(new ContaBancaria(userId));
    }

    public Integer getNumConta(String userId){
        return repository.findContaByUserId(userId);
    }

    public BigDecimal consultarSaldo(String userId) {
        return repository.findSaldoByUserId(userId);
    }

}
