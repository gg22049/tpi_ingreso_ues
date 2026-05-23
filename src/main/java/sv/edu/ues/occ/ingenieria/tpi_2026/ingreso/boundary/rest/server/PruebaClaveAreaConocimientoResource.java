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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClave;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPK;

/**
 *
 * @author caesar
 */
@Path("prueba/{idPrueba}/clave/{idClave}/area/{idArea}")
public class PruebaClaveAreaConocimientoResource {

    @Inject
    PruebaDAOImp pruebaDI;

    @Inject
    PruebaClaveDAOImp pruebaClaveDI;

    @Inject
    AreaConocimientoDAOImp areaDI;

    @Inject
    PruebaClaveAreaConocimientoDAOImp pruebaClaveAreaConocimientoDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @Valid PruebaClaveAreaConocimiento entity,
            @Context UriInfo uriInfo
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClave pruebaClavefound = pruebaClaveDI.findById(idClave);
        if (pruebaClavefound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Clave: " + idClave);
        }

        AreaConocimiento areaFound = areaDI.findById(idArea);
        if (pruebaClavefound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Area: " + idArea);
        }

        entity.setPruebaClaveAreaConocimientoPK(new PruebaClaveAreaConocimientoPK(idClave, idArea));
        pruebaClaveAreaConocimientoDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea
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

        return Response.ok(pruebaClaveAreaConocimientoFound, MediaType.APPLICATION_JSON).build();

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @Valid PruebaClaveAreaConocimiento entity
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }
        PruebaClaveAreaConocimientoPK key = new PruebaClaveAreaConocimientoPK(idClave, idArea);
        PruebaClaveAreaConocimiento pruebaClaveAreaConocimientoFound = pruebaClaveAreaConocimientoDI.findById(key);
        if (pruebaClaveAreaConocimientoFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
            );
        }

        entity.setPruebaClaveAreaConocimientoPK(key);
        pruebaClaveAreaConocimientoDI.update(entity);
        return Response.noContent().build();
    }

    @DELETE
    public Response delete(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea
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

        pruebaClaveAreaConocimientoDI.delete(pruebaClaveAreaConocimientoFound);
        return Response.noContent().build();

    }

}
