package ec.webmarket.restful.api.v1;

import ec.webmarket.restful.domain.Horario;
import ec.webmarket.restful.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @PostMapping
    public ResponseEntity<Horario> crearHorario(@RequestBody Horario horario) {
        return ResponseEntity.ok(horarioService.crearHorario(horario));
    }

    @GetMapping
    public List<Horario> obtenerHorarios() {
        return horarioService.obtenerHorarios();
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Horario>> obtenerHorariosPorFecha(@PathVariable String fecha) {
        return ResponseEntity.ok(horarioService.obtenerHorariosPorFecha(LocalDate.parse(fecha)));
    }

    @GetMapping("/odontologo/{cedula}")
    public ResponseEntity<List<Horario>> obtenerHorariosPorOdontologo(@PathVariable String cedula) {
        return ResponseEntity.ok(horarioService.obtenerHorariosPorOdontologo(cedula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> actualizarHorario(@PathVariable Long id, @RequestBody Horario horario) {
        return ResponseEntity.ok(horarioService.actualizarHorario(id, horario));
    }
}