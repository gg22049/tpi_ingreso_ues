package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
import java.util.stream.Collectors;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.DomainException;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta;

/**
 *
 * @author usermein
 */
@Path("pregunta")
public class PreguntaResource {

    @Inject
    PreguntaDAOImp preguntaDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@NotNull @Valid PreguntaDTO preguntaDTO, UriInfo uriInfo) throws DomainException {
        try {
            Pregunta nuevaPregunta = preguntaDI.toEntity(preguntaDTO);
            preguntaDI.create(nuevaPregunta);
            URI uriCreada = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(nuevaPregunta.getIdPregunta()))
                    .build();
            return Response.created(uriCreada)
                    .entity(preguntaDTO)
                    .build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Path("/{idPregunta:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idPregunta") @Min(1) @Max(Integer.MAX_VALUE) Integer idPregunta, @Context UriInfo uriInfo) throws DomainException {
        try {
            Pregunta pregunta = preguntaDI.findById(idPregunta);
            if (pregunta == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe Pregunta con ID: " + idPregunta,
                                uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            preguntaDI.delete(pregunta);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Path("/{idPregunta:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @PathParam("idPregunta") @Min(1L) @Max(Long.MAX_VALUE) Long idPregunta,
            @Context UriInfo uriInfo
    ) throws DomainException {
        try {
            Pregunta pregunta = preguntaDI.findById(idPregunta);
            if (pregunta == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe Pregunta con ID: " + idPregunta,
                                uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            PreguntaDTO preguntaDTO = preguntaDI.toDto(pregunta);
            return Response.ok(preguntaDTO, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<Pregunta> listaPreguntas = preguntaDI.findByRange(params.getOffset(), params.getLimit());
            List<PreguntaDTO> listaPrreguntasDTO = listaPreguntas
                    .stream()
                    .map(pregunta -> preguntaDI.toDto(pregunta))
                    .collect(Collectors.toList());
            return Response
                    .ok(listaPrreguntasDTO)
                    .header(HeaderName.TOTAL_RECORDS.toString(), listaPrreguntasDTO.size())
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Path("/{idPregunta:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("idPregunta") Long idPregunta,
            @Valid PreguntaDTO preguntaDTO,
            @Context UriInfo uriInfo) throws DomainException {
        try {
            Pregunta pregunta = preguntaDI.findById(idPregunta);
            if (pregunta == null) {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe Pregunta con ID: " + idPregunta,
                                uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            Pregunta entity = preguntaDI.toEntity(preguntaDTO);
            entity.setIdPregunta(idPregunta);
            preguntaDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

}
