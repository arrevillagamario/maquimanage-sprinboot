package com.mycompany.maquimanage.services;

import com.mycompany.maquimanage.entities.SalidasGenerale;
import com.mycompany.maquimanage.models.dto.RegistrarSalidaDTO;

import java.math.BigDecimal;
import java.util.List;

public interface SalidasGeneralesService {
    SalidasGenerale crearSalida(RegistrarSalidaDTO salidaDTO, Integer idUsuario);
    List<SalidasGenerale> obtenerTodasLasSalidas();
    BigDecimal obtenerSumaSalidasDelDia();
    BigDecimal obtenerSumaSalidasGeneral();
}
