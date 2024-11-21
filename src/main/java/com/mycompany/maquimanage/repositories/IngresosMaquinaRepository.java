package com.mycompany.maquimanage.repositories;

import com.mycompany.maquimanage.entities.IngresosMaquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface IngresosMaquinaRepository extends JpaRepository<IngresosMaquina, Integer> {

    @Query("SELECT i FROM IngresosMaquina i ORDER BY i.fechaDeposito DESC")
    Optional<IngresosMaquina> findAllByOrderByFechaDepositoDesc();

    @Query("SELECT SUM(i.montoGanancia) " +
            "FROM IngresosMaquina i " +
            "WHERE CAST(i.fechaDeposito AS date) = CAST(:fechaDeposito AS date)")
    Optional<BigDecimal> obtenerSumaIngresosDelDia(@Param("fechaDeposito") LocalDate fechaDeposito);




    @Query("SELECT SUM(i.montoGanancia) FROM IngresosMaquina i")
    Optional<BigDecimal> obtenerSumaIngresosGeneral();
}
