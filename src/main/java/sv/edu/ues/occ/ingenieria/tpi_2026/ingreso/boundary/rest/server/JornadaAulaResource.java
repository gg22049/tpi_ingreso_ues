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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaAulaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.JornadaAulaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAula;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAulaPK;

/**
 *
 * @author usermein
 */
@Path("jornada-aula")
public class JornadaAulaResource {

    @Inject
    JornadaAulaDAOImp jornadaAulaDI;
    
   

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid JornadaAulaDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            JornadaAula nuevaJornadaAula= jornadaAulaDI.toEntity(dto);
            //Aqui tengo la duda sobre lo que ibamos a consumir desde fuera, que son las aulas entonces me parece algo ilogico comprobar con el
            //findById si no tenemos una tabla directamente en eso
            if (jornadaAulaDI.findById(dto.idAula()) == null && jornadaAulaDI.findById(dto.idJornada()) == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdJornada:" + dto.idJornada()+ ", IdAula: " + dto.idAula(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            jornadaAulaDI.create(nuevaJornadaAula);
            UriBuilder   uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder
                    .queryParam("idJornada", String.valueOf(dto.idJornada()))
                    .queryParam("idAula", String.valueOf(dto.idAula()))
                    .build();
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@Valid JornadaAulaPK key, @Context UriInfo uriInfo) {
        try {
            JornadaAula found = jornadaAulaDI.findById(
                    new JornadaAulaPK(
                            key.getIdJornada(),
                            key.getIdAula())
            );
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdJornada:" + key.getIdJornada() + ", IdAula: " + key.getIdAula(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            jornadaAulaDI.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@Valid JornadaAulaPK key, @Context UriInfo uriInfo) throws DomainException {
        try {
            JornadaAula found = jornadaAulaDI.findById(key);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdJornada:" + key.getIdJornada() + ", IdAula: " + key.getIdAula(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        ))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            return Response.ok(jornadaAulaDI.toDto(found), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam  FindRangeParamDTO params) throws DomainException {
        try {
            List<JornadaAulaDTO> resultList = jornadaAulaDI.findByRange(params.getOffset(), params.getLimit())
                    .stream()
                    .map(r -> jornadaAulaDI.toDto(r)).toList();
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid JornadaAulaDTO dto, @Context UriInfo uriInfo) {
        try {
            JornadaAula found = jornadaAulaDI.findById(new JornadaAulaPK(dto.idJornada(), dto.idAula()));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdJornada:" + dto.idJornada() + ", IdAula: " + dto.idAula(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            JornadaAula entity = jornadaAulaDI.toEntity(dto);
            jornadaAulaDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
}
