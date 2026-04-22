/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import static org.junit.jupiter.api.Assertions.*;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.JornadaAulaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.JornadaDTO;

/**
 *
 * @author usermein
 */
public class CreacionJordanasAndAsignacionAulasE2E extends STAbstract {

    private URI locationJornada;
    private String aulaA3;
    private String aulaA4;
    private Response response;
    @Given("Se tiene un servidor corriendo con la aplicacion desplegada")
    public void se_tiene_un_servidor_corriendo_con_la_aplicacion_desplegada() {
        System.out.println("--------------------------------------------");
        System.out.println("-------------SERVIDOR CORRIENDO--------------");
        System.out.println("--------------------------------------------");
        response = webTarget.path("jornada")
                .queryParam("offset", 0)
                .queryParam("limit", 10)
                .request()
                .get();
        System.out.println("STATUS: " + response.getStatus());
        System.out.println("BODY: " + response.readEntity(String.class));
        assertTrue(response.getStatus() < 500, "El servidor no esta funcionando correctamente");

    }

    @When("puedo crear una Jornada nueva para pruebas\\(examen)")
    public void puedo_crear_una_jornada_nueva_para_pruebas_examen() {
        System.out.println("------------------------------------------------------");
        System.out.println("--------------JORNADA NUEVA PARA PRUEBAS-------------");
        System.out.println("------------------------------------------------------");
        String json = """
        {
          "nombre": "Jornada de prueba",
          "fechaInicio": "2026-04-20T00:00:00",
          "fechaFin": "2026-04-20T00:00:00",
          "observaciones": "solo estoy probando esta onda"
        }
        """;
      response = webTarget.path("jornada")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        this.locationJornada = response.getLocation();
    }

    @When("puedo asociar distintas aulas para esa jornada")
    public void puedo_asociar_distintas_aulas_para_esa_jornada() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("-------------------ASOCIAR AULAS PARA PRUEBAS-----------------------------");
        System.out.println("--------------------------------------------------------------------------");
        //COMPROBAR DATOS EN TABLA JORNADA-AULA
      response = webTarget.path("jornada-aula")
                .queryParam("offset", 0)
                .queryParam("limit", 10)
                .request()
                .get();

        String body = response.readEntity(String.class);
        String totalRecord = response.getHeaderString("TOTAL_RECORDS");
        assertEquals(200, response.getStatus());
        int numEsperado = Integer.parseInt(totalRecord);
        //ASOCIAR
        String json = """
        {
          "idJornada": 1,
          "idAula": "A3",
          "observaciones": "Todos van para el matadero"
        }
        """;
        this.aulaA3 = "A3";
        response = webTarget.path("jornada-aula")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
        assertTrue(response.getStatus() == 201);
        json = """
        {
          "idJornada": 1,
          "idAula": "A4"
          
        }
        """;
        aulaA4 = "A4";
        response = webTarget.path("jornada-aula")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
        assertTrue(response.getStatus() == 201);

        response = webTarget.path("jornada-aula")
                .queryParam("offset", 0)
                .queryParam("limit", 10)
                .request()
                .get();
        String totalRecord1 = response.getHeaderString("TOTAL_RECORDS");

        //PARA CREAR UNA LISTA DTO DE LOS DATOS QUE FUERON TRAIDOS
        //Jsonb jsonb = JsonbBuilder.create();
        // JornadaAulaDTO[] array = jsonb.fromJson(body, JornadaAulaDTO[].class);
        // List<JornadaAulaDTO> lista = Arrays.asList(array);
        assertEquals(200, response.getStatus());
        assertEquals(numEsperado + 2, Integer.parseInt(totalRecord1));

    }

    @Then("puedo consultar la fecha en que se llevara a cabo la jornada")
    public void puedo_consultar_la_fecha_en_que_se_llevara_a_cabo_la_jornada() {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("-------------------CONSULTAR LA FECHA EN QUE SE LLEVARA A CABO--------------");
        System.out.println("--------------------------------------------------------------------------");
        Response response = client.target(locationJornada)
                .request(MediaType.APPLICATION_JSON)
                .get();
        String body = response.readEntity(String.class);
        assertEquals(200, response.getStatus());
        assertTrue(body.contains("2026-04-20T00:00:00"));
    }

    @Then("consultar que aulas estan asignadas a dicha jornada")
    public void consultar_que_aulas_estan_asignadas_a_dicha_jornada() {
        String path=locationJornada.getPath();
        String idJornada = path.substring(path.lastIndexOf("/")+1);
        response = webTarget.path("jornada-aula")
                .queryParam("offset", 0)
                .queryParam("limit", 10)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
        String body=response.readEntity(String.class);
        Jsonb jsonb = JsonbBuilder.create();
        JornadaAulaDTO[] array = jsonb.fromJson(body, JornadaAulaDTO[].class);
        List<JornadaAulaDTO> lista = Arrays.asList(array);
        assertTrue(lista.stream().anyMatch(dto->dto.idAula().equals("A3")));
        assertTrue(lista.stream().anyMatch(dto->dto.idAula().equals("A4")));
      
    }

}
