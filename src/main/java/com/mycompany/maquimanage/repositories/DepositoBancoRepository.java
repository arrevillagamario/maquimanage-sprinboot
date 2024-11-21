package com.mycompany.maquimanage.repositories;

import com.mycompany.maquimanage.entities.DepositosBanco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface DepositoBancoRepository extends JpaRepository<DepositosBanco, Integer> {

    // Obtener depósitos dentro de un rango de fechas
    List<DepositosBanco> findByFechaBetween(Instant startDate, Instant endDate);

    // Suma de depósitos del día
    @Query("SELECT SUM(d.monto) FROM DepositosBanco d WHERE d.fecha BETWEEN :startOfDay AND :endOfDay")
    Optional<BigDecimal> obtenerSumaDepositosDelDia(Instant startOfDay, Instant endOfDay);

    // Suma total de depósitos
    @Query("SELECT SUM(d.monto) FROM DepositosBanco d")
    Optional<BigDecimal> obtenerSumaDepositosGeneral();
}
