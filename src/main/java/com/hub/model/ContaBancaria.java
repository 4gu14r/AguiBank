package com.hub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "contas_bancaria")
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conta;

    private String userId;

    private float saldo;

    public enum TipoConta {
        CORRENTE,
        POUPANCA
    }

    private TipoConta tipoConta; 

    
    public ContaBancaria() {} // Construtor padrão exigido pelo JPA

    public ContaBancaria(String userId) {
        this.userId = userId;
        this.saldo = 0;
        this.tipoConta = TipoConta.CORRENTE;
    }


    // Getters e Setters
    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public String getIdUsuario() {
        return userId;
    }

    public void setIdUsuario(String userId) {
        this.userId = userId;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }
}
