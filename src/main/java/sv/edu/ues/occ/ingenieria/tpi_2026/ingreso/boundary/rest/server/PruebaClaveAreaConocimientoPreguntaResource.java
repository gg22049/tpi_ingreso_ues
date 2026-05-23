/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.EntityNotFoundInRepositoryExcpetion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoPreguntaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaPK;

/**
 *
 * @author caesar
 */
@Path("prueba/{idPrueba}/clave/{idClave}/area/{idArea}/pregunta/{idPregunta}")
public class PruebaClaveAreaConocimientoPreguntaResource {

    @Inject
    PruebaDAOImp pruebaDI;

    @Inject
    PreguntaDAOImp preguntaDI;

    @Inject
    PruebaClaveAreaConocimientoDAOImp pruebaClaveAreaConocimientoDI;

    @Inject
    PruebaClaveAreaConocimientoPreguntaDAOImp pruebaClaveAreaConocimientoPreguntaDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @Valid PruebaClaveAreaConocimientoPregunta entity,
            @Context UriInfo uriInfo
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimiento pruebaClaveAreaConocimientoFound = pruebaClaveAreaConocimientoDI.findById(new PruebaClaveAreaConocimientoPK(idClave, idArea));
        if (pruebaClaveAreaConocimientoFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
            );
        }

        Pregunta preguntaFound = preguntaDI.findById(idPregunta);
        if (preguntaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Pregunta: " + idPregunta);
        }

        entity.setPruebaClaveAreaConocimientoPreguntaPK(new PruebaClaveAreaConocimientoPreguntaPK(idClave, idArea, idPregunta));
        pruebaClaveAreaConocimientoPreguntaDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimientoPregunta pruebaClaveAreaConocimientoPreguntaFound = pruebaClaveAreaConocimientoPreguntaDI.findById(new PruebaClaveAreaConocimientoPreguntaPK(idClave, idArea, idPregunta));
        if (pruebaClaveAreaConocimientoPreguntaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
                    + " Pregunta: " + idPregunta
            );
        }

        return Response.ok(pruebaClaveAreaConocimientoPreguntaFound, MediaType.APPLICATION_JSON).build();

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @Valid PruebaClaveAreaConocimientoPregunta entity
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimientoPreguntaPK key = new PruebaClaveAreaConocimientoPreguntaPK(idClave, idArea, idPregunta);
        PruebaClaveAreaConocimientoPregunta pruebaClaveAreaConocimientoPreguntaFound = pruebaClaveAreaConocimientoPreguntaDI.findById(key);
        if (pruebaClaveAreaConocimientoPreguntaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
                    + " Pregunta: " + idPregunta
            );
        }

        entity.setPruebaClaveAreaConocimientoPreguntaPK(key);
        pruebaClaveAreaConocimientoPreguntaDI.update(entity);
        return Response.noContent().build();

    }

    @DELETE
    public Response delete(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimientoPregunta pruebaClaveAreaConocimientoPreguntaFound = pruebaClaveAreaConocimientoPreguntaDI.findById(new PruebaClaveAreaConocimientoPreguntaPK(idClave, idArea, idPregunta));
        if (pruebaClaveAreaConocimientoPreguntaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
                    + " Pregunta: " + idPregunta
            );
        }

        pruebaClaveAreaConocimientoPreguntaDI.delete(pruebaClaveAreaConocimientoPreguntaFound);
        return Response.noContent().build();

    }

}
