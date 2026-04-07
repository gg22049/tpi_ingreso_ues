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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoPreguntaDistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoPreguntaDistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractorPK;

/**
 *
 * @author usermein
 */
@Path("prueba-clave-area-pregunta-distractor")
public class PruebaClaveAreaConocimientoPreguntaDistractorResource {

    @Inject
    PruebaClaveAreaConocimientoPreguntaDistractorDAOImp PruebaClaveAreaConocimientoPreguntaDistractorDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid PruebaClaveAreaConocimientoPreguntaDistractorDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaClaveAreaConocimientoPreguntaDistractor newPruebaClaveAreaConocimientoPreguntaDistractor = PruebaClaveAreaConocimientoPreguntaDistractorDI.toEntity(dto);
            //Aqui tengo la duda sobre lo que ibamos a consumir desde fuera, que son las aulas entonces me parece algo ilogico comprobar con el
            //findById si no tenemos una tabla directamente en eso
            if (PruebaClaveAreaConocimientoPreguntaDistractorDI.findById(dto.idPruebaClave()) == null
                    && PruebaClaveAreaConocimientoPreguntaDistractorDI.findById(dto.idAreaConocimiento()) == null
                    && PruebaClaveAreaConocimientoPreguntaDistractorDI.findById(dto.idPregunta()) == null
                    && PruebaClaveAreaConocimientoPreguntaDistractorDI.findById(dto.idDistractor()) == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + dto.idPruebaClave() + ", IdAreaConocimiento: " + dto.idAreaConocimiento()
                                + ", IdPregunta: " + dto.idPregunta() + ", IdDistractor: " + dto.idDistractor(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PruebaClaveAreaConocimientoPreguntaDistractorDI.create(newPruebaClaveAreaConocimientoPreguntaDistractor);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder
                    .queryParam("idPruebaClave", String.valueOf(dto.idPruebaClave()))
                    .queryParam("idAreaConocimiento", String.valueOf(dto.idAreaConocimiento()))
                    .queryParam("idAula", String.valueOf(dto.idPregunta()))
                    .queryParam("idDistractor", String.valueOf(dto.idDistractor()))
                    .build();
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@Valid PruebaClaveAreaConocimientoPreguntaDistractorPK key, @Context UriInfo uriInfo) {
        try {
            PruebaClaveAreaConocimientoPreguntaDistractor found = PruebaClaveAreaConocimientoPreguntaDistractorDI.findById(
                    new PruebaClaveAreaConocimientoPreguntaDistractorPK(
                            key.getIdPruebaClave(),
                            key.getIdAreaConocimiento(),
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
                                "No entity with id: IdPruebaClave:" + key.getIdPruebaClave() + ", IdAreaConocimiento: " + key.getIdAreaConocimiento()
                                + ", IdPregunta: " + key.getIdPregunta() + ", IdDistractor: " + key.getIdDistractor(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PruebaClaveAreaConocimientoPreguntaDistractorDI.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@Valid PruebaClaveAreaConocimientoPreguntaDistractorPK key, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaClaveAreaConocimientoPreguntaDistractor found = PruebaClaveAreaConocimientoPreguntaDistractorDI.findById(key);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + key.getIdPruebaClave() + ", IdAreaConocimiento: " + key.getIdAreaConocimiento()
                                + ", IdPregunta: " + key.getIdPregunta() + ", IdDistractor: " + key.getIdDistractor(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        ))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            return Response.ok(PruebaClaveAreaConocimientoPreguntaDistractorDI.toDto(found), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<PruebaClaveAreaConocimientoPreguntaDistractorDTO> resultList = PruebaClaveAreaConocimientoPreguntaDistractorDI.findByRange(params.getOffset(), params.getLimit())
                    .stream()
                    .map(r -> PruebaClaveAreaConocimientoPreguntaDistractorDI.toDto(r)).toList();
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid PruebaClaveAreaConocimientoPreguntaDistractorDTO dto, @Context UriInfo uriInfo) {
        try {
            PruebaClaveAreaConocimientoPreguntaDistractor found = PruebaClaveAreaConocimientoPreguntaDistractorDI
                    .findById(new PruebaClaveAreaConocimientoPreguntaDistractorPK(
                            dto.idPruebaClave(),
                            dto.idAreaConocimiento(),
                            dto.idPregunta(),
                            dto.idDistractor()
                    ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPruebaClave:" + dto.idPruebaClave() + ", IdAreaConocimiento: " + dto.idAreaConocimiento()
                                + ", IdPregunta: " + dto.idPregunta() + ", IdDistractor: " + dto.idDistractor(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PruebaClaveAreaConocimientoPreguntaDistractor entity = PruebaClaveAreaConocimientoPreguntaDistractorDI.toEntity(dto);
            PruebaClaveAreaConocimientoPreguntaDistractorDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

}
