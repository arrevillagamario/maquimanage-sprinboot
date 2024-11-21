package com.mycompany.maquimanage.services;

import com.mycompany.maquimanage.models.dto.TransaccionDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TransaccionesService {
    List<TransaccionDTO> listarTransaccionesDelDia();
    List<TransaccionDTO> listarTodasLasTransacciones();
    BigDecimal obtenerSumaTransaccionesDelDia();
}
