/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class AreaConocimientoDAOImp extends AbstractCRUD<AreaConocimiento, AreaConocimientoDTO> {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public AreaConocimientoDAOImp() {
        super(AreaConocimiento.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public AreaConocimiento toEntity(AreaConocimientoDTO dto) throws IllegalStateException {
        try {
            return new AreaConocimiento(
                    dto.idAreaConocimiento(),
                    dto.nombre(),
                    dto.descripcion(),
                    dto.activo(),
                    dto.idAreaConocimientoPadre() == null ? null : em.find(AreaConocimiento.class, dto.idAreaConocimientoPadre())
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public AreaConocimientoDTO toDto(AreaConocimiento entity) throws IllegalStateException {
        try {
            return new AreaConocimientoDTO(
                    entity.getIdAreaConocimiento(),
                    entity.getNombre(),
                    entity.getDescripcion(),
                    entity.getActivo(),
                    entity.getIdAreaConocimientoPadre() == null ? null : entity.getIdAreaConocimientoPadre().getIdAreaConocimiento()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
