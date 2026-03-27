/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author caesar
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AreaConocimientoResourceST extends STAbstract {

    @Test
    @Order(1)
    void create() {
        System.out.println("AreaConocimientoResource.create");
        //null param of entity
        String json = """
    {
        "nombre": null
    }
    """;
        Response response = webTarget
                .path("area-conocimiento")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        String body = response.readEntity(String.class);
        System.out.println("Body: " + body);
        // 201
        json = """
    {
        "nombre": "Matemáticas"
    }
    """;
        response = webTarget
                .path("area-conocimiento")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
        assertNotNull(response);
        assertEquals(201, response.getStatus());
    }

//    @Test
//    @Order(2)
//    void findById() {
//        System.out.println("AreaConocimientoResource.findById");
//        Response response = webTarget.path("area-conocimiento/1").request(MediaType.APPLICATION_JSON).get();
//        assertNotNull(response);
//        assertEquals(200, response.getStatus());
//    }
}
