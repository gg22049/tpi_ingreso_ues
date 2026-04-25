package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author usermein
 */
public record PruebaJornadaAulaAspiranteOpcionExamenDTO(
        @NotNull
        long idPrueba,
        @NotNull
        long idJornada,
        @NotNull
        String idAula,
        @NotNull
        long idAspiranteOpcion,
        @Min(1)
        @Max(100)
        @NotNull
        BigDecimal resultado,
        @NotNull
        Date fechaResultado,
        String imagenUrl,
        String observaciones
        ) {

}
