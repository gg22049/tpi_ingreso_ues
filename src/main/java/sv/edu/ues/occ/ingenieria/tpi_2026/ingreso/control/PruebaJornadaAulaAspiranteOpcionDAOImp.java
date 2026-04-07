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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaJornadaAulaAspiranteOpcionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionPK;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PruebaJornadaAulaAspiranteOpcionDAOImp extends AbstractCRUD<PruebaJornadaAulaAspiranteOpcion, PruebaJornadaAulaAspiranteOpcionDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PruebaJornadaAulaAspiranteOpcionDAOImp() {
        super(PruebaJornadaAulaAspiranteOpcion.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public PruebaJornadaAulaAspiranteOpcion toEntity(PruebaJornadaAulaAspiranteOpcionDTO dto) throws IllegalStateException {
        try {
            return new PruebaJornadaAulaAspiranteOpcion(
                    new PruebaJornadaAulaAspiranteOpcionPK(dto.idPrueba(), dto.idJornada(), dto.idAula(), dto.idAspiranteOpcion()),
                    dto.activo(),
                    dto.fecha()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public PruebaJornadaAulaAspiranteOpcionDTO toDto(PruebaJornadaAulaAspiranteOpcion entity) throws IllegalStateException {
        try {
            PruebaJornadaAulaAspiranteOpcionPK key = entity.getPruebaJornadaAulaAspiranteOpcionPK();
            return new PruebaJornadaAulaAspiranteOpcionDTO(
                    key.getIdPrueba(),
                    key.getIdJornada(),
                    key.getIdAula(),
                    key.getIdAspiranteOpcion(),
                    entity.getActivo(),
                    entity.getFecha()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
