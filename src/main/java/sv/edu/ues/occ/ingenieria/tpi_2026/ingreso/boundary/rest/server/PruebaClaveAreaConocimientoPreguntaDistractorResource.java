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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoPreguntaDistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoPreguntaDistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractorPK;

/**
 *
 * @author usermein
 */
@Path("prueba-clave-area-pregunta-distractor")
public class PruebaClaveAreaConocimientoPreguntaDistractorResource {

    @Inject
    PruebaClaveAreaConocimientoPreguntaDistractorDAOImp PruebaClaveAreaConocimientoPreguntaDistractorDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid PruebaClaveAreaConocimientoPreguntaDistractorDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaClaveAreaConocimientoPreguntaDistractor newPruebaClaveAreaConocimientoPreguntaDistractor = PruebaClaveAreaConocimientoPreguntaDistractorDI.toEntity(dto);
            PruebaClaveAreaConocimientoPreguntaDistractorDI.create(newPruebaClaveAreaConocimientoPreguntaDistractor);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder
                    .queryParam("idPruebaClave", String.valueOf(dto.idPruebaClave()))
                    .queryParam("idAreaConocimiento", String.valueOf(dto.idAreaConocimiento()))
                    .queryParam("idPregunta", String.valueOf(dto.idPregunta()))
                    .queryParam("idDistractor", String.valueOf(dto.idDistractor()))
                    .build();
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Path("/{idPruebaClave:\\d+}/{idAreaConocimiento:\\d+}/{idPregunta:\\d+}/{idDistractor:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("idPruebaClave") @Min(1L) @Max(Long.MAX_VALUE) long idPruebaClave,
            @PathParam("idAreaConocimiento") @Min(1) @Max(Integer.MAX_VALUE) int idAreaConocimiento,
            @PathParam("idPregunta") @Min(1L) @Max(Long.MAX_VALUE) long idPregunta,
            @PathParam("idDistractor") @Min(1L) @Max(Long.MAX_VALUE) long idDistractor,
            @Context UriInfo uriInfo
    ) throws DomainException {
        try {
            PruebaClaveAreaConocimientoPreguntaDistractor found = PruebaClaveAreaConocimientoPreguntaDistractorDI.findById(
                    new PruebaClaveAreaConocimientoPreguntaDistractorPK(
                            idPruebaClave,
                            idAreaConocimiento,
                            idPregunta,
                            idDistractor
                    ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + idPruebaClave + ", IdAreaConocimiento: " + idAreaConocimiento
                                + ", IdPregunta: " + idPregunta + ", IdDistractor: " + idDistractor,
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PruebaClaveAreaConocimientoPreguntaDistractorDI.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Path("/{idPruebaClave:\\d+}/{idAreaConocimiento:\\d+}/{idPregunta:\\d+}/{idDistractor:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @PathParam("idPruebaClave") @Min(1L) @Max(Long.MAX_VALUE) long idPruebaClave,
            @PathParam("idAreaConocimiento") @Min(1) @Max(Integer.MAX_VALUE) int idAreaConocimiento,
            @PathParam("idPregunta") @Min(1L) @Max(Long.MAX_VALUE) long idPregunta,
            @PathParam("idDistractor") @Min(1L) @Max(Long.MAX_VALUE) long idDistractor,
            @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaClaveAreaConocimientoPreguntaDistractor found = PruebaClaveAreaConocimientoPreguntaDistractorDI.findById(
                    new PruebaClaveAreaConocimientoPreguntaDistractorPK(
                            idPruebaClave,
                            idAreaConocimiento,
                            idPregunta,
                            idDistractor
                    ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + idPruebaClave + ", IdAreaConocimiento: " + idAreaConocimiento
                                + ", IdPregunta: " + idPregunta + ", IdDistractor: " + idDistractor,
                                uriInfo.getAbsolutePath().toString(),
                                null
                        ))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            return Response.ok(PruebaClaveAreaConocimientoPreguntaDistractorDI.toDto(found), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<PruebaClaveAreaConocimientoPreguntaDistractorDTO> resultList = PruebaClaveAreaConocimientoPreguntaDistractorDI.findByRange(params.getOffset(), params.getLimit())
                    .stream()
                    .map(r -> PruebaClaveAreaConocimientoPreguntaDistractorDI.toDto(r)).toList();
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @Valid PruebaClaveAreaConocimientoPreguntaDistractorDTO dto,
            @Context UriInfo uriInfo
    ) throws DomainException {
        try {
            PruebaClaveAreaConocimientoPreguntaDistractor found = PruebaClaveAreaConocimientoPreguntaDistractorDI
                    .findById(new PruebaClaveAreaConocimientoPreguntaDistractorPK(
                            dto.idPruebaClave(),
                            dto.idAreaConocimiento(),
                            dto.idPregunta(),
                            dto.idDistractor()
                    ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + dto.idPruebaClave() + ", IdAreaConocimiento: " + dto.idAreaConocimiento()
                                + ", IdPregunta: " + dto.idPregunta() + ", IdDistractor: " + dto.idDistractor(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PruebaClaveAreaConocimientoPreguntaDistractor entity = PruebaClaveAreaConocimientoPreguntaDistractorDI.toEntity(dto);
            PruebaClaveAreaConocimientoPreguntaDistractorDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

}
