/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta;

/**
 *
 * @author usermein
 */
public record PreguntaDTO (
        Long idPregunta,
        String valor,
        Boolean activo,
        String imageUrl,
        String observaciones
        ){
    
    public PreguntaDTO( Pregunta pregunta){
        this(
        pregunta.getIdPregunta(),
        pregunta.getValor(),
        pregunta.getActivo(),
        pregunta.getImagenUrl(),
        pregunta.getObservaciones()
        );
    }
    
}
