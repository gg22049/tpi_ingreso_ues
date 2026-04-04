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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClave;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PruebaClaveDAOImp extends AbstractCRUD<PruebaClave, PruebaClaveDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PruebaClaveDAOImp() {
        super(PruebaClave.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public PruebaClave toEntity(PruebaClaveDTO dto) throws IllegalStateException {
        try {
            return new PruebaClave(
                    dto.idPruevaClave(),
                    dto.nombre(),
                    dto.idPrueba() == null ? null : em.find(Prueba.class, dto.idPrueba())
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public PruebaClaveDTO toDto(PruebaClave entity) throws IllegalStateException {
        try {
            return new PruebaClaveDTO(
                    entity.getIdPruebaClave(),
                    entity.getNombre(),
                    entity.getIdPrueba() == null ? null : entity.getIdPrueba().getIdPrueba()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
