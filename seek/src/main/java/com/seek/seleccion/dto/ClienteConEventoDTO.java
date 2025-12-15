package com.seek.seleccion.dto;

import java.time.LocalDate;

public class ClienteConEventoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private LocalDate fechaNacimiento;
    private LocalDate fechaEsperanzaVida; // cálculo derivado

    public ClienteConEventoDTO(Long id, String nombre, String apellido, Integer edad, LocalDate fechaNacimiento, LocalDate fechaEsperanzaVida) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaEsperanzaVida = fechaEsperanzaVida;
    }

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public Integer getEdad() { return edad; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public LocalDate getFechaEsperanzaVida() { return fechaEsperanzaVida; }
}
