
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
import static sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.STAbstract.webTarget;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.TipoPruebaDTO;

/**
 *
 * @author usermein
 */
public class CrearPruebaTipoNuevoIngresoE2E extends STAbstract {

    private URI locationTipoPrueba;
    private URI locationPrueba;
    private URI locationPruebaClave;

    private Response response;

    private Integer idTipoPrueba;
    private Long idPrueba;
    private Long idPruebaClave;

    @Given("existe un tipo de prueba llamado {string}")
    public void existe_un_tipo_de_prueba_llamado(String string) {
        TipoPruebaDTO tipoPruebaDTO = new TipoPruebaDTO(null, string, Boolean.parseBoolean(string), null);
        response = webTarget.path("tipo-prueba")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(tipoPruebaDTO, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());
        locationTipoPrueba = response.getLocation();
        String path = locationTipoPrueba.getPath();
        this.idTipoPrueba =Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
    }

    @When("creo una prueba:")
    public void creo_una_prueba(DataTable dataTable) {
        BigDecimal bd;
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        PruebaDTO pruebaDTO = new PruebaDTO(null, data.get("nombre"),
                data.get("indicaciones"),
                new BigDecimal(data.get("puntajeMaximo")),
                new BigDecimal(data.get("notaAprobacion")),
                Integer.parseInt(data.get("duracion")),
                null,
                idTipoPrueba
        );
        response = webTarget.path("prueba")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(pruebaDTO, MediaType.APPLICATION_JSON));

        assertEquals(201, response.getStatus());
        locationPrueba = response.getLocation();
        String path = locationPrueba.getPath();
        this.idPrueba =Long.parseLong(path.substring(path.lastIndexOf("/") + 1));

    }

    @And("creo una clave para la prueba llamada {string}")
    public void creo_una_clave_para_la_prueba_llamada(String string) {

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

    @Then("puedo consultar la prueba recien creada {string}")
    public void puedo_consultar_la_prueba_recien_creada(String nuevoIngreso) {
        response = client.target(locationPrueba)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
        PruebaDTO pruebaDTO = response.readEntity(PruebaDTO.class);
        assertEquals(idPrueba, pruebaDTO.idPrueba());
        assertEquals(nuevoIngreso, pruebaDTO.nombre());
    }

    @And("verifico que la prueba tiene asociado el tipo {string}")
    public void verifico_que_la_prueba_tiene_asociado_el_tipo(String string) {
        response = client.target(locationPrueba)
                .request(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(200, response.getStatus());
        PruebaDTO pruebaDTO = response.readEntity(PruebaDTO.class);
        assertEquals(200, response.getStatus());
        assertEquals(idTipoPrueba,pruebaDTO.idTipoPrueba());
      
        
    }

    @And("verifico que la prueba tiene asociada la clave {string}")
    public void verifico_que_la_prueba_tiene_asociada_la_clave(String string) {
           response = client.target(locationPruebaClave)
                .request(MediaType.APPLICATION_JSON)
                .get();
           PruebaClaveDTO claveDTO=response.readEntity(PruebaClaveDTO.class);
           assertEquals(idPruebaClave, claveDTO.idPruebaClave());
           assertEquals(200, response.getStatus());
           
    }

}
