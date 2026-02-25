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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoDocumentoIdentificacion;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class TipoDocumentoIdentificacionDAOImp extends AbstractCRUD<TipoDocumentoIdentificacion> implements DefaultDAO<TipoDocumentoIdentificacion>, Serializable {

    @PersistenceUnit(unitName = "Ingreso-PU")
    EntityManager em;

    public TipoDocumentoIdentificacionDAOImp(Class<TipoDocumentoIdentificacion> tipoDato) {
        super(tipoDato);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void createDAO(TipoDocumentoIdentificacion entity) throws RuntimeException {
        try {
            create(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TipoDocumentoIdentificacion> findAllDAO() throws RuntimeException {
        try {
            return findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TipoDocumentoIdentificacion findByIdDAO(Object id) throws RuntimeException {
        try {
            return findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TipoDocumentoIdentificacion> findByRangeDAO(int offset, int limit) throws RuntimeException {
        try {
            return findByRange(offset, limit);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TipoDocumentoIdentificacion updateDAO(TipoDocumentoIdentificacion entity) throws RuntimeException {
        try {
            return update(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDAO(TipoDocumentoIdentificacion entity) throws RuntimeException {
        try {
            delete(entity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
