/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import org.testcontainers.postgresql.PostgreSQLContainer;

/**
 *
 * @author caesar
 */
public class IntegrationTestSingleton {

    private static final PostgreSQLContainer pgContainer = new PostgreSQLContainer("postgres:18.2-alpine3.23")
            .withDatabaseName("tpi_db")
            .withUsername("postgres")
            .withPassword("postgres1234")
            .withInitScript("tpi_ddl.sql")
            .withExposedPorts(5432);
    private static EntityManagerFactory emf;
    private static IntegrationTestSingleton instance;

    private IntegrationTestSingleton() {
        pgContainer.start();
        Map<String, Object> properties = new HashMap<>();
        properties.put("jakarta.persistence.jdbc.url", pgContainer.getJdbcUrl());
        emf = Persistence.createEntityManagerFactory("IngresoIT-PU", properties);
    }

    /**
     * Metodo para instanciar y obtener esa instancia unica de
     * IntegrationTestSingleton.
     *
     * @return Intancia unica de IntegrationTestSingleton;
     */
    public static IntegrationTestSingleton getInstance() {
        if (instance == null) {
            instance = new IntegrationTestSingleton();
        }
        return instance;
    }

    /**
     * Metodo para obtener un EntityManager del EntityManagerFactory global.
     *
     * @return EntityManager si el EntityManagerFactory no es null; null en caso
     * contrario.
     */
    public static EntityManager getEntityManager() {

        if (emf != null) {
            return emf.createEntityManager();
        } else {
            return null;
        }

    }

}
