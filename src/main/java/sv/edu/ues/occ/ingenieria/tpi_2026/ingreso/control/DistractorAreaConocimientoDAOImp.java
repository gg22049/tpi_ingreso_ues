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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.DistractorAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimientoPK;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class DistractorAreaConocimientoDAOImp extends AbstractCRUD<DistractorAreaConocimiento, DistractorAreaConocimientoDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public DistractorAreaConocimientoDAOImp() {
        super(DistractorAreaConocimiento.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public DistractorAreaConocimiento toEntity(DistractorAreaConocimientoDTO dto) throws IllegalStateException {
        try {
            return new DistractorAreaConocimiento(
                    new DistractorAreaConocimientoPK(dto.idDistractor(), dto.idAreaConocimiento()),
                    dto.observaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public DistractorAreaConocimientoDTO toDto(DistractorAreaConocimiento entity) throws IllegalStateException {
        try {
            DistractorAreaConocimientoPK key = entity.getDistractorAreaConocimientoPK();
            return new DistractorAreaConocimientoDTO(
                    key == null ? null : key.getIdDistractor(),
                    key == null ? 0 : key.getIdAreaConocimiento(),
                    entity.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
