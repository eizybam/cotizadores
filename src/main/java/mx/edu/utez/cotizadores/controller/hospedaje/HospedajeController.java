package mx.edu.utez.cotizadores.controller.hospedaje;

import jakarta.validation.Valid;
import mx.edu.utez.cotizadores.controller.hospedaje.dto.RequestHospedajeDTO;
import mx.edu.utez.cotizadores.controller.hospedaje.dto.ResponseHospedajeDTO;
import mx.edu.utez.cotizadores.service.hospedaje.HospedajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/hospedajes")
public class HospedajeController {

    private final HospedajeService hospedajeService;

    public HospedajeController(HospedajeService hospedajeService) {
        this.hospedajeService = hospedajeService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<ResponseHospedajeDTO> cotizar(@RequestBody @Valid RequestHospedajeDTO payload){
        return ResponseEntity
                .status(200)
                .body(
                        hospedajeService.cotizar(payload)
                );
    }
}
