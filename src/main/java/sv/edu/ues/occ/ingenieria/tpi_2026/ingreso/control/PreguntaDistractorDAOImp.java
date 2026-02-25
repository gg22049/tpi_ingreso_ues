/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceUnit;
import java.io.Serializable;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractor;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PreguntaDistractorDAOImp extends AbstractCRUD<PreguntaDistractor> implements DefaultDAO<PreguntaDistractor>, Serializable {

    @PersistenceUnit(unitName = "Ingreso-PU")
    EntityManager em;

    public PreguntaDistractorDAOImp(Class<PreguntaDistractor> tipoDato) {
        super(tipoDato);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void createDAO(PreguntaDistractor entity) throws RuntimeException {
        try {
            create(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PreguntaDistractor> findAllDAO() throws RuntimeException {
        try {
            return findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PreguntaDistractor findByIdDAO(Object id) throws RuntimeException {
        try {
            return findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PreguntaDistractor> findByRangeDAO(int offset, int limit) throws RuntimeException {
        try {
            return findByRange(offset, limit);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PreguntaDistractor updateDAO(PreguntaDistractor entity) throws RuntimeException {
        try {
            return update(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDAO(PreguntaDistractor entity) throws RuntimeException {
        try {
            delete(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
