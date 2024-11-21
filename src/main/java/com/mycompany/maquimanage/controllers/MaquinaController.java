package com.mycompany.maquimanage.controllers;

import com.mycompany.maquimanage.models.dto.MaquinaRequestDTO;
import com.mycompany.maquimanage.services.MaquinaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {

    private final MaquinaService maquinaService;

    public MaquinaController(MaquinaService maquinaService) {
        this.maquinaService = maquinaService;
    }

    @GetMapping
    public ResponseEntity<List<?>> listarMaquinas() {
        return ResponseEntity.ok(maquinaService.listarMaquinas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMaquinaPorId(@PathVariable Integer id) {
        return maquinaService.obtenerMaquinaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> registrarMaquina(@RequestBody MaquinaRequestDTO dto) {
        return ResponseEntity.ok(maquinaService.registrarMaquina(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMaquina(@PathVariable Integer id, @RequestBody MaquinaRequestDTO dto) {
        return maquinaService.actualizarMaquina(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/actualizar-depositos")
    public ResponseEntity<?> actualizarDepositos(@PathVariable Integer id, @RequestBody Map<String, BigDecimal> requestBody) {
        BigDecimal nuevoDeposito = requestBody.get("nuevoDeposito");
        return maquinaService.actualizarDepositos(id, nuevoDeposito)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMaquina(@PathVariable Integer id) {
        if (maquinaService.eliminarMaquina(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
