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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PruebaDAOImp extends AbstractCRUD<Prueba, PruebaDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PruebaDAOImp() {
        super(Prueba.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Prueba toEntity(PruebaDTO dto) throws IllegalStateException {
        try {
            return new Prueba(
                    dto.idPrueba(),
                    dto.nombre(),
                    dto.indicciones(),
                    dto.puntajeMaximo(),
                    dto.notaAprobacion(),
                    dto.duracion(),
                    dto.fechaCreacion(),
                    dto.idTipoPrueba() == null ? null : em.find(TipoPrueba.class, dto.idTipoPrueba())
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public PruebaDTO toDto(Prueba entity) throws IllegalStateException {
        try {
            return new PruebaDTO(
                    entity.getIdPrueba(),
                    entity.getNombre(),
                    entity.getIndicaciones(),
                    entity.getPuntajeMaximo(),
                    entity.getNotaAprobacion(),
                    entity.getDuracion(),
                    entity.getFechaCreacion(),
                    entity.getIdPrueba() == null ? null : entity.getIdTipoPrueba().getIdTipoPrueba()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
