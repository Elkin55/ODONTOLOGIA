package ec.webmarket.restful.api.v1;

import ec.webmarket.restful.domain.Odontologo;
import ec.webmarket.restful.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
    }

    @GetMapping
    public List<Odontologo> obtenerOdontologos() {
        return odontologoService.obtenerOdontologos();
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<Optional<Odontologo>> obtenerOdontologo(@PathVariable String cedula) {
        return ResponseEntity.ok(odontologoService.obtenerOdontologoPorCedula(cedula));
    }

    @GetMapping("/{cedula}/citas")
    public ResponseEntity<?> obtenerCitasOdontologo(@PathVariable String cedula) {
        return ResponseEntity.ok(odontologoService.obtenerCitasPorOdontologo(cedula));
    }
}
