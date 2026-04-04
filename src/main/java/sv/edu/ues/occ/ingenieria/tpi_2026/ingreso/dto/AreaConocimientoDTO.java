package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author usermein
 */
public record AreaConocimientoDTO(
        Integer idAreaConocimiento,
        @NotNull
        String nombre,
        String descripcion,
        @NotNull
        Boolean activo,
        Integer idAreaConocimientoPadre) {

}
