package com.seek.seleccion;

import com.seek.seleccion.dto.ClienteConEventoDTO;
import com.seek.seleccion.dto.ClienteMetricasDTO;
import com.seek.seleccion.model.Cliente;
import com.seek.seleccion.repository.ClienteRepository;
import com.seek.seleccion.service.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceImplTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteServiceImpl service;

    @BeforeEach
    void setUp() {
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

        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = service.registrar(cliente);

        assertNotNull(resultado);
        assertEquals("Micaela", resultado.getNombre());
        verify(repository, times(1)).save(cliente);
    }

    @Test
    void testListarClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Micaela");

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Paula");

        when(repository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> resultado = service.listar();

        assertEquals(2, resultado.size());
        assertEquals("Micaela", resultado.get(0).getNombre());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testCalcularMetricas() {
        Cliente cliente1 = new Cliente();
        cliente1.setEdad(10);

        Cliente cliente2 = new Cliente();
        cliente2.setEdad(20);

        when(repository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        ClienteMetricasDTO metricas = service.calcularMetricas();

        assertEquals(15.0, metricas.getPromedioEdad());
        assertTrue(metricas.getDesviacionEstandarEdad() > 0);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testListarConEsperanzaVida() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Micaela");
        cliente.setApellido("Ramírez");
        cliente.setEdad(6);
        cliente.setFechaNacimiento(LocalDate.of(2019, 5, 12));

        when(repository.findAll()).thenReturn(Arrays.asList(cliente));

        List<ClienteConEventoDTO> resultado = service.listarConEsperanzaVida();

        assertEquals(1, resultado.size());
        assertEquals("Micaela", resultado.get(0).getNombre());
        assertEquals(LocalDate.of(2099, 5, 12), resultado.get(0).getFechaEsperanzaVida());
        verify(repository, times(1)).findAll();
    }
}