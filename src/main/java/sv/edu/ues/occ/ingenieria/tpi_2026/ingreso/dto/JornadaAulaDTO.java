/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author usermein
 */
public record JornadaAulaDTO(
        @NotNull
        @Min(1L)
        @Max(Long.MAX_VALUE)
        long idJornada,
        @NotBlank
        @Size(min = 1, max = 124)
        String idAula,
        String observaciones
        ) {

}
