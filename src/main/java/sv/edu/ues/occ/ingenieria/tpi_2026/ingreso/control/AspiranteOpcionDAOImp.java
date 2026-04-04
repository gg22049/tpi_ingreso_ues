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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteOpcionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteOpcion;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class AspiranteOpcionDAOImp extends AbstractCRUD<AspiranteOpcion, AspiranteOpcionDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public AspiranteOpcionDAOImp() {
        super(AspiranteOpcion.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public AspiranteOpcion toEntity(AspiranteOpcionDTO dto) throws IllegalStateException {
        try {
            return new AspiranteOpcion(
                    dto.idAspiranteOpcion(),
                    dto.idOpcion(),
                    dto.fechaCreacion(),
                    em.find(Aspirante.class, dto.idAspirante())
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public AspiranteOpcionDTO toDto(AspiranteOpcion entity) throws IllegalStateException {
        try {
            return new AspiranteOpcionDTO(
                    entity.getIdAspiranteOpcion(),
                    entity.getIdOpcion(),
                    entity.getFechaCreacion(),
                    entity.getIdAspirante() == null ? null : entity.getIdAspirante().getIdAspirante()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
