package ec.webmarket.restful.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_cedula")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "odontologo_cedula")
    private Odontologo odontologo;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;

    private String estado; // "confirmada", "cancelada", "reprogramada"
    private String motivo;

    // Constructor vacío (necesario para JPA)
    public Cita() {
    }

    // Constructor con parámetros
    public Cita(Paciente paciente, Odontologo odontologo, Horario horario, String estado, String motivo) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.horario = horario;
        this.estado = estado;
        this.motivo = motivo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Odontologo getOdontologo() { return odontologo; }
    public void setOdontologo(Odontologo odontologo) { this.odontologo = odontologo; }

    public Horario getHorario() { return horario; }
    public void setHorario(Horario horario) { this.horario = horario; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}