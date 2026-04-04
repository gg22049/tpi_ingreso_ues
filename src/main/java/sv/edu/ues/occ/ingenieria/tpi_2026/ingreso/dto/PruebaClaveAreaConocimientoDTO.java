/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 *
 * @author usermein
 */
public record PruebaClaveAreaConocimientoDTO(
        @NotNull
        long idPruebaClave,
        @NotNull
        int idAreaConocimiento,
        Integer cantidad,
        @Min(value = 0)
        @Max(value = 100)
        BigDecimal porcentaje
        ) {

}
