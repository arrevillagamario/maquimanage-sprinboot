package com.mycompany.maquimanage.repositories;

import com.mycompany.maquimanage.entities.IngresosMaquina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngresoMaquinaRepository extends JpaRepository<IngresosMaquina, Integer> {
}
