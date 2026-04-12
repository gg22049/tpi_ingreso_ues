/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    dto.fechaCreacion(),
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

    public Aspirante findByEmail(String email) throws IllegalArgumentException, IllegalStateException {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email invalido");
        }
        try {
            TypedQuery<Aspirante> q = em.createNamedQuery("Aspirante.findByCorreo", Aspirante.class);
            q.setParameter("correo", email);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException("Error recuperando la entidad", e);
        }
    }

}
