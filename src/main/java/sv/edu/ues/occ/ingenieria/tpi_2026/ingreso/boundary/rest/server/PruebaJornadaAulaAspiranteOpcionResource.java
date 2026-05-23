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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AspiranteOpcionDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaAulaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaAulaAspiranteOpcionDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaAulaAspiranteOpcionExamenDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteOpcion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAula;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAulaPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamen;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamenPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionPK;

/**
 *
 * @author caesar
 */
@Path("prueba/{idPrueba}/jornada/{idJornada}/aula/{idAula}/opcion/{idAspiranteOpcion}")
public class PruebaJornadaAulaAspiranteOpcionResource {

    @Inject
    PruebaDAOImp pruebaDI;

    @Inject
    JornadaAulaDAOImp jornadaAulaDI;

    @Inject
    AspiranteOpcionDAOImp aspiranteOpcionDI;

    @Inject
    PruebaJornadaAulaAspiranteOpcionDAOImp pruebaJornadaAulaAspiranteOpcionDI;

    @Inject
    PruebaJornadaAulaAspiranteOpcionExamenDAOImp pruebaJornadaAulaAspiranteOpcionExamenDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion,
            @Valid PruebaJornadaAulaAspiranteOpcion entity,
            @Context UriInfo uriInfo
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        JornadaAula jornadaAulaFound = jornadaAulaDI.findById(new JornadaAulaPK(idJornada, idAula));
        if (jornadaAulaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Jornada: " + idJornada + " Aula: " + idAula);
        }

        AspiranteOpcion aspiranteOpcionFound = aspiranteOpcionDI.findById(idAspiranteOpcion);
        if (aspiranteOpcionFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("AspiranteOpcion: " + idAspiranteOpcion);
        }

        entity.setPruebaJornadaAulaAspiranteOpcionPK(new PruebaJornadaAulaAspiranteOpcionPK(idPrueba, idJornada, idAula, idAspiranteOpcion));
        pruebaJornadaAulaAspiranteOpcionDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaJornadaAulaAspiranteOpcion pruebaJornadaAulaAspiranteOpcionFound = pruebaJornadaAulaAspiranteOpcionDI.findById(
                new PruebaJornadaAulaAspiranteOpcionPK(idPrueba, idJornada, idAula, idAspiranteOpcion)
        );
        if (pruebaJornadaAulaAspiranteOpcionFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Jornada: " + idJornada
                    + " Aula: " + idAula
                    + " Opcion: " + idAspiranteOpcion);
        }

        return Response.ok(pruebaJornadaAulaAspiranteOpcionFound, MediaType.APPLICATION_JSON).build();

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion,
            @Valid PruebaJornadaAulaAspiranteOpcion entity
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaJornadaAulaAspiranteOpcionPK key = new PruebaJornadaAulaAspiranteOpcionPK(
                idPrueba,
                idJornada,
                idAula,
                idAspiranteOpcion
        );
        PruebaJornadaAulaAspiranteOpcion pruebaJornadaAulaAspiranteOpcionFound = pruebaJornadaAulaAspiranteOpcionDI.findById(key);
        if (pruebaJornadaAulaAspiranteOpcionFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Jornada: " + idJornada
                    + " Aula: " + idAula
                    + " Opcion: " + idAspiranteOpcion);
        }

        entity.setPruebaJornadaAulaAspiranteOpcionPK(key);
        pruebaJornadaAulaAspiranteOpcionDI.update(entity);
        return Response.noContent().build();

    }

    @DELETE
    public Response delete(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaJornadaAulaAspiranteOpcion pruebaJornadaAulaAspiranteOpcionFound = pruebaJornadaAulaAspiranteOpcionDI.findById(
                new PruebaJornadaAulaAspiranteOpcionPK(idPrueba, idJornada, idAula, idAspiranteOpcion)
        );
        if (pruebaJornadaAulaAspiranteOpcionFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Jornada: " + idJornada
                    + " Aula: " + idAula
                    + " Opcion: " + idAspiranteOpcion);
        }
        pruebaJornadaAulaAspiranteOpcionDI.delete(pruebaJornadaAulaAspiranteOpcionFound);
        return Response.noContent().build();

    }

    @POST
    @Path("examen")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createExamen(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion,
            @Valid PruebaJornadaAulaAspiranteOpcionExamen entity,
            @Context UriInfo uriInfo
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        JornadaAula jornadaAulaFound = jornadaAulaDI.findById(new JornadaAulaPK(idJornada, idAula));
        if (jornadaAulaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Jornada: " + idJornada + " Aula: " + idAula);
        }

        AspiranteOpcion aspiranteOpcionFound = aspiranteOpcionDI.findById(idAspiranteOpcion);
        if (aspiranteOpcionFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("AspiranteOpcion: " + idAspiranteOpcion);
        }

        entity.setPruebaJornadaAulaAspiranteOpcionExamenPK(new PruebaJornadaAulaAspiranteOpcionExamenPK(idPrueba, idJornada, idAula, idAspiranteOpcion));
        pruebaJornadaAulaAspiranteOpcionExamenDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();

    }

    @GET
    @Path("examen")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdExamen(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaJornadaAulaAspiranteOpcionExamen pruebaJornadaAulaAspiranteOpcionExamenFound = pruebaJornadaAulaAspiranteOpcionExamenDI.findById(
                new PruebaJornadaAulaAspiranteOpcionExamenPK(idPrueba, idJornada, idAula, idAspiranteOpcion)
        );
        if (pruebaJornadaAulaAspiranteOpcionExamenFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Jornada: " + idJornada
                    + " Aula: " + idAula
                    + " Opcion: " + idAspiranteOpcion);
        }

        return Response.ok(pruebaJornadaAulaAspiranteOpcionExamenFound, MediaType.APPLICATION_JSON).build();

    }

    @PUT
    @Path("examen")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateExamen(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion,
            @Valid PruebaJornadaAulaAspiranteOpcionExamen entity
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }
        PruebaJornadaAulaAspiranteOpcionExamenPK key = new PruebaJornadaAulaAspiranteOpcionExamenPK(
                idPrueba,
                idJornada,
                idAula,
                idAspiranteOpcion
        );

        PruebaJornadaAulaAspiranteOpcionExamen pruebaJornadaAulaAspiranteOpcionExamenFound = pruebaJornadaAulaAspiranteOpcionExamenDI.findById(key);

        if (pruebaJornadaAulaAspiranteOpcionExamenFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Jornada: " + idJornada
                    + " Aula: " + idAula
                    + " Opcion: " + idAspiranteOpcion);
        }

        entity.setPruebaJornadaAulaAspiranteOpcionExamenPK(key);
        pruebaJornadaAulaAspiranteOpcionExamenDI.update(entity);
        return Response.noContent().build();

    }

    @DELETE
    @Path("examen")
    public Response deleteExamen(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaJornadaAulaAspiranteOpcionExamen pruebaJornadaAulaAspiranteOpcionExamenFound = pruebaJornadaAulaAspiranteOpcionExamenDI.findById(
                new PruebaJornadaAulaAspiranteOpcionExamenPK(idPrueba, idJornada, idAula, idAspiranteOpcion)
        );
        if (pruebaJornadaAulaAspiranteOpcionExamenFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Jornada: " + idJornada
                    + " Aula: " + idAula
                    + " Opcion: " + idAspiranteOpcion);
        }
        pruebaJornadaAulaAspiranteOpcionExamenDI.delete(pruebaJornadaAulaAspiranteOpcionExamenFound);
        return Response.noContent().build();

    }

}
