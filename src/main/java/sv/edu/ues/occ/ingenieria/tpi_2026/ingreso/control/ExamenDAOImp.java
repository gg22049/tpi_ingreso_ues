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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Examen;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class ExamenDAOImp extends AbstractCRUD<Examen> implements DefaultDAO<Examen>, Serializable {

    @PersistenceUnit(unitName = "Ingreso-PU")
    EntityManager em;

    public ExamenDAOImp(Class<Examen> tipoDato) {
        super(tipoDato);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void createDAO(Examen entity) throws RuntimeException {
        try {
            create(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Examen> findAllDAO() throws RuntimeException {
        try {
            return findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Examen findByIdDAO(Object id) throws RuntimeException {
        try {
            return findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Examen> findByRangeDAO(int offset, int limit) throws RuntimeException {
        try {
            return findByRange(offset, limit);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Examen updateDAO(Examen entity) throws RuntimeException {
        try {
            return update(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDAO(Examen entity) throws RuntimeException {
        try {
            delete(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
