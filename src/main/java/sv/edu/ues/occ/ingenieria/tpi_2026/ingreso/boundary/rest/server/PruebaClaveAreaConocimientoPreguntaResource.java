package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
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
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.DomainException;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoPreguntaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoPreguntaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaPK;

/**
 *
 * @author usermein
 */
@Path("prueba-clave-area-pregunta")
public class PruebaClaveAreaConocimientoPreguntaResource {

    @Inject
    PruebaClaveAreaConocimientoPreguntaDAOImp PruebaClaveAreaConocimientoPreguntaDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid PruebaClaveAreaConocimientoPreguntaDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaClaveAreaConocimientoPregunta newPruebaClaveAreaConocimientoPregunta = PruebaClaveAreaConocimientoPreguntaDI.toEntity(dto);
            PruebaClaveAreaConocimientoPreguntaDI.create(newPruebaClaveAreaConocimientoPregunta);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder
                    .path(String.valueOf(dto.idPruebaClave()))
                    .path(String.valueOf(dto.idAreaConocimiento()))
                    .path(String.valueOf(dto.idPregunta()))
                    .build();
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Path("/{idPruebaClave:\\d+}/{idAreaConocimiento:\\d+}/{idPregunta:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("idPruebaClave") @Min(1L) @Max(Long.MAX_VALUE) long idPruebaClave,
            @PathParam("idAreaConocimiento") @Min(1) @Max(Integer.MAX_VALUE) int idAreaConocimiento,
            @PathParam("idPregunta") @Min(1L) @Max(Long.MAX_VALUE) long idPregunta,
            @Context UriInfo uriInfo) {
        try {
            PruebaClaveAreaConocimientoPregunta found = PruebaClaveAreaConocimientoPreguntaDI.findById(
                    new PruebaClaveAreaConocimientoPreguntaPK(
                            idPruebaClave,
                            idAreaConocimiento,
                            idPregunta
                    ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + idPruebaClave + ", IdAreaConocimiento: " + idAreaConocimiento
                                + ", IdPregunta: " + idPregunta,
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PruebaClaveAreaConocimientoPreguntaDI.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Path("/{idPruebaClave:\\d+}/{idAreaConocimiento:\\d+}/{idPregunta:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @PathParam("idPruebaClave") @Min(1L) @Max(Long.MAX_VALUE) long idPruebaClave,
            @PathParam("idAreaConocimiento") @Min(1) @Max(Integer.MAX_VALUE) int idAreaConocimiento,
            @PathParam("idPregunta") @Min(1L) @Max(Long.MAX_VALUE) long idPregunta,
            @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaClaveAreaConocimientoPregunta found = PruebaClaveAreaConocimientoPreguntaDI.findById(
                    new PruebaClaveAreaConocimientoPreguntaPK(
                            idPruebaClave,
                            idAreaConocimiento,
                            idPregunta
                    ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + idPruebaClave + ", IdAreaConocimiento: " + idAreaConocimiento
                                + ", IdPregunta: " + idPregunta,
                                uriInfo.getAbsolutePath().toString(),
                                null
                        ))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            return Response.ok(PruebaClaveAreaConocimientoPreguntaDI.toDto(found), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<PruebaClaveAreaConocimientoPreguntaDTO> resultList = PruebaClaveAreaConocimientoPreguntaDI.findByRange(params.getOffset(), params.getLimit())
                    .stream()
                    .map(r -> PruebaClaveAreaConocimientoPreguntaDI.toDto(r)).toList();
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @Valid PruebaClaveAreaConocimientoPreguntaDTO dto,
            @Context UriInfo uriInfo) {
        try {
            PruebaClaveAreaConocimientoPregunta found = PruebaClaveAreaConocimientoPreguntaDI.findById(
                    new PruebaClaveAreaConocimientoPreguntaPK(
                            dto.idPruebaClave(),
                            dto.idAreaConocimiento(),
                            dto.idPregunta()
                    ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + dto.idPruebaClave() + ", IdAreaConocimiento: " + dto.idAreaConocimiento()
                                + ", IdPregunta: " + dto.idPregunta(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PruebaClaveAreaConocimientoPregunta entity = PruebaClaveAreaConocimientoPreguntaDI.toEntity(dto);
            PruebaClaveAreaConocimientoPreguntaDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

}
