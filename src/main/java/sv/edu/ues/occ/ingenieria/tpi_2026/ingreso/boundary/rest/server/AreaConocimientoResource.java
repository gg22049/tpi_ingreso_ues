/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.FindRangeDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.EntityNotFoundInRepositoryExcpetion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AreaConocimientoDAOImp;

/**
 *
 * @author caesar
 */
@Path("area-conocimiento")
public class AreaConocimientoResource {

    @Inject
    AreaConocimientoDAOImp areaDI;

    /**
     * Crea un AreaConocimiento - POST /area-conocimiento
     *
     * @param entity Json e la entidad a persistir.
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
    public Response create(
            @Valid AreaConocimiento entity,
            @Context UriInfo uriInfo
    ) {

        areaDI.create(entity);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(String.valueOf(entity.getIdAreaConocimiento()));
        return Response.created(uriBuilder.build()).build();

    }

    /**
     * Retorna un AreaConocimiento segun id - GET /area-conocimiento/{id}
     *
     * @param id Llave primaria para realizar la busqueda.
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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") @Min(1) Integer id) {

        AreaConocimiento found = areaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }
        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    /**
     * Retorna una lista de AreaConocimiento segun el rango especificado. - GET
     * /area-conocimiento?offset={offset}&limit={limit}
     *
     * @param offset índice inicial (>= 0).
     * @param limit tamaño de página (>= offset).
     *
     * @return
     * <ul>
     * <li>200 Ok + Json con la lista.</li>
     * <li>400 Bad Request Si limit menor que offset.</li>
     * <li>400 Bad Request Si el rango solicitado es mayor a 50 elementos.</li>
     * <li>500 Internal Server Error en excepciones internas.</li>
     * </ul>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam FindRangeDTO params) {

        List<AreaConocimiento> resultList = areaDI.findByRange(params.getOffset(), params.getLimit());
        return Response
                .ok(resultList)
                .header(HeaderName.TOTAL_RECORDS.toString(), resultList.size())
                .type(MediaType.APPLICATION_JSON)
                .build();

    }

    /**
     * Actualiza un AreaConocimiento. - put /area-conocimiento/{id}
     *
     * @param id Llave primaria de entidad modificada.
     * @param entity Entidad modificada.
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
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") @Min(1) Integer id, @Valid AreaConocimiento entity) {

        AreaConocimiento found = areaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }
        entity.setIdAreaConocimiento(id);
        entity.setIdAreaConocimientoPadre(found.getIdAreaConocimientoPadre());
        areaDI.update(entity);
        return Response.noContent().build();

    }

    /**
     * Elimina un AreaConocimiento. - DELETE /area-conocimiento/{id}
     *
     * @param id Llave primaria de entidad a eliminar.
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
    @Path("/{id}")
    public Response delete(@PathParam("id") @Min(1) Integer id) {

        AreaConocimiento found = areaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }
        areaDI.delete(found);
        return Response.noContent().build();

    }

    /**
     * Asigna una AreaConocimiento padre a otra AreaConocimiento - PUT
     * /area-conocimiento/{idArea}/area-padre/{idPadre}
     *
     * @param idArea Id para realizar la busqueda.
     * @param idPadre Id para realizar la busqueda.
     *
     * @return
     * <ul>
     * <li>201 Created + Location del recurso creado.</li>
     * <li>400 Bad Request si el payload es invalido.
     * <li>404 Not Found + Id no encontrado.</li>
     * <li>500 Internal Server Error en excepciones internas.</li>
     * </ul>
     */
    @PUT
    @Path("/{idArea}/area-padre/{idPadre}")
    public Response setAreaPadre(
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPadre") @Min(1) Integer idPadre
    ) {

        AreaConocimiento area = areaDI.findById(idArea);
        if (area == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idArea);
        }

        AreaConocimiento areaPadre = areaDI.findById(idPadre);
        if (areaPadre == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPadre);
        }

        area.setIdAreaConocimientoPadre(areaPadre);
        areaDI.update(area);
        return Response.noContent().build();

    }

    /**
     * Desasginar una AreaConocimiento padre a otra AreaConocimiento - DELETE
     * /area-conocimiento/{idArea}/area-padre/
     *
     * @param idArea Llave primaria para realizar la busqueda de la entidad.
     *
     * @return
     * <ul>
     * <li>201 Created + Location del recurso creado.</li>
     * <li>400 Bad Request si el payload es invalido.
     * <li>404 Not Found + Id no encontrado.</li>
     * <li>500 Internal Server Error en excepciones internas.</li>
     * </ul>
     */
    @DELETE
    @Path("/{idArea}/area-padre")
    public Response unsetAreaPadre(@PathParam("idArea") @Min(1) Integer idArea) {

        AreaConocimiento found = areaDI.findById(idArea);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idArea);
        }

        found.setIdAreaConocimientoPadre(null);
        areaDI.update(found);
        return Response.noContent().build();

    }

}
