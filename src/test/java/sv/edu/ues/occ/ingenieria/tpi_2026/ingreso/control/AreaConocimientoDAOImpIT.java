package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.jupiter.api.Assertions.*;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author caesar
 */
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AreaConocimientoDAOImpIT {

    IntegrationTestSingleton its;

    @BeforeAll
    void start() {
        its = IntegrationTestSingleton.getInstance();
    }

    @Test
    void testCreate() {
        System.out.println("AreaConocimientoDAOImp.testCreate");
        AreaConocimiento newEntity = new AreaConocimiento();
        newEntity.setNombre("xd");
        AreaConocimientoDAOImp cut = new AreaConocimientoDAOImp();
        cut.em = its.getEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.create(newEntity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        assertNotNull(newEntity.getIdAreaConocimiento());
        cut.em.close();
    }

}
