/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaJornadaAulaAspiranteOpcionExamenDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamen;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamenPK;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PruebaJornadaAulaAspiranteOpcionExamenDAOImp extends AbstractCRUD<PruebaJornadaAulaAspiranteOpcionExamen, PruebaJornadaAulaAspiranteOpcionExamenDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PruebaJornadaAulaAspiranteOpcionExamenDAOImp() {
        super(PruebaJornadaAulaAspiranteOpcionExamen.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public PruebaJornadaAulaAspiranteOpcionExamen toEntity(PruebaJornadaAulaAspiranteOpcionExamenDTO dto) throws IllegalStateException {
        try {
            return new PruebaJornadaAulaAspiranteOpcionExamen(
                    new PruebaJornadaAulaAspiranteOpcionExamenPK(dto.idPrueba(), dto.idJornada(), dto.idAula(), dto.idAspiranteOpcion()),
                    dto.resultado(),
                    dto.fechaResultado(),
                    dto.imagenUrl(),
                    dto.observaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public PruebaJornadaAulaAspiranteOpcionExamenDTO toDto(PruebaJornadaAulaAspiranteOpcionExamen entity) throws IllegalStateException {
        try {
            return new PruebaJornadaAulaAspiranteOpcionExamenDTO(
                    entity.getPruebaJornadaAulaAspiranteOpcionExamenPK().getIdPrueba(),
                    entity.getPruebaJornadaAulaAspiranteOpcionExamenPK().getIdJornada(),
                    entity.getPruebaJornadaAulaAspiranteOpcionExamenPK().getIdAula(),
                    entity.getPruebaJornadaAulaAspiranteOpcionExamenPK().getIdAspiranteOpcion(),
                    entity.getResultado(),
                    entity.getFechaResultado(),
                    entity.getImagenUrl(),
                    entity.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
