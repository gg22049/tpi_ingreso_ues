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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.FindRangeDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.EntityNotFoundInRepositoryExcpetion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaAulaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAula;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAulaPK;

/**
 *
 * @author usermein
 */
@Path("jornada")
public class JornadaResource implements Serializable {

    @Inject
    JornadaDAOImp jornadaDI;

    @Inject
    JornadaAulaDAOImp jornadaAulaDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Jornada entity, @Context UriInfo uriInfo) {

        jornadaDI.create(entity);
        URI uriCreada = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(entity.getIdJornada()))
                .build();
        return Response.created(uriCreada).type(MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") @Min(1L) Long id) {

        Jornada jornada = jornadaDI.findById(id);
        if (jornada == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }
        jornadaDI.delete(jornada);
        return Response.noContent().build();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") @Min(1L) Long id) {

        Jornada found = jornadaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }
        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@Valid @BeanParam FindRangeDTO params) {
        List<Jornada> resultList = jornadaDI.findByRange(params.getOffset(), params.getLimit());
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
            @PathParam("id") @Min(1L) Long id,
            @Valid Jornada entity) {

        Jornada found = jornadaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }
        entity.setIdJornada(id);
        jornadaDI.update(entity);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idJornada}/aula/{idAula}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createJornadaAula(
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") @NotBlank String idAula,
            @Valid JornadaAula entity,
            @Context UriInfo uriInfo
    ) {

        Jornada found = jornadaDI.findById(idJornada);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idJornada);
        }

        entity.setJornadaAulaPK(new JornadaAulaPK(idJornada, idAula));
        jornadaAulaDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).type(MediaType.APPLICATION_JSON).build();

    }

    @GET
    @Path("/{idJornada}/aula/{idAula}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findJornadaAula(
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") @NotBlank String idAula
    ) {

        JornadaAula found = jornadaAulaDI.findById(new JornadaAulaPK(idJornada, idAula));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idJornada);
        }

        return Response.ok(found).type(MediaType.APPLICATION_JSON).build();

    }

    @GET
    @Path("/{idJornada}/aula/{idAula}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteJornadaAula(
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") @NotBlank String idAula
    ) {

        JornadaAula found = jornadaAulaDI.findById(new JornadaAulaPK(idJornada, idAula));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idJornada);
        }

        jornadaAulaDI.delete(found);
        return Response.noContent().build();

    }

}
