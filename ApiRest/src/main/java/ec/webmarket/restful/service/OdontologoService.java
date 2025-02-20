package ec.webmarket.restful.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.webmarket.restful.domain.Cita;
import ec.webmarket.restful.domain.Odontologo;
import ec.webmarket.restful.persistence.CitaRepository;
import ec.webmarket.restful.persistence.OdontologoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;

    @Autowired
    private CitaRepository citaRepository;

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    public List<Odontologo> obtenerOdontologos() {
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> obtenerOdontologoPorCedula(String cedula) {
        return odontologoRepository.findById(cedula);
    }

    public List<Cita> obtenerCitasPorOdontologo(String cedula) {
        return citaRepository.findByOdontologo_Cedula(cedula);
    }
}