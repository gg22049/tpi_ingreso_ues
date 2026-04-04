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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimientoPK;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PreguntaAreaConocimientoDAOImp extends AbstractCRUD<PreguntaAreaConocimiento, PreguntaAreaConocimientoDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PreguntaAreaConocimientoDAOImp() {
        super(PreguntaAreaConocimiento.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public PreguntaAreaConocimiento toEntity(PreguntaAreaConocimientoDTO dto) {
        try {
            return new PreguntaAreaConocimiento(
                    new PreguntaAreaConocimientoPK(dto.idPregunta(), dto.idAreaConocimiento()),
                    dto.observaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public PreguntaAreaConocimientoDTO toDto(PreguntaAreaConocimiento entity) {
        try {
            return new PreguntaAreaConocimientoDTO(
                    entity.getPreguntaAreaConocimientoPK().getIdPregunta(),
                    entity.getPreguntaAreaConocimientoPK().getIdAreaConocimiento(),
                    entity.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
