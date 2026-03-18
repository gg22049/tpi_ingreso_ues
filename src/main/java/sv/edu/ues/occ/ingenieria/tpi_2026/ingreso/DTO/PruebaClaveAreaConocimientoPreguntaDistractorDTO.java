/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import java.util.Date;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractor;

/**
 *
 * @author usermein
 */
public record PruebaClaveAreaConocimientoPreguntaDistractorDTO(
        long idPruebaClave,
        int idAreaConocimiento,
        long idPregunta,
        long idDistractor,
        Date fechaCracion,
        String observaciones
        ) {

    public PruebaClaveAreaConocimientoPreguntaDistractorDTO(PruebaClaveAreaConocimientoPreguntaDistractor pruebaClaveAreaConocimientoPreguntaDistractor) {
        this(
                pruebaClaveAreaConocimientoPreguntaDistractor.getPruebaClaveAreaConocimientoPreguntaDistractorPK().getIdPruebaClave(),
                pruebaClaveAreaConocimientoPreguntaDistractor.getPruebaClaveAreaConocimientoPreguntaDistractorPK().getIdAreaConocimiento(),
                pruebaClaveAreaConocimientoPreguntaDistractor.getPruebaClaveAreaConocimientoPreguntaDistractorPK().getIdPregunta(),
                pruebaClaveAreaConocimientoPreguntaDistractor.getPruebaClaveAreaConocimientoPreguntaDistractorPK().getIdDistractor(),
                pruebaClaveAreaConocimientoPreguntaDistractor.getFechaCreacion(),
                pruebaClaveAreaConocimientoPreguntaDistractor.getObservaciones()
        );
    }
}
