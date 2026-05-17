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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractorPK;

/**
 *
 * @author caesar
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PruebaClaveAreaConocimientoPreguntaDistractorResourceST extends STAbstract {

    private final String PATH = "prueba";

    private Long idPruebaClave = 1L;
    private Integer idArea = 1;
    private long idPregunta = 1L;
    private long idDistractor;

    @BeforeAll
    void init() {
        Distractor dto = new Distractor(0L, "enunciado-pc-ac-pd", Boolean.FALSE, null);
        Response response = webTarget
                .path("distractor")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));

        String location = response.getHeaderString("Location");
        idDistractor = Long.valueOf(
                location.substring(location.lastIndexOf("/") + 1)
        );
    }

    @Test
    @Order(1)
    void create() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDistractorResource.create");

        // 400 - constraint validation
        PruebaClaveAreaConocimientoPreguntaDistractor dto = new PruebaClaveAreaConocimientoPreguntaDistractor(new PruebaClaveAreaConocimientoPreguntaDistractorPK(0l, 0, 0l, 0l), Date.from(Instant.now()), null);
        //0L, 0, 0L, 0L, Date.from(Instant.now()), null);
        Response response = webTarget
                .path(PATH + "/0/clave/0/area/0/pregunta/0/distractor/0")
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
        dto = new PruebaClaveAreaConocimientoPreguntaDistractor(idPruebaClave, idArea, idPregunta, idDistractor);
        dto.setFechaCreacion(Date.from(Instant.now()));

        response = webTarget
                .path(PATH + "/" + 1 + "/clave/" + idPruebaClave + "/area/" + idArea + "/pregunta/" + idPregunta + "/distractor/" + idDistractor)
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
        System.out.println("PruebaClaveAreaConocimientoPreguntaDistractorResource.findById");

        // 400 - constraint validation
        Response response = webTarget
                .path(PATH + "/0/clave/0/area/0/pregunta/0/distractor/0")
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
                .path(PATH + "/100/clave/100/area/100/pregunta/100/distractor/100")
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
                .path(PATH + "/" + 1 + "/clave/" + idPruebaClave + "/area/" + idArea + "/pregunta/" + idPregunta + "/distractor/" + idDistractor)
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        PruebaClaveAreaConocimientoPreguntaDistractor dtoResponse = response.readEntity(PruebaClaveAreaConocimientoPreguntaDistractor.class);

        // assertEquals(idPruebaClave, dtoResponse.gteIdPruebaClave());
        //assertEquals(idArea, dtoResponse.gteIdAreaConocimiento());
        //assertEquals(idPregunta, dtoResponse.gteIdPregunta());
        //assertEquals(idDistractor, dtoResponse.gteIdDistractor());
        assertNotNull(dtoResponse);
    }

   /* @Test
    @Order(3)
    public void findByRange() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDistractorResource.findByRange");

        // 400 - constraint validation
        Response response = webTarget
                .path(PATH + "/0/clave/0/area/0/pregunta/0/distractor/0")
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
                .path(PATH + "/" + 1 + "/clave/" + idPruebaClave + "/area/" + idArea + "/pregunta/" + idPregunta + "/distractor/" + idDistractor)
                .queryParam("offset", 0)
                .queryParam("limit", 10)
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        //List<PruebaClaveAreaConocimientoPreguntaDistractor> resultList = response.readEntity(new GenericType<List<PruebaClaveAreaConocimientoPreguntaDistractor>>() {});
        String json = response.readEntity(String.class);
System.out.println(json);
      //  assertNotNull(resultList);
       // assertFalse(resultList.isEmpty());

    }
/*
    @Test
    @Order(4)
    public void update() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDistractorResource.update");

        // 400 - constraint validation
        PruebaClaveAreaConocimientoPreguntaDistractor dto = new PruebaClaveAreaConocimientoPreguntaDistractor(new PruebaClaveAreaConocimientoPreguntaDistractorPK(0, 0, 0, 0), Date.from(Instant.now()), null);
        //  PruebaClaveAreaConocimientoPreguntaDistractor dto = new PruebaClaveAreaConocimientoPreguntaDistractor(0L, 0, 0L, 0L, Date.from(Instant.now()), null);
        Response response = webTarget
                .path(PATH + "/0/clave/0/area/0/pregunta/0/distractor/0")
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
        dto = new PruebaClaveAreaConocimientoPreguntaDistractor(new PruebaClaveAreaConocimientoPreguntaDistractorPK(100l, 100, 100l, 100l), Date.from(Instant.now()), null);
        //   dto = new PruebaClaveAreaConocimientoPreguntaDistractor(100L, 100, 100L, 100L, Date.from(Instant.now()), null);
        response = webTarget
                .path(PATH + "/100/clave/100/area/100/pregunta/100/distractor/100")
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
        dto = new PruebaClaveAreaConocimientoPreguntaDistractor(new PruebaClaveAreaConocimientoPreguntaDistractorPK(idPruebaClave, idArea, idPregunta, idDistractor), Date.from(Instant.now()), "observaciones");
        response = webTarget
                .path(PATH + "/" + 1 + "/clave/" + idPruebaClave + "/area/" + idArea + "/pregunta/" + idPregunta + "/distractor/" + idDistractor)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(dto, MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(204, response.getStatus());
    }
*/
    @Test
    @Order(3)
    public void delete() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDistractorResource.delete");

        // 400 - constraint validation
        Response response = webTarget
                .path(PATH + "/0/clave/0/area/0/pregunta/0/distractor/0")
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
                .path(PATH + "/100/clave/100/area/100/pregunta/100/distractor/100")
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
                .path(PATH + "/" + 1 + "/clave/" + idPruebaClave + "/area/" + idArea + "/pregunta/" + idPregunta + "/distractor/" + idDistractor)
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertNotNull(response);
        assertEquals(204, response.getStatus());
    }

}
