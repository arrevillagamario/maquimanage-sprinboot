package com.mycompany.maquimanage.repositories;

import com.mycompany.maquimanage.entities.Premio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Integer> {

    @Query("SELECT SUM(COALESCE(p.montoBillete, 0) + COALESCE(p.montoCoras, 0)) " +
            "FROM Premio p " +
            "WHERE p.fechaEntrega >= :startOfDay AND p.fechaEntrega < :endOfDay")
    Optional<BigDecimal> obtenerSumaPremiosDelDia(@Param("startOfDay") Instant startOfDay,
                                                  @Param("endOfDay") Instant endOfDay);

    @Query("SELECT SUM(COALESCE(p.montoBillete, 0) + COALESCE(p.montoCoras, 0)) " +
            "FROM Premio p")
    Optional<BigDecimal> obtenerSumaPremiosGeneral();
}


