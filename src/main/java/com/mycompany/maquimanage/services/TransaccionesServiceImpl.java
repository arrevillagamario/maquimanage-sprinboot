package com.mycompany.maquimanage.services;

import com.mycompany.maquimanage.models.dto.TransaccionDTO;
import com.mycompany.maquimanage.repositories.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransaccionesServiceImpl implements TransaccionesService {

    private final DepositoBancoRepository depositoBancoRepository;
    private final SalidaGeneralRepository salidaGeneralRepository;
    private final IngresosMaquinaRepository ingresoMaquinaRepository;
    private final PremioRepository premioRepository;

    public TransaccionesServiceImpl(DepositoBancoRepository depositoBancoRepository,
                                    SalidaGeneralRepository salidaGeneralRepository,
                                    IngresosMaquinaRepository ingresoMaquinaRepository,
                                    PremioRepository premioRepository) {
        this.depositoBancoRepository = depositoBancoRepository;
        this.salidaGeneralRepository = salidaGeneralRepository;
        this.ingresoMaquinaRepository = ingresoMaquinaRepository;
        this.premioRepository = premioRepository;
    }

    @Override
    public List<TransaccionDTO> listarTransaccionesDelDia() {
        LocalDate today = LocalDate.now();
        Instant startOfDay = today.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endOfDay = today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();

        List<TransaccionDTO> transacciones = new ArrayList<>();

        depositoBancoRepository.findByFechaBetween(startOfDay, endOfDay).forEach(deposito -> {
            TransaccionDTO dto = new TransaccionDTO();
            dto.setTipoTransaccion("Depósito");
            dto.setMonto(deposito.getMonto().doubleValue());
            dto.setFecha(deposito.getFecha().toString());
            dto.setUsuario(deposito.getIdUsuario().getNombre());
            transacciones.add(dto);
        });

        salidaGeneralRepository.findAllByOrderByFechaHoraDesc().forEach(salida -> {
            TransaccionDTO dto = new TransaccionDTO();
            dto.setTipoTransaccion("Salida");
            dto.setMonto(salida.getMonto().doubleValue());
            dto.setFecha(salida.getFechaHora().toString());
            dto.setDetalle(salida.getDetalle());
            dto.setUsuario(salida.getIdUsuario().getNombre());
            transacciones.add(dto);
        });

        // Similar lógica para ingresos y premios...

        return transacciones;
    }

    @Override
    public List<TransaccionDTO> listarTodasLasTransacciones() {
        List<TransaccionDTO> transacciones = new ArrayList<>();

        // Formato de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                .withLocale(new Locale("es", "ES"));

        // Depósitos
        depositoBancoRepository.findAll().forEach(deposito -> {
            TransaccionDTO dto = new TransaccionDTO();
            dto.setTipoTransaccion("Depósito");
            dto.setMonto(deposito.getMonto() != null ? deposito.getMonto().doubleValue() : 0.0);
            dto.setFecha(deposito.getFecha().atZone(ZoneId.systemDefault()).format(formatter));
            dto.setUsuario(deposito.getIdUsuario().getNombre());
            transacciones.add(dto);
        });

        // Salidas
        salidaGeneralRepository.findAllByOrderByFechaHoraDesc().forEach(salida -> {
            TransaccionDTO dto = new TransaccionDTO();
            dto.setTipoTransaccion("Salida");
            dto.setMonto(salida.getMonto() != null ? salida.getMonto().doubleValue() : 0.0);
            dto.setFecha(salida.getFechaHora().atZone(ZoneId.systemDefault()).format(formatter));
            dto.setDetalle(salida.getDetalle());
            dto.setUsuario(salida.getIdUsuario().getNombre());
            transacciones.add(dto);
        });

        // Ingresos
        ingresoMaquinaRepository.findAll().forEach(ingreso -> {
            TransaccionDTO dto = new TransaccionDTO();
            dto.setTipoTransaccion("Ingreso");
            dto.setMonto(ingreso.getMontoGanancia() != null ? ingreso.getMontoGanancia().doubleValue() : 0.0);
            dto.setFecha(ingreso.getFechaDeposito().atZone(ZoneId.systemDefault()).format(formatter));
            dto.setUsuario(ingreso.getIdUsuario().getNombre());
            transacciones.add(dto);
        });

        // Premios
        premioRepository.findAll().forEach(premio -> {
            TransaccionDTO dto = new TransaccionDTO();
            dto.setTipoTransaccion("Premio");

            // Manejo de posibles valores null para MontoBillete y MontoCoras
            BigDecimal montoBillete = premio.getMontoBillete() != null ? premio.getMontoBillete() : BigDecimal.ZERO;
            BigDecimal montoCoras = premio.getMontoCoras() != null ? premio.getMontoCoras() : BigDecimal.ZERO;

            dto.setMonto(montoBillete.add(montoCoras).doubleValue());
            dto.setFecha(premio.getFechaEntrega().atZone(ZoneId.systemDefault()).format(formatter));
            dto.setUsuario(premio.getIdUsuarioEntrega().getNombre());
            transacciones.add(dto);
        });

        // Ordenar por fecha
        transacciones.sort(Comparator.comparing(TransaccionDTO::getFecha).reversed());

        return transacciones;
    }



    @Override
    public BigDecimal obtenerSumaTransaccionesDelDia() {
        // Obtener todas las transacciones del día
        List<TransaccionDTO> transacciones = listarTransaccionesDelDia();

        // Sumamos todos los montos de las transacciones, asegurándonos de convertir a BigDecimal
        BigDecimal sumaTotal = transacciones.stream()
                .map(TransaccionDTO::getMonto) // Obtener el monto
                .filter(Objects::nonNull) // Evitar valores nulos
                .map(BigDecimal::valueOf) // Convertir Double a BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Sumar

        System.out.println("Suma total de transacciones del día: " + sumaTotal);
        return sumaTotal;
    }





}
