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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.DistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoPreguntaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoPreguntaDistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractorPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaPK;

/**
 *
 * @author caesar
 */
@Path("prueba/{idPrueba}/clave/{idClave}/area/{idArea}/pregunta/{idPregunta}/distractor/{idDistractor}")
public class PruebaClaveAreaConocimientoPreguntaDistractorResource {

    @Inject
    PruebaDAOImp pruebaDI;

    @Inject
    DistractorDAOImp distractorDI;

    @Inject
    PruebaClaveAreaConocimientoPreguntaDAOImp pruebaClaveAreaConocimientoPreguntaDI;

    @Inject
    PruebaClaveAreaConocimientoPreguntaDistractorDAOImp pruebaClaveAreaConocimientoPreguntaDistractorDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @PathParam("idDistractor") @Min(1L) Long idDistractor,
            @Valid PruebaClaveAreaConocimientoPreguntaDistractor entity,
            @Context UriInfo uriInfo
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

        Distractor distractorFound = distractorDI.findById(idDistractor);
        if (distractorFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Distractor: " + idDistractor);
        }

        entity.setPruebaClaveAreaConocimientoPreguntaDistractorPK(
                new PruebaClaveAreaConocimientoPreguntaDistractorPK(idClave, idArea, idPregunta, idDistractor)
        );
        pruebaClaveAreaConocimientoPreguntaDistractorDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @PathParam("idDistractor") @Min(1L) Long idDistractor
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimientoPreguntaDistractor pruebaClaveAreaConocimientoPreguntaDistractorFound
                = pruebaClaveAreaConocimientoPreguntaDistractorDI
                        .findById(new PruebaClaveAreaConocimientoPreguntaDistractorPK(idClave, idArea, idPregunta, idDistractor));
        if (pruebaClaveAreaConocimientoPreguntaDistractorFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
                    + " Pregunta: " + idPregunta
                    + " Distractor: " + idDistractor
            );
        }

        return Response.ok(pruebaClaveAreaConocimientoPreguntaDistractorFound, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @PathParam("idDistractor") @Min(1L) Long idDistractor,
            @Valid PruebaClaveAreaConocimientoPreguntaDistractor entity
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimientoPreguntaDistractorPK key = new PruebaClaveAreaConocimientoPreguntaDistractorPK(
                idClave,
                idArea,
                idPregunta,
                idDistractor
        );
        PruebaClaveAreaConocimientoPreguntaDistractor pruebaClaveAreaConocimientoPreguntaDistractorFound
                = pruebaClaveAreaConocimientoPreguntaDistractorDI.findById(key);
        if (pruebaClaveAreaConocimientoPreguntaDistractorFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
                    + " Pregunta: " + idPregunta
                    + " Distractor: " + idDistractor
            );
        }

        entity.setPruebaClaveAreaConocimientoPreguntaDistractorPK(key);
        pruebaClaveAreaConocimientoPreguntaDistractorDI.update(entity);
        return Response.noContent().build();
    }

    @DELETE
    public Response delete(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @PathParam("idDistractor") @Min(1L) Long idDistractor
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimientoPreguntaDistractor pruebaClaveAreaConocimientoPreguntaDistractorFound
                = pruebaClaveAreaConocimientoPreguntaDistractorDI
                        .findById(new PruebaClaveAreaConocimientoPreguntaDistractorPK(idClave, idArea, idPregunta, idDistractor));
        if (pruebaClaveAreaConocimientoPreguntaDistractorFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
                    + " Pregunta: " + idPregunta
                    + " Distractor: " + idDistractor
            );
        }

        pruebaClaveAreaConocimientoPreguntaDistractorDI.delete(pruebaClaveAreaConocimientoPreguntaDistractorFound);
        return Response.noContent().build();

    }

}
