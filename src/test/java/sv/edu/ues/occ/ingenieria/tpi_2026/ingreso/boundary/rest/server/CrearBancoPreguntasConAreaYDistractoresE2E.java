/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.DistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaDistractorDTO;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author caesar
 */
public class CrearBancoPreguntasConAreaYDistractoresE2E extends STAbstract {

    private Integer idArea;
    private Long idPregunta;
    private Long idDistractorUno;
    private Long idDistractorDos;

    @Given("existe un area de conocimiento llamada {string}")
    public void existe_un_area_de_conocimiento(String area) {
        AreaConocimientoDTO dto = new AreaConocimientoDTO(null, area, "descripcion", Boolean.TRUE, null);
        Response response = webTarget
                .path("area-conocimiento")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
        String location = response.getHeaderString("Location");
        idArea = Integer.valueOf(
                location.substring(location.lastIndexOf("/") + 1)
        );
    }

    @And("existe un distractor uno activo con valor {string}")
    public void existe_un_distractor_uno_activo(String distractor) {
        DistractorDTO dto = new DistractorDTO(0L, distractor, Boolean.TRUE, null);
        Response response = webTarget
                .path("distractor")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
        String location = response.getHeaderString("Location");
        idDistractorUno = Long.valueOf(
                location.substring(location.lastIndexOf("/") + 1)
        );
    }

    @And("existe un distractor dos activo con valor {string}")
    public void existe_un_distractor_dos_activo(String distractor) {
        DistractorDTO dto = new DistractorDTO(0L, distractor, Boolean.TRUE, null);
        Response response = webTarget
                .path("distractor")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
        String location = response.getHeaderString("Location");
        idDistractorDos = Long.valueOf(
                location.substring(location.lastIndexOf("/") + 1)
        );
    }

    @When("creo una pregunta con el texto {string}")
    public void creo_una_pregunta_con_el_texto(String pregunta) {
        PreguntaDTO dto = new PreguntaDTO(null, pregunta, Boolean.TRUE, null, null);
        Response response = webTarget
                .path("pregunta")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
        String location = response.getHeaderString("Location");
        idPregunta = Long.valueOf(
                location.substring(location.lastIndexOf("/") + 1)
        );
    }

    @And("asocio la pregunta al area de conocimiento {string}")
    public void asocio_la_pregunta_al_area_de_conocimiento(String string) {
        PreguntaAreaConocimientoDTO dto = new PreguntaAreaConocimientoDTO(idPregunta, idArea, null);
        Response response = webTarget
                .path("pregunta-area-conocimiento")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
    }

    @And("asocio el distractor uno a la pregunta marcando correcto como {string}")
    public void asocio_distractor_uno_como_opcion_correcta(String correcto) {
        Boolean bool = Boolean.parseBoolean(correcto);
        PreguntaDistractorDTO dto = new PreguntaDistractorDTO(idPregunta, idDistractorUno, bool, null);
        Response response = webTarget
                .path("pregunta-distractor")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
    }

    @And("asocio el distractor dos a la pregunta marcando correcto como {string}")
    public void asocio_distractor_dos_como_opcion_incorrecta(String correcto) {
        Boolean bool = Boolean.parseBoolean(correcto);
        PreguntaDistractorDTO dto = new PreguntaDistractorDTO(idPregunta, idDistractorDos, bool, null);
        Response response = webTarget
                .path("pregunta-distractor")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
    }

    @Then("puedo consultar la pregunta recien creada con el texto {string}")
    public void puedo_consultar_la_pregunta_recien_creada(String pregunta) {
        Response response = webTarget
                .path("pregunta/" + idPregunta)
                .request(MediaType.APPLICATION_JSON)
                .get();
        PreguntaDTO dtoResponse = response.readEntity(PreguntaDTO.class);
        assertEquals(idPregunta, dtoResponse.idPregunta());
        assertEquals(pregunta, dtoResponse.valor());
    }

    @And("verifico que la pregunta pertenece al area")
    public void verifico_que_la_pregunta_pertenece_al_area() {
        Response response = webTarget
                .path("pregunta-area-conocimiento/" + idPregunta + "/" + idArea)
                .request(MediaType.APPLICATION_JSON)
                .get();
        PreguntaAreaConocimientoDTO dtoResponse = response.readEntity(PreguntaAreaConocimientoDTO.class);
        assertEquals(idPregunta, dtoResponse.idPregunta());
        assertEquals(idArea, dtoResponse.idAreaConocimiento());
    }

    @And("verifico que la pregunta se asocio al distractor uno")
    public void verifico_que_la_pregunta_se_asocio_al_distractor_uno() {
        Response response = webTarget
                .path("pregunta-distractor/" + idPregunta + "/" + idDistractorUno)
                .request(MediaType.APPLICATION_JSON)
                .get();
        PreguntaDistractorDTO dtoResponse = response.readEntity(PreguntaDistractorDTO.class);
        assertEquals(idPregunta, dtoResponse.idPregunta());
        assertEquals(idDistractorUno, dtoResponse.idDistractor());
    }

    @And("verifico que la pregunta se asocio al distractor dos")
    public void verifico_que_la_pregunta_se_asocio_al_distractor_dos() {
        Response response = webTarget
                .path("pregunta-distractor/" + idPregunta + "/" + idDistractorDos)
                .request(MediaType.APPLICATION_JSON)
                .get();
        PreguntaDistractorDTO dtoResponse = response.readEntity(PreguntaDistractorDTO.class);
        assertEquals(idPregunta, dtoResponse.idPregunta());
        assertEquals(idDistractorDos, dtoResponse.idDistractor());
    }

}
