/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import java.util.Date;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteOpcion;

/**
 *
 * @author usermein lo dejare aqui por si acaso al ser tabla intermedia
 */
public record AspiranteOpcionDTO(
        Long idAspiranteOpcion,
        String idOpcion,
        Date fechaCreacion,
        Long idAspirante
        ) {

    public AspiranteOpcionDTO(AspiranteOpcion aspiranteOpcion) {
        this(
                aspiranteOpcion.getIdAspiranteOpcion(),
                aspiranteOpcion.getIdOpcion(),
                aspiranteOpcion.getFechaCreacion(),
                aspiranteOpcion.getIdAspirante() != null
                ? aspiranteOpcion.getIdAspirante().getIdAspirante() : null
        );
    }

}
