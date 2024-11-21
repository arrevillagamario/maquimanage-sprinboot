package com.mycompany.maquimanage.services;

import com.mycompany.maquimanage.entities.DepositosBanco;
import com.mycompany.maquimanage.entities.Usuario;
import com.mycompany.maquimanage.models.dto.RegistrarDepositoDTO;
import com.mycompany.maquimanage.repositories.DepositoBancoRepository;
import com.mycompany.maquimanage.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class DepositoService {

    private final DepositoBancoRepository depositoBancoRepository;
    private final UsuarioRepository usuarioRepository;

    public DepositoService(DepositoBancoRepository depositoBancoRepository, UsuarioRepository usuarioRepository) {
        this.depositoBancoRepository = depositoBancoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Registrar un nuevo depósito
    public DepositosBanco registrarDeposito(RegistrarDepositoDTO depositoDTO, Integer idUsuario) {
        if (depositoDTO.getMonto() == null || depositoDTO.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero.");
        }

        // Buscar el usuario por ID
        Usuario usuario = usuarioRepository.findById(idUsuario.longValue())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el ID: " + idUsuario));


        DepositosBanco nuevoDeposito = new DepositosBanco();
        nuevoDeposito.setMonto(depositoDTO.getMonto());
        nuevoDeposito.setFecha(Instant.now());
        nuevoDeposito.setIdUsuario(usuario);

        return depositoBancoRepository.save(nuevoDeposito);
    }
    // Obtener todos los depósitos
    public List<DepositosBanco> obtenerDepositos() {
        return depositoBancoRepository.findAll();
    }

    // Obtener la suma de los depósitos del día
    public BigDecimal obtenerSumaDepositosDelDia() {
        LocalDate today = LocalDate.now();
        Instant startOfDay = today.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endOfDay = today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();

        return depositoBancoRepository.obtenerSumaDepositosDelDia(startOfDay, endOfDay).orElse(BigDecimal.ZERO);
    }

    // Obtener la suma total de los depósitos
    public BigDecimal obtenerSumaDepositosGeneral() {
        return depositoBancoRepository.obtenerSumaDepositosGeneral().orElse(BigDecimal.ZERO);
    }
}

