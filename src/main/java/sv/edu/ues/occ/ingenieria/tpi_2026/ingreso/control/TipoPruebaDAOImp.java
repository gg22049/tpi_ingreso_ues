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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.TipoPruebaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class TipoPruebaDAOImp extends AbstractCRUD<TipoPrueba, TipoPruebaDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public TipoPruebaDAOImp() {
        super(TipoPrueba.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public TipoPrueba toEntity(TipoPruebaDTO dto) throws IllegalStateException {
        try {
            return new TipoPrueba(
                    dto.idTipoPrueba(),
                    dto.valor(),
                    dto.activo(),
                    dto.observaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public TipoPruebaDTO toDto(TipoPrueba entity) throws IllegalStateException {
        try {
            return new TipoPruebaDTO(
                    entity.getIdTipoPrueba(),
                    entity.getValor(),
                    entity.getActivo(),
                    entity.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
