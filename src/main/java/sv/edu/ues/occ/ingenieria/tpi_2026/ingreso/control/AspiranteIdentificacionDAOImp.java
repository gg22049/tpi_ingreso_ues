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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteIdentificacionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteIdentificacion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteIdentificacionPK;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class AspiranteIdentificacionDAOImp extends AbstractCRUD<AspiranteIdentificacion, AspiranteIdentificacionDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public AspiranteIdentificacionDAOImp() {
        super(AspiranteIdentificacion.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public AspiranteIdentificacion toEntity(AspiranteIdentificacionDTO dto) throws IllegalStateException {
        try {
            return new AspiranteIdentificacion(
                    new AspiranteIdentificacionPK(dto.idAspirante(), dto.idTipoIdentificacion()),
                    dto.valor(),
                    dto.toString(),
                    dto.observaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public AspiranteIdentificacionDTO toDto(AspiranteIdentificacion entity) throws IllegalStateException {
        try {
            return new AspiranteIdentificacionDTO(
                    entity.getAspiranteIdentificacionPK() == null ? null : entity.getAspiranteIdentificacionPK().getIdAspirante(),
                    entity.getAspiranteIdentificacionPK() == null ? null : entity.getAspiranteIdentificacionPK().getIdTipoIdentificacion(),
                    entity.getValor(),
                    entity.getImagenUrl(),
                    entity.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
