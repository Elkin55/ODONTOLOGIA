package ec.webmarket.restful.persistence;

import ec.webmarket.restful.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByPaciente_Cedula(String cedula);
    List<Cita> findByOdontologo_Cedula(String cedula);
}
