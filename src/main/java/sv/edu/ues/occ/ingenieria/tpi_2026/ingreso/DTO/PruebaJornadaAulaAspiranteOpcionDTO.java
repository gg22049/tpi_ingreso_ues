package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import java.util.Date;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcion;

/**
 *
 * @author usermein
 */
public record PruebaJornadaAulaAspiranteOpcionDTO(
        long idPrueba,
        long idJornada,
        String idAula,
        long idAspiranteOpcion,
        Boolean activo,
        Date fecha
        ) {
    
    
    public PruebaJornadaAulaAspiranteOpcionDTO(PruebaJornadaAulaAspiranteOpcion pruebaJornadaAulaAspiranteOpcion){
        this(
        pruebaJornadaAulaAspiranteOpcion.getPruebaJornadaAulaAspiranteOpcionPK().getIdPrueba(),
                pruebaJornadaAulaAspiranteOpcion.getPruebaJornadaAulaAspiranteOpcionPK().getIdJornada(),
                pruebaJornadaAulaAspiranteOpcion.getPruebaJornadaAulaAspiranteOpcionPK().getIdAula(),
                pruebaJornadaAulaAspiranteOpcion.getPruebaJornadaAulaAspiranteOpcionPK().getIdAspiranteOpcion(),
                pruebaJornadaAulaAspiranteOpcion.getActivo(),
                pruebaJornadaAulaAspiranteOpcion.getFecha()
        );
    }
}
