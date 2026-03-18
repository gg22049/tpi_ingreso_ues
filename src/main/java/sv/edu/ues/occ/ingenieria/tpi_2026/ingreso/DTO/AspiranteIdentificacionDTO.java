/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteIdentificacion;

/**
 *
 * @author usermein
 */
public record AspiranteIdentificacionDTO(
        int idAspirante,
        int idTipoIdentificacion,
        String valor,
        String imagenUrl,
        String observaciones
        ) {

    public AspiranteIdentificacionDTO(AspiranteIdentificacion aspiranteIdentificacion) {
      
        
        this(
                aspiranteIdentificacion.getAspiranteIdentificacionPK().getIdAspirante() ,
                aspiranteIdentificacion.getAspiranteIdentificacionPK().getIdTipoIdentificacion() ,
                aspiranteIdentificacion.getValor(),
                aspiranteIdentificacion.getImagenUrl(),
                aspiranteIdentificacion.getObservaciones()
        );
    }
}
