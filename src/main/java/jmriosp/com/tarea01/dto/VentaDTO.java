package jmriosp.com.tarea01.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Schema(description = "Venta DATA")
public class VentaDTO {
    private Integer idVenta;

    @Schema(description = "fecha de la venta")
    private LocalDateTime fecha;

    @Schema(description = "Persona a la que se le realiza la venta")
    private PersonaDTO persona;

    @Schema(description = "Valor total de la venta")
    private Double importe;

    private List<DetalleVentaDTO> detalleVentas;

}
