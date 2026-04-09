/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.DistractorAreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.DistractorAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimientoPK;

/**
 *
 * @author caesar
 */
@Path("distractor-area-conocimiento")
public class DistractorAreaConocimientoResource {

    @Inject
    DistractorAreaConocimientoDAOImp DI;

    /**
     * Crea un DistractorAreaConocimiento. - POST /area-conocimiento
     *
     * @param dto Json de la entidad a persistir
     * @param uriInfo Contexto de la Request para construir Location.
     *
     * @return
     * <ul>
     * <li>201 Created + Location del recurso creado.</li>
     * <li>400 Bad Request si el payload es invalido.
     * <li>500 Internal Server Error en excepciones internas.</li>
     * </ul>
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid DistractorAreaConocimientoDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            DistractorAreaConocimiento entity = DI.toEntity(dto);
            DI.create(entity);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder
                    .path(String.valueOf(entity.getDistractorAreaConocimientoPK().getIdDistractor()))
                    .path(String.valueOf(entity.getDistractorAreaConocimientoPK().getIdAreaConocimiento()));
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * Retorna un DistractorAreaConocimiento segun id. - GET
     * /area-conocimiento/{id}
     *
     * @param idDistractor parte del id de la entidad.
     * @param idAreaConocimiento parte del id de la entidad.
     *
     * @return
     * <ul>
     * <li>200 Ok + Json de la entidad.</li>
     * <li>400 Bad Request Por parametro invalido.</li>
     * <li>404 Not Found + Id no encontrado.</li>
     * <li>500 Internal Server Error en excepciones internas.</li>
     * </ul>
     */
    @GET
    @Path("/{idDistractor:\\d+}/{idAreaConocimiento:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @PathParam("idDistractor") @Min(1) @Max(Integer.MAX_VALUE) Long idDistractor,
            @PathParam("idAreaConocimiento") @Min(1) @Max(Integer.MAX_VALUE) int idAreaConocimiento,
            @Context UriInfo uriInfo
    ) throws DomainException {
        try {
            DistractorAreaConocimiento found = DI.findById(new DistractorAreaConocimientoPK(
                    idDistractor,
                    idAreaConocimiento
            ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: idJornada " + idDistractor + ", idAula " + idAreaConocimiento,
                                uriInfo.getAbsolutePath().toString(),
                                null
                        ))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            return Response.ok(DI.toDto(found), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * Retorna una lista de DistractorAreaConocimiento segun el rango
     * especificado. - GET /area-conocimiento?offset={offset}&limit={limit}
     *
     * @param offset índice inicial (>= 0).
     * @param limit tamaño de página (>= offset).
     *
     * @return
     * <ul>
     * <li>200 Ok + Json con la lista.</li>
     * <li>400 Bad Request Si limit mayor que offset.</li>
     * <li>500 Internal Server Error en excepciones internas.</li>
     * </ul>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<DistractorAreaConocimientoDTO> resultList = DI.findByRange(params.getOffset(), params.getLimit()).stream().map(r -> DI.toDto(r)).toList();
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * Actualiza un DistractorAreaConocimiento. - put /area-conocimiento/{id}
     *
     * @param dto Entidad modificada.
     *
     * @return
     * <ul>
     * <li>204 No Content Entidad actualizada.</li>
     * <li>400 Bad Request Payload invalido o id invalido.</li>
     * <li>404 Not Found + Id no encontrado.</li>
     * <li>500 Internal Server Error en excepciones internas.</li>
     * </ul>
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid DistractorAreaConocimientoDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            DistractorAreaConocimiento found = DI.findById(new DistractorAreaConocimientoPK(
                    dto.idDistractor(),
                    dto.idAreaConocimiento()
            ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: idJornada " + dto.idDistractor() + ", idAula " + dto.idAreaConocimiento(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            DistractorAreaConocimiento entity = DI.toEntity(dto);
            DI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * Elimina un DistractorAreaConocimiento. - DELETE /area-conocimiento/{id}
     *
     * @param idDistractor parte del id de la entidad.
     * @param idAreaConocimiento parte del id de la entidad.
     *
     * @return
     * <ul>
     * <li>204 No Content Entidad actualizada.</li>
     * <li>400 Bad Request Id invalido.</li>
     * <li>404 Not Found + Id no encontrado.</li>
     * <li>500 Internal Server Error en excepciones internas.</li>
     * </ul>
     */
    @DELETE
    @Path("/{idDistractor:\\d+}/{idAreaConocimiento:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("idDistractor") @Min(1) @Max(Integer.MAX_VALUE) Long idDistractor,
            @PathParam("idAreaConocimiento") @Min(1) @Max(Integer.MAX_VALUE) int idAreaConocimiento,
            @Context UriInfo uriInfo
    ) throws DomainException {
        try {
            DistractorAreaConocimiento found = DI.findById(new DistractorAreaConocimientoPK(
                    idDistractor,
                    idAreaConocimiento
            ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: idJornada " + idDistractor + ", idAula " + idAreaConocimiento,
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            DI.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
}
