package com.mycompany.maquimanage.services;

import com.mycompany.maquimanage.entities.Maquina;
import com.mycompany.maquimanage.entities.Premio;
import com.mycompany.maquimanage.entities.Usuario;
import com.mycompany.maquimanage.models.dto.RegistrarPremioDTO;
import com.mycompany.maquimanage.repositories.MaquinaRepository;
import com.mycompany.maquimanage.repositories.PremioRepository;
import com.mycompany.maquimanage.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PremioService {

    private final PremioRepository premioRepository;
    private final MaquinaRepository maquinaRepository;
    private final UsuarioRepository usuarioRepository;

    public PremioService(PremioRepository premioRepository,
                         MaquinaRepository maquinaRepository,
                         UsuarioRepository usuarioRepository) {
        this.premioRepository = premioRepository;
        this.maquinaRepository = maquinaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Premio registrarPremio(RegistrarPremioDTO premioDTO) {
        // Buscar la máquina
        Maquina maquina = maquinaRepository.findById(premioDTO.getIdMaquina())
                .orElseThrow(() -> new IllegalArgumentException("La máquina con el ID proporcionado no existe."));

        // Buscar el usuario
        Usuario usuario = usuarioRepository.findById(premioDTO.getIdUsuario().longValue())
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el ID proporcionado no existe."));

        // Crear el nuevo premio
        Premio nuevoPremio = new Premio();
        nuevoPremio.setIdMaquina(maquina);
        nuevoPremio.setMontoBillete(premioDTO.getMontoBillete());
        nuevoPremio.setMontoCoras(premioDTO.getMontoCoras());
        nuevoPremio.setFechaEntrega(Instant.now());
        nuevoPremio.setIdUsuarioEntrega(usuario);

        return premioRepository.save(nuevoPremio);
    }

    public List<Premio> obtenerPremios() {
        return premioRepository.findAll();
    }

    public Map<String, Object> obtenerSumaPremiosDelDia() {
        // Calcular el inicio y el final del día actual
        LocalDate today = LocalDate.now();
        Instant startOfDay = today.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endOfDay = today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();

        // Obtener la suma desde el repositorio
        BigDecimal sumaDelDia = premioRepository.obtenerSumaPremiosDelDia(startOfDay, endOfDay).orElse(BigDecimal.ZERO);

        // Crear un mapa para la respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("fecha", today.toString());
        response.put("sumaPremiosDelDia", sumaDelDia);

        return response;
    }

    public Map<String, Object> obtenerSumaPremiosGeneral() {
        // Obtener la suma general desde el repositorio
        BigDecimal sumaGeneral = premioRepository.obtenerSumaPremiosGeneral().orElse(BigDecimal.ZERO);

        // Crear un mapa para la respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("sumaPremiosGeneral", sumaGeneral);

        return response;
    }
}
