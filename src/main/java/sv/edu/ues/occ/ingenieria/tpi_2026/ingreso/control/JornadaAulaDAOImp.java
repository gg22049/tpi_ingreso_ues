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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.JornadaAulaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAula;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAulaPK;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class JornadaAulaDAOImp extends AbstractCRUD<JornadaAula, JornadaAulaDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public JornadaAulaDAOImp() {
        super(JornadaAula.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public JornadaAula toEntity(JornadaAulaDTO dto) throws IllegalStateException {
        try {
            return new JornadaAula(
                    new JornadaAulaPK(dto.idJornada(), dto.idAula()),
                    dto.observaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public JornadaAulaDTO toDto(JornadaAula entity) throws IllegalStateException {
        try {
            return new JornadaAulaDTO(
                    entity.getJornadaAulaPK().getIdJornada(),
                    entity.getJornadaAulaPK().getIdAula(),
                    entity.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
