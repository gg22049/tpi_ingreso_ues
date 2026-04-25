/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.JornadaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;

/**
 *
 * @author caesar
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JornadaResourceST extends STAbstract {

    private final String PATH = "jornada";
    private Long idJornada;

    @Test
    @Order(1)
    void create() {
        System.out.println("JornadaResource.create");

        // 400 - constraint validation
        JornadaDTO dto = new JornadaDTO(null, "", null, null, null);
        Response response = webTarget
                .path(PATH)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        ErrorDetailDTO body = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, body.status());
        assertNotNull(body.type());
        assertNotNull(body.detail());
        assertNotNull(body.instance());
        assertNotNull(body.issues());
        assertFalse(body.issues().isEmpty());

        // 201 - created
        dto = new JornadaDTO(null, "name", Date.from(Instant.parse("2020-01-23T00:00:00+00:08")), Date.from(Instant.now()), null);
        response = webTarget
                .path(PATH)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(201, response.getStatus());
        String location = response.getHeaderString("Location");
        assertNotNull(location);
        assertTrue(location.contains(webTarget.getUri().toString() + PATH));
        idJornada = Long.valueOf(
                location.substring(location.lastIndexOf("/") + 1)
        );
    }

    @Test
    @Order(2)
    void findById() {
        System.out.println("JornadaResource.findById");

        // 400 - constraint validation
        Response response = webTarget
                .path(PATH + "/0")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        ErrorDetailDTO dtoError = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, dtoError.status());
        assertNotNull(dtoError.type());
        assertNotNull(dtoError.detail());
        assertNotNull(dtoError.instance());
        assertNotNull(dtoError.issues());
        assertFalse(dtoError.issues().isEmpty());
        assertEquals("arg0", dtoError.issues().getFirst().field());
        assertTrue(dtoError.issues().getFirst().message().contains("must be greater than or equal to 1"));

        // 404 - not found
        response = webTarget
                .path(PATH + "/100")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertNotNull(response);
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(404, response.getStatus());

        dtoError = response.readEntity(ErrorDetailDTO.class);

        assertEquals(404, dtoError.status());
        assertNotNull(dtoError.type());
        assertNotNull(dtoError.detail());
        assertNotNull(dtoError.instance());
        assertTrue(dtoError.detail().contains("No entity with id:"));

        // 200 - found
        response = webTarget
                .path(PATH + "/" + idJornada)
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        JornadaDTO dtoResponse = response.readEntity(JornadaDTO.class);

        assertEquals(idJornada, dtoResponse.idJornada());
        assertNotNull(dtoResponse.nombre());
        assertFalse(dtoResponse.nombre().isBlank());

    }

    @Test
    @Order(3)
    public void findByRange() {
        System.out.println("JornadaResource.findByRange");

        // 400 - constraint validation
        Response response = webTarget
                .path(PATH)
                .queryParam("offset", -1)
                .queryParam("limit", 10)
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        ErrorDetailDTO dtoError = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, dtoError.status());
        assertNotNull(dtoError.type());
        assertNotNull(dtoError.detail());
        assertNotNull(dtoError.instance());

        // 200 - found
        response = webTarget
                .path(PATH)
                .queryParam("offset", 0)
                .queryParam("limit", 10)
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        List<JornadaDTO> resultList = response.readEntity(new GenericType<List<JornadaDTO>>() {
        });
        assertNotNull(resultList);
        assertFalse(resultList.isEmpty());

    }

    @Test
    @Order(4)
    public void update() {
        System.out.println("JornadaResource.update");

        // 400 - constraint validation
        JornadaDTO dto = new JornadaDTO(null, "", null, null, null);
        Response response = webTarget
                .path(PATH + "/0")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(dto, MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        ErrorDetailDTO dtoError = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, dtoError.status());
        assertNotNull(dtoError.type());
        assertNotNull(dtoError.detail());
        assertNotNull(dtoError.instance());
        assertNotNull(dtoError.issues());
        assertFalse(dtoError.issues().isEmpty());

        // 404 - not found
        dto = new JornadaDTO(null, "name", Date.from(Instant.parse("2020-01-23T00:00:00+00:08")), Date.from(Instant.now()), null);
        response = webTarget
                .path(PATH + "/100")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(dto, MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        dtoError = response.readEntity(ErrorDetailDTO.class);

        assertEquals(404, dtoError.status());
        assertNotNull(dtoError.type());
        assertNotNull(dtoError.detail());
        assertNotNull(dtoError.instance());
        assertTrue(dtoError.detail().contains("No entity with id:"));

        // 204 - updated
        dto = new JornadaDTO(null, "new name", Date.from(Instant.parse("2020-01-23T00:00:00+00:08")), Date.from(Instant.now()), null);
        response = webTarget
                .path(PATH + "/" + idJornada)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(dto, MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(204, response.getStatus());
    }

    @Test
    @Order(5)
    public void delete() {
        System.out.println("JornadaResource.delete");

        // 400 - constraint validation
        Response response = webTarget
                .path(PATH + "/0")
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertNotNull(response);
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        ErrorDetailDTO dtoError = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, dtoError.status());
        assertNotNull(dtoError.type());
        assertNotNull(dtoError.detail());
        assertNotNull(dtoError.instance());
        assertNotNull(dtoError.issues());
        assertFalse(dtoError.issues().isEmpty());
        assertEquals("arg0", dtoError.issues().getFirst().field());
        assertTrue(dtoError.issues().getFirst().message().contains("must be greater than or equal to 1"));

        // 404 - not found
        response = webTarget
                .path(PATH + "/100")
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        dtoError = response.readEntity(ErrorDetailDTO.class);

        assertEquals(404, dtoError.status());
        assertNotNull(dtoError.type());
        assertNotNull(dtoError.detail());
        assertNotNull(dtoError.instance());
        assertTrue(dtoError.detail().contains("No entity with id:"));

        //204 - deleted
        response = webTarget
                .path(PATH + "/" + idJornada)
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertNotNull(response);
        assertEquals(204, response.getStatus());
    }

}
