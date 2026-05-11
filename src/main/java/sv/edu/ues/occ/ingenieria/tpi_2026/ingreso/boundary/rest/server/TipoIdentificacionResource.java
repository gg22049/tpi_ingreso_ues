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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.TipoIdentificacionDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.FindRangeDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.EntityNotFoundInRepositoryExcpetion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoIdentificacion;

/**
 *
 * @author usermein
 */
@Path("tipo-identificacion")
public class TipoIdentificacionResource {

    @Inject
    TipoIdentificacionDAOImp tipoIdentificacionDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid TipoIdentificacion entity, @Context UriInfo uriInfo) {

        tipoIdentificacionDI.create(entity);
        URI uriCreada = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(entity.getIdTipoIdentificacion()))
                .build();
        return Response.created(uriCreada).build();

    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") @Min(1) Integer id) {

        TipoIdentificacion found = tipoIdentificacionDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }
        tipoIdentificacionDI.delete(found);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") @Min(1) Integer id) {

        TipoIdentificacion found = tipoIdentificacionDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@Valid @BeanParam FindRangeDTO params) {

        List<TipoIdentificacion> resultList = tipoIdentificacionDI.findByRange(params.getOffset(), params.getLimit());
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
            @Valid TipoIdentificacion entity
    ) {

        TipoIdentificacion found = tipoIdentificacionDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        entity.setIdTipoIdentificacion(id);
        tipoIdentificacionDI.update(entity);
        return Response.noContent().build();

    }
}
