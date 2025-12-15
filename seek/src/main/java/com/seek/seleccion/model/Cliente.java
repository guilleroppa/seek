package com.seek.seleccion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del cliente", example = "1")
    private Long id;

    @Schema(description = "Nombre del cliente", example = "Micaela")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Schema(description = "Apellido del cliente", example = "Chicata")
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @Schema(description = "Edad del cliente", example = "6")
    @Min(value = 0, message = "La edad no puede ser negativa")
    private Integer edad;

    @Schema(description = "Fecha de nacimiento", example = "2019-05-12")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

}
