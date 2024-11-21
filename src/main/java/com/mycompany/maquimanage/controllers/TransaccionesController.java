package com.mycompany.maquimanage.controllers;

import com.mycompany.maquimanage.models.dto.TransaccionDTO;
import com.mycompany.maquimanage.services.TransaccionesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionesController {

    private final TransaccionesService transaccionesService;

    public TransaccionesController(TransaccionesService transaccionesService) {
        this.transaccionesService = transaccionesService;
    }

    @GetMapping("/dia")
    public ResponseEntity<Map<String, Object>> listarTransaccionesDelDia() {
        // Obtenemos las transacciones del día
        List<TransaccionDTO> transacciones = transaccionesService.listarTransaccionesDelDia();
        // Calculamos la suma total de las transacciones del día
        BigDecimal sumaTotal = transaccionesService.obtenerSumaTransaccionesDelDia();

        // Creamos un mapa para incluir ambas respuestas
        Map<String, Object> response = new HashMap<>();
        response.put("transacciones", transacciones);
        response.put("sumaTotal", sumaTotal);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/general")
    public ResponseEntity<Map<String, Object>> listarTodasLasTransacciones() {
        // Obtenemos todas las transacciones
        List<TransaccionDTO> transacciones = transaccionesService.listarTodasLasTransacciones();

        // Calculamos la suma total (opcional si es necesario)
        BigDecimal sumaTotalGeneral = transacciones.stream()
                .map(TransaccionDTO::getMonto)
                .filter(Objects::nonNull)
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Creamos un mapa para incluir ambas respuestas
        Map<String, Object> response = new HashMap<>();
        response.put("transacciones", transacciones);
        response.put("sumaTotalGeneral", sumaTotalGeneral);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/dia/suma")
    public ResponseEntity<Map<String, Object>> obtenerSumaTransaccionesDelDia() {
        // Calculamos la suma total de las transacciones del día
        BigDecimal sumaTotal = transaccionesService.obtenerSumaTransaccionesDelDia();

        // Retornamos la respuesta como JSON
        Map<String, Object> response = new HashMap<>();
        response.put("sumaTotal", sumaTotal);

        return ResponseEntity.ok(response);
    }

}
