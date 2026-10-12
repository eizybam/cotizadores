package mx.edu.utez.cotizadores.service.hospedaje;

import mx.edu.utez.cotizadores.controller.hospedaje.dto.RequestHospedajeDTO;
import mx.edu.utez.cotizadores.controller.hospedaje.dto.ResponseHospedajeDTO;
import mx.edu.utez.cotizadores.exception.customExceptions.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    public ResponseHospedajeDTO cotizar(RequestHospedajeDTO payload){

        if (payload.getNumeroNoches() > 30){
            throw new CustomBadRequestException("No se acepta la reservacion, el numero de noches no puede superar 30");
        }

        double costoNoche = 0;
        int capacidad = 0;

        switch (payload.getTipoHabitacion()){
            case "INDIVIDUAL":
                costoNoche = 700;
                capacidad = 1;
                break;
            case "DOBLE":
                costoNoche = 1100;
                capacidad = 2;
                break;
            case "SUITE":
                costoNoche = 1800;
                capacidad = 4;
                break;
        }

        if (payload.getNumeroHuespedes() > capacidad){
            throw new CustomBadRequestException(
                    "No se acepta la reservacion, la habitacion " + payload.getTipoHabitacion()
                            + " es para maximo " + capacidad + " persona(s)"
            );
        }

        double costoHospedaje = costoNoche * payload.getNumeroNoches();

        double ajusteTemporada = 0;

        switch (payload.getTemporada()){
            case "BAJA":
                ajusteTemporada = -(costoHospedaje * 0.10);
                break;
            case "ALTA":
                ajusteTemporada = costoHospedaje * 0.25;
                break;
        }

        double costoDesayuno = 0;

        if (payload.getIncluyeDesayuno()){
            costoDesayuno = payload.getNumeroHuespedes() * payload.getNumeroNoches() * 150;
        }

        double costoEstacionamiento = 0;

        if (payload.getIncluyeEstacionamiento()){
            costoEstacionamiento = payload.getNumeroNoches() * 100;
        }

        double descuentoEstancia = 0;

        if (payload.getNumeroNoches() >= 7){
            descuentoEstancia = costoHospedaje * 0.08;
        }

        double subtotal = costoHospedaje + ajusteTemporada - descuentoEstancia + costoDesayuno + costoEstacionamiento;
        double impuesto = subtotal * 0.04;
        double total = subtotal + impuesto;

        ResponseHospedajeDTO respuesta = new ResponseHospedajeDTO();

        respuesta.setNombreHuesped(payload.getNombreHuesped());
        respuesta.setTipoHabitacion(payload.getTipoHabitacion());
        respuesta.setCostoHospedaje(redondear(costoHospedaje));
        respuesta.setAjusteTemporada(redondear(ajusteTemporada));
        respuesta.setDescuentoEstancia(redondear(descuentoEstancia));
        respuesta.setCostoDesayuno(redondear(costoDesayuno));
        respuesta.setCostoEstacionamiento(redondear(costoEstacionamiento));
        respuesta.setSubtotal(redondear(subtotal));
        respuesta.setImpuesto(redondear(impuesto));
        respuesta.setTotal(redondear(total));

        return respuesta;
    }

    private double redondear(double valor){
        return Math.round(valor * 100.0) / 100.0;
    }
}
