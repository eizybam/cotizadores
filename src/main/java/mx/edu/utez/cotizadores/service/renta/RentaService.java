package mx.edu.utez.cotizadores.service.renta;

import mx.edu.utez.cotizadores.controller.renta.dto.RequestRentaDTO;
import mx.edu.utez.cotizadores.controller.renta.dto.ResponseRentaDTO;
import mx.edu.utez.cotizadores.exception.customExceptions.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class RentaService {

    public ResponseRentaDTO cotizar(RequestRentaDTO payload){

        if (payload.getEdadConductor() < 18){
            throw new CustomBadRequestException("No se acepta la renta, el conductor es menor de 18 años");
        }

        if (payload.getDiasRenta() > 30){
            throw new CustomBadRequestException("No se acepta la renta, no puede superar los 30 dias");
        }

        if (payload.getKilometrosEstimados() > 5000){
            throw new CustomBadRequestException("No se acepta la renta, los kilometros estimados no pueden superar 5000");
        }

        if (payload.getTipoVehiculo().equals("CAMIONETA") && payload.getEdadConductor() < 25){
            throw new CustomBadRequestException("No se acepta la renta, para una CAMIONETA el conductor debe tener al menos 25 años");
        }

        double costoDiario = 0;

        switch (payload.getTipoVehiculo()){
            case "COMPACTO":
                costoDiario = 550;
                break;
            case "SEDAN":
                costoDiario = 700;
                break;
            case "SUV":
                costoDiario = 950;
                break;
            case "CAMIONETA":
                costoDiario = 1200;
                break;
        }

        double costoRenta = costoDiario * payload.getDiasRenta();

        int kilometrosIncluidos = payload.getDiasRenta() * 100;
        int kilometrosAdicionales = 0;

        if (payload.getKilometrosEstimados() > kilometrosIncluidos){
            kilometrosAdicionales = payload.getKilometrosEstimados() - kilometrosIncluidos;
        }

        double cargoKilometros = kilometrosAdicionales * 4;

        double cargoEdad = 0;

        if (payload.getEdadConductor() >= 18 && payload.getEdadConductor() <= 24){
            cargoEdad = (costoRenta + cargoKilometros) * 0.15;
        }

        double seguro = 0;

        if (payload.getSeguroCompleto()){
            seguro = payload.getDiasRenta() * 180;
        }

        double descuento = 0;

        if (payload.getDiasRenta() >= 7){
            descuento = costoRenta * 0.10;
        }

        double total = costoRenta - descuento + cargoKilometros + cargoEdad + seguro;

        ResponseRentaDTO respuesta = new ResponseRentaDTO();

        respuesta.setNombreCliente(payload.getNombreCliente());
        respuesta.setTipoVehiculo(payload.getTipoVehiculo());
        respuesta.setCostoRenta(redondear(costoRenta));
        respuesta.setKilometrosAdicionales(kilometrosAdicionales);
        respuesta.setCargoKilometros(redondear(cargoKilometros));
        respuesta.setCargoEdad(redondear(cargoEdad));
        respuesta.setSeguro(redondear(seguro));
        respuesta.setDescuento(redondear(descuento));
        respuesta.setTotal(redondear(total));

        return respuesta;
    }

    private double redondear(double valor){
        return Math.round(valor * 100.0) / 100.0;
    }
}
