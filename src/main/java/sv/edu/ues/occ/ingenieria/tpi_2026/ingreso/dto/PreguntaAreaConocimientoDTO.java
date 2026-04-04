/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author usermein
 */
public record PreguntaAreaConocimientoDTO(
        @NotNull
        long idPregunta,
        @NotNull
        int idAreaConocimiento,
        String observaciones
        ) {

}
