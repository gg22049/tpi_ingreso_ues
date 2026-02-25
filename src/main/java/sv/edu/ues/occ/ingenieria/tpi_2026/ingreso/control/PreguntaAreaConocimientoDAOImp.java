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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimiento;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PreguntaAreaConocimientoDAOImp extends AbstractCRUD<PreguntaAreaConocimiento> implements DefaultDAO<PreguntaAreaConocimiento>, Serializable {

    @PersistenceUnit(unitName = "Ingreso-PU")
    EntityManager em;

    public PreguntaAreaConocimientoDAOImp(Class<PreguntaAreaConocimiento> tipoDato) {
        super(tipoDato);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void createDAO(PreguntaAreaConocimiento entity) throws RuntimeException {
        try {
            create(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PreguntaAreaConocimiento> findAllDAO() throws RuntimeException {
        try {
            return findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PreguntaAreaConocimiento findByIdDAO(Object id) throws RuntimeException {
        try {
            return findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PreguntaAreaConocimiento> findByRangeDAO(int offset, int limit) throws RuntimeException {
        try {
            return findByRange(offset, limit);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PreguntaAreaConocimiento updateDAO(PreguntaAreaConocimiento entity) throws RuntimeException {
        try {
            return update(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDAO(PreguntaAreaConocimiento entity) throws RuntimeException {
        try {
            delete(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
