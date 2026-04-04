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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.JornadaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class JornadaDAOImp extends AbstractCRUD<Jornada, JornadaDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public JornadaDAOImp() {
        super(Jornada.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Jornada toEntity(JornadaDTO dto) throws IllegalStateException {
        try {
            return new Jornada(
                    dto.idJornada(),
                    dto.nombre(),
                    dto.fechaInicio(),
                    dto.fechaFin(),
                    dto.observaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public JornadaDTO toDto(Jornada entity) throws IllegalStateException {
        try {
            return new JornadaDTO(
                    entity.getIdJornada(),
                    entity.getNombre(),
                    entity.getFechaInicio(),
                    entity.getFechaFin(),
                    entity.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
