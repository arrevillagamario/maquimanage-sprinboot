package com.mycompany.maquimanage.services;

import com.mycompany.maquimanage.entities.CategoriasSalida;
import com.mycompany.maquimanage.models.dto.RegistrarCategoriaSalidaDTO;
import com.mycompany.maquimanage.repositories.CategoriaSalidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaSalidaService {

    private final CategoriaSalidaRepository categoriaSalidaRepository;

    public CategoriaSalidaService(CategoriaSalidaRepository categoriaSalidaRepository) {
        this.categoriaSalidaRepository = categoriaSalidaRepository;
    }

    public List<CategoriasSalida> listarCategorias() {
        return categoriaSalidaRepository.findAll();
    }

    public Optional<CategoriasSalida> obtenerCategoriaPorId(Long id) {
        return categoriaSalidaRepository.findById(id);
    }

    public CategoriasSalida crearCategoria(RegistrarCategoriaSalidaDTO dto) {
        CategoriasSalida nuevaCategoria = new CategoriasSalida();
        nuevaCategoria.setDescripcion(dto.getDescripcion());
        return categoriaSalidaRepository.save(nuevaCategoria);
    }

    public boolean eliminarCategoria(Long id) {
        if (categoriaSalidaRepository.existsById(id)) {
            categoriaSalidaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
