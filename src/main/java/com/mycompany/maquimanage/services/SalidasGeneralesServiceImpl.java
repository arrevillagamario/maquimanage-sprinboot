package com.mycompany.maquimanage.services;

import com.mycompany.maquimanage.entities.CategoriasSalida;
import com.mycompany.maquimanage.entities.SalidasGenerale;
import com.mycompany.maquimanage.entities.Usuario;
import com.mycompany.maquimanage.models.dto.RegistrarSalidaDTO;
import com.mycompany.maquimanage.repositories.CategoriaSalidaRepository;
import com.mycompany.maquimanage.repositories.SalidaGeneralRepository;
import com.mycompany.maquimanage.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class SalidasGeneralesServiceImpl implements SalidasGeneralesService {

    private final SalidaGeneralRepository salidaGeneralRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaSalidaRepository categoriaSalidaRepository;

    public SalidasGeneralesServiceImpl(SalidaGeneralRepository salidaGeneralRepository,
                                       UsuarioRepository usuarioRepository,
                                       CategoriaSalidaRepository categoriaSalidaRepository) {
        this.salidaGeneralRepository = salidaGeneralRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaSalidaRepository = categoriaSalidaRepository;
    }

    @Override
    public SalidasGenerale crearSalida(RegistrarSalidaDTO salidaDTO, Integer idUsuario) {
        if (salidaDTO.getMonto() == null || salidaDTO.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }

        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findById(Long.valueOf(idUsuario))
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        // Verificar si la categoría de salida existe
        categoriaSalidaRepository.findById(salidaDTO.getIdCategoriaSalida())
                .orElseThrow(() -> new IllegalArgumentException("Categoría de salida no encontrada."));

        // Crear la nueva salida
        SalidasGenerale nuevaSalida = new SalidasGenerale();
        nuevaSalida.setDetalle(salidaDTO.getDetalle());
        nuevaSalida.setMonto(salidaDTO.getMonto());
        nuevaSalida.setFechaHora(Instant.now());
        nuevaSalida.setIdUsuario(usuario);
        CategoriasSalida categoriaSalida = categoriaSalidaRepository
                .findById(salidaDTO.getIdCategoriaSalida())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada."));
        nuevaSalida.setIdCategoriaSalida(categoriaSalida);

        // Guardar la salida en la base de datos
        return salidaGeneralRepository.save(nuevaSalida);
    }

    @Override
    public List<SalidasGenerale> obtenerTodasLasSalidas() {
        return salidaGeneralRepository.findAllByOrderByFechaHoraDesc();
    }

    @Override
    public BigDecimal obtenerSumaSalidasDelDia() {
        LocalDate today = LocalDate.now();
        Instant startOfDay = today.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endOfDay = today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();

        return salidaGeneralRepository.obtenerSumaSalidasDelDia(startOfDay, endOfDay)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal obtenerSumaSalidasGeneral() {
        return salidaGeneralRepository.obtenerSumaSalidasGeneral()
                .orElse(BigDecimal.ZERO);
    }
}
