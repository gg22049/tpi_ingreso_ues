/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto;

import java.util.List;
import java.util.Map;

/**
 *
 * @author caesar
 */
public class ErrorDetailDTO {

    private String title;
    private int status;
    private String detail;
    private String instance;
    private List<Map<String, String>> issues;

    public ErrorDetailDTO() {
    }

    public ErrorDetailDTO(String title, int status, String detail, String instance, List<Map<String, String>> issues) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.instance = instance;
        this.issues = issues;
    }

}
