/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;

/**
 *
 * @author caesar
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AreaConocimientoResourceST extends STAbstract {

    private final String PATH = "area-conocimiento";

    @Test
    @Order(1)
    void create() {
        System.out.println("AreaConocimientoResource.create");

        // 400 - constraint validation
        String json = """
    {
        "nombre": null
    }
    """;
        Response response = webTarget
                .path(PATH)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        String body = response.readEntity(String.class);
        JsonObject jsonObj = Json.createReader(new StringReader(body)).readObject();

        assertEquals(400, jsonObj.getInt("status"));
        assertTrue(jsonObj.containsKey("type"));
        assertTrue(jsonObj.containsKey("detail"));
        assertTrue(jsonObj.containsKey("instance"));
        JsonArray issues = jsonObj.getJsonArray("issues");
        assertNotNull(issues);
        assertFalse(issues.isEmpty());
        JsonObject issue = issues.getJsonObject(0);
        assertEquals("nombre", issue.getString("field"));
        assertTrue(issue.getString("message").contains("must not be null"));

        // 201 - created
        json = """
    {
        "nombre": "valido"
    }
    """;
        response = webTarget
                .path(PATH)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(201, response.getStatus());
        String header = response.getHeaderString("Location");
        assertNotNull(header);
        assertTrue(header.contains(webTarget.getUri().toString() + PATH));
    }

    @Test
    @Order(2)
    void findById() {
        System.out.println("AreaConocimientoResource.findById");

        // 400 - constraint validation
        Response response = webTarget
                .path(PATH + "/0")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertNotNull(response);
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        String body = response.readEntity(String.class);
        JsonObject jsonObj = Json.createReader(new StringReader(body)).readObject();

        assertEquals(400, jsonObj.getInt("status"));
        assertTrue(jsonObj.containsKey("type"));
        assertTrue(jsonObj.containsKey("detail"));
        assertTrue(jsonObj.containsKey("instance"));
        JsonArray issues = jsonObj.getJsonArray("issues");
        assertNotNull(issues);
        assertFalse(issues.isEmpty());
        JsonObject issue = issues.getJsonObject(0);
        assertEquals("arg0", issue.getString("field"));
        assertTrue(issue.getString("message").contains("must be greater than or equal to 1"));

        // 404 - not found
        response = webTarget
                .path(PATH + "/100")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertNotNull(response);
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(404, response.getStatus());

        body = response.readEntity(String.class);
        jsonObj = Json.createReader(new StringReader(body)).readObject();

        assertEquals(404, jsonObj.getInt("status"));
        assertTrue(jsonObj.containsKey("type"));
        assertTrue(jsonObj.containsKey("detail"));
        assertTrue(jsonObj.getString("detail").contains("AreaConocimiento with id"));
        assertTrue(jsonObj.containsKey("instance"));
        String detail = jsonObj.getString("detail");
        assertTrue(detail.contains("AreaConocimiento with id"));

        // 200 - found
        response = webTarget
                .path(PATH + "/1")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        body = response.readEntity(String.class);
        jsonObj = Json.createReader(new StringReader(body)).readObject();

        assertEquals(1, jsonObj.getInt("idAreaConocimiento"));
        assertTrue(jsonObj.containsKey("nombre"));

    }

    @Test
    @Order(3)
    public void findByRange() {
        System.out.println("AreaConocimientoResource.findByRange");

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

        String body = response.readEntity(String.class);
        JsonObject jsonObj = Json.createReader(new StringReader(body)).readObject();

        assertEquals(400, jsonObj.getInt("status"));
        assertTrue(jsonObj.containsKey("type"));
        assertTrue(jsonObj.containsKey("detail"));
        assertTrue(jsonObj.containsKey("instance"));

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

        body = response.readEntity(String.class);
        List<AreaConocimiento> resultList = JsonbBuilder.create().fromJson(body, new ArrayList<AreaConocimiento>() {
        }.getClass().getGenericSuperclass());
        assertNotNull(resultList);
        assertFalse(resultList.isEmpty());

        AreaConocimiento result = resultList.getFirst();
        assertNotNull(result.getIdAreaConocimiento());
        assertNotNull(result.getNombre());

    }

    @Test
    @Order(4)
    public void update() {
        System.out.println("AreaConocimientoResource.update");

        // 400 - constraint validation
        String json = """
    {
        "nombre": null
    }
    """;
        Response response = webTarget
                .path(PATH + "/1")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(json, MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        String body = response.readEntity(String.class);
        JsonObject jsonObj = Json.createReader(new StringReader(body)).readObject();

        assertEquals(400, jsonObj.getInt("status"));
        assertTrue(jsonObj.containsKey("type"));
        assertTrue(jsonObj.containsKey("detail"));
        assertTrue(jsonObj.containsKey("instance"));
        JsonArray issues = jsonObj.getJsonArray("issues");
        assertNotNull(issues);
        assertFalse(issues.isEmpty());
        JsonObject issue = issues.getJsonObject(0);
        assertEquals("nombre", issue.getString("field"));
        assertTrue(issue.getString("message").contains("must not be null"));

        // 404 - not found
        json = """
    {
        "nombre": "actualizado"
    }
    """;
        response = webTarget
                .path(PATH + "/0")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(json, MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        body = response.readEntity(String.class);
        jsonObj = Json.createReader(new StringReader(body)).readObject();

        assertEquals(404, jsonObj.getInt("status"));
        assertTrue(jsonObj.containsKey("type"));
        assertTrue(jsonObj.containsKey("detail"));
        assertTrue(jsonObj.getString("detail").contains("No entity with id:"));
        assertTrue(jsonObj.containsKey("instance"));

        // 204 - updated
        json = """
    {
        "nombre":  "actualizado"
    }
    """;
        response = webTarget
                .path(PATH + "/1")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(json, MediaType.APPLICATION_JSON));

        assertNotNull(response);
        assertEquals(204, response.getStatus());
    }

    @Test
    @Order(5)
    public void delete() {
        System.out.println("AreaConocimientoResource.delete");

// 400 - constraint validation
        Response response = webTarget
                .path(PATH + "/0")
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertNotNull(response);
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));
        assertEquals(400, response.getStatus());

        String body = response.readEntity(String.class);
        JsonObject jsonObj = Json.createReader(new StringReader(body)).readObject();

        assertEquals(400, jsonObj.getInt("status"));
        assertTrue(jsonObj.containsKey("type"));
        assertTrue(jsonObj.containsKey("detail"));
        assertTrue(jsonObj.containsKey("instance"));
        JsonArray issues = jsonObj.getJsonArray("issues");
        assertNotNull(issues);
        assertFalse(issues.isEmpty());
        JsonObject issue = issues.getJsonObject(0);
        assertEquals("arg0", issue.getString("field"));
        assertTrue(issue.getString("message").contains("must be greater than or equal to 1"));

        // 404 - not found
        response = webTarget
                .path(PATH + "/100")
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString("Content-Type"));

        body = response.readEntity(String.class);
        jsonObj = Json.createReader(new StringReader(body)).readObject();

        assertEquals(404, jsonObj.getInt("status"));
        assertTrue(jsonObj.containsKey("type"));
        assertTrue(jsonObj.containsKey("detail"));
        assertTrue(jsonObj.getString("detail").contains("No entity with id:"));
        assertTrue(jsonObj.containsKey("instance"));

        //204 - deleted
        response = webTarget
                .path(PATH + "/1")
                .request(MediaType.APPLICATION_JSON)
                .delete();

        assertNotNull(response);
        assertEquals(204, response.getStatus());
    }

}
