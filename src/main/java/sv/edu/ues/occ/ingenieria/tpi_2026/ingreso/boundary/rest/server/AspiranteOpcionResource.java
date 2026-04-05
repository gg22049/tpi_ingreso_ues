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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteOpcionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteOpcion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.DomainException;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AspiranteOpcionDAOImp;

/**
 *
 * @author caesar
 */
@Path("aspirante-opcion")
public class AspiranteOpcionResource {

    @Inject
    AspiranteOpcionDAOImp DI;

    /**
     * Crea un AspiranteOpcion. - POST /aspirante-opcion
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
    public Response create(@Valid AspiranteOpcionDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            AspiranteOpcion entity = DI.toEntity(dto);
            DI.create(entity);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(String.valueOf(entity.getIdAspiranteOpcion().toString()));
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * Retorna un AspiranteOpcion segun id. - GET /aspirante-opcion/{id}
     *
     * @param idAspiranteOpcion Id para realizar la busqueda.
     *
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
    @Path("/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") @Min(1L) @Max(Long.MAX_VALUE) Long id, @Context UriInfo uriInfo) throws DomainException {
        try {
            AspiranteOpcion found = DI.findById(id);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "AspiranteOpcion with id " + id + " not found",
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
     * Retorna una lista de AspiranteOpcion segun el rango especificado. - GET
     * /aspirante-opcion?offset={offset}&limit={limit}
     *
     * @param offset índice inicial (>= 0).
     * @param limit tamaño de página (>= offset).
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
            List<AspiranteOpcionDTO> resultList = DI.findByRange(params.getOffset(), params.getLimit()).stream().map(r -> DI.toDto(r)).toList();
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * Actualiza un AspiranteOpcion. - put /aspirante-opcion/{id}
     *
     * @param id Id de entidad modificada.
     * @param dto Entidad modificada.
     * @return
     * <ul>
     * <li>204 No Content Entidad actualizada.</li>
     * <li>400 Bad Request Payload invalido o id invalido.</li>
     * <li>404 Not Found + Id no encontrado.</li>
     * <li>500 Internal Server Error en excepciones internas.</li>
     * </ul>
     */
    @PUT
    @Path("/{id:\\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") @Min(1L) @Max(Long.MAX_VALUE) Long id, @Valid AspiranteOpcionDTO dto, @Context UriInfo uriInfo) {
        try {
            AspiranteOpcion found = DI.findById(id);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: " + id,
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            AspiranteOpcion entity = DI.toEntity(dto);
            entity.setIdAspiranteOpcion(id);
            DI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * Elimina un AspiranteOpcion. - DELETE /aspirante-opcion/{id}
     *
     * @param id Id de entidad modificada.
     * @return
     * <ul>
     * <li>204 No Content Entidad actualizada.</li>
     * <li>400 Bad Request Id invalido.</li>
     * <li>404 Not Found + Id no encontrado.</li>
     * <li>500 Internal Server Error en excepciones internas.</li>
     * </ul>
     */
    @DELETE
    @Path("/{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") @Min(1L) @Max(Long.MAX_VALUE) Long id, @Context UriInfo uriInfo) {
        try {
            AspiranteOpcion found = DI.findById(id);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: " + id,
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
