package mx.edu.utez.cotizadores.service.envio;

import mx.edu.utez.cotizadores.controller.envio.dto.RequestEnvioDTO;
import mx.edu.utez.cotizadores.controller.envio.dto.ResponseEnvioDTO;
import mx.edu.utez.cotizadores.exception.customExceptions.CustomBadRequestException;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    public ResponseEnvioDTO cotizar(RequestEnvioDTO payload){

        if (payload.getPesoKg() > 50){
            throw new CustomBadRequestException("No se aceptan paquetes de mas de 50 kg");
        }

        if (payload.getLargoCm() > 150 || payload.getAnchoCm() > 150 || payload.getAltoCm() > 150){
            throw new CustomBadRequestException("No se aceptan paquetes con alguna dimension mayor a 150 cm");
        }

        double volumen = payload.getLargoCm() * payload.getAnchoCm() * payload.getAltoCm();

        if (volumen > 1000000){
            throw new CustomBadRequestException("No se aceptan paquetes con un volumen mayor a 1,000,000 cm3");
        }

        double costo = 80;
        costo += payload.getPesoKg() * 12;

        if (volumen > 50000){
            costo += 100;
        }

        switch (payload.getTipoEnvio()){
            case "EXPRESS":
                costo = costo * 1.40;
                break;
            case "MISMO_DIA":
                costo = costo * 1.70;
                break;
        }

        double seguro = 0;

        if (payload.getValorDeclarado() > 10000){
            seguro = payload.getValorDeclarado() * 0.02;
        }

        ResponseEnvioDTO respuesta = new ResponseEnvioDTO();

        respuesta.setCodigoPostal(payload.getCodigoPostal());
        respuesta.setTipoEnvio(payload.getTipoEnvio());
        respuesta.setVolumenCm3(redondear(volumen));
        respuesta.setCostoEnvio(redondear(costo));
        respuesta.setSeguro(redondear(seguro));
        respuesta.setTotal(redondear(costo + seguro));

        return respuesta;
    }

    private double redondear(double valor){
        return Math.round(valor * 100.0) / 100.0;
    }
}
