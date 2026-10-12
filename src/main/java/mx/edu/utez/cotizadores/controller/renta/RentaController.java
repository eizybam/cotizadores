package mx.edu.utez.cotizadores.controller.renta;

import jakarta.validation.Valid;
import mx.edu.utez.cotizadores.controller.renta.dto.RequestRentaDTO;
import mx.edu.utez.cotizadores.controller.renta.dto.ResponseRentaDTO;
import mx.edu.utez.cotizadores.service.renta.RentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/rentas")
public class RentaController {

    private final RentaService rentaService;

    public RentaController(RentaService rentaService) {
        this.rentaService = rentaService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<ResponseRentaDTO> cotizar(@RequestBody @Valid RequestRentaDTO payload){
        return ResponseEntity
                .status(200)
                .body(
                        rentaService.cotizar(payload)
                );
    }
}
