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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.FindRangeDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.EntityNotFoundInRepositoryExcpetion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.TipoPruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClave;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
@Path("prueba")
public class PruebaResource {

    @Inject
    PruebaDAOImp pruebaDI;

    @Inject
    TipoPruebaDAOImp tipoPruebaDI;

    @Inject
    JornadaDAOImp jornadaDI;

    @Inject
    PruebaJornadaDAOImp pruebaJornadaDI;

    @Inject
    PruebaClaveDAOImp pruebaClaveDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Prueba entity, @Context UriInfo uriInfo) {

        pruebaDI.create(entity);
        URI uriCreada = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(entity.getIdPrueba()))
                .build();
        return Response.created(uriCreada).build();

    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") @Min(1L) Long id) {

        Prueba found = pruebaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        pruebaDI.delete(found);
        return Response.noContent().build();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("id") @Min(1L) Long id) {

        Prueba found = pruebaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@Valid @BeanParam FindRangeDTO params) {

        List<Prueba> resultList = pruebaDI.findByRange(params.getOffset(), params.getLimit());
        return Response
                .ok(resultList)
                .header(HeaderName.TOTAL_RECORDS.toString(), resultList.size())
                .type(MediaType.APPLICATION_JSON)
                .build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") @Min(1L) Long id, @Valid Prueba entity) {

        Prueba found = pruebaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        entity.setIdPrueba(id);
        pruebaDI.update(entity);
        return Response.noContent().build();

    }

    @PUT
    @Path("/{idPrueba}/tipo/{idTipoPrueba}")
    public Response setTipoPrueba(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idTipoPrueba") @Min(1) Integer idTipoPrueba
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPrueba);
        }

        TipoPrueba tipoPruebaFound = tipoPruebaDI.findById(idTipoPrueba);
        if (tipoPruebaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(tipoPruebaFound);
        }

        pruebafound.setIdTipoPrueba(tipoPruebaFound);
        pruebaDI.update(pruebafound);
        return Response.noContent().build();

    }

    @DELETE
    @Path("/{idPrueba}/tipo")
    public Response unsetTipoPrueba(
            @PathParam("idPrueba") @Min(1L) Long idPrueba
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPrueba);
        }

        pruebafound.setIdTipoPrueba(null);
        pruebaDI.update(pruebafound);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idPrueba}/clave")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPruebaClave(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @Valid PruebaClave entity,
            @Context UriInfo uriInfo
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPrueba);
        }

        entity.setIdPrueba(pruebafound);
        pruebaClaveDI.create(entity);
        URI uriCreada = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(entity.getIdPruebaClave()))
                .build();
        return Response.created(uriCreada).build();

    }

    @GET
    @Path("/{idPrueba}/clave/{idPruebaClave}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPruebaClave(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idPruebaClave") @Min(1L) Long idPruebaClave
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPrueba);
        }

        PruebaClave pruebaClavefound = pruebaClaveDI.findById(idPruebaClave);
        if (pruebaClavefound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPruebaClave);
        }

        return Response.ok(pruebaClavefound, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idPrueba}/clave/{idPruebaClave}")
    public Response deletePruebaClave(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idPruebaClave") @Min(1L) Long idPruebaClave
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPrueba);
        }

        PruebaClave pruebaClavefound = pruebaClaveDI.findById(idPruebaClave);
        if (pruebaClavefound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPruebaClave);
        }

        pruebaClaveDI.delete(pruebaClavefound);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idPrueba}/jornada/{idJornada}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPruebaJornada(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @Valid PruebaJornada entity,
            @Context UriInfo uriInfo
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPrueba);
        }

        Jornada jornadaFound = jornadaDI.findById(idJornada);
        if (jornadaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idJornada);
        }

        entity.setPruebaJornadaPK(new PruebaJornadaPK(idPrueba, idJornada));
        pruebaJornadaDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();

    }

    @GET
    @Path("/{idPrueba}/jornada/{idJornada}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPruebaJornada(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada
    ) {

        PruebaJornada found = pruebaJornadaDI.findById(new PruebaJornadaPK(idPrueba, idJornada));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idJornada);
        }

        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idPrueba}/jornada/{idJornada}")
    public Response deletePruebaJornada(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada
    ) {

        PruebaJornada found = pruebaJornadaDI.findById(new PruebaJornadaPK(idPrueba, idJornada));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idJornada);
        }

        pruebaJornadaDI.delete(found);
        return Response.noContent().build();

    }

}
