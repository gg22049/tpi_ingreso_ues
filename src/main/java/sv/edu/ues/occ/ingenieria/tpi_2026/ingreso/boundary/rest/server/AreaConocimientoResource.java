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
import java.io.Serializable;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.DomainException;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.record.ErrorRecord;

/**
 *
 * @author caesar
 */
@Path("area-conocimiento")
public class AreaConocimientoResource implements Serializable {

    @Inject
    AreaConocimientoDAOImp DAOImp;

    /**
     * Crea un AreaConocimiento. - POST /area-conocimiento
     *
     * @param entity Json de la entidad a persistir
     * @param uriInfo Contexto de la Request para construir Location.
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
    public Response create(@Valid AreaConocimiento entity, @Context UriInfo uriInfo) throws DomainException {
        try {
            DAOImp.create(entity);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(String.valueOf(entity.getIdAreaConocimiento()));
            return Response.created(uriBuilder.build()).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * // * Retorna un AreaConocimiento segun id. - GET /examen-jornada/{id} //
     * * // * @param idAreaConocimiento Id para realizar la busqueda. //
     *
     *
     * @return // * <ul>
     * // * <li>200 Ok + Json de la entidad.</li>
     * // * <li>400 Bad Request Por parametro nulo.</li>
     * // * <li>404 Not Found + Id invalido.</li>
     * // * <li>500 Internal Server Error en excepciones internas.</li>
     * // * </ul> //
     */
    @GET
    @Path("/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") @Min(1) @Max(Integer.MAX_VALUE) Integer id, @Context UriInfo uriInfo) throws DomainException {
        try {
            AreaConocimiento found = DAOImp.findById(id);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorRecord(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "AreaConocimiento with id " + id + " not found",
                                uriInfo.getAbsolutePath().toString(),
                                null
                        ))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            return Response.ok(found, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * Retorna una lista de AreaConocimiento segun el rango especificado. - GET
     * /facturas/detalle-sala?offset={offset}&limit={limit}
     *
     * @param offset índice inicial (>= 0). Default: 0
     * @param limit tamaño de página (1..50). Default: 50
     * @return
     * <ul>
     * <li>200 Ok + Json con la lista.</li>
     * <li>400 Bad Request Si offset < 0, limit
     * > 50 o limit < offset.</li> <
     * li>500 Internal Server Error en excepciones internas.</li>
     * </ul>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<AreaConocimiento> resultList = DAOImp.findByRange(params.getOffset(), params.getLimit());
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * Actualizar un AreaConocimiento. - PATCH /examen-jornada
     *
     * @param ej Entidad modificada.
     * @return
     * <ul>
     * <li>204 No Content Entidad actualizada.</li>
     * <li>400 Bad Request Si el payload es nulo o alguno de sus valores
     * obligatorios.</li>
     * <li>422 Unprocessable Content con parametros invalidos.</li>
     * <li>500 Internal Server Error en excepciones internas.</li>
     * </ul>
     */
    @PUT
    @Path("/{id:\\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, @Valid AreaConocimiento entity, @Context UriInfo uriInfo) {
        try {
            AreaConocimiento found = DAOImp.findById(id);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(
                                new ErrorRecord(
                                        null,
                                        ErrorType.NO_MATCH_ID.toString(),
                                        404,
                                        "No entity with id: " + id,
                                        uriInfo.getAbsolutePath().toString(),
                                        null
                                )
                        ).build();
            }
            entity.setIdAreaConocimiento(id);
            DAOImp.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Path("/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") @Min(1) @Max(Integer.MAX_VALUE) Integer id, @Context UriInfo uriInfo) {
        try {
            AreaConocimiento found = DAOImp.findById(id);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(
                                new ErrorRecord(
                                        null,
                                        ErrorType.NO_MATCH_ID.toString(),
                                        404,
                                        "No entity with id: " + id,
                                        uriInfo.getAbsolutePath().toString(),
                                        null
                                )
                        ).build();
            }
            DAOImp.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

}
