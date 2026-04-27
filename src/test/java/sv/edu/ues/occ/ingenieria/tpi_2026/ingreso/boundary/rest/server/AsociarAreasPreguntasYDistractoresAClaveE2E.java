/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.DistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaDistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoPreguntaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoPreguntaDistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.TipoPruebaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPK;

/**
 *
 * @author usermein
 */
public class AsociarAreasPreguntasYDistractoresAClaveE2E extends STAbstract {

    private URI locationTipoPrueba;
    private URI locationPrueba;
    private URI locationPruebaClave;
    private URI locationAreaConocimiento;
    private URI locationPregunta;
    private URI locationPreguntaArea;
    private URI locationDistractorIncorrecto;
    private URI locationDistractorCorrecto;
    private URI locationPcac;
    private URI locationPcacp;

    private URI locationPcacpdIncorrecto;
    private URI locationPcacpdCorrecto;

    private Response response;

    private Integer idTipoPrueba;
    private Long idPrueba;
    private Long idPruebaClave;
    private Integer idAreaConocimiento;
    private Long idPregunta;
    private String idPreguntaArea;
    private Long idDistractorIncorrecto;
    private Long idDistractorCorrecto;

   

    @Given("se tiene un tipo de prueba {string} {string}:")
    public void se_tiene_un_tipo_de_prueba(String string, String string2) {
        TipoPruebaDTO tipoPruebaDTO = new TipoPruebaDTO(null, string, Boolean.parseBoolean(string2), null);
        response = webTarget.path("tipo-prueba")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(tipoPruebaDTO, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        locationTipoPrueba = response.getLocation();
        String path = locationTipoPrueba.getPath();
        this.idTipoPrueba =Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
        Response get = webTarget
                .path("tipo-prueba/" + idTipoPrueba)
                .request(MediaType.APPLICATION_JSON)
                .get();
        TipoPruebaDTO found = response.readEntity(TipoPruebaDTO.class);
        assertEquals(string, found.valor());
    }

    @And("crear una prueba:")
    public void crear_una_prueba(DataTable dataTable) {
        System.out.println("-------------------------------------------------------");
        System.out.println("CREAR PRUEBA");
        System.out.println("-------------------------------------------------------");
        BigDecimal bd;
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String path = locationTipoPrueba.getPath();
        String id = path.substring(path.lastIndexOf("/") + 1);
        PruebaDTO pruebaDTO = new PruebaDTO(null, data.get("nombre"),
                data.get("indicaciones"),
                new BigDecimal(data.get("puntajeMaximo")),
                new BigDecimal(data.get("notaAprobacion")),
                Integer.parseInt(data.get("duracion")),
                null,
                Integer.parseInt(id)
        );
        response = webTarget.path("prueba")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(pruebaDTO, MediaType.APPLICATION_JSON));

        assertEquals(201, response.getStatus());
        locationPrueba = response.getLocation();
        path = locationPrueba.getPath();
        this.idPrueba =Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
    }

