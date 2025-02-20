package ec.webmarket.restful.api.v1;

import ec.webmarket.restful.domain.Paciente;
import ec.webmarket.restful.persistence.PacienteRepository;
import ec.webmarket.restful.service.PacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.registrarPaciente(paciente));
    }

    @GetMapping
    public List<Paciente> obtenerPacientes() {
        return pacienteService.obtenerPacientes();
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<Optional<Paciente>> obtenerPaciente(@PathVariable String cedula) {
        return ResponseEntity.ok(pacienteService.obtenerPacientePorCedula(cedula));
    }

    @PutMapping("/{cedula}")
    public ResponseEntity<Paciente> actualizarPaciente(@PathVariable String cedula, @RequestBody Paciente paciente) {
        Paciente actualizado = pacienteService.actualizarPaciente(cedula, paciente);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }
}
