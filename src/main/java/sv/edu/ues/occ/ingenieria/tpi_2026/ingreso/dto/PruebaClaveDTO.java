package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author usermein
 */
public record PruebaClaveDTO(
        Long idPruebaClave,
        @NotBlank
        @Size(min = 1, max = 64)
        String nombre,
        @Min(1L)
        @Max(Long.MAX_VALUE)
        @NotNull
        Long idPrueba) {

}
