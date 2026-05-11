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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.DistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.FindRangeDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.EntityNotFoundInRepositoryExcpetion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.DistractorAreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimientoPK;

/**
 *
 * @author usermein
 */
@Path("distractor")
public class DistractorResource {

    @Inject
    DistractorDAOImp distractorDI;

    @Inject
    AreaConocimientoDAOImp areaDI;

    @Inject
    DistractorAreaConocimientoDAOImp distractorAreaDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(
            @Valid Distractor entity,
            @Context UriInfo uriInfo
    ) {

        distractorDI.create(entity);
        URI uriCreada = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(entity.getIdDistractor()))
                .build();

        return Response
                .created(uriCreada)
                .type(MediaType.APPLICATION_JSON)
                .build();

    }

    @DELETE
    @Path("/{idDistractor}")
    public Response delete(
            @PathParam("idDistractor")
            @Min(1L) Long id
    ) {

        Distractor found = distractorDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }
        distractorDI.delete(found);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(
            @PathParam("id") @Min(1L) Long id
    ) {

        Distractor found = distractorDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }
        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@Valid @BeanParam FindRangeDTO params) {

        List<Distractor> resultList = distractorDI.findByRange(params.getOffset(), params.getLimit());
        return Response
                .ok(resultList)
                .header(HeaderName.TOTAL_RECORDS.toString(), resultList.size())
                .type(MediaType.APPLICATION_JSON)
                .build();

    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("id") @Min(1L) Long id,
            Distractor entity
    ) {

        Distractor found = distractorDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        entity.setIdDistractor(id);
        distractorDI.update(entity);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idDistractor}/area/{idArea}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDistractorAreaConocimiento(
            @PathParam("idDistractor") @Min(1L) Long idDistractor,
            @PathParam("idArea") @Min(1) Integer idArea,
            @Valid DistractorAreaConocimiento entity,
            @Context UriInfo uriInfo
    ) {

        Distractor distractorfound = distractorDI.findById(idDistractor);
        if (distractorfound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idDistractor);
        }

        AreaConocimiento areaFound = areaDI.findById(idArea);
        if (areaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(areaFound);
        }

        entity.setDistractorAreaConocimientoPK(new DistractorAreaConocimientoPK(idDistractor, idArea));
        distractorAreaDI.create(entity);
        return Response
                .created(uriInfo.getAbsolutePath())
                .type(MediaType.APPLICATION_JSON)
                .build();

    }

    @GET
    @Path("/{idDistractor}/area/{idArea}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findDistractorAreaConocimiento(
            @PathParam("idDistractor") @Min(1L) Long idDistractor,
            @PathParam("idArea") @Min(1) Integer idArea
    ) {

        DistractorAreaConocimiento found = distractorAreaDI.findById(new DistractorAreaConocimientoPK(idDistractor, idArea));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Distractor:" + idDistractor + " Area:" + idArea);
        }

        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idDistractor}/area/{idArea}")
    public Response deleteDistractorAreaConocimiento(
            @PathParam("idDistractor") @Min(1L) Long idDistractor,
            @PathParam("idArea") @Min(1) Integer idArea
    ) {

        DistractorAreaConocimiento found = distractorAreaDI.findById(new DistractorAreaConocimientoPK(idDistractor, idArea));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Distractor:" + idDistractor + " Area:" + idArea);
        }

        distractorAreaDI.delete(found);
        return Response.noContent().build();

    }

}
