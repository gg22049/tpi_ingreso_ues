/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 *
 * @author usermein
 */
public class AsociarAreasPreguntasYDistractoresAClaveE2E extends STAbstract {

    @Given("crear un tipo de prueba {string} {string}:")
    public void crear_un_tipo_de_prueba(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("crear una prueba:")
    public void crear_una_prueba(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    @Given("existe una clave llamada {string} asociada a la prueba")
    public void existe_una_clave_llamada_asociada_a_la_prueba(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("existe un area de conocimiento llamada {string}")
    public void existe_un_area_de_conocimiento_llamada(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("existe la pregunta \"¿En que año se firmaron los acuerdos de paz? \"asociada al area sociales")
    public void existe_la_pregunta_en_que_año_se_firmaron_los_acuerdos_de_paz_asociada_al_area_sociales() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("existe el distractor {string} como opcion incorrecta")
    public void existe_el_distractor_como_opcion_incorrecta(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("existe el distractor {string} como opcion correcta")
    public void existe_el_distractor_como_opcion_correcta(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("asocio el area {string} a la clave {string} con cantidad {int} y porcentaje {int}")
    public void asocio_el_area_a_la_clave_con_cantidad_y_porcentaje(String string, String string2, Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("asocio la pregunta a la clave y al area {string} con porcentaje {int}")
    public void asocio_la_pregunta_a_la_clave_y_al_area_con_porcentaje(String string, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("asocio los distractores de la pregunta a la clave")
    public void asocio_los_distractores_de_la_pregunta_a_la_clave() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("puedo consultar la clave de la prueba")
    public void puedo_consultar_la_clave_de_la_prueba() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("verifico que la clave contiene el area {string}")
    public void verifico_que_la_clave_contiene_el_area(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("verifico que la clave contiene la pregunta asociada")
    public void verifico_que_la_clave_contiene_la_pregunta_asociada() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("verifico que la clave contiene los distractores de la pregunta")
    public void verifico_que_la_clave_contiene_los_distractores_de_la_pregunta() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
