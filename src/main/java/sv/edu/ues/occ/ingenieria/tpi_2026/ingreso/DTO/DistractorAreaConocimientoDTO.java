package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimiento;

/**
 *
 * @author usermein
 */
public record DistractorAreaConocimientoDTO(
        Long idDistractor,
        int idAreaConocimiento,
        String observaciones
        ) {

    public DistractorAreaConocimientoDTO(DistractorAreaConocimiento distractorAreaConocimiento) {
        this(
                distractorAreaConocimiento.getDistractorAreaConocimientoPK().getIdDistractor(),
                distractorAreaConocimiento.getDistractorAreaConocimientoPK().getIdAreaConocimiento(),
                distractorAreaConocimiento.getObservaciones()
        );
    }
}
