package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author usermein
 */
public record DistractorDTO(
        long idDistractor,
        @NotBlank
        String valor,
        @NotNull
        Boolean activo,
        String imagenUrl) {

}
