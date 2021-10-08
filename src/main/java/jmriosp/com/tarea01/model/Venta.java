package jmriosp.com.tarea01.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false, foreignKey = @ForeignKey(name = "fk_venta_persona"))
    private Persona persona;

    @Column(nullable = false)
    private Double importe;

    @OneToMany(mappedBy = "venta", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<DetalleVenta> detalleVentas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venta)) return false;
        Venta venta = (Venta) o;
        return getIdVenta().equals(venta.getIdVenta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVenta());
    }

    @PrePersist
    private void setFecha() {
        if (this.fecha == null)
            this.fecha = LocalDateTime.now();
    }
}
