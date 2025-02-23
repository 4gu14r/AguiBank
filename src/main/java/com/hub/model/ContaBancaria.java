package com.hub.model;

import java.math.BigDecimal;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "contas_bancaria")
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conta;

    private BigDecimal saldo; 

    public enum TipoConta {
        CORRENTE,
        POUPANCA
    }
    
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @Column(name = "user_id")
    private String userId;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataHoraCriacao;

    @UpdateTimestamp
    @Column(name = "data_modificacao", nullable = false)
    private LocalDateTime dataHoraModificacao;
   
    public ContaBancaria() {} // Construtor padrão exigido pelo JPA

    public ContaBancaria(String userId) {
        this.saldo = BigDecimal.ZERO; 
        this.tipoConta = TipoConta.CORRENTE;
        this.userId = userId;
    }

    // Getters e Setters
    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public LocalDateTime getDataHoraModificacao() {
        return dataHoraModificacao;
    }

    public void setDataHoraModificacao(LocalDateTime dataHoraModificacao) {
        this.dataHoraModificacao = dataHoraModificacao;
    }
}