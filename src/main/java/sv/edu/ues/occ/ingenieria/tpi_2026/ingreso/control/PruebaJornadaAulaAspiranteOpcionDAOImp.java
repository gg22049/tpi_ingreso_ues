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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ResultadoExamenDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcion;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PruebaJornadaAulaAspiranteOpcionDAOImp extends AbstractDefaultDAOImp<PruebaJornadaAulaAspiranteOpcion> {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PruebaJornadaAulaAspiranteOpcionDAOImp() {
        super(PruebaJornadaAulaAspiranteOpcion.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public List<ResultadoExamenDTO> findExamenByCorreo(String correo) throws IllegalArgumentException, IllegalStateException {
        if (correo.isBlank()) {
            throw new IllegalArgumentException("Correo invalido");
        }
        if (em == null) {
            throw new IllegalStateException("Error accediendo al repositorio");
        }
        try {
            TypedQuery<ResultadoExamenDTO> q = em.createNamedQuery("PruebaJornadaAulaAspiranteOpcion.findExamenByCorreo", ResultadoExamenDTO.class);
            q.setParameter("correo", correo);
            return q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException("Error al obtener el rango de registros", e);
        }
    }

}
