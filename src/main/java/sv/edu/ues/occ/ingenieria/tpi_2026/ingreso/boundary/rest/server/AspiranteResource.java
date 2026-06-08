package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.FindRangeDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.EntityNotFoundInRepositoryExcpetion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.ExistentEntityException;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AspiranteDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AspiranteIdentificacionDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AspiranteOpcionDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.TipoIdentificacionDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteIdentificacion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteIdentificacionPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteOpcion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoIdentificacion;

/**
 *
 * @author usermein
 */
@Path("aspirante")
public class AspiranteResource {

    @Inject
    AspiranteDAOImp aspiranteDI;

    @Inject
    AspiranteIdentificacionDAOImp aspiranteIdentificacionDI;

    @Inject
    TipoIdentificacionDAOImp tipoIdentificacionDI;

    @Inject
    AspiranteOpcionDAOImp aspiranteOpcionDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Aspirante entity, @Context UriInfo uriInfo) {

        Aspirante found = aspiranteDI.findByEmail(entity.getCorreo());

        if (found == null) {
            aspiranteDI.create(entity);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(String.valueOf(entity.getIdAspirante().toString()));
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        }

        return Response.status(Response.Status.CONFLICT).build();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") @Min(1) Long id) {

        Aspirante found = aspiranteDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @GET
    @Path("/email/disponible")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEmail(@QueryParam("email") @NotBlank String email) {

        Aspirante found = aspiranteDI.findByEmail(email);
        if (found == null) {
            return Response.noContent().build();
        }

        return Response.status(Response.Status.CONFLICT).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam FindRangeDTO params) {

        List<Aspirante> resultList = aspiranteDI.findByRange(params.getOffset(), params.getLimit());
        return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") @Min(1L) Long id, @Valid Aspirante entity) {

        Aspirante found = aspiranteDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        entity.setIdAspirante(id);
        aspiranteDI.update(entity);
        return Response.noContent().build();

    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") @Min(1L) Long id) {

        Aspirante found = aspiranteDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        aspiranteDI.delete(found);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idAspirante}/identificacion/{idIdentificacion}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAspiranteIdentificacion(
            @PathParam("idAspirante") @Min(1L) Long idAspirante,
            @PathParam("idIdentificacion") @Min(1) Integer idIdentificacion,
            @Valid AspiranteIdentificacion entity,
            @Context UriInfo uriInfo
    ) {

        Aspirante aspiranteFound = aspiranteDI.findById(idAspirante);
        if (aspiranteFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idAspirante);
        }

        TipoIdentificacion tipoIdentificacionFound = tipoIdentificacionDI.findById(idIdentificacion);
        if (tipoIdentificacionFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idIdentificacion);
        }

        if (aspiranteIdentificacionDI.findById(new AspiranteIdentificacionPK(idAspirante, idIdentificacion)) != null) {
            throw new ExistentEntityException("idAspirante: " + idAspirante + " idIdentificacion: " + idIdentificacion);
        }
        entity.setAspiranteIdentificacionPK(new AspiranteIdentificacionPK(idAspirante, idIdentificacion));
        aspiranteIdentificacionDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();

    }

    @GET
    @Path("/{idAspirante}/identificacion/{idIdentificacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findIdentificacion(
            @PathParam("idAspirante") @Min(1L) Long idAspirante,
            @PathParam("idIdentificacion") @Min(1) Integer idIdentificacion
    ) {

        AspiranteIdentificacion found = aspiranteIdentificacionDI.findById(new AspiranteIdentificacionPK(idAspirante, idIdentificacion));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Aspirante: " + idAspirante + " TipoIdentificacion: " + idIdentificacion);
        }
        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idAspirante}/identificacion/{idIdentificacion}")
    public Response deleteIdentificacion(
            @PathParam("idAspirante") @Min(1L) Long idAspirante,
            @PathParam("idIdentificacion") @Min(1) Integer idIdentificacion
    ) {

        AspiranteIdentificacion found = aspiranteIdentificacionDI.findById(new AspiranteIdentificacionPK(idAspirante, idIdentificacion));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idAspirante);
        }

        aspiranteIdentificacionDI.delete(found);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idAspirante}/opcion")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAspiranteOpcion(
            @PathParam("idAspirante") @Min(1L) Long idAspirante,
            @Valid AspiranteOpcion entity,
            @Context UriInfo uriInfo
    ) {

        Aspirante found = aspiranteDI.findById(idAspirante);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idAspirante);
        }

        entity.setIdAspirante(found);
        aspiranteOpcionDI.create(entity);

        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        uriBuilder
                .path("aspirante")
                .path(String.valueOf(entity.getIdAspirante()))
                .path("opcion")
                .path(String.valueOf(entity.getIdAspiranteOpcion()));
        return Response.created(uriBuilder.build()).build();

    }

    @GET
    @Path("/{idAspirante}/opcion/{idAspiranteOpcion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAspiranteOpcion(
            @PathParam("idAspirante") @Min(1L) Long idAspirante,
            @PathParam("idAspiranteOpcion") @Min(1) Long idAspiranteOpcion
    ) {

        Aspirante aspiranteFound = aspiranteDI.findById(idAspirante);
        if (aspiranteFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idAspirante);
        }

        AspiranteOpcion aspiranteOpcionFound = aspiranteOpcionDI.findById(idAspiranteOpcion);
        if (aspiranteOpcionFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idAspiranteOpcion);
        }

        return Response.ok(aspiranteOpcionFound, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idAspirante}/opcion/{idAspiranteOpcion}")
    public Response deleteAspiranteOpcion(
            @PathParam("idAspirante") @Min(1L) Long idAspirante,
            @PathParam("idAspiranteOpcion") @Min(1) Long idAspiranteOpcion
    ) {

        Aspirante aspiranteFound = aspiranteDI.findById(idAspirante);
        if (aspiranteFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idAspirante);
        }

        AspiranteOpcion aspiranteOpcionFound = aspiranteOpcionDI.findById(idAspiranteOpcion);
        if (aspiranteOpcionFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idAspiranteOpcion);
        }

        aspiranteOpcionDI.delete(aspiranteOpcionFound);
        return Response.noContent().build();

    }

}
