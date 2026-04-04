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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.TipoIdentificacionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoIdentificacion;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class TipoIdentificacionDAOImp extends AbstractCRUD<TipoIdentificacion, TipoIdentificacionDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public TipoIdentificacionDAOImp() {
        super(TipoIdentificacion.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public TipoIdentificacion toEntity(TipoIdentificacionDTO dto) throws IllegalStateException {
        try {
            return new TipoIdentificacion(
                    dto.idTipoIdentificacion(),
                    dto.nombre(),
                    dto.observaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public TipoIdentificacionDTO toDto(TipoIdentificacion entity) throws IllegalStateException {
        try {
            return new TipoIdentificacionDTO(
                    entity.getIdTipoIdentificacion(),
                    entity.getNombre(),
                    entity.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
