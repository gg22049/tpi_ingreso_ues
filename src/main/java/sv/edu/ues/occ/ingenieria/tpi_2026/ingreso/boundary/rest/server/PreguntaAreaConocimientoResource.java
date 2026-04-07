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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaAreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimientoPK;

/**
 *
 * @author usermein
 */
@Path("pregunta-areaconocimiento")
public class PreguntaAreaConocimientoResource {

    @Inject
    PreguntaAreaConocimientoDAOImp preguntaAreaConocimientoDI;
    @Inject
    AreaConocimientoDAOImp areaConocimientoDI;
    @Inject
    PreguntaDAOImp preguntaDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid PreguntaAreaConocimientoDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            PreguntaAreaConocimiento newPreguntaAreaConocimiento = preguntaAreaConocimientoDI.toEntity(dto);
            //Aqui tengo la duda sobre lo que ibamos a consumir desde fuera, que son las aulas entonces me parece algo ilogico comprobar con el
            //findById si no tenemos una tabla directamente en eso
            if (preguntaAreaConocimientoDI.findById(dto.idAreaConocimiento()) == null && preguntaAreaConocimientoDI.findById(dto.idAreaConocimiento()) == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPregunta:" + dto.idPregunta() + ", IdAreaConocimiento: " + dto.idAreaConocimiento(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            preguntaAreaConocimientoDI.create(newPreguntaAreaConocimiento);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder
                    .queryParam("idPregunta", String.valueOf(dto.idPregunta()))
                    .queryParam("dto.idAreaConocimiento", String.valueOf(dto.idAreaConocimiento()))
                    .build();
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@Valid PreguntaAreaConocimientoPK key, @Context UriInfo uriInfo) {
        try {
            PreguntaAreaConocimiento found = preguntaAreaConocimientoDI.findById(
                    new PreguntaAreaConocimientoPK(
                            key.getIdPregunta(),
                            key.getIdAreaConocimiento())
            );
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPregunta:" + key.getIdPregunta() + ", IdAreaConocimiento: " + key.getIdAreaConocimiento(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            preguntaAreaConocimientoDI.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@Valid PreguntaAreaConocimientoPK key, @Context UriInfo uriInfo) throws DomainException {
        try {
            PreguntaAreaConocimiento found = preguntaAreaConocimientoDI.findById(key);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPregunta:" + key.getIdPregunta() + ", IdAreaConocimiento: " + key.getIdAreaConocimiento(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        ))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            return Response.ok(preguntaAreaConocimientoDI.toDto(found), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<PreguntaAreaConocimientoDTO> resultList = preguntaAreaConocimientoDI.findByRange(params.getOffset(), params.getLimit())
                    .stream()
                    .map(r -> preguntaAreaConocimientoDI.toDto(r)).toList();
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid PreguntaAreaConocimientoDTO dto, @Context UriInfo uriInfo) {
        try {
            PreguntaAreaConocimiento found = preguntaAreaConocimientoDI
                    .findById(new PreguntaAreaConocimientoPK(dto.idPregunta(), dto.idAreaConocimiento()));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPregunta:" + dto.idPregunta() + ", IdAreaConocimiento: " + dto.idAreaConocimiento(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PreguntaAreaConocimiento entity = preguntaAreaConocimientoDI.toEntity(dto);
            preguntaAreaConocimientoDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
}
