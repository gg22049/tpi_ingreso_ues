/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author caesar
 */
public record ResultadoExamenDTO(
        String nombre,
        Date fechaRealizacion,
        BigDecimal notaAprobacion,
        BigDecimal resultado
        ) {

}
