/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author usermein
 */
public record PreguntaDTO(
        Long idPregunta,
        @NotBlank
        @Size(min = 1, max = 2147483647)
        String valor,
        @NotNull
        Boolean activo,
        String imageUrl,
        String observaciones
        ) {

}
