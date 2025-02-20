package ec.webmarket.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.webmarket.restful.domain.Cita;
import ec.webmarket.restful.domain.Horario;
import ec.webmarket.restful.persistence.CitaRepository;
import ec.webmarket.restful.persistence.HorarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    public Cita crearCita(Cita cita) {
        Optional<Horario> horario = horarioRepository.findById(cita.getHorario().getId());
        if (horario.isPresent() && horario.get().isDisponible()) {
            horario.get().setDisponible(false);
            horarioRepository.save(horario.get());
            return citaRepository.save(cita);
        }
        return null;
    }

    public List<Cita> obtenerCitas() {
        return citaRepository.findAll();
    }

    public Cita obtenerCitaPorId(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    public List<Cita> obtenerCitasPorPaciente(String cedula) {
        return citaRepository.findByPaciente_Cedula(cedula);
    }

    public List<Cita> obtenerCitasPorOdontologo(String cedula) {
        return citaRepository.findByOdontologo_Cedula(cedula);
    }

    public Cita actualizarCita(Long id, Cita citaActualizada) {
        Optional<Cita> citaExistente = citaRepository.findById(id);
        if (citaExistente.isPresent()) {
            Cita cita = citaExistente.get();
            cita.setPaciente(citaActualizada.getPaciente());
            cita.setOdontologo(citaActualizada.getOdontologo());
            cita.setHorario(citaActualizada.getHorario());
            cita.setEstado(citaActualizada.getEstado());
            cita.setMotivo(citaActualizada.getMotivo());
            return citaRepository.save(cita);
        }
        return null;
    }

    public void cancelarCita(Long id) {
        Optional<Cita> cita = citaRepository.findById(id);
        if (cita.isPresent()) {
            Cita c = cita.get();
            c.setEstado("cancelada");
            Horario horario = c.getHorario();
            horario.setDisponible(true);
            horarioRepository.save(horario);
            citaRepository.save(c);
        }
    }
}