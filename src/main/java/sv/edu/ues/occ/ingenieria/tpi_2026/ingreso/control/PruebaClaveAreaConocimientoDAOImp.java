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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPK;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PruebaClaveAreaConocimientoDAOImp extends AbstractCRUD<PruebaClaveAreaConocimiento, PruebaClaveAreaConocimientoDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PruebaClaveAreaConocimientoDAOImp() {
        super(PruebaClaveAreaConocimiento.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public PruebaClaveAreaConocimiento toEntity(PruebaClaveAreaConocimientoDTO dto) throws IllegalStateException {
        try {
            return new PruebaClaveAreaConocimiento(
                    new PruebaClaveAreaConocimientoPK(dto.idPruebaClave(), dto.idAreaConocimiento()),
                    dto.cantidad(),
                    dto.porcentaje()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public PruebaClaveAreaConocimientoDTO toDto(PruebaClaveAreaConocimiento entity) throws IllegalStateException {
        try {
            return new PruebaClaveAreaConocimientoDTO(
                    entity.getPruebaClaveAreaConocimientoPK().getIdPruebaClave(),
                    entity.getPruebaClaveAreaConocimientoPK().getIdAreaConocimiento(),
                    entity.getCantidad(),
                    entity.getPorcentaje()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
