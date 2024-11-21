package com.mycompany.maquimanage.controllers;

import com.mycompany.maquimanage.entities.Premio;
import com.mycompany.maquimanage.models.dto.RegistrarPremioDTO;
import com.mycompany.maquimanage.services.PremioService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/premios")
public class PremioController {

    private final PremioService premioService;

    public PremioController(PremioService premioService) {
        this.premioService = premioService;
    }

    @PostMapping
    public ResponseEntity<Premio> registrarPremio(@Validated @RequestBody RegistrarPremioDTO premioDTO) {
        return ResponseEntity.ok(premioService.registrarPremio(premioDTO));
    }

    @GetMapping
    public ResponseEntity<List<Premio>> listarPremios() {
        return ResponseEntity.ok(premioService.obtenerPremios());
    }

    @GetMapping("/suma-dia")
    public ResponseEntity<Map<String, Object>> obtenerSumaPremiosDelDia() {
        // Utilizar el servicio para obtener la suma del día como un JSON estructurado
        Map<String, Object> response = premioService.obtenerSumaPremiosDelDia();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/suma-general")
    public ResponseEntity<Map<String, Object>> obtenerSumaPremiosGeneral() {
        // Utilizar el servicio para obtener la suma general como un JSON estructurado
        Map<String, Object> response = premioService.obtenerSumaPremiosGeneral();
        return ResponseEntity.ok(response);
    }
}
