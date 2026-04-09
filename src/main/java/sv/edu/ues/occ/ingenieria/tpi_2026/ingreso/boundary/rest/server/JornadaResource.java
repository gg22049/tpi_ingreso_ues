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
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.DomainException;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaAulaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.JornadaDTO;

/**
 *
 * @author usermein
 */
@Path("jornada")
public class JornadaResource implements Serializable {

    @Inject
    JornadaDAOImp jornadaDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@NotNull @Valid JornadaDTO jornadaDTO, UriInfo uriInfo) throws DomainException {
        try {
            Jornada nuevaJornada = jornadaDI.toEntity(jornadaDTO);
            jornadaDI.create(nuevaJornada);
            URI uriCreada = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(nuevaJornada.getIdJornada()))
                    .build();
            return Response.created(uriCreada)
                    .entity(jornadaDTO)
                    .build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Path("/{idJornada:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idJornada") @Min(1L) @Max(Long.MAX_VALUE) Long idJornada, @Context UriInfo uriInfo) throws DomainException {
        try {
            Jornada jornada = jornadaDI.findById(idJornada);
            if (jornada == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe Jornada con ID: " + idJornada,
                                uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            jornadaDI.delete(jornada);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Path("/{idJornada:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("idJornada") @Min(1L) @Max(Long.MAX_VALUE) Long idJornada,
            @Context UriInfo uriInfo
    ) throws DomainException {
        try {
            Jornada jornada = jornadaDI.findById(idJornada);
            if (jornada == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe Jornada con ID: " + idJornada,
                                uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            JornadaDTO jornadaDTO = jornadaDI.toDto(jornada);
            return Response.ok(jornadaDTO, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<Jornada> listaJornadas = jornadaDI.findByRange(params.getOffset(), params.getLimit());
            List<JornadaDTO> listaJornadasDTO = listaJornadas
                    .stream()
                    .map(jornada -> jornadaDI.toDto(jornada))
                    .collect(Collectors.toList());
            return Response
                    .ok(listaJornadasDTO)
                    .header(HeaderName.TOTAL_RECORDS.toString(), listaJornadasDTO.size())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Path("/{idJornada:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("idJornada") @Min(1L) @Max(Long.MAX_VALUE) Long idJornada,
            @Valid JornadaDTO jornadaDTO,
            @Context UriInfo uriInfo) throws DomainException {
        try {
            Jornada jornada = jornadaDI.findById(idJornada);
            if (jornada == null) {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe Pregunta con ID: " + idJornada,
                                uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            Jornada entity = jornadaDI.toEntity(jornadaDTO);
            entity.setIdJornada(idJornada);
            jornadaDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
}
