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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.DistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class DistractorDAOImp extends AbstractCRUD<Distractor, DistractorDTO> implements Serializable {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public DistractorDAOImp() {
        super(Distractor.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Distractor toEntity(DistractorDTO dto) throws IllegalStateException {
        try {
            return new Distractor(
                    dto.idDistractor(),
                    dto.valor(),
                    dto.activo(),
                    dto.imagenUrl()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando dto a entity");
        }
    }

    @Override
    public DistractorDTO toDto(Distractor entity) throws IllegalStateException {
        try {
            return new DistractorDTO(
                    entity.getIdDistractor(),
                    entity.getValor(),
                    entity.getActivo(),
                    entity.getImagenUrl()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error mapeando entidad a dto");
        }
    }

}
