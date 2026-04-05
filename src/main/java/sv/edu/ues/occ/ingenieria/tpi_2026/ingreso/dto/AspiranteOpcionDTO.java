/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteOpcion;

/**
 *
 * @author usermein lo dejare aqui por si acaso al ser tabla intermedia
 */
public record AspiranteOpcionDTO(
        Long idAspiranteOpcion,
        @NotBlank
        String idOpcion,
        @NotNull
        Date fechaCreacion,
        @NotNull
        Long idAspirante
        ) {

}
