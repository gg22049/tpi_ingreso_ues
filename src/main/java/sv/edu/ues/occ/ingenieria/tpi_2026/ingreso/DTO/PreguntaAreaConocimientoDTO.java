/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimiento;

/**
 *
 * @author usermein
 */
public record PreguntaAreaConocimientoDTO(
        long idPregunta,
        int idAreaConocimiento,
        String observaciones
        ) {

    public PreguntaAreaConocimientoDTO(PreguntaAreaConocimiento preguntaAreaConocimiento) {
        this(
                preguntaAreaConocimiento.getPreguntaAreaConocimientoPK().getIdPregunta(),
                preguntaAreaConocimiento.getPreguntaAreaConocimientoPK().getIdAreaConocimiento(),
                preguntaAreaConocimiento.getObservaciones()
        );
    }

}
