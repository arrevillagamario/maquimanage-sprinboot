package com.mycompany.maquimanage.controllers;

import com.mycompany.maquimanage.models.dto.RegistrarSalidaDTO;
import com.mycompany.maquimanage.services.SalidasGeneralesService;
import com.mycompany.maquimanage.entities.SalidasGenerale;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/salidas-generales")
public class SalidasGeneralesController {

    private final SalidasGeneralesService salidasGeneralesService;

    public SalidasGeneralesController(SalidasGeneralesService salidasGeneralesService) {
        this.salidasGeneralesService = salidasGeneralesService;
    }

    // Endpoint para registrar una salida
    @PostMapping
    public ResponseEntity<SalidasGenerale> registrarSalida(@RequestBody RegistrarSalidaDTO salidaDTO) {
        try {
            if (salidaDTO.getIdUsuario() == null) {
                return ResponseEntity.badRequest().body(null);
            }

            SalidasGenerale nuevaSalida = salidasGeneralesService.crearSalida(salidaDTO, salidaDTO.getIdUsuario().intValue());
            return ResponseEntity.ok(nuevaSalida);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Endpoint para listar todas las salidas
    @GetMapping
    public ResponseEntity<List<SalidasGenerale>> listarSalidas() {
        List<SalidasGenerale> salidas = salidasGeneralesService.obtenerTodasLasSalidas();
        return ResponseEntity.ok(salidas);
    }

    @GetMapping("/suma-dia")
    public ResponseEntity<Map<String, Object>> obtenerSumaSalidasDelDia() {
        try {
            BigDecimal sumaSalidas = salidasGeneralesService.obtenerSumaSalidasDelDia();

            // Construimos la respuesta en formato JSON
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Suma de salidas del día obtenida correctamente");
            response.put("suma", sumaSalidas);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            // En caso de error, devolvemos un mensaje y el código de error
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error al obtener la suma de salidas del día");
            response.put("error", ex.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/suma-general")
    public ResponseEntity<Map<String, Object>> obtenerSumaSalidasGeneral() {
        try {
            BigDecimal sumaSalidasGeneral = salidasGeneralesService.obtenerSumaSalidasGeneral();

            // Construimos la respuesta en formato JSON
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Suma total de salidas generales obtenida correctamente");
            response.put("suma", sumaSalidasGeneral);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            // En caso de error, devolvemos un mensaje y el código de error
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error al obtener la suma total de salidas generales");
            response.put("error", ex.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
