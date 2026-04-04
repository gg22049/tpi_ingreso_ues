package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author usermein
 */
public record PruebaClaveDTO(
        @NotNull
        Long idPruevaClave,
        @NotBlank
        @Size(min = 1, max = 64)
        String nombre,
        @NotNull
        Long idPrueba) {

}
