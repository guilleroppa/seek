package com.seek.seleccion.controller;

import com.seek.seleccion.dto.ClienteConEventoDTO;
import com.seek.seleccion.dto.ErrorResponse;
import com.seek.seleccion.model.Cliente;
import com.seek.seleccion.service.ClienteServiceImpl;
import com.seek.seleccion.util.BusinessException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Operaciones relacionadas con clientes")
public class ClienteController {

    private final ClienteServiceImpl service;

    public ClienteController(ClienteServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> registrar(@Valid @RequestBody Cliente cliente) {
        if (cliente.getEdad() < 18) {
            throw new BusinessException("El cliente debe ser mayor de edad");
        }

        Cliente nuevo = service.registrar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }


    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = service.listar();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/metricas")
    public ResponseEntity<Object> obtenerMetricas() {
        return ResponseEntity.ok(service.calcularMetricas());
    }

    @GetMapping("/con-esperanza")
    public ResponseEntity<List<ClienteConEventoDTO>> listarConEsperanza() {
        return ResponseEntity.ok(service.listarConEsperanzaVida());
    }

}
