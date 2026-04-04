/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author usermein
 */
public record PruebaDTO(
        Long idPrueba,
        @NotBlank
        String nombre,
        @NotBlank
        String indicciones,
        @NotNull
        BigDecimal puntajeMaximo,
        @NotNull
        BigDecimal notaAprobacion,
        @NotNull
        Integer duracion,
        Date fechaCreacion,
        @NotNull
        Integer idTipoPrueba
        ) {

}
