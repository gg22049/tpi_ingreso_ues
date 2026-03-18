/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import java.math.BigDecimal;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimiento;

/**
 *
 * @author usermein
 */
public record PruebaClaveAreaConocimientoDTO(
        long idPruebaClave,
        int idAreaConocimiento,
        Integer cantidad,
        BigDecimal porcentaje
        ) {

    public PruebaClaveAreaConocimientoDTO(PruebaClaveAreaConocimiento pruebaClaveAreaConocimiento) {
        this(
                pruebaClaveAreaConocimiento.getPruebaClaveAreaConocimientoPK().getIdPruebaClave(),
                pruebaClaveAreaConocimiento.getPruebaClaveAreaConocimientoPK().getIdAreaConocimiento(),
                pruebaClaveAreaConocimiento.getCantidad(),
                pruebaClaveAreaConocimiento.getPorcentaje()
        );
    }
}
