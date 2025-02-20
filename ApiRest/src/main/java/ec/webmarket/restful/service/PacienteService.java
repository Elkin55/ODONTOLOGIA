package ec.webmarket.restful.service;


import ec.webmarket.restful.domain.Paciente;
import ec.webmarket.restful.persistence.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente registrarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> obtenerPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> obtenerPacientePorCedula(String cedula) {
        return pacienteRepository.findById(cedula);
    }

    public Paciente actualizarPaciente(String cedula, Paciente paciente) {
        if (pacienteRepository.existsById(cedula)) {
            return pacienteRepository.save(paciente);
        }
        return null;
    }
}

