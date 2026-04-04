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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaJornadaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaPK;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PruebaJornadaDAOImp extends AbstractCRUD<PruebaJornada, PruebaJornadaDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PruebaJornadaDAOImp() {
        super(PruebaJornada.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public PruebaJornada toEntity(PruebaJornadaDTO dto) throws IllegalStateException {
        try {
            return new PruebaJornada(
                    new PruebaJornadaPK(dto.idPrueba(), dto.idJornada()),
                    dto.fechaCreacion(),
                    dto.observaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public PruebaJornadaDTO toDto(PruebaJornada entity) throws IllegalStateException {
        try {
            return new PruebaJornadaDTO(
                    entity.getPruebaJornadaPK().getIdPrueba(),
                    entity.getPruebaJornadaPK().getIdJornada(),
                    entity.getFechaCreacion(),
                    entity.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
