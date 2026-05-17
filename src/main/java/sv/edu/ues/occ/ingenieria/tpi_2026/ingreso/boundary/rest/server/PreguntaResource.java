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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.FindRangeDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.EntityNotFoundInRepositoryExcpetion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.DistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaAreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaDistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimientoPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractorPK;

/**
 *
 * @author usermein
 */
@Path("pregunta")
public class PreguntaResource {

    @Inject
    PreguntaDAOImp preguntaDI;

    @Inject
    AreaConocimientoDAOImp areaDI;

    @Inject
    PreguntaAreaConocimientoDAOImp preguntaAreaDI;

    @Inject
    DistractorDAOImp distractorDI;

    @Inject
    PreguntaDistractorDAOImp preguntaDistractorDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Pregunta entity, @Context UriInfo uriInfo) {

        preguntaDI.create(entity);
        URI uriCreada = uriInfo
                .getAbsolutePathBuilder()
                .path(String.valueOf(entity.getIdPregunta()))
                .build();
        return Response.created(uriCreada).build();

    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") @Min(1L) Long id) {

        Pregunta found = preguntaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        preguntaDI.delete(found);
        return Response.noContent().build();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") @Min(1L) Long id) {

        Pregunta found = preguntaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@Valid @BeanParam FindRangeDTO params) {

        List<Pregunta> resultList = preguntaDI.findByRange(params.getOffset(), params.getLimit());
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
            @Valid Pregunta entity
    ) {

        Pregunta found = preguntaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }
        entity.setIdPregunta(id);
        preguntaDI.update(entity);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idPregunta}/area/{idArea}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPreguntaAreaConocimiento(
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @PathParam("idArea") @Min(1) Integer idArea,
            @Valid PreguntaAreaConocimiento entity,
            @Context UriInfo uriInfo
    ) {

        Pregunta preguntaFound = preguntaDI.findById(idPregunta);
        if (preguntaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPregunta);
        }

        AreaConocimiento areaFound = areaDI.findById(idArea);
        if (areaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idArea);
        }

        entity.setPreguntaAreaConocimientoPK(new PreguntaAreaConocimientoPK(idPregunta, idArea));
        preguntaAreaDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();
    }

    @GET
    @Path("/{idPregunta}/area/{idArea}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPreguntaAreaConocimiento(
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @PathParam("idArea") @Min(1) Integer idArea,
            @Valid PreguntaAreaConocimiento entity
    ) {

        PreguntaAreaConocimiento found = preguntaAreaDI.findById(new PreguntaAreaConocimientoPK(idPregunta, idArea));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Pregunta: " + idPregunta + " Area: " + idArea);
        }

        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idPregunta}/area/{idArea}")
    public Response deletePreguntaAreaConocimiento(
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @PathParam("idArea") @Min(1) Integer idArea
    ) {

        PreguntaAreaConocimiento found = preguntaAreaDI.findById(new PreguntaAreaConocimientoPK(idPregunta, idArea));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Pregunta: " + idPregunta + " Area: " + idArea);
        }

        preguntaAreaDI.delete(found);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idPregunta}/distractor/{idDistractor}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPreguntaDistractor(
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @PathParam("idDistractor") @Min(1L) Long idDistractor,
            @Valid PreguntaDistractor entity,
            @Context UriInfo uriInfo
    ) {

        Pregunta preguntaFound = preguntaDI.findById(idPregunta);
        if (preguntaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPregunta);
        }

        Distractor distractorFound = distractorDI.findById(idDistractor);
        if (distractorFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idDistractor);
        }

        entity.setPreguntaDistractorPK(new PreguntaDistractorPK(idPregunta, idDistractor));
        preguntaDistractorDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();
    }

    @GET
    @Path("/{idPregunta}/distractor/{idDistractor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPreguntaDistractor(
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @PathParam("idDistractor") @Min(1L) Long idDistractor
    ) {

        PreguntaDistractor found = preguntaDistractorDI.findById(new PreguntaDistractorPK(idPregunta, idDistractor));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Pregunta: " + idPregunta + " Distractor: " + idDistractor);
        }

        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idPregunta}/distractor/{idDistractor}")
    public Response deletePreguntaDistractor(
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @PathParam("idDistractor") @Min(1L) Long idDistractor
    ) {

        PreguntaDistractor found = preguntaDistractorDI.findById(new PreguntaDistractorPK(idPregunta, idDistractor));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Pregunta: " + idPregunta + " Area: " + idDistractor);
        }

        preguntaDistractorDI.delete(found);
        return Response.noContent().build();

    }
}
