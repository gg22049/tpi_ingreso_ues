/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.record;

import java.util.List;

/**
 *
 * @author caesar
 */
public record ErrorRecord(
        String errorId,
        String type,
        int status,
        String detail,
        String instance,
        List<IssueRecord> issues) {

}
