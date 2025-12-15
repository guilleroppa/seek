package com.seek.seleccion.service;

import com.seek.seleccion.dto.ClienteConEventoDTO;
import com.seek.seleccion.dto.ClienteMetricasDTO;
import com.seek.seleccion.model.Cliente;
import com.seek.seleccion.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente registrar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @Override
    public ClienteMetricasDTO calcularMetricas() {
        List<Cliente> clientes = repository.findAll();

        if (clientes.isEmpty()) {
            return new ClienteMetricasDTO(0, 0);
        }

        double promedio = clientes.stream()
                .mapToInt(Cliente::getEdad)
                .average()
                .orElse(0);

        double varianza = clientes.stream()
                .mapToDouble(c -> Math.pow(c.getEdad() - promedio, 2))
                .average()
                .orElse(0);

        double desviacionEstandar = Math.sqrt(varianza);

        return new ClienteMetricasDTO(promedio, desviacionEstandar);

    }

    @Override
    public List<ClienteConEventoDTO> listarConEsperanzaVida() {
        List<Cliente> clientes = repository.findAll();

        return clientes.stream().map(cliente -> {
            LocalDate fechaEsperanzaVida = cliente.getFechaNacimiento().plusYears(80); // cálculo derivado
            return new ClienteConEventoDTO(
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getEdad(),
                    cliente.getFechaNacimiento(),
                    fechaEsperanzaVida
            );
        }).collect(Collectors.toList());

    }
}
