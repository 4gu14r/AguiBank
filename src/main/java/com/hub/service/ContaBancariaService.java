package com.hub.service;

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
}
