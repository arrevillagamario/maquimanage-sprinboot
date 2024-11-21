package com.mycompany.maquimanage.controllers;

import com.mycompany.maquimanage.entities.CategoriasSalida;
import com.mycompany.maquimanage.models.dto.RegistrarCategoriaSalidaDTO;
import com.mycompany.maquimanage.services.CategoriaSalidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categoriasSalida")
public class CategoriaSalidaController {

    private final CategoriaSalidaService categoriaSalidaService;

    public CategoriaSalidaController(CategoriaSalidaService categoriaSalidaService) {
        this.categoriaSalidaService = categoriaSalidaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriasSalida>> listarCategorias() {
        return ResponseEntity.ok(categoriaSalidaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasSalida> obtenerCategoriaPorId(@PathVariable Long id) {
        return categoriaSalidaService.obtenerCategoriaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriasSalida> crearCategoria(
            @Valid @RequestBody RegistrarCategoriaSalidaDTO dto) {
        CategoriasSalida nuevaCategoria = categoriaSalidaService.crearCategoria(dto);
        return ResponseEntity.ok(nuevaCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        if (categoriaSalidaService.eliminarCategoria(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
