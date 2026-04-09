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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClave;

/**
 *
 * @author usermein
 */
@Path("prueba-clave")
public class PruebaClaveResource {

    @Inject
    PruebaClaveDAOImp PruebaClaveDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid PruebaClaveDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaClave entity = PruebaClaveDI.toEntity(dto);
            PruebaClaveDI.create(entity);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(String.valueOf(entity.getIdPruebaClave().toString()));
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * Retorna un AreaConocimiento segun id. - GET /area-conocimiento/{id}
     *
     * @param idAreaConocimiento Id para realizar la busqueda.
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
    @Path("/{idPruebacClave:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @PathParam("idPruebaClave") @Min(1L) @Max(Long.MAX_VALUE) Long idPruebaClave,
            @Context UriInfo uriInfo
    ) throws DomainException {
        try {
            PruebaClave found = PruebaClaveDI.findById(idPruebaClave);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: " + idPruebaClave,
                                uriInfo.getAbsolutePath().toString(),
                                null
                        ))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            return Response.ok(PruebaClaveDI.toDto(found), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * Retorna una lista de AreaConocimiento segun el rango especificado. - GET
     * /area-conocimiento?offset={offset}&limit={limit}
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
            List<PruebaClaveDTO> resultList = PruebaClaveDI
                    .findByRange(params.getOffset(), params.getLimit())
                    .stream().map(r -> PruebaClaveDI.toDto(r)).toList();
            return Response.ok(resultList)
                    .header(HeaderName.TOTAL_RECORDS.toString(), resultList.size())
                    .type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * Actualiza un AreaConocimiento. - put /area-conocimiento/{id}
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
    @Path("/{idPruebaClave:\\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("idPruebaClave") @Min(1L) @Max(Long.MAX_VALUE) Long idPruebaClave,
            @Valid PruebaClaveDTO dto,
            @Context UriInfo uriInfo) {
        try {
            PruebaClave found = PruebaClaveDI.findById(idPruebaClave);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: " + idPruebaClave,
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PruebaClave entity = PruebaClaveDI.toEntity(dto);
            entity.setIdPruebaClave(idPruebaClave);
            PruebaClaveDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    /**
     * Elimina un AreaConocimiento. - DELETE /area-conocimiento/{id}
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
    @Path("/{idPruebaClave:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idPruebaClave") @Min(1L) @Max(Long.MAX_VALUE) Long idPruebaClave, @Context UriInfo uriInfo) {
        try {
            PruebaClave found = PruebaClaveDI.findById(idPruebaClave);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: " + idPruebaClave,
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PruebaClaveDI.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
}
