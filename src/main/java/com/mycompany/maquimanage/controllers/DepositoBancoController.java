package com.mycompany.maquimanage.controllers;

import com.mycompany.maquimanage.entities.DepositosBanco;
import com.mycompany.maquimanage.models.dto.RegistrarDepositoDTO;
import com.mycompany.maquimanage.services.DepositoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/depositos")
public class DepositoBancoController {

    private final DepositoService depositoBancoService;

    public DepositoBancoController(DepositoService depositoBancoService) {
        this.depositoBancoService = depositoBancoService;
    }

    @PostMapping
    public ResponseEntity<DepositosBanco> registrarDeposito(@RequestBody RegistrarDepositoDTO request) {
        try {
            // Aquí puedes obtener el idUsuario desde el DTO, token o lógica interna
            Integer idUsuario = request.getIdUsuario(); // Cambia esto según cómo obtengas el usuario

            if (idUsuario == null) {
                throw new IllegalArgumentException("El ID de usuario no puede ser nulo.");
            }

            DepositosBanco nuevoDeposito = depositoBancoService.registrarDeposito(request, idUsuario);
            return ResponseEntity.ok(nuevoDeposito);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<DepositosBanco>> obtenerTodosLosDepositos() {
        List<DepositosBanco> depositos = depositoBancoService.obtenerDepositos();
        return ResponseEntity.ok(depositos);
    }

    @GetMapping("/suma-dia")
    public ResponseEntity<Map<String, BigDecimal>> obtenerSumaDepositosDelDia() {
        BigDecimal sumaDepositos = depositoBancoService.obtenerSumaDepositosDelDia();
        Map<String, BigDecimal> response = new HashMap<>();
        response.put("sumaDepositosDia", sumaDepositos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/suma-general")
    public ResponseEntity<Map<String, BigDecimal>> obtenerSumaDepositosGeneral() {
        BigDecimal sumaGeneral = depositoBancoService.obtenerSumaDepositosGeneral();
        Map<String, BigDecimal> response = new HashMap<>();
        response.put("sumaDepositosGeneral", sumaGeneral);
        return ResponseEntity.ok(response);
    }

}
