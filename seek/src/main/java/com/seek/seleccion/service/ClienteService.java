package com.seek.seleccion.service;

import com.seek.seleccion.dto.ClienteConEventoDTO;
import com.seek.seleccion.dto.ClienteMetricasDTO;
import com.seek.seleccion.model.Cliente;
import java.util.List;

public interface ClienteService {

    Cliente registrar(Cliente cliente);
    List<Cliente> listar();
    ClienteMetricasDTO calcularMetricas();
    List<ClienteConEventoDTO> listarConEsperanzaVida();

}
