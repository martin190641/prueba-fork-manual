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
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Size(min = 3, message = "{nombres.size}")
    @Column(nullable = false, length = 50)
    private String nombre;

    @Size(min = 3, message = "{marca.size}")
    @Column(nullable = false, length = 50)
    private String marca;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return getIdProducto().equals(producto.getIdProducto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProducto());
    }
}
