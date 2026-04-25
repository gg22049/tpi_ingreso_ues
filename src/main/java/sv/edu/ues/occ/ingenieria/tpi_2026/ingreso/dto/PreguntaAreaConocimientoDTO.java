/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author usermein
 */
public record PreguntaAreaConocimientoDTO(
        @NotNull
        @Min(1)
        @Max(Long.MAX_VALUE)
        long idPregunta,
        @NotNull
        @Min(1)
        @Max(Integer.MAX_VALUE)
        int idAreaConocimiento,
        String observaciones
        ) {

}
