/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;

/**
 *
 * @author caesar
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AreaConocimientoResourceST extends STAbstract {

    private final String PATH = "area-conocimiento";
    private Integer idArea;

    @Test
    @Order(1)
    void create() {
        System.out.println("AreaConocimientoResource.create");

        // 400 - constraint validation
        AreaConocimiento entity = new AreaConocimiento(null, null, null, null, null);
        Response response = webTarget
                .path(PATH)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(entity, MediaType.APPLICATION_JSON));

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        ErrorDetailDTO errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.VALIDATION_ERROR.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertNotNull(errorDetail.issues());
        assertFalse(errorDetail.issues().isEmpty());

        // 201 - created
        entity.setNombre("name");
        entity.setActivo(Boolean.TRUE);
        response = webTarget
                .path(PATH)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(entity, MediaType.APPLICATION_JSON));

        assertEquals(201, response.getStatus());
        String location = response.getHeaderString("Location");
        assertNotNull(location);
        assertTrue(location.contains(webTarget.getUri().toString() + PATH));
        idArea = Integer.valueOf(
                location.substring(location.lastIndexOf("/") + 1)
        );
    }

    @Test
    @Order(2)
    public void setAreaPadre() {
        System.out.println("AreaConocimientoResource.setAreaPadre");

        // 400 - constraint validation - param idArea
        Response response = webTarget
                .path(PATH + "/0/area-padre/0")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity("", MediaType.APPLICATION_JSON));

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        ErrorDetailDTO errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.VALIDATION_ERROR.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertNotNull(errorDetail.issues());
        assertFalse(errorDetail.issues().isEmpty());

        // 400 - constraint validation - param idPadre
        response = webTarget
                .path(PATH + "/" + idArea + "/area-padre/0")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity("", MediaType.APPLICATION_JSON));

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.VALIDATION_ERROR.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertNotNull(errorDetail.issues());
        assertFalse(errorDetail.issues().isEmpty());

        // 404 - not found - param idArea
        response = webTarget
                .path(PATH + "/100/area-padre/10")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity("", MediaType.APPLICATION_JSON));

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(404, response.getStatus());

        errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(404, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.NO_MATCH_ID.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertTrue(errorDetail.detail().contains("No entity with id:"));

        // 404 - not found - param idPadre
        response = webTarget
                .path(PATH + "/" + idArea + "/area-padre/100")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity("", MediaType.APPLICATION_JSON));

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(404, response.getStatus());

        errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(404, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.NO_MATCH_ID.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertTrue(errorDetail.detail().contains("No entity with id:"));

        // 204 - no content
        response = webTarget
                .path(PATH + "/" + idArea + "/area-padre/1")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity("", MediaType.APPLICATION_JSON));

        assertEquals(204, response.getStatus());

    }

    @Test
    @Order(3)
    public void unsetAreaPadre() {
        System.out.println("AreaConocimientoResource.unsetAreaPadre");

        // 400 - constraint validation
        Response response = webTarget
                .path(PATH + "/0/area-padre")
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        ErrorDetailDTO errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.VALIDATION_ERROR.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertNotNull(errorDetail.issues());
        assertFalse(errorDetail.issues().isEmpty());
        assertEquals("arg0", errorDetail.issues().getFirst().field());
        assertTrue(errorDetail.issues().getFirst().message().contains("must be greater than or equal to 1"));

        // 404 - not found
        response = webTarget
                .path(PATH + "/100/area-padre")
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(404, response.getStatus());

        errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(404, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.NO_MATCH_ID.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertTrue(errorDetail.detail().contains("No entity with id:"));

        // 204 - no content
        response = webTarget
                .path(PATH + "/" + idArea + "/area-padre")
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertEquals(204, response.getStatus());
    }

    @Test
    @Order(4)
    void findById() {
        System.out.println("AreaConocimientoResource.findById");

        // 400 - constraint validation
        Response response = webTarget
                .path(PATH + "/0")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        ErrorDetailDTO errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.VALIDATION_ERROR.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertNotNull(errorDetail.issues());
        assertFalse(errorDetail.issues().isEmpty());
        assertEquals("arg0", errorDetail.issues().getFirst().field());
        assertTrue(errorDetail.issues().getFirst().message().contains("must be greater than or equal to 1"));

        // 404 - not found
        response = webTarget
                .path(PATH + "/100")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(404, response.getStatus());

        errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(404, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.NO_MATCH_ID.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertTrue(errorDetail.detail().contains("No entity with id:"));

        // 200 - found
        response = webTarget
                .path(PATH + "/" + idArea)
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        AreaConocimiento entityResponse = response.readEntity(AreaConocimiento.class);

        assertNotNull(entityResponse.getActivo());
        assertFalse(entityResponse.getNombre().isBlank());

    }

    @Test
    @Order(5)
    public void findByRange() {
        System.out.println("AreaConocimientoResource.findByRange");

        // 400 - constraint validation
        Response response = webTarget
                .path(PATH)
                .queryParam("offset", -1)
                .queryParam("limit", 10)
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertEquals(400, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        ErrorDetailDTO errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());

        // 200 - found
        response = webTarget
                .path(PATH)
                .queryParam("offset", 0)
                .queryParam("limit", 10)
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        List<AreaConocimiento> resultList = response.readEntity(new GenericType<List<AreaConocimiento>>() {
        });
        assertNotNull(resultList);
        assertFalse(resultList.isEmpty());

    }

    @Test
    @Order(6)
    public void update() {
        System.out.println("AreaConocimientoResource.update");

        // 400 - constraint validation
        AreaConocimiento entity = new AreaConocimiento(null, null, null, null, null);
        Response response = webTarget
                .path(PATH + "/1")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(entity, MediaType.APPLICATION_JSON));

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        ErrorDetailDTO errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.VALIDATION_ERROR.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertNotNull(errorDetail.issues());
        assertFalse(errorDetail.issues().isEmpty());

        // 400 - constraint validation
        entity.setNombre("name");
        entity.setActivo(Boolean.TRUE);

        response = webTarget
                .path(PATH + "/0")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(entity, MediaType.APPLICATION_JSON));

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.VALIDATION_ERROR.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertNotNull(errorDetail.issues());
        assertFalse(errorDetail.issues().isEmpty());

        // 404 - not found
        response = webTarget
                .path(PATH + "/100")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(entity, MediaType.APPLICATION_JSON));

        assertEquals(404, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(404, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.NO_MATCH_ID.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertTrue(errorDetail.detail().contains("No entity with id:"));

        // 204 - updated
        response = webTarget
                .path(PATH + "/" + idArea)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(entity, MediaType.APPLICATION_JSON));

        assertEquals(204, response.getStatus());
    }

    @Test
    @Order(7)
    public void delete() {
        System.out.println("AreaConocimientoResource.delete");

        // 400 - constraint validation
        Response response = webTarget
                .path(PATH + "/0")
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        ErrorDetailDTO errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(400, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.VALIDATION_ERROR.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertNotNull(errorDetail.issues());
        assertFalse(errorDetail.issues().isEmpty());
        assertEquals("arg0", errorDetail.issues().getFirst().field());
        assertTrue(errorDetail.issues().getFirst().message().contains("must be greater than or equal to 1"));

        // 404 - not found
        response = webTarget
                .path(PATH + "/100")
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertEquals(404, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        errorDetail = response.readEntity(ErrorDetailDTO.class);

        assertEquals(404, errorDetail.status());
        assertNotNull(errorDetail.type());
        assertEquals(ErrorType.NO_MATCH_ID.toString(), errorDetail.type());
        assertNotNull(errorDetail.detail());
        assertNotNull(errorDetail.instance());
        assertTrue(errorDetail.detail().contains("No entity with id:"));

        //204 - deleted
        response = webTarget
                .path(PATH + "/" + idArea)
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertEquals(204, response.getStatus());
    }

}
