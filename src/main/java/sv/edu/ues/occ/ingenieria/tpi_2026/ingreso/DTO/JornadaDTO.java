
import java.util.Date;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author usermein
 */
public record JornadaDTO(
        Long idJornada,
        String nombre,
        Date fechaInicio,
        Date fechaFin,
        String observaciones
        ) {
    
    
    public JornadaDTO(Jornada jornada){
        this(
        jornada.getIdJornada(),
         jornada.getNombre(),
         jornada.getFechaInicio(),
         jornada.getFechaFin(),
         jornada.getObservaciones()
        );
    }
}
