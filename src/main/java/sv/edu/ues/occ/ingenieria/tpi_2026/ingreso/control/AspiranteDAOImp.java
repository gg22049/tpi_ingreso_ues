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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class AspiranteDAOImp extends AbstractCRUD<Aspirante, AspiranteDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public AspiranteDAOImp() {
        super(Aspirante.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Aspirante toEntity(AspiranteDTO dto) throws IllegalStateException {

        try {
            return new Aspirante(
                    dto.idAspirante(),
                    dto.nombres(),
                    dto.apellidos(),
                    dto.fechaNacimiento(),
                    dto.correo(),
                    dto.observaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public AspiranteDTO toDto(Aspirante entity) throws IllegalStateException {
        try {
            return new AspiranteDTO(
                    entity.getIdAspirante(),
                    entity.getNombres(),
                    entity.getApellidos(),
                    entity.getFechaNacimiento(),
                    entity.getCorreo(),
                    entity.getFechaCreacion(),
                    entity.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
