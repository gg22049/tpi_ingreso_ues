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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPK;

/**
 *
 * @author usermein
 */
@Path("prueba-clave-area")
public class PruebaClaveAreaConocimientoResource {

    @Inject
    PruebaClaveAreaConocimientoDAOImp pruebaClaveAreaConocimientoDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid PruebaClaveAreaConocimientoDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaClaveAreaConocimiento newPruebaClaveAreaConocimiento = pruebaClaveAreaConocimientoDI.toEntity(dto);
            pruebaClaveAreaConocimientoDI.create(newPruebaClaveAreaConocimiento);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder
                    .path(String.valueOf(dto.idPruebaClave()))
                    .path(String.valueOf(dto.idAreaConocimiento()))
                    .build();
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Path("/{idPruebaClave:\\d+}/{idAreaConocimiento:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("idPruebaClave") @Min(1L) @Max(Long.MAX_VALUE) long idPruebaClave,
            @PathParam("idAreaConocimiento") @Min(1) @Max(Integer.MAX_VALUE) int idAreaConocimiento,
            @Context UriInfo uriInfo)
            throws DomainException {
        try {
            PruebaClaveAreaConocimiento found = pruebaClaveAreaConocimientoDI.findById(
                    new PruebaClaveAreaConocimientoPK(
                            idPruebaClave,
                            idAreaConocimiento
                    ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + idPruebaClave + ", IdAreaConocimiento: " + idAreaConocimiento,
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            pruebaClaveAreaConocimientoDI.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Path("/{idPruebaClave:\\d+}/{idAreaConocimiento:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @PathParam("idPruebaClave") @Min(1L) @Max(Long.MAX_VALUE) long idPruebaClave,
            @PathParam("idAreaConocimiento") @Min(1) @Max(Integer.MAX_VALUE) int idAreaConocimiento,
            @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaClaveAreaConocimiento found = pruebaClaveAreaConocimientoDI.findById(new PruebaClaveAreaConocimientoPK(idPruebaClave, idAreaConocimiento));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + idPruebaClave + ", IdAreaConocimiento: " + idAreaConocimiento,
                                uriInfo.getAbsolutePath().toString(),
                                null
                        ))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            return Response.ok(pruebaClaveAreaConocimientoDI.toDto(found), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<PruebaClaveAreaConocimientoDTO> resultList = pruebaClaveAreaConocimientoDI.findByRange(params.getOffset(), params.getLimit())
                    .stream()
                    .map(r -> pruebaClaveAreaConocimientoDI.toDto(r)).toList();
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Path("/{idPruebaClave:\\d+}/{idAreaConocimiento:\\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("idPruebaClave") @Min(1L) @Max(Long.MAX_VALUE) long idPruebaClave,
            @PathParam("idAreaConocimiento") @Min(1) @Max(Integer.MAX_VALUE) int idAreaConocimiento,
            @Valid PruebaClaveAreaConocimientoDTO dto,
            @Context UriInfo uriInfo)
            throws DomainException {
        try {
            PruebaClaveAreaConocimiento found = pruebaClaveAreaConocimientoDI.findById(
                    new PruebaClaveAreaConocimientoPK(idPruebaClave, idAreaConocimiento
                    ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + idPruebaClave + ", IdAreaConocimiento: " + idAreaConocimiento,
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PruebaClaveAreaConocimiento entity = pruebaClaveAreaConocimientoDI.toEntity(dto);
            pruebaClaveAreaConocimientoDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
}
