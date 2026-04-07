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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.DistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaDistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaDistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractorPK;

/**
 *
 * @author usermein
 */
@Path("pregunta-distractor")
public class PreguntaDistractorResource {

    @Inject
    PreguntaDistractorDAOImp preguntaDistractorDI;
    
    @Inject
    PreguntaDAOImp preguntaDI;
    
    @Inject 
    DistractorDAOImp distractorDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid PreguntaDistractorDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            PreguntaDistractor nuevaPreguntaDistractor = preguntaDistractorDI.toEntity(dto);
            if (preguntaDistractorDI.findById(dto.idPregunta()) == null && preguntaDistractorDI.findById(dto.idDistractor()) == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPregunta:" + dto.idPregunta() + ", IdTDistractor: " + dto.idDistractor(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            preguntaDistractorDI.create(nuevaPreguntaDistractor);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder
                    .queryParam("idPregunta", String.valueOf(dto.idPregunta()))
                    .queryParam("idDistractor", String.valueOf(dto.idDistractor()))
                    .build();
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@Valid PreguntaDistractorPK key, @Context UriInfo uriInfo) {
        try {
            PreguntaDistractor found = preguntaDistractorDI.findById(
                    new PreguntaDistractorPK(
                            key.getIdPregunta(),
                            key.getIdDistractor())
            );
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPregunta:" + key.getIdPregunta() + ", IdDistractor: " + key.getIdDistractor(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            preguntaDistractorDI.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@Valid PreguntaDistractorPK key, @Context UriInfo uriInfo) throws DomainException {
        try {
            PreguntaDistractor found = preguntaDistractorDI.findById(key);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPregunta:" + key.getIdPregunta() + ", IdDistractor: " + key.getIdDistractor(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        ))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            return Response.ok(preguntaDistractorDI.toDto(found), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<PreguntaDistractorDTO> resultList = preguntaDistractorDI.findByRange(params.getOffset(), params.getLimit())
                    .stream()
                    .map(r -> preguntaDistractorDI.toDto(r)).toList();
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid PreguntaDistractorDTO dto, @Context UriInfo uriInfo) {
        try {
            PreguntaDistractor found = preguntaDistractorDI.findById(new PreguntaDistractorPK(dto.idPregunta(), dto.idDistractor()));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPregunta:" + dto.idPregunta() + ", IdDistractor: " + dto.idDistractor(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PreguntaDistractor entity = preguntaDistractorDI.toEntity(dto);
            preguntaDistractorDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
}
