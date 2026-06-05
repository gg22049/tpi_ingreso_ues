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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ProcesoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornada;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PruebaJornadaDAOImp extends AbstractDefaultDAOImp<PruebaJornada> {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PruebaJornadaDAOImp() {
        super(PruebaJornada.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public List<ProcesoDTO> findByFilteredRangeProcesos(int offset, int limit, String filter) throws IllegalArgumentException, IllegalStateException {
        if (offset < 0 || limit < offset) {
            throw new IllegalArgumentException("Rango invalido");
        }
        if (em == null) {
            throw new IllegalStateException("Error accediendo al repositorio");
        }
        try {
            TypedQuery<ProcesoDTO> q = em.createNamedQuery("PruebaJornada.findByFilteredRangeProcesos", ProcesoDTO.class);
            q.setParameter("name", filter);
            q.setFirstResult(offset);
            q.setMaxResults(limit);
            return q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException("Error al obtener el rango de registros", e);
        }
    }

}
