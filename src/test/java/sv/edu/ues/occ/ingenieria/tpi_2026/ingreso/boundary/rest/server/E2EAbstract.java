/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.MountableFile;

/**
 *
 * @author caesar
 */
public abstract class E2EAbstract {

    protected static EntityManagerFactory emf;
    protected static Client client;
    protected static WebTarget webTarget;
    private static Network net = Network.newNetwork();
    private static MountableFile war = MountableFile.forHostPath(Paths.get("target/nuevo-ingreso.war").toAbsolutePath());
    private static final PostgreSQLContainer PG_CONTAINER = new PostgreSQLContainer("postgres:18.2-alpine3.23")
            .withDatabaseName("tpi_db")
            .withUsername("postgres")
            .withPassword("postgres1234")
            .withInitScript("tpi_ddl.sql")
            .withExposedPorts(5432)
            .withNetwork(net)
            .withNetworkAliases("pgdb");
    private static final GenericContainer OL_CONTAINER = new GenericContainer("icr.io/appcafe/open-liberty:25.0.0.6-kernel-slim-java21-openj9-ubi-minimal")
            .withCopyFileToContainer(war, "/opt/ol/wlp/usr/servers/defaultServer/dropins/nuevo-ingreso.war")
            .withExposedPorts(9080)
            .withNetwork(net)
            .withEnv("PGSERVER", "pgdb")
            .withEnv("PGPORT", "5432")
            .withEnv("PGDBNAME", "tpi_db")
            .withEnv("PGUSER", "postgres")
            .withEnv("PGPASSWORD", "postgres1234")
            .dependsOn(PG_CONTAINER)
            .waitingFor(Wait.forLogMessage(".*CWWKF0011I.*", 1));

    static {
        PG_CONTAINER.start();
        OL_CONTAINER.start();
        Map<String, Object> properties = new HashMap<>();
        properties.put("jakarta.persistence.jdbc.url", PG_CONTAINER.getJdbcUrl());
        emf = Persistence.createEntityManagerFactory("IngresoIT-PU", properties);
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://localhost:%d/nuevo-ingreso/v1/", OL_CONTAINER.getMappedPort(9080)));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            emf.close();
            PG_CONTAINER.stop();
            OL_CONTAINER.stop();
        }));
    }

}