    @And("existe una clave llamada {string} asociada a la prueba")
    public void existe_una_clave_llamada_asociada_a_la_prueba(String string) {
        PruebaClaveDTO pruebaClaveDTO = new PruebaClaveDTO(null, string,idPrueba);
        response = webTarget.path("prueba-clave")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(pruebaClaveDTO, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        this.locationPruebaClave = response.getLocation();
        String path = locationPruebaClave.getPath();
        this.idPruebaClave =Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
        response = webTarget
                .path("prueba-clave/" + idPruebaClave)
                .request(MediaType.APPLICATION_JSON)
                .get();
        PruebaClaveDTO found = response.readEntity(PruebaClaveDTO.class);
        assertEquals(string, found.nombre());
    }

    @And("existe el area de conocimiento llamada {string}")
    public void existe_el_area_de_conocimiento_llamada(String string) {
        AreaConocimientoDTO areaConocimientoDTO = new AreaConocimientoDTO(null, string, null, true, null);
        response = webTarget.path("area-conocimiento")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(areaConocimientoDTO, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        this.locationAreaConocimiento = response.getLocation();
        String path = locationAreaConocimiento.getPath();
        System.out.println(locationAreaConocimiento);
        this.idAreaConocimiento = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
        response = webTarget
                .path("area-conocimiento/" + idAreaConocimiento)
                .request(MediaType.APPLICATION_JSON)
                .get();
        AreaConocimientoDTO found = response.readEntity(AreaConocimientoDTO.class);
        assertEquals(string, found.nombre());
    }

    @And("existe la pregunta {string} asociada al area sociales")
    public void existe_la_pregunta_asociada_al_area_sociales(String string) {
        PreguntaDTO preguntaDTO = new PreguntaDTO(null, string, true, null, null);
        response = webTarget.path("pregunta")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(preguntaDTO, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        this.locationPregunta = response.getLocation();
        String path = locationPregunta.getPath();
        this.idPregunta = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
        response = webTarget
                .path("pregunta/" + idPregunta)
                .request(MediaType.APPLICATION_JSON)
                .get();
        PreguntaDTO found = response.readEntity(PreguntaDTO.class);
        assertEquals(string, found.valor());
        //la pregunta debe estar asociada
        PreguntaAreaConocimientoDTO preguntaAreaConocimientoDTO = new PreguntaAreaConocimientoDTO(idPregunta, idAreaConocimiento, "exelente pregunta");
        response = webTarget.path("pregunta-area-conocimiento")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(preguntaAreaConocimientoDTO, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        this.locationPreguntaArea = response.getLocation();
        path = locationPreguntaArea.getPath();
        this.idPreguntaArea = path.substring(path.lastIndexOf("/") + 1);
        response = webTarget
                .path("pregunta-area-conocimiento/" + idPregunta + "/" + idAreaConocimiento)
                .request(MediaType.APPLICATION_JSON)
                .get();
        PreguntaAreaConocimientoDTO found2 = response.readEntity(PreguntaAreaConocimientoDTO.class);
        assertEquals(idAreaConocimiento, found2.idAreaConocimiento());
        assertEquals(idPregunta, found2.idPregunta());
    }

    @And("existe el distractor {string} como opcion incorrecta")
    public void existe_el_distractor_como_opcion_incorrecta(String string) {
        DistractorDTO distractorDTO = new DistractorDTO(0, string, true, null);
        response = webTarget.path("distractor")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(distractorDTO, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        this.locationDistractorIncorrecto = response.getLocation();
        String path = locationDistractorIncorrecto.getPath();
        this.idDistractorIncorrecto = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
        response = webTarget
                .path("distractor/" + idDistractorIncorrecto)
                .request(MediaType.APPLICATION_JSON)
                .get();
        DistractorDTO found = response.readEntity(DistractorDTO.class);
        assertEquals(string, found.valor());
        PreguntaDistractorDTO preguntaDistractorDTO = new PreguntaDistractorDTO(idPregunta, idDistractorIncorrecto, false, null);
        response = webTarget.path("pregunta-distractor")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(preguntaDistractorDTO, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
    }

    @And("existe el distractor {string} como opcion correcta")
    public void existe_el_distractor_como_opcion_correcta(String string) {
        DistractorDTO distractorDTO = new DistractorDTO(0, string, true, null);
        response = webTarget.path("distractor")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(distractorDTO, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        this.locationDistractorCorrecto = response.getLocation();
        String path = locationDistractorCorrecto.getPath();
        this.idDistractorCorrecto = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
        response = webTarget
                .path("distractor/" + idDistractorCorrecto)
                .request(MediaType.APPLICATION_JSON)
                .get();
        DistractorDTO found = response.readEntity(DistractorDTO.class);
        assertEquals(string, found.valor());
        //asociar a una pregunta para saber si es correcto
        PreguntaDistractorDTO preguntaDistractorDTO = new PreguntaDistractorDTO(idPregunta,              
                idDistractorCorrecto,             
                true,
                null);
        response = webTarget.path("pregunta-distractor")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(preguntaDistractorDTO, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());

    }

    @When("asocio el area {string} a la clave {string} con cantidad {int} y porcentaje {int}")
    public void asocio_el_area_a_la_clave_con_cantidad_y_porcentaje(String string, String string2, Integer int1, Integer int2) {
        BigDecimal dec = new BigDecimal(int2);
        PruebaClaveAreaConocimientoDTO pruebaClaveAreaConocimientoDTO = new PruebaClaveAreaConocimientoDTO(
                idPruebaClave,
                idAreaConocimiento,
                int1,
                dec);
        response = webTarget.path("prueba-clave-area-conocimiento")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(pruebaClaveAreaConocimientoDTO, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        this.locationPcac = response.getLocation();

    }

    @And("asocio la pregunta a la clave y al area {string} con porcentaje {int}")
    public void asocio_la_pregunta_a_la_clave_y_al_area_con_porcentaje(String string, Integer int1) {
        BigDecimal dec = new BigDecimal(int1);

        PruebaClaveAreaConocimientoPreguntaDTO pcacpDTO = new PruebaClaveAreaConocimientoPreguntaDTO(idPruebaClave,
                idAreaConocimiento,
                idPregunta,
                dec);
        response = webTarget.path("prueba-clave-area-conocimiento-pregunta")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(pcacpDTO, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        this.locationPcacp = response.getLocation();
    }

    @And("asocio los distractores de la pregunta a la clave")
    public void asocio_los_distractores_de_la_pregunta_a_la_clave() {
        PruebaClaveAreaConocimientoPreguntaDistractorDTO pcacpdDTOCorrecto = new PruebaClaveAreaConocimientoPreguntaDistractorDTO(idPruebaClave,
                idAreaConocimiento,
                idPregunta,
                idDistractorCorrecto,
                null,
                null);
        response = webTarget.path("prueba-clave-area-conocimiento-pregunta-distractor")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(pcacpdDTOCorrecto, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        this.locationPcacpdCorrecto = response.getLocation();
        PruebaClaveAreaConocimientoPreguntaDistractorDTO pcacpdDTOincorrecto = new PruebaClaveAreaConocimientoPreguntaDistractorDTO(
               idPruebaClave,
                idAreaConocimiento,
                idPregunta,
                idDistractorIncorrecto,
                null,
                null);
        response = webTarget.path("prueba-clave-area-conocimiento-pregunta-distractor")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(pcacpdDTOincorrecto, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        this.locationPcacpdIncorrecto = response.getLocation();
        System.out.println(locationPcacpdCorrecto);
        System.out.println(locationPcacpdIncorrecto);
    }

    @Then("puedo consultar la clave de la prueba")
    public void puedo_consultar_la_clave_de_la_prueba() {
        response = client.target(locationPruebaClave)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
        PruebaClaveDTO pcDTO = response.readEntity(PruebaClaveDTO.class);
        assertNotNull(pcDTO);
        assertEquals("Clave A", pcDTO.nombre());
    }

    @And("verifico que la clave contiene el area {string}")
    public void verifico_que_la_clave_contiene_el_area(String string) {
        response = client.target(locationPcac)
                .request(MediaType.APPLICATION_JSON)
                .get();
        PruebaClaveAreaConocimientoDTO pcacDTO = response.readEntity(PruebaClaveAreaConocimientoDTO.class);
        assertEquals(200, response.getStatus());
        assertNotNull(pcacDTO);
        assertEquals(idAreaConocimiento, pcacDTO.idAreaConocimiento());
       
    }

    @And("verifico que la clave contiene la pregunta asociada")
    public void verifico_que_la_clave_contiene_la_pregunta_asociada() {
        response = client.target(locationPcacp)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
        PruebaClaveAreaConocimientoPreguntaDTO pcacpDTO = response.readEntity(PruebaClaveAreaConocimientoPreguntaDTO.class);
        assertNotNull(pcacpDTO);
        assertEquals(idPregunta, pcacpDTO.idPregunta());
        assertTrue(pcacpDTO.idPregunta() ==idPregunta);
    }

    @And("verifico que la clave contiene los distractores de la pregunta")
    public void verifico_que_la_clave_contiene_los_distractores_de_la_pregunta() {
        response = client.target(locationPcacpdCorrecto)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
        PruebaClaveAreaConocimientoPreguntaDistractorDTO pcacpdDTOCorrecto = response.readEntity(PruebaClaveAreaConocimientoPreguntaDistractorDTO.class);
        assertNotNull(pcacpdDTOCorrecto);
         assertEquals(idDistractorCorrecto, pcacpdDTOCorrecto.idDistractor());

        response = client.target(locationPcacpdIncorrecto)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
        PruebaClaveAreaConocimientoPreguntaDistractorDTO pcacpdDTOIncorecto = response.readEntity(PruebaClaveAreaConocimientoPreguntaDistractorDTO.class);
        assertNotNull(pcacpdDTOIncorecto);
        assertEquals(idDistractorIncorrecto, pcacpdDTOIncorecto.idDistractor());
      
    }

}
