package com.mycompany.maquimanage.repositories;

import com.mycompany.maquimanage.entities.SalidasGenerale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface SalidaGeneralRepository extends JpaRepository<SalidasGenerale, Integer> {

    @Query("SELECT SUM(s.monto) FROM SalidasGenerale s WHERE s.fechaHora BETWEEN :start AND :end")
    Optional<BigDecimal> obtenerSumaSalidasDelDia(Instant start, Instant end);

    @Query("SELECT SUM(s.monto) FROM SalidasGenerale s")
    Optional<BigDecimal> obtenerSumaSalidasGeneral();

    List<SalidasGenerale> findAllByOrderByFechaHoraDesc();
}
