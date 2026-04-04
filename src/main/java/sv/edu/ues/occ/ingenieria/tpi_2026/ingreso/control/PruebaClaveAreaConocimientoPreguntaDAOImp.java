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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoPreguntaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaPK;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PruebaClaveAreaConocimientoPreguntaDAOImp extends AbstractCRUD<PruebaClaveAreaConocimientoPregunta, PruebaClaveAreaConocimientoPreguntaDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PruebaClaveAreaConocimientoPreguntaDAOImp() {
        super(PruebaClaveAreaConocimientoPregunta.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public PruebaClaveAreaConocimientoPregunta toEntity(PruebaClaveAreaConocimientoPreguntaDTO dto) throws IllegalStateException {
        try {
            return new PruebaClaveAreaConocimientoPregunta(
                    new PruebaClaveAreaConocimientoPreguntaPK(dto.idPruebaClave(), dto.idAreaConocimiento(), dto.idPregunta()),
                    dto.porcentaje()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public PruebaClaveAreaConocimientoPreguntaDTO toDto(PruebaClaveAreaConocimientoPregunta entity) throws IllegalStateException {
        try {
            return new PruebaClaveAreaConocimientoPreguntaDTO(
                    entity.getPruebaClaveAreaConocimientoPreguntaPK().getIdPruebaClave(),
                    entity.getPruebaClaveAreaConocimientoPreguntaPK().getIdAreaConocimiento(),
                    entity.getPruebaClaveAreaConocimientoPreguntaPK().getIdPregunta(),
                    entity.getPorcentaje()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
