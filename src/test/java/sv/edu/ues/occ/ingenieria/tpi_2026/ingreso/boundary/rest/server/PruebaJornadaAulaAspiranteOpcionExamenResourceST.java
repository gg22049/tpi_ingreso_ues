/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import static sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.STAbstract.webTarget;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaJornadaAulaAspiranteOpcionExamenDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.JornadaAulaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaJornadaAulaAspiranteOpcionDTO;

/**
 *
 * @author caesar
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PruebaJornadaAulaAspiranteOpcionExamenResourceST extends STAbstract {

    private final String PATH = "prueba-jornada-aula-aspirante-opcion-examen";
    private Long idPrueba = 1L;
    private Long idJornada = 1L;
    private String idAula = String.valueOf(UUID.randomUUID());
    private Long idAspiranteOpcion = 1L;

    @BeforeAll
    void init() {
        // jornada-aula
        JornadaAulaDTO jornadaAulaDto = new JornadaAulaDTO(1L, idAula, null);
        Response response = webTarget
                .path("jornada-aula")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(jornadaAulaDto, MediaType.APPLICATION_JSON));
        // prueba-jornada-aula-aspirante-opcion
        PruebaJornadaAulaAspiranteOpcionDTO dto = new PruebaJornadaAulaAspiranteOpcionDTO(idPrueba, idJornada, idAula, idAspiranteOpcion, Boolean.FALSE, Date.from(Instant.now()));
        response = webTarget
                .path("prueba-jornada-aula-aspirante-opcion")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(1)
    void create() {
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenResource.create");

        // 400 - constraint validation
        PruebaJornadaAulaAspiranteOpcionExamenDTO dto = new PruebaJornadaAulaAspiranteOpcionExamenDTO(0L, 0L, "", 0L, null, null, "", null);
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
        dto = new PruebaJornadaAulaAspiranteOpcionExamenDTO(idPrueba, idJornada, idAula, idAspiranteOpcion, BigDecimal.ONE, Date.from(Instant.now()), null, null);
        response = webTarget
                .path(PATH)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(201, response.getStatus());
        String location = response.getHeaderString("Location");
        assertNotNull(location);
        assertTrue(location.contains(webTarget.getUri().toString() + PATH));
    }

    @Test
    @Order(2)
    void findById() {
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenResource.findById");

        // 400 - constraint validation
        Response response = webTarget
                .path(PATH + "/0/0/%20/0")
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

        // 404 - not found
        response = webTarget
                .path(PATH + "/100/100/100/100")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertEquals(404, response.getStatus());
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
                .path(PATH + "/" + idPrueba + "/" + idJornada + "/" + idAula + "/" + idAspiranteOpcion)
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        PruebaJornadaAulaAspiranteOpcionExamenDTO dtoResponse = response.readEntity(PruebaJornadaAulaAspiranteOpcionExamenDTO.class);

        assertEquals(idPrueba, dtoResponse.idPrueba());
        assertEquals(idJornada, dtoResponse.idJornada());
        assertEquals(idAula, dtoResponse.idAula());
        assertEquals(idAspiranteOpcion, dtoResponse.idAspiranteOpcion());

    }

    @Test
    @Order(3)
    public void findByRange() {
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenResource.findByRange");

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

        List<PruebaJornadaAulaAspiranteOpcionExamenDTO> resultList = response.readEntity(new GenericType<List<PruebaJornadaAulaAspiranteOpcionExamenDTO>>() {
        });
        assertNotNull(resultList);
        assertFalse(resultList.isEmpty());

    }

    @Test
    @Order(4)
    public void update() {
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenResource.update");

        // 400 - constraint validation
        PruebaJornadaAulaAspiranteOpcionExamenDTO dto = new PruebaJornadaAulaAspiranteOpcionExamenDTO(0L, 0L, "", 0L, null, null, "", null);
        Response response = webTarget
                .path(PATH)
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
        dto = new PruebaJornadaAulaAspiranteOpcionExamenDTO(100L, 100L, "100", 100L, BigDecimal.ONE, Date.from(Instant.now()), null, null);
        response = webTarget
                .path(PATH)
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
        dto = new PruebaJornadaAulaAspiranteOpcionExamenDTO(idPrueba, idJornada, idAula, idAspiranteOpcion, BigDecimal.valueOf(60), Date.from(Instant.now()), null, null);
        response = webTarget
                .path(PATH)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(dto, MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(204, response.getStatus());
    }

    @Test
    @Order(5)
    public void delete() {
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenResource.delete");

        // 400 - constraint validation
        Response response = webTarget
                .path(PATH + "/0/0/0/0")
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

        // 404 - not found
        response = webTarget
                .path(PATH + "/100/100/100/100")
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
                .path(PATH + "/" + idPrueba + "/" + idJornada + "/" + idAula + "/" + idAspiranteOpcion)
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertNotNull(response);
        assertEquals(204, response.getStatus());
    }

}
