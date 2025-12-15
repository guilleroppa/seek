package com.seek.seleccion.dto;

public class ClienteMetricasDTO {

    private double promedioEdad;
    private double desviacionEstandarEdad;

    public ClienteMetricasDTO(double promedioEdad, double desviacionEstandarEdad) {
        this.promedioEdad = promedioEdad;
        this.desviacionEstandarEdad = desviacionEstandarEdad;
    }

    public double getPromedioEdad() {
        return promedioEdad;
    }

    public double getDesviacionEstandarEdad() {
        return desviacionEstandarEdad;
    }

}
