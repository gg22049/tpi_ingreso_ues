package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.jupiter.api.Assertions.*;
import org.testcontainers.shaded.org.yaml.snakeyaml.emitter.Emitter;

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

    EntityManager em;

    @BeforeAll
    void start() {
        IntegrationTestSingleton its = IntegrationTestSingleton.getInstance();
        em = its.getEntityManager();
    }

    @AfterAll
    void stop() {
        em.close();
    }

    @Test
    void test() {
        System.out.println("AreaConocimientoDAOImp.create");
        System.out.println(em.isOpen());
    }

}
