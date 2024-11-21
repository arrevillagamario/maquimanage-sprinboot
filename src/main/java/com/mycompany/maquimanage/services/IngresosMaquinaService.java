package com.mycompany.maquimanage.services;

import com.mycompany.maquimanage.entities.IngresosMaquina;
import com.mycompany.maquimanage.entities.Maquina;
import com.mycompany.maquimanage.entities.Usuario;
import com.mycompany.maquimanage.models.dto.RegistrarIngresoDTO;
import com.mycompany.maquimanage.repositories.IngresosMaquinaRepository;
import com.mycompany.maquimanage.repositories.MaquinaRepository;
import com.mycompany.maquimanage.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class IngresosMaquinaService {

    private final IngresosMaquinaRepository ingresosMaquinaRepository;
    private final MaquinaRepository maquinaRepository;
    private final UsuarioRepository usuarioRepository;

    public IngresosMaquinaService(IngresosMaquinaRepository ingresosMaquinaRepository,
                                  MaquinaRepository maquinaRepository,
                                  UsuarioRepository usuarioRepository) {
        this.ingresosMaquinaRepository = ingresosMaquinaRepository;
        this.maquinaRepository = maquinaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public IngresosMaquina registrarIngreso(RegistrarIngresoDTO ingresoDTO, Integer idUsuario) {
        if (ingresoDTO.getMontoDepositado() == null || ingresoDTO.getMontoDepositado().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto depositado debe ser mayor que cero.");
        }

        // Buscar la entidad Maquina por ID
        Maquina maquina = maquinaRepository.findById(ingresoDTO.getIdMaquina())
                .orElseThrow(() -> new IllegalArgumentException("La máquina con el ID proporcionado no existe."));

        // Crear el nuevo ingreso
        IngresosMaquina nuevoIngreso = new IngresosMaquina();
        nuevoIngreso.setIdMaquina(maquina); // Asignar la entidad Maquina
        nuevoIngreso.setMontoDepositado(ingresoDTO.getMontoDepositado());
        nuevoIngreso.setMontoGanancia(ingresoDTO.getMontoRetirado() != null
                ? ingresoDTO.getMontoRetirado()
                : BigDecimal.ZERO);
        nuevoIngreso.setFechaDeposito(Instant.now());
        nuevoIngreso.setIdUsuario(usuarioRepository.findById(idUsuario.longValue())
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el ID proporcionado no existe.")));


        return ingresosMaquinaRepository.save(nuevoIngreso);
    }

    public List<IngresosMaquina> obtenerIngresosMaquinas() {
        return ingresosMaquinaRepository.findAll();
    }

    public BigDecimal obtenerSumaIngresosDelDia() {
        LocalDate hoy = LocalDate.now(); // Fecha actual
        return ingresosMaquinaRepository.obtenerSumaIngresosDelDia(hoy)
                .orElse(BigDecimal.ZERO);
    }


    public BigDecimal obtenerSumaIngresosGeneral() {
        return ingresosMaquinaRepository.obtenerSumaIngresosGeneral()
                .orElse(BigDecimal.ZERO);
    }
}
