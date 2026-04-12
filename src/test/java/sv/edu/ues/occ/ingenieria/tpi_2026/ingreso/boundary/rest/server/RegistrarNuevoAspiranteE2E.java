/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import java.util.Map;
import io.cucumber.datatable.DataTable;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import static org.junit.jupiter.api.Assertions.*;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteDTO;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author caesar
 */
public class RegistrarNuevoAspiranteE2E extends STAbstract {

    private Response response;

    private String path = "aspirante/";

    @Given("que no existe un aspirante con correo {string}")
    public void setup(String email) {
        response = webTarget
                .path(path + email)
                .request(MediaType.APPLICATION_JSON)
                .delete();
    }

    @When("registro un aspirante con:")
    public void test(DataTable table) {
        Map<String, String> data = table.asMap(String.class, String.class);
        AspiranteDTO dto = new AspiranteDTO(
                null,
                data.get("nombres"),
                data.get("apellidos"),
                Date.from(Instant.parse(data.get("fechaNacimiento"))),
                data.get("correo"),
                null,
                null
        );
        response = webTarget
                .path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
    }

    @Then("el sistema responde con codigo {int}")
    public void verifyStatus(int status) {
        assertEquals(status, response.getStatus());
        assertNotNull(response.getLocation());
    }

    @And("el aspirante queda almacenado con correo {string}")
    public void verifyAspirante(String email) {
        Response get = webTarget
                .path(path + email)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, get.getStatus());
        AspiranteDTO found = get.readEntity(AspiranteDTO.class);
        assertNotNull(found);
        assertEquals(email, found.correo());
    }

}
