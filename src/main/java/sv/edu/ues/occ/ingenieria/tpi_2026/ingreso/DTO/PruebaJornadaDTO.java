/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import java.util.Date;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornada;

/**
 *
 * @author usermein
 */
public record PruebaJornadaDTO(
        long idPrueba,
        long idJornada,
        Date fechaCreacion,
        String observaciones
        ) {

    public PruebaJornadaDTO(PruebaJornada pruebaJornada) {
        this(
                pruebaJornada.getPruebaJornadaPK().getIdPrueba(),
                pruebaJornada.getPruebaJornadaPK().getIdJornada(),
                pruebaJornada.getFechaCreacion(),
                pruebaJornada.getObservaciones()
        );
    }
}
