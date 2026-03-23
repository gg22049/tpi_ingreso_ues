/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO.ErrorDetailDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;

/**
 *
 * @author caesar
 */
@Path("area-conocimiento")
public class AreaConocimientoResource implements Serializable {

    @Inject
    AreaConocimientoDAOImp areaConocimientoDI;

    /**
     * Crea un AreaConocimiento. - POST /examen-jornada
     *
     * @param AreaConocimiento Entidad para crear.
     * @param uriInfo Contexto de la Request para construir Location.
     * @return
     * <ul>
     * <li>201 Created + Location del recurso creado.</li>
     * <li>400 Bad Request si el payload es nulo.< 0, limit > 50 o limit <
     * offset.</li> <
     * l
     * i>422 Unprocessable Content con parametros invalidos o faltantes.</li>
     * <li>500 Internal Server Error en excepciones internas.</li>
     * </ul>
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAreaConocimiento(@NotNull @Valid AreaConocimiento entity, @Context UriInfo uriInfo) throws Exception {
        try {
            areaConocimientoDI.create(entity);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder.path(String.valueOf(entity.getIdAreaConocimiento()));
            return Response.created(uriBuilder.build()).build();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new Exception();
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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdAreaConocimiento(@NotNull @Min(0) @PathParam("id") Integer id, @Context UriInfo uriInfo) throws Exception {
        try {
            AreaConocimiento found = areaConocimientoDI.findById(id);
            if (found == null) {
                ErrorDetailDTO error = new ErrorDetailDTO(
                        ErrorTitle.INVALID_ID.toString(),
                        404,
                        "AreaConocimiento with id " + id + " not found",
                        uriInfo.getPath(),
                        null
                );

                return Response
                        .status(404)
                        .entity(error)
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.ok(found).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new Exception();
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
    public Response findByRangeAreaConocimiento(@Valid @BeanParam FindRangeParamDTO params) throws Exception {
        try {
            List<AreaConocimiento> resultList = areaConocimientoDI.findByRange(params.getOffset(), params.getLimit());
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new Exception();
        }
    }

//    /**
//     * Actualizar un AreaConocimiento. - PATCH /examen-jornada
//     *
//     * @param ej Entidad modificada.
//     * @return
//     * <ul>
//     * <li>204 No Content Entidad actualizada.</li>
//     * <li>400 Bad Request Si el payload es nulo o alguno de sus valores
//     * obligatorios.</li>
//     * <li>422 Unprocessable Content con parametros invalidos o faltantes.</li>
//     * <li>500 Internal Server Error en excepciones internas.</li>
//     * </ul>
//     */
//    @PATCH
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateAreaConocimiento(AreaConocimiento ej) {
//        if (ej != null && ej.getIdAreaConocimiento() != null && ej.getIdExamen() != null && ej.getIdJornada() != null) {
//            try {
//                Examen eEncontrado = examenDI.findById(ej.getIdExamen());
//                Jornada jEncontrado = jornadaDI.findById(ej.getIdJornada());
//                if (eEncontrado != null && jEncontrado != null) {
//                    ej.setIdExamen(eEncontrado);
//                    ej.setIdJornada(jEncontrado);
//                    examenJornadaDI.update(ej);
//                    return Response.noContent().build();
//                }
//                return Response.status(422).entity(ResponseMessage.MALFORMED_ENTITY).build();
//            } catch (Exception e) {
//                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
//                return Response.status(500).entity(ResponseMessage.INTERNAL_EXCEPTION).build();
//            }
//        }
//        return Response.status(400).entity(ResponseMessage.NULL_PAYLOAD).build();
//    }
}
