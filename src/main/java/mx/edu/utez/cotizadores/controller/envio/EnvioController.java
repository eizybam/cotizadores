package mx.edu.utez.cotizadores.controller.envio;

import jakarta.validation.Valid;
import mx.edu.utez.cotizadores.controller.envio.dto.RequestEnvioDTO;
import mx.edu.utez.cotizadores.controller.envio.dto.ResponseEnvioDTO;
import mx.edu.utez.cotizadores.service.envio.EnvioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @PostMapping("/cotizar")
    public ResponseEntity<ResponseEnvioDTO> cotizar(@RequestBody @Valid RequestEnvioDTO payload){
        return ResponseEntity
                .status(200)
                .body(
                        envioService.cotizar(payload)
                );
    }
}
