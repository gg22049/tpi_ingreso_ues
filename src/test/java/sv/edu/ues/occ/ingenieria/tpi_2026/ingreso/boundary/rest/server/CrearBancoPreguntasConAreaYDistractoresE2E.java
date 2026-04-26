/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 *
 * @author cesar
 */
public class CrearBancoPreguntasConAreaYDistractoresE2E extends STAbstract{
@Given("crear un tipo de prueba {string} {string}:")
   @Given("se tiene un servidor corriendo con la aplicación desplegada")
    public void servidor_corriendo() {
        // Aquí podrías inicializar cliente HTTP o validar que el server responde
    }

    @When("creo un area de conocimiento llamada {string}")
    public void creo_area_conocimiento(String nombre) {
        // Llamar POST /areaConocimiento
        // guardar ID para usar después
    }

    @And("creo una pregunta con el texto {string}")
    public void creo_pregunta(String texto) {
        // POST /pregunta
    }

    @And("asocio la pregunta al area de conocimiento {string}")
    public void asocio_pregunta_area(String nombreArea) {
        // POST relación pregunta-area
    }

    @And("creo el distractor {int} como opcion incorrecta {string}")
    public void creo_distractor_incorrecto(Integer valor, String estado) {
        boolean esCorrecto = Boolean.parseBoolean(estado);
        // POST distractor incorrecto
    }

    @And("creo el distractor {int} como opcion correcta {string}")
    public void creo_distractor_correcto(Integer valor, String estado) {
        boolean esCorrecto = Boolean.parseBoolean(estado);
        // POST distractor correcto
    }

    @And("asocio los distractores a la pregunta")
    public void asocio_distractores() {
        // POST relación pregunta-distractores
    }

    @Then("puedo consultar la pregunta recien creada")
    public void consultar_pregunta() {
        // GET /pregunta/{id}
        // assertNotNull(response);
    }

    @And("verifico que la pregunta pertenece al area {string}")
    public void verificar_area(String nombreArea) {
        // assertEquals(nombreArea, respuesta.getArea())
    }

    @And("verifico que la pregunta tiene el distractor {int}")
    public void verificar_distractor(Integer valor) {
        // assertTrue(listaDistractores.contains(valor))
    }

    @And("verifico que el distractor {int} esta marcado como correcto")
    public void verificar_distractor_correcto(Integer valor) {
        // buscar distractor y validar flag correcto == true
    }
}
