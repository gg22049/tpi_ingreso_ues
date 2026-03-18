/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import java.math.BigDecimal;
import java.util.Date;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;

/**
 *
 * @author usermein
 */
public record PruebaDTO(
        Long idPrueba,
        String nombre,
        String indicciones,
        BigDecimal puntajeMaximo,
        BigDecimal notaAprobacion,
        Integer duracion,
        Date fechaCreacion,
        Integer idTipoPrueba
        ) {

    public PruebaDTO(Prueba prueba) {
        this(
                prueba.getIdPrueba(),
                prueba.getNombre(),
                prueba.getIndicaciones(),
                prueba.getPuntajeMaximo(),
                prueba.getNotaAprobacion(),
                prueba.getDuracion(),
                prueba.getFechaCreacion(),
                prueba.getIdTipoPrueba() != null ? 
                        prueba.getIdTipoPrueba().getIdTipoPrueba() : null
                );
    }

}
