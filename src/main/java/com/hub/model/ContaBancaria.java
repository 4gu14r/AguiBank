package com.hub.model;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "contas_bancaria")
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int conta;

    private BigDecimal saldo; 

    public enum TipoConta {
        CORRENTE,
        POUPANCA
    }
    
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    private String userId;
   
    public ContaBancaria() {} // Construtor padrão exigido pelo JPA

    public ContaBancaria(String userId) {
        this.saldo = BigDecimal.ZERO; 
        this.tipoConta = TipoConta.CORRENTE;
        this.userId = userId;
    }

    // Getters e Setters
    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public BigDecimal getSaldo() { 
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) { 
        this.saldo = saldo;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getUserID() {
        return userId;
    }

    public void setUserID(String userId) {
        this.userId = userId;
    }
}