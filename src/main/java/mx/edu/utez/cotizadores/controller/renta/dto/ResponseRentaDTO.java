package mx.edu.utez.cotizadores.controller.renta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRentaDTO {
    private String nombreCliente;
    private String tipoVehiculo;
    private double costoRenta;
    private int kilometrosAdicionales;
    private double cargoKilometros;
    private double cargoEdad;
    private double seguro;
    private double descuento;
    private double total;
}
