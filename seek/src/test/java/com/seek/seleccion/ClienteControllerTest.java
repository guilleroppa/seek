package com.seek.seleccion;

import com.seek.seleccion.controller.ClienteController;
import com.seek.seleccion.dto.ClienteConEventoDTO;
import com.seek.seleccion.dto.ClienteMetricasDTO;
import com.seek.seleccion.model.Cliente;
import com.seek.seleccion.service.ClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @Mock
    private ClienteServiceImpl service;

    @InjectMocks
    private ClienteController controller;

    public ClienteControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Micaela");
        cliente.setApellido("Ramírez");
        cliente.setEdad(6);
        cliente.setFechaNacimiento(LocalDate.of(2019, 5, 12));

        when(service.registrar(any(Cliente.class))).thenReturn(cliente);

        ResponseEntity<Cliente> response = controller.registrar(cliente);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(201, response.getStatusCode().value());
        assertEquals("Micaela", response.getBody().getNombre());
        verify(service, times(1)).registrar(cliente);
    }


    @Test
    void testListarClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Micaela");

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Paula");

        when(service.listar()).thenReturn(Arrays.asList(cliente1, cliente2));

        ResponseEntity<List<Cliente>> response = controller.listar();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
        verify(service, times(1)).listar();
    }

    @Test
    void testObtenerMetricas() {
        ClienteMetricasDTO metricas = new ClienteMetricasDTO(8.0, 2.0);
        when(service.calcularMetricas()).thenReturn(metricas);

        ResponseEntity<Object> response = controller.obtenerMetricas();

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody() instanceof ClienteMetricasDTO);
        assertEquals(8.0, ((ClienteMetricasDTO) response.getBody()).getPromedioEdad());
        verify(service, times(1)).calcularMetricas();
    }

    @Test
    void testListarConEvento() {
        ClienteConEventoDTO dto = new ClienteConEventoDTO(
                1L, "Micaela", "Ramírez", 6,
                LocalDate.of(2019, 5, 12),
                LocalDate.of(2099, 5, 12)
        );

        when(service.listarConEsperanzaVida()).thenReturn(Arrays.asList(dto));

        ResponseEntity<List<ClienteConEventoDTO>> response = controller.listarConEvento();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
        assertEquals("Micaela", response.getBody().get(0).getNombre());
        verify(service, times(1)).listarConEsperanzaVida();
    }
}