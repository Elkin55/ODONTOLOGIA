package ec.webmarket.restful.persistence;

import ec.webmarket.restful.domain.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByFecha(LocalDate fecha);
    List<Horario> findByOdontologo_Cedula(String cedula);
}
