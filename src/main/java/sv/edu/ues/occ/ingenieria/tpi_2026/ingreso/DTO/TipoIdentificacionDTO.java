/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoIdentificacion;

/**
 *
 * @author usermein
 */
public record TipoIdentificacionDTO(
        Integer idTipoIdentificacion,
        String nombre,
        String observaciones) {
    
    public TipoIdentificacionDTO(TipoIdentificacion tipoIdentificacion){
        this(tipoIdentificacion.getIdTipoIdentificacion(),
                tipoIdentificacion.getNombre(),
                tipoIdentificacion.getObservaciones());
    }
    
}
