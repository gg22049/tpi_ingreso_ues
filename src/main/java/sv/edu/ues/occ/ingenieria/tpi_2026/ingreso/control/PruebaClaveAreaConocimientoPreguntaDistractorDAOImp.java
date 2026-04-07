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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoPreguntaDistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractorPK;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PruebaClaveAreaConocimientoPreguntaDistractorDAOImp extends AbstractCRUD<PruebaClaveAreaConocimientoPreguntaDistractor, PruebaClaveAreaConocimientoPreguntaDistractorDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PruebaClaveAreaConocimientoPreguntaDistractorDAOImp() {
        super(PruebaClaveAreaConocimientoPreguntaDistractor.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public PruebaClaveAreaConocimientoPreguntaDistractor toEntity(PruebaClaveAreaConocimientoPreguntaDistractorDTO dto) throws IllegalStateException {
        try {
            return new PruebaClaveAreaConocimientoPreguntaDistractor(
                    new PruebaClaveAreaConocimientoPreguntaDistractorPK(dto.idPruebaClave(), dto.idAreaConocimiento(), dto.idPregunta(), dto.idDistractor()),
                    dto.fechaCracion(),
                    dto.observaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public PruebaClaveAreaConocimientoPreguntaDistractorDTO toDto(PruebaClaveAreaConocimientoPreguntaDistractor entity) throws IllegalStateException {
        try {
            PruebaClaveAreaConocimientoPreguntaDistractorPK key = entity.getPruebaClaveAreaConocimientoPreguntaDistractorPK();
            return new PruebaClaveAreaConocimientoPreguntaDistractorDTO(
                    key == null ? 0L : key.getIdPruebaClave(),
                    key == null ? 0 : key.getIdAreaConocimiento(),
                    key == null ? 0L : key.getIdPregunta(),
                    key == null ? 0L : key.getIdDistractor(),
                    entity.getFechaCreacion(),
                    entity.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
