package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author usermein
 */
public record PruebaJornadaAulaAspiranteOpcionDTO(
        @NotNull
        long idPrueba,
        @NotNull
        long idJornada,
        @NotNull
        String idAula,
        @NotNull
        long idAspiranteOpcion,
        @NotNull
        Boolean activo,
        Date fecha
        ) {

}
