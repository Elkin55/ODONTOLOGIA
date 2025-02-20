package ec.webmarket.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.webmarket.restful.domain.Horario;
import ec.webmarket.restful.persistence.HorarioRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public Horario crearHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public List<Horario> obtenerHorarios() {
        return horarioRepository.findAll();
    }

    public List<Horario> obtenerHorariosPorFecha(LocalDate fecha) {
        return horarioRepository.findByFecha(fecha);
    }

    public List<Horario> obtenerHorariosPorOdontologo(String cedula) {
        return horarioRepository.findByOdontologo_Cedula(cedula);
    }

    public Horario actualizarHorario(Long id, Horario horario) {
        Optional<Horario> horarioExistente = horarioRepository.findById(id);
        if (horarioExistente.isPresent()) {
            Horario h = horarioExistente.get();
            h.setFecha(horario.getFecha());
            h.setHoraInicio(horario.getHoraInicio());
            h.setHoraFin(horario.getHoraFin());
            h.setDisponible(horario.isDisponible());
            return horarioRepository.save(h);
        }
        return null;
    }
}