/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import org.testcontainers.postgresql.PostgreSQLContainer;

/**
 *
 * @author caesar
 */
public abstract class ITAbstract {

    private static final PostgreSQLContainer PG_CONTAINER = new PostgreSQLContainer("postgres:18.2-alpine3.23")
            .withDatabaseName("tpi_db")
            .withUsername("postgres")
            .withPassword("postgres1234")
            .withExposedPorts(5432)
            .withInitScript("tpi_ddl.sql");
    protected static EntityManagerFactory emf;

    static {
        PG_CONTAINER.start();
        Map<String, Object> properties = new HashMap<>();
        properties.put("jakarta.persistence.jdbc.url", PG_CONTAINER.getJdbcUrl());
        emf = Persistence.createEntityManagerFactory("IngresoIT-PU", properties);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            emf.close();
            PG_CONTAINER.stop();
        }));
    }

}
