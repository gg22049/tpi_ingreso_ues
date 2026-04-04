package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 *
 * @author usermein
 */
public record PruebaClaveAreaConocimientoPreguntaDTO(
        @NotNull
        long idPruebaClave,
        @NotNull
        int idAreaConocimiento,
        @NotNull
        long idPregunta,
        @NotNull
        @Min(value = 0)
        @Max(value = 100)
        BigDecimal porcentaje
        ) {

}
