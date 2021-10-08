package jmriosp.com.tarea01.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "Detalle de la venta DATA")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DetalleVentaDTO {
    private Integer idDetalleVenta;

    @Schema(description = "Identificador de la venta")
    @JsonIgnore
    private VentaDTO venta;

    @Schema(description = "Identificador del producto")
    private ProductoDTO producto;

    @Schema(description = "Catidad de productos")
    private Integer cantidad;

}
