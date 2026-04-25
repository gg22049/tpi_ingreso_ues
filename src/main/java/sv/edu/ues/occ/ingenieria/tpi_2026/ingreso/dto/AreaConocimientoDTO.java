package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author usermein
 */
public record AreaConocimientoDTO(
        Integer idAreaConocimiento,
        @NotBlank
        String nombre,
        String descripcion,
        @NotNull
        Boolean activo,
        Integer idAreaConocimientoPadre) {

}
