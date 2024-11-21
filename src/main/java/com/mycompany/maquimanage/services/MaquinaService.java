package com.mycompany.maquimanage.services;

import com.mycompany.maquimanage.entities.Maquina;
import com.mycompany.maquimanage.models.dto.MaquinaRequestDTO;
import com.mycompany.maquimanage.repositories.MaquinaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MaquinaService {

    private final MaquinaRepository maquinaRepository;

    public MaquinaService(MaquinaRepository maquinaRepository) {
        this.maquinaRepository = maquinaRepository;
    }

    public List<Maquina> listarMaquinas() {
        return maquinaRepository.findAll();
    }

    public Optional<Maquina> obtenerMaquinaPorId(Integer id) {
        return maquinaRepository.findById(id);
    }

    public Maquina registrarMaquina(MaquinaRequestDTO dto) {
        Maquina nuevaMaquina = new Maquina();
        nuevaMaquina.setEstado(dto.getEstado());
        nuevaMaquina.setMaquina(dto.getMaquina());
        nuevaMaquina.setDepositos(dto.getDepositos());
        return maquinaRepository.save(nuevaMaquina);
    }

    public Optional<Maquina> actualizarMaquina(Integer id, MaquinaRequestDTO dto) {
        Optional<Maquina> maquinaOpt = maquinaRepository.findById(id);
        if (maquinaOpt.isPresent()) {
            Maquina maquina = maquinaOpt.get();
            maquina.setEstado(dto.getEstado());
            maquina.setMaquina(dto.getMaquina());
            maquina.setDepositos(dto.getDepositos());
            return Optional.of(maquinaRepository.save(maquina));
        }
        return Optional.empty();
    }

    public Optional<Maquina> actualizarDepositos(Integer id, BigDecimal nuevoDeposito) {
        if (nuevoDeposito.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El valor del depósito no puede ser negativo.");
        }

        Optional<Maquina> maquinaOpt = maquinaRepository.findById(id);
        if (maquinaOpt.isPresent()) {
            Maquina maquina = maquinaOpt.get();
            maquina.setDepositos(nuevoDeposito);
            return Optional.of(maquinaRepository.save(maquina));
        }
        return Optional.empty();
    }

    public boolean eliminarMaquina(Integer id) {
        if (maquinaRepository.existsById(id)) {
            maquinaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
