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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.DistractorAreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.DistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.DistractorAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimientoPK;
/**
 *
 * @author usermein
 */
@Path("distractor-areaconocimiento")
public class DistractorAreaConocimientoResource {
    @Inject
    DistractorAreaConocimientoDAOImp distractorAreaConocimientoDI; 
    @Inject
    DistractorDAOImp distractroDI;
    @Inject
    AreaConocimientoDAOImp  AreaConocimientoDI;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid DistractorAreaConocimientoDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            DistractorAreaConocimiento newDistractorAreaConocimiento  = distractorAreaConocimientoDI.toEntity(dto);
            //Aqui tengo la duda sobre lo que ibamos a consumir desde fuera, que son las aulas entonces me parece algo ilogico comprobar con el
            //findById si no tenemos una tabla directamente en eso
            if (distractorAreaConocimientoDI.findById(dto.idAreaConocimiento()) == null && distractorAreaConocimientoDI.findById(dto.idAreaConocimiento()) == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdDistractor:" + dto.idDistractor()+ ", IdAreaConocimiento: " + dto.idAreaConocimiento(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            distractorAreaConocimientoDI.create(newDistractorAreaConocimiento);
            UriBuilder   uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder
                    .queryParam("idDistractor", String.valueOf(dto.idDistractor()))
                    .queryParam("idAreaConocimiento", String.valueOf(dto.idAreaConocimiento()))
                    .build();
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@Valid DistractorAreaConocimientoPK key, @Context UriInfo uriInfo) {
        try {
             DistractorAreaConocimiento found = distractorAreaConocimientoDI.findById(
                    new  DistractorAreaConocimientoPK(
                            key.getIdDistractor(),
                            key.getIdAreaConocimiento())
            );
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdDistractor:" + key.getIdDistractor() + ", IdAreaConocimiento: " + key.getIdAreaConocimiento(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            distractorAreaConocimientoDI.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@Valid  DistractorAreaConocimientoPK key, @Context UriInfo uriInfo) throws DomainException {
        try {
             DistractorAreaConocimiento found =  distractorAreaConocimientoDI.findById(key);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdDistractor:" + key.getIdDistractor() + ", IdAreaConocimiento: " + key.getIdAreaConocimiento(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        ))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            return Response.ok(distractorAreaConocimientoDI.toDto(found), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam  FindRangeParamDTO params) throws DomainException {
        try {
            List<DistractorAreaConocimientoDTO> resultList = distractorAreaConocimientoDI.findByRange(params.getOffset(), params.getLimit())
                    .stream()
                    .map(r -> distractorAreaConocimientoDI.toDto(r)).toList();
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid DistractorAreaConocimientoDTO dto, @Context UriInfo uriInfo) {
        try {
            DistractorAreaConocimiento found = distractorAreaConocimientoDI.findById(new DistractorAreaConocimientoPK(dto.idDistractor(), dto.idAreaConocimiento()));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdDistractor:" + dto.idDistractor() + ", IdAreaConocimiento: " + dto.idAreaConocimiento(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            DistractorAreaConocimiento entity = distractorAreaConocimientoDI.toEntity(dto);
            distractorAreaConocimientoDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
}
