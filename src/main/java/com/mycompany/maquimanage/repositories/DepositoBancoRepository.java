package com.mycompany.maquimanage.repositories;

import com.mycompany.maquimanage.entities.DepositosBanco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface DepositoBancoRepository extends JpaRepository<DepositosBanco, Integer> {
    List<DepositosBanco> findByFechaBetween(Instant startDate, Instant endDate);
}
