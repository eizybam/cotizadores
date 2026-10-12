package mx.edu.utez.cotizadores.controller.envio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEnvioDTO {
    private String codigoPostal;
    private String tipoEnvio;
    private double volumenCm3;
    private double costoEnvio;
    private double seguro;
    private double total;
}
