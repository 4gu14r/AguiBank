package com.hub.repository;

import com.hub.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByContaOrigemContaOrderByDataHoraCriacaoDesc(Long contaId);
    List<Transacao> findByContaDestinoContaOrderByDataHoraCriacaoDesc(Long contaId);
}