package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author usermein
 */
public record DistractorAreaConocimientoDTO(
        @NotNull
        @Min(1L)
        @Max(Long.MAX_VALUE)
        Long idDistractor,
        @NotNull
        @Min(1)
        @Max(Integer.MAX_VALUE)
        int idAreaConocimiento,
        String observaciones
        ) {

}
