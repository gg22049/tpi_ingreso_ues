/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAula;

/**
 *
 * @author usermein
 */
public record JornadaAulaDTO(
        long idJornada,
        String idAula,
        String observaciones
        ) {

    public JornadaAulaDTO(JornadaAula jornadaAula) {
        this(
                jornadaAula.getJornadaAulaPK().getIdJornada(),
                jornadaAula.getJornadaAulaPK().getIdAula(),
                jornadaAula.getObservaciones()
        );

    }
}
