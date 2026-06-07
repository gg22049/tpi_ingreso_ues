/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class AreaConocimientoDAOImp extends AbstractDefaultDAOImp<AreaConocimiento> {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public AreaConocimientoDAOImp() {
        super(AreaConocimiento.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return this.em;
    }

    public List<AreaConocimiento> findByIdArbolAreas(Long idPrueba) throws IllegalArgumentException, IllegalStateException {
        if (idPrueba == null) {
            throw new IllegalArgumentException("El id de Prueba no puede ser nulo");
        }
        if (em == null) {
            throw new IllegalStateException("Error accediendo al repositorio");
        }
        try {
            TypedQuery<AreaConocimiento> q = em.createNamedQuery("AreaConocimiento.findByIdArbolAreas", AreaConocimiento.class);
            q.setParameter("idPrueba", idPrueba);
            return q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException("Error al obtener el rango de registros", e);
        }
    }

}
