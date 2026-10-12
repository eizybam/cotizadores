package mx.edu.utez.cotizadores.controller.hospedaje.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseHospedajeDTO {
    private String nombreHuesped;
    private String tipoHabitacion;
    private double costoHospedaje;
    private double ajusteTemporada;
    private double descuentoEstancia;
    private double costoDesayuno;
    private double costoEstacionamiento;
    private double subtotal;
    private double impuesto;
    private double total;
}
