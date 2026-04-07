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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaJornadaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaPK;

/**
 *
 * @author usermein
 */
@Path("prueba-jornada")
public class PruebaJornadaResource {

    @Inject
    PruebaJornadaDAOImp pruebaJornadaDI;

    @Inject
    PruebaDAOImp pruebaDI;

    @Inject
    JornadaDAOImp jornadaDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid PruebaJornadaDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaJornada newPreguntaAreaConocimiento = pruebaJornadaDI.toEntity(dto);

            if (pruebaJornadaDI.findById(dto.idPrueba()) == null && pruebaJornadaDI.findById(dto.idJornada()) == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPrueba:" + dto.idPrueba() + ", IdJornada: " + dto.idJornada(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            pruebaJornadaDI.create(newPreguntaAreaConocimiento);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder
                    .queryParam("idPrueba", String.valueOf(dto.idPrueba()))
                    .queryParam("dto.idJornada", String.valueOf(dto.idJornada()))
                    .build();
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@Valid PruebaJornadaPK key, @Context UriInfo uriInfo) {
        try {
            PruebaJornada found = pruebaJornadaDI.findById(
                    new PruebaJornadaPK(
                            key.getIdPrueba(),
                            key.getIdJornada())
            );
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPrueba:" + key.getIdPrueba() + ", IdJornada: " + key.getIdJornada(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            pruebaJornadaDI.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@Valid PruebaJornadaPK key, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaJornada found = pruebaJornadaDI.findById(key);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPrueba:" + key.getIdPrueba() + ", IdJornada: " + key.getIdJornada(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        ))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            return Response.ok(pruebaJornadaDI.toDto(found), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<PruebaJornadaDTO> resultList = pruebaJornadaDI.findByRange(params.getOffset(), params.getLimit())
                    .stream()
                    .map(r -> pruebaJornadaDI.toDto(r)).toList();
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid PruebaJornadaDTO dto, @Context UriInfo uriInfo) {
        try {
            PruebaJornada found = pruebaJornadaDI
                    .findById(new PruebaJornadaPK(dto.idPrueba(), dto.idJornada()));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPrueba:" + dto.idPrueba() + ", IdJornada: " + dto.idJornada(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PruebaJornada entity = pruebaJornadaDI.toEntity(dto);
            pruebaJornadaDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
}
