/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import java.nio.file.Paths;
import java.util.List;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.MountableFile;

/**
 *
 * @author caesar
 */
public abstract class STAbstract {

    protected static Client client;
    protected static WebTarget webTarget;
    private static Network net = Network.newNetwork();
    private static final PostgreSQLContainer PG_CONTAINER = new PostgreSQLContainer("postgres:18.2-alpine3.23")
            .withDatabaseName("tpi_db")
            .withUsername("postgres")
            .withPassword("postgres1234")
            .withInitScript("tpi_ddl.sql")
            .withExposedPorts(5432)
            .withNetwork(net)
            .withNetworkAliases("pgdb");
    private static final GenericContainer OL_CONTAINER = new GenericContainer("icr.io/appcafe/open-liberty:26.0.0.2-full-java21-openj9-ubi-minimal")
            .withCopyFileToContainer(MountableFile.forHostPath(Paths.get("target/nuevo-ingreso.war").toAbsolutePath()), "/opt/ol/wlp/usr/servers/defaultServer/dropins/nuevo-ingreso.war")
            .withCopyFileToContainer(MountableFile.forClasspathResource("server.xml"), "/opt/ol/wlp/usr/servers/defaultServer/server.xml")
            .withCopyFileToContainer(MountableFile.forClasspathResource("postgresql-42.7.10.jar"), "/opt/ol/wlp/usr/servers/defaultServer/lib/postgresql-42.7.10.jar")
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
//        Startables.deepStart(List.of(PG_CONTAINER, OL_CONTAINER));
        PG_CONTAINER.start();
        OL_CONTAINER.start();
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://localhost:%d/nuevo-ingreso/v1/", OL_CONTAINER.getMappedPort(9080)));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            PG_CONTAINER.stop();
            OL_CONTAINER.stop();
        }));
    }

}
