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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaDistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractorPK;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PreguntaDistractorDAOImp extends AbstractCRUD<PreguntaDistractor, PreguntaDistractorDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PreguntaDistractorDAOImp() {
        super(PreguntaDistractor.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public PreguntaDistractor toEntity(PreguntaDistractorDTO dto) throws IllegalStateException {
        try {
            return new PreguntaDistractor(
                    new PreguntaDistractorPK(dto.idPregunta(), dto.idDistractor()),
                    dto.correcto(),
                    dto.observaciones());
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public PreguntaDistractorDTO toDto(PreguntaDistractor entity) throws IllegalStateException {
        try {
            PreguntaDistractorPK key = entity.getPreguntaDistractorPK();
            return new PreguntaDistractorDTO(
                    key == null ? 0L : key.getIdPregunta(),
                    key == null ? 0L : key.getIdDistractor(),
                    entity.getCorrecto(),
                    entity.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
