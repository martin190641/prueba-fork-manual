package jmriosp.com.tarea01.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;

    @Size(min = 3, message = "{nombres.size}")
    @Column(nullable = false, length = 60)
    private String nombres;

    @Size(min = 3, message = "{apellidos.size}")
    @Column(nullable = false, length = 60)
    private String apellidos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return getIdPersona().equals(persona.getIdPersona());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPersona());
    }
}
