/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.BeanParam;
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
import java.net.URI;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.TipoPruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.FindRangeDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.EntityNotFoundInRepositoryExcpetion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
@Path("tipo-prueba")
public class TipoPruebaResource {

    @Inject
    TipoPruebaDAOImp tipoPruebaDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid TipoPrueba entity, @Context UriInfo uriInfo) {

        tipoPruebaDI.create(entity);
        URI uriCreada = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(entity.getIdTipoPrueba()))
                .build();

        return Response.created(uriCreada).build();

    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") @Min(1) Integer id) {

        TipoPrueba found = tipoPruebaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        tipoPruebaDI.delete(found);
        return Response.noContent().build();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("id") @Min(1) Integer id) {

        TipoPrueba found = tipoPruebaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@Valid @BeanParam FindRangeDTO params) {

        List<TipoPrueba> resultList = tipoPruebaDI.findByRange(params.getOffset(), params.getLimit());

        return Response
                .ok(resultList)
                .header(HeaderName.TOTAL_RECORDS.toString(), resultList.size())
                .type(MediaType.APPLICATION_JSON)
                .build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("id") @Min(1) Integer id,
            @Valid TipoPrueba entity
    ) {

        TipoPrueba found = tipoPruebaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        entity.setIdTipoPrueba(id);
        tipoPruebaDI.update(entity);
        return Response.noContent().build();

    }
}
