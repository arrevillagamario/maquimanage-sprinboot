package com.mycompany.maquimanage.repositories;

import com.mycompany.maquimanage.entities.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaldoRepository extends JpaRepository<Saldo, Integer> {
}
