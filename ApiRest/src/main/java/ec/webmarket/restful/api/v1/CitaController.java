package ec.webmarket.restful.api.v1;

import ec.webmarket.restful.domain.Cita;
import ec.webmarket.restful.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<Cita> crearCita(@RequestBody Cita cita) {
        return ResponseEntity.ok(citaService.crearCita(cita));
    }

    @GetMapping
    public List<Cita> obtenerCitas() {
        return citaService.obtenerCitas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerCita(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.obtenerCitaPorId(id));
    }

    @GetMapping("/paciente/{cedula}")
    public ResponseEntity<List<Cita>> obtenerCitasPorPaciente(@PathVariable String cedula) {
        return ResponseEntity.ok(citaService.obtenerCitasPorPaciente(cedula));
    }

    @GetMapping("/odontologo/{cedula}")
    public ResponseEntity<List<Cita>> obtenerCitasPorOdontologo(@PathVariable String cedula) {
        return ResponseEntity.ok(citaService.obtenerCitasPorOdontologo(cedula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizarCita(@PathVariable Long id, @RequestBody Cita cita) {
        return ResponseEntity.ok(citaService.actualizarCita(id, cita));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarCita(@PathVariable Long id) {
        citaService.cancelarCita(id);
        return ResponseEntity.noContent().build();
    }
}