package com.mycompany.maquimanage.repositories;

import com.mycompany.maquimanage.entities.Conversione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionRepository extends JpaRepository<Conversione, Integer> {
}
