package com.mycompany.maquimanage.controllers;

import com.mycompany.maquimanage.entities.Premio;
import com.mycompany.maquimanage.models.dto.RegistrarPremioDTO;
import com.mycompany.maquimanage.services.PremioService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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
    public ResponseEntity<BigDecimal> obtenerSumaPremiosDelDia() {
        return ResponseEntity.ok(premioService.obtenerSumaPremiosDelDia());
    }

    @GetMapping("/suma-general")
    public ResponseEntity<BigDecimal> obtenerSumaPremiosGeneral() {
        return ResponseEntity.ok(premioService.obtenerSumaPremiosGeneral());
    }
}
