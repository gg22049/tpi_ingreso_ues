/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.Instant;
import java.util.Date;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteOpcionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.TipoIdentificacionDTO;
import io.cucumber.datatable.DataTable;
import java.util.Map;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteIdentificacionDTO;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author caesar
 */
public class CrearAspiranteYAsociarCarrerasE2E extends STAbstract {

    private Integer idTipoIdentificacion;
    private Long idAspirante;
    private Long idOpcionUno;
    private Long idOpcionDos;

    @Given("existe una identificacion tipo {string}")
    public void existe_una_identificacion(String tipo) {
        TipoIdentificacionDTO dto = new TipoIdentificacionDTO(null, tipo, null);
        Response response = webTarget
                .path("tipo-identificacion")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
        String location = response.getHeaderString("Location");
        idTipoIdentificacion = Integer.valueOf(
                location.substring(location.lastIndexOf("/") + 1)
        );
    }

    @And("existe un aspirante:")
    public void existe_un_aspirante(DataTable data) {
        Map<String, String> dataMap = data.asMap();
        AspiranteDTO dto = new AspiranteDTO(
                idAspirante,
                dataMap.get("nombres"),
                dataMap.get("apellidos"),
                Date.from(Instant.parse(dataMap.get("fechaNacimiento"))),
                dataMap.get("correo"),
                null,
                null
        );
        Response response = webTarget
                .path("aspirante")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
        String location = response.getHeaderString("Location");
        idAspirante = Long.valueOf(
                location.substring(location.lastIndexOf("/") + 1)
        );
    }

    @When("asocio al aspirante la identificacion con valor {string}")
    public void asocio_una_identificacion(String valor) {
        AspiranteIdentificacionDTO dto = new AspiranteIdentificacionDTO(
                idAspirante,
                idTipoIdentificacion,
                valor,
                null,
                null
        );
        Response response = webTarget
                .path("aspirante-identificacion")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
    }

    @And("asocio al aspirante la primera opcion de carrera {string}")
    public void asocio_una_primera_opcion(String carrera) {
        AspiranteOpcionDTO dto = new AspiranteOpcionDTO(null, carrera, Date.from(Instant.now()), idAspirante);
        Response response = webTarget
                .path("aspirante-opcion")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
        String location = response.getHeaderString("Location");
        idOpcionUno = Long.valueOf(
                location.substring(location.lastIndexOf("/") + 1)
        );
    }

    @And("asocio al aspirante la segunda opcion de carrera {string}")
    public void asocio_una_segunda_opcion(String carrera) {
        AspiranteOpcionDTO dto = new AspiranteOpcionDTO(null, carrera, Date.from(Instant.now()), idAspirante);
        Response response = webTarget
                .path("aspirante-opcion")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
        String location = response.getHeaderString("Location");
        idOpcionDos = Long.valueOf(
                location.substring(location.lastIndexOf("/") + 1)
        );
    }

    @Then("puedo consultar el perfil del aspirante recien creado con correo {string}")
    public void puedo_consultar_el_perfil_aspirante(String correo) {
        Response response = webTarget
                .path("aspirante/" + correo)
                .request(MediaType.APPLICATION_JSON)
                .get();
        AspiranteDTO dtoResponse = response.readEntity(AspiranteDTO.class);
        assertEquals(idAspirante, dtoResponse.idAspirante());
        assertEquals(correo, dtoResponse.correo());
    }

    @And("verifico que la identificacion {string} pertenece al aspirante creado")
    public void verifico_que_la_identificacion_pertenece_al_aspirante_creado(String identificacion) {
        Response response = webTarget
                .path("aspirante-identificacion/" + idAspirante + "/" + idTipoIdentificacion)
                .request(MediaType.APPLICATION_JSON)
                .get();
        AspiranteIdentificacionDTO dtoResponse = response.readEntity(AspiranteIdentificacionDTO.class);
        assertEquals(idAspirante, dtoResponse.idAspirante());
        assertEquals(identificacion, dtoResponse.valor());
    }

    @And("verifico que el aspirante tiene asociada la opcion uno {string}")
    public void verifico_que_el_aspirante_tiene_asociada_la_opcion_uno(String opcion) {
        Response response = webTarget
                .path("aspirante-opcion/" + idOpcionUno)
                .request(MediaType.APPLICATION_JSON)
                .get();
        AspiranteOpcionDTO dtoResponse = response.readEntity(AspiranteOpcionDTO.class);
        assertEquals(idAspirante, dtoResponse.idAspirante());
        assertEquals(opcion, dtoResponse.idOpcion());
    }

    @And("verifico que el aspirante tiene asociada la opcion dos {string}")
    public void verifico_que_el_aspirante_tiene_asociada_la_opcion_dos(String opcion) {
        Response response = webTarget
                .path("aspirante-opcion/" + idOpcionDos)
                .request(MediaType.APPLICATION_JSON)
                .get();
        AspiranteOpcionDTO dtoResponse = response.readEntity(AspiranteOpcionDTO.class);
        assertEquals(idAspirante, dtoResponse.idAspirante());
        assertEquals(opcion, dtoResponse.idOpcion());
    }

}
