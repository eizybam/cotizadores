package mx.edu.utez.cotizadores.controller.hospedaje.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestHospedajeDTO {

    @NotBlank(message = "El nombre del huesped es obligatorio")
    @Size(min = 3, message = "El nombre del huesped debe tener al menos 3 caracteres")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitacion es obligatorio")
    @Pattern(
            regexp = "^(INDIVIDUAL|DOBLE|SUITE)$",
            message = "El tipo de habitacion debe ser INDIVIDUAL, DOBLE o SUITE"
    )
    private String tipoHabitacion;

    @NotNull(message = "El numero de noches es obligatorio")
    @Positive(message = "El numero de noches debe ser mayor a 0")
    private Integer numeroNoches;

    @NotNull(message = "El numero de huespedes es obligatorio")
    @Positive(message = "El numero de huespedes debe ser mayor a 0")
    private Integer numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    @Pattern(
            regexp = "^(BAJA|REGULAR|ALTA)$",
            message = "La temporada debe ser BAJA, REGULAR o ALTA"
    )
    private String temporada;

    @NotNull(message = "Indica si se incluye desayuno")
    private Boolean incluyeDesayuno;

    @NotNull(message = "Indica si se incluye estacionamiento")
    private Boolean incluyeEstacionamiento;
}
