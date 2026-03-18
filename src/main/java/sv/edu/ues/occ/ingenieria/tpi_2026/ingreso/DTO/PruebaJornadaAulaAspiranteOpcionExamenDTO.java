package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import java.math.BigDecimal;
import java.util.Date;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamen;

/**
 *
 * @author usermein
 */
public record PruebaJornadaAulaAspiranteOpcionExamenDTO(
        long idPrueba,
        long idJornada,
        String idAula,
        long idAspiranteOpcion,
        BigDecimal resultado,
         Date fechaResultado,
        String imagenUrl,
        String observaciones
        ) {

    public PruebaJornadaAulaAspiranteOpcionExamenDTO(PruebaJornadaAulaAspiranteOpcionExamen pruebaJornadaAulaAspiranteOpcionExamen){
        this(
            pruebaJornadaAulaAspiranteOpcionExamen.getPruebaJornadaAulaAspiranteOpcionExamenPK().getIdPrueba(),
            pruebaJornadaAulaAspiranteOpcionExamen.getPruebaJornadaAulaAspiranteOpcionExamenPK().getIdJornada(),
            pruebaJornadaAulaAspiranteOpcionExamen.getPruebaJornadaAulaAspiranteOpcionExamenPK().getIdAula(),
            pruebaJornadaAulaAspiranteOpcionExamen.getPruebaJornadaAulaAspiranteOpcionExamenPK().getIdAspiranteOpcion(),
            pruebaJornadaAulaAspiranteOpcionExamen.getResultado(),
            pruebaJornadaAulaAspiranteOpcionExamen.getFechaResultado(),
            pruebaJornadaAulaAspiranteOpcionExamen.getImagenUrl(),
            pruebaJornadaAulaAspiranteOpcionExamen.getObservaciones()
        );
    }
    
}
