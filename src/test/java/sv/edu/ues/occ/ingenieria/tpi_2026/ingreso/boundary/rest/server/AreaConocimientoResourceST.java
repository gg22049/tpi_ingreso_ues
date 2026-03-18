/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

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
    void test() {
        System.out.println("AreaConocimientoDAOImp.create");
        System.out.println(webTarget.path("area-conocimiento/1"));
        System.out.println(webTarget.getUri());
        Response response = webTarget.path("area-conocimiento/1").request(MediaType.APPLICATION_JSON).get();
        assertNotNull(response);
        assertEquals(404, response.getStatus());
    }

}
