package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import java.math.BigDecimal;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPregunta;

/**
 *
 * @author usermein
 */
public record PruebaClaveAreaConocimientoPreguntaDTO(
        long idPruebaClave,
        int idAreaConocimiento,
        long idPregunta,
        BigDecimal porcentaje
        ) {

    public PruebaClaveAreaConocimientoPreguntaDTO(PruebaClaveAreaConocimientoPregunta pruebaClaveAreaConocimientoPregunta) {
        this(
                pruebaClaveAreaConocimientoPregunta.getPruebaClaveAreaConocimientoPreguntaPK().getIdPruebaClave(),
                pruebaClaveAreaConocimientoPregunta.getPruebaClaveAreaConocimientoPreguntaPK().getIdAreaConocimiento(),
                pruebaClaveAreaConocimientoPregunta.getPruebaClaveAreaConocimientoPreguntaPK().getIdPregunta(),
                pruebaClaveAreaConocimientoPregunta.getPorcentaje()
        );
    }
}
