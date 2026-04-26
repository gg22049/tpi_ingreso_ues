    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.validation.constraints.AssertTrue;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteOpcionDTO;

/**
 *
 * @author usermein
 */
public class CreacionDeAspiranteE2E extends STAbstract {

    private URI locationAspirante;
    private URI locationAspiranteOpcion;
    // private String path = "aspirante/";
    private Response response;
    
    @Given("Se tiene un servidor corriendo con la aplicacion desplegada")
public void se_tiene_un_servidor_corriendo_con_la_aplicacion_desplegada() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}
    @When("puedo crear un aspirante")
    public void puedo_crear_un_aspirante() {
        System.out.println("-------------------------------------------------------");
        System.out.println("CREAR ASPIRANTE");
        System.out.println("-------------------------------------------------------");
        String json = """
                    {
                    "nombres": "Meinson Fernando",
                    "apellidos": "Torrento Quezada",
                    "fechaNacimiento": "2026-04-20T00:00:00Z",
                    "correo":"quetinPorta@ues.tpi.sv",
                    "observaciones":"El alumno no va a pasar la materia"
                    }
                    """;

        response = webTarget.path("aspirante/")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
        System.out.println(response.getStatus());
        assertEquals(201, response.getStatus());
        System.out.println(response.readEntity(String.class));
        this.locationAspirante = response.getLocation();
        String path = locationAspirante.getPath();
        String id = path.substring(path.lastIndexOf("/") + 1);
        response = webTarget.path("aspirante/" + Long.parseLong(id))
                .request(MediaType.APPLICATION_JSON)
                .get();
        System.out.println(response.getStatus());
    }

    @When("puedo asociarlo a una opcion de carrera, ejemplo I300515")
    public void puedo_asociarlo_a_una_opcion_de_carrera_ejemplo_i300515() {
        System.out.println("-------------------------------------------------------");
        System.out.println("ASOCIAR ESTUDIANTE A LA CARRERA I300515");
        System.out.println("-------------------------------------------------------");
        String path = locationAspirante.getPath();
        String id = path.substring(path.lastIndexOf("/") + 1);
        String json = """
                    {
                  
                     "idOpcion": "I300515",
                     "fechaCreacion": "2026-04-20T00:00:00Z",
                     "idAspirante": %s
                    }
                    """.formatted(Long.parseLong(id));
        response = webTarget
                .path("aspirante-opcion/")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));

        System.out.println("BODY:" + response.readEntity(String.class));
        assertEquals(201, response.getStatus());
        this.locationAspiranteOpcion = response.getLocation();

    }

    @Then("puedo consultar el perfil del aspirante recien creado")
    public void puedo_consultar_el_perfil_del_aspirante_recien_creado() {
        System.out.println("-------------------------------------------------------");
        System.out.println("CONSULTAR PERFIL ASPIRANTE RECIEN CREADO");
        System.out.println("-------------------------------------------------------");
        String path = locationAspirante.getPath();
        String idAspirante = path.substring(path.lastIndexOf("/") + 1);
        response = webTarget
                .path("aspirante/" + idAspirante)
                .request(MediaType.APPLICATION_JSON)
                .get();
        AspiranteDTO dtoRespuesta = response.readEntity(AspiranteDTO.class);
        assertEquals(200, response.getStatus());
        System.out.println(response.getStatus());
        assertEquals("Meinson Fernando", dtoRespuesta.nombres());
    }

    @Then("verificar la opcion de carrera a la que se asocio")
    public void verificar_la_opcion_de_carrera_a_la_que_se_asocio() {
        System.out.println("-------------------------------------------------------");
        System.out.println("VERIFICA LA OPCION DE CARRERA A LA QUE SE ASOCIO");
        System.out.println("-------------------------------------------------------");
        String path = locationAspiranteOpcion.getPath();
        String idAspiranteOpcion = path.substring(path.lastIndexOf("/") + 1);

        response = webTarget.path("aspirante-opcion/" + idAspiranteOpcion)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
        AspiranteOpcionDTO dtoRespuesta = response.readEntity(AspiranteOpcionDTO.class);
        assertEquals("I300515", dtoRespuesta.idOpcion());
    }
}
