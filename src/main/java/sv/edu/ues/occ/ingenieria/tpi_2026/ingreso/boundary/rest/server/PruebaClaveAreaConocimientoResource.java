package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.DomainException;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaDAOImp;
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
    @Inject
    PruebaDAOImp pruebaDI;
    @Inject
    AreaConocimientoDAOImp areaConocimientoDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid PruebaClaveAreaConocimientoDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaClaveAreaConocimiento newPruebaClaveAreaConocimiento = pruebaClaveAreaConocimientoDI.toEntity(dto);
            //Aqui tengo la duda sobre lo que ibamos a consumir desde fuera, que son las aulas entonces me parece algo ilogico comprobar con el
            //findById si no tenemos una tabla directamente en eso
            if (pruebaClaveAreaConocimientoDI.findById(dto.idPruebaClave()) == null
                    && pruebaClaveAreaConocimientoDI.findById(dto.idAreaConocimiento()) == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + dto.idPruebaClave() + ", IdAreaConocimiento: " + dto.idAreaConocimiento(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            pruebaClaveAreaConocimientoDI.create(newPruebaClaveAreaConocimiento);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder
                    .queryParam("idPregunta", String.valueOf(dto.idPruebaClave()))
                    .queryParam("dto.idAreaConocimiento", String.valueOf(dto.idAreaConocimiento()))
                    .build();
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@Valid PruebaClaveAreaConocimientoPK key, @Context UriInfo uriInfo) {
        try {
            PruebaClaveAreaConocimiento found = pruebaClaveAreaConocimientoDI.findById(
                    new PruebaClaveAreaConocimientoPK(
                            key.getIdPruebaClave(),
                            key.getIdAreaConocimiento())
            );
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + key.getIdPruebaClave() + ", IdAreaConocimiento: " + key.getIdAreaConocimiento(),
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@Valid PruebaClaveAreaConocimientoPK key, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaClaveAreaConocimiento found = pruebaClaveAreaConocimientoDI.findById(key);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + key.getIdPruebaClave() + ", IdAreaConocimiento: " + key.getIdAreaConocimiento(),
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid PruebaClaveAreaConocimientoDTO dto, @Context UriInfo uriInfo) {
        try {
            PruebaClaveAreaConocimiento found = pruebaClaveAreaConocimientoDI
                    .findById(new PruebaClaveAreaConocimientoPK(dto.idPruebaClave(), dto.idAreaConocimiento()));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + dto.idPruebaClave()+ ", IdAreaConocimiento: " + dto.idAreaConocimiento(),
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
