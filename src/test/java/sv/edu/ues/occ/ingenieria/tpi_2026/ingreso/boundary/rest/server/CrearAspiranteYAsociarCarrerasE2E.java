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
 * @author cesar
 */
public class CrearAspiranteYAsociarCarrerasE2E extends STAbstract {

    @Given("se tiene un servidor corriendo con la aplicación desplegada")
    public void se_tiene_un_servidor_corriendo_con_la_aplicación_desplegada() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("creo un aspirante con nombres, apellidos, fecha de nacimiento y correo:")
    public void creo_un_aspirante_con_nombres_apellidos_fecha_de_nacimiento_y_correo(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    @When("registro una identificacion para el aspirante con tipo {string} y valor {string}")
    public void registro_una_identificacion_para_el_aspirante_con_tipo_y_valor(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("asocio al aspirante la opcion de carrera {string}")
    public void asocio_al_aspirante_la_opcion_de_carrera(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("verifico que el aspirante fue creado correctamente")
    public void verifico_que_el_aspirante_fue_creado_correctamente() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("verifico que la identificacion {string} pertenece al aspirante creado")
    public void verifico_que_la_identificacion_pertenece_al_aspirante_creado(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("verifico que el aspirante tiene asociada la opcion {string}")
    public void verifico_que_el_aspirante_tiene_asociada_la_opcion(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
