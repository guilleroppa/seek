package com.seek.seleccion.controller;

import com.seek.seleccion.dto.ClienteConEventoDTO;
import com.seek.seleccion.model.Cliente;
import com.seek.seleccion.service.ClienteServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Operaciones relacionadas con clientes")
public class ClienteController {

    private final ClienteServiceImpl service;

    public ClienteController(ClienteServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> registrar(@RequestBody Cliente cliente) {
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
