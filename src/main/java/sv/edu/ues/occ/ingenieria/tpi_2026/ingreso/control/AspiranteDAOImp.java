/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class AspiranteDAOImp extends AbstractDefaultDAOImp<Aspirante> {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public AspiranteDAOImp() {
        super(Aspirante.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    /**
     * Metodo para buscar Aspirantes por correo en el repositorio.
     *
     * @param email Correo electronico para realizar la busqueda.
     *
     * @return Aspirante encontrado o null si no existe registro.
     *
     * @throws IllegalArgumentException En caso de correo blank.
     * @throws IllegalStateException En caso de error en el repositorio o en la
     * operacion.
     */
    public Aspirante findByEmail(String email) throws IllegalArgumentException, IllegalStateException {
        if (email.isBlank()) {
            throw new IllegalArgumentException("Email blank");
        }
        EntityManager em = null;
        em = getEntityManager();
        if (em == null) {
            throw new IllegalStateException("Error accediendo al repositorio");
        }
        try {
            TypedQuery<Aspirante> q = em.createNamedQuery("Aspirante.findByCorreo", Aspirante.class);
            q.setParameter("correo", email);
            return q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException("Error al obtener el rango de registros", e);
        }
    }

}
