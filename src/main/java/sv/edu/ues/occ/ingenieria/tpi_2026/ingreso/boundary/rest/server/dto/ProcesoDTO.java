/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto;

import java.util.Date;

/**
 *
 * @author caesar
 */
public record ProcesoDTO(
        Long idPrueba,
        Long idJornada,
        String idAula,
        String nombre,
        Date fechaInicio,
        Date fechaFin
        ) {

}
