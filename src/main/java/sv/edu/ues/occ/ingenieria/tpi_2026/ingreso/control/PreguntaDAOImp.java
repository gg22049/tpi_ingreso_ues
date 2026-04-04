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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PreguntaDAOImp extends AbstractCRUD<Pregunta, PreguntaDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PreguntaDAOImp() {
        super(Pregunta.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Pregunta toEntity(PreguntaDTO dto) throws IllegalStateException {
        try {
            return new Pregunta(
                    dto.idPregunta(),
                    dto.valor(),
                    dto.activo(),
                    dto.imageUrl(),
                    dto.observaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public PreguntaDTO toDto(Pregunta entity) throws IllegalStateException {
        try {
            return new PreguntaDTO(
                    entity.getIdPregunta(),
                    entity.getValor(),
                    entity.getActivo(),
                    entity.getImagenUrl(),
                    entity.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
