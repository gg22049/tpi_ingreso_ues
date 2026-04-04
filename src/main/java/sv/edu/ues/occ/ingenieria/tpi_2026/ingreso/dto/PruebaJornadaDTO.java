/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author usermein
 */
public record PruebaJornadaDTO(
        @NotNull
        long idPrueba,
        @NotNull
        long idJornada,
        @NotNull
        Date fechaCreacion,
        String observaciones
        ) {

}
