/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author usermein
 */
public record PruebaClaveAreaConocimientoPreguntaDistractorDTO(
        @Min(1)
        @Max(Long.MAX_VALUE)
        @NotNull
        long idPruebaClave,
        @Min(1)
        @Max(Integer.MAX_VALUE)
        @NotNull
        int idAreaConocimiento,
        @Min(1)
        @Max(Long.MAX_VALUE)
        @NotNull
        long idPregunta,
        @NotNull
        @Min(1)
        @Max(Long.MAX_VALUE)
        long idDistractor,
        Date fechaCracion,
        String observaciones
        ) {

}
