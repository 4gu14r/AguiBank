package com.hub.repository;

import com.hub.model.ContaBancaria;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Integer> {

    @Query("SELECT c.saldo FROM ContaBancaria c WHERE c.userId = :userId")
    BigDecimal findSaldoByUserId(@Param("userId") String userId);

}
