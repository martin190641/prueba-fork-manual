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
public class PersonaDTO {
    private Integer idPersona;

    @Schema(description = "nombres de la persona")
    @Size(min = 3, message = "{nombres.size}")
    private String nombres;

    @Schema(description = "apellidos de la persona")
    @Size(min = 3, message = "{apellidos.size}")
    private String apellidos;
}
