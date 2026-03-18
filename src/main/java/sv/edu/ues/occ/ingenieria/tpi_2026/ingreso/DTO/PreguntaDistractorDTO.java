/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractor;

/**
 *
 * @author usermein
 */
public record PreguntaDistractorDTO(
        long idPregunta,
        long idDistractor,
        Boolean correcto,
        String observaciones
        ) {

    public PreguntaDistractorDTO(PreguntaDistractor preguntaDistractor) {
        this(
                preguntaDistractor.getPreguntaDistractorPK().getIdPregunta(),
                preguntaDistractor.getPreguntaDistractorPK().getIdDistractor(),
                preguntaDistractor.getCorrecto(),
                preguntaDistractor.getObservaciones()
        );
    }
}
