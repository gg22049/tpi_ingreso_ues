/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;

/**
 *
 * @author caesar
 */
@Stateless
@LocalBean
public class PruebaDAOImp extends AbstractDefaultDAOImp<Prueba> {

    @PersistenceContext(unitName = "Ingreso-PU")
    EntityManager em;

    public PruebaDAOImp() {
        super(Prueba.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
