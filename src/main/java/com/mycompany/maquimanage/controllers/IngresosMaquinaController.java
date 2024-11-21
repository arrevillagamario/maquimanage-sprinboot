package com.mycompany.maquimanage.controllers;

import com.mycompany.maquimanage.entities.IngresosMaquina;
import com.mycompany.maquimanage.models.dto.RegistrarIngresoDTO;
import com.mycompany.maquimanage.services.IngresosMaquinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/ingresos-maquinas")
public class IngresosMaquinaController {

    private final IngresosMaquinaService ingresosMaquinaService;

    public IngresosMaquinaController(IngresosMaquinaService ingresosMaquinaService) {
        this.ingresosMaquinaService = ingresosMaquinaService;
    }

    // Endpoint para registrar un nuevo ingreso
    @PostMapping
    public ResponseEntity<?> registrarIngreso(@Valid @RequestBody RegistrarIngresoDTO ingresoDTO) {
        try {
            IngresosMaquina ingreso = ingresosMaquinaService.registrarIngreso(ingresoDTO, ingresoDTO.getIdUsuario());
            return ResponseEntity.ok(ingreso);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocurrió un error al registrar el ingreso.");
        }
    }


    // Endpoint para obtener todos los ingresos
    @GetMapping
    public ResponseEntity<List<IngresosMaquina>> listarIngresos() {
        List<IngresosMaquina> ingresos = ingresosMaquinaService.obtenerIngresosMaquinas();
        return ResponseEntity.ok(ingresos);
    }

    // Endpoint para obtener la suma de ingresos del día
    @GetMapping("/suma-dia")
    public ResponseEntity<?> obtenerSumaIngresosDelDia() {
        try {
            BigDecimal sumaIngresos = ingresosMaquinaService.obtenerSumaIngresosDelDia();
            return ResponseEntity.ok(new HashMap<>() {{
                put("sumaIngresos", sumaIngresos);
            }});
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al obtener la suma de ingresos del día.");
        }
    }


    // Endpoint para obtener la suma general de ingresos
    @GetMapping("/suma-general")
    public ResponseEntity<?> obtenerSumaIngresosGeneral() {
        try {
            BigDecimal sumaIngresosGeneral = ingresosMaquinaService.obtenerSumaIngresosGeneral();
            return ResponseEntity.ok(new SumaResponse(sumaIngresosGeneral, "Suma general de ingresos calculada con éxito."));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocurrió un error al obtener la suma general de ingresos.");
        }
    }

    // Clase interna para respuestas de suma
    private static class SumaResponse {
        private final BigDecimal sumaIngresos;
        private final String mensaje;

        public SumaResponse(BigDecimal sumaIngresos, String mensaje) {
            this.sumaIngresos = sumaIngresos;
            this.mensaje = mensaje;
        }

        public BigDecimal getSumaIngresos() {
            return sumaIngresos;
        }

        public String getMensaje() {
            return mensaje;
        }
    }
}
