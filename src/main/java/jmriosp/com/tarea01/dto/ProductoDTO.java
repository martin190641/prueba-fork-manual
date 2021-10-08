package jmriosp.com.tarea01.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Schema(description = "Persona DATA")
public class ProductoDTO {
    private Integer idProducto;

    @Size(min = 3, message = "{nombres.size}")
    @Schema(description = "nombre del producto")
    private String nombre;

    @Size(min = 3, message = "{marca.size}")
    @Schema(description = "marca del producto")
    private String marca;
}
