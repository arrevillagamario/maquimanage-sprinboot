package com.mycompany.maquimanage.repositories;

import com.mycompany.maquimanage.entities.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaquinaRepository extends JpaRepository<Maquina, Integer> {
}
