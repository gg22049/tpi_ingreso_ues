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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaAulaAspiranteOpcionExamenDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaJornadaAulaAspiranteOpcionExamenDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamen;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamenPK;

/**
 *
 * @author usermein
 */
@Path("prueba-jornada-aula-aspirante-opcion-examen")
public class PruebaJornadaAulaAspiranteOpcionExamenResource {
   @Inject
   PruebaJornadaAulaAspiranteOpcionExamenDAOImp PruebaJornadaAulaAspiranteOpcionExamenDI;
  
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid PruebaJornadaAulaAspiranteOpcionExamenDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaJornadaAulaAspiranteOpcionExamen newPruebaJornadaAulaAspiranteOpcionExamen = PruebaJornadaAulaAspiranteOpcionExamenDI.toEntity(dto);
            //Aqui tengo la duda sobre lo que ibamos a consumir desde fuera, que son las aulas entonces me parece algo ilogico comprobar con el
            //findById si no tenemos una tabla directamente en eso
            if (PruebaJornadaAulaAspiranteOpcionExamenDI.findById(dto.idPrueba()) == null
                    && PruebaJornadaAulaAspiranteOpcionExamenDI.findById(dto.idJornada()) == null
                    && PruebaJornadaAulaAspiranteOpcionExamenDI.findById(dto.idAula())==null
                    && PruebaJornadaAulaAspiranteOpcionExamenDI.findById(dto.idAspiranteOpcion())==null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPrueba:" + dto.idPrueba() + ", IdJornada: " + dto.idJornada()
                                + ", IdAula: " + dto.idAula()+ ", IdAspiranteOpcion: " + dto.idAspiranteOpcion(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PruebaJornadaAulaAspiranteOpcionExamenDI.create(newPruebaJornadaAulaAspiranteOpcionExamen);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder
                    .queryParam("idPrueba", String.valueOf(dto.idPrueba()))
                    .queryParam("idJornada", String.valueOf(dto.idJornada()))
                    .queryParam("idAula",dto.idAula())
                    .queryParam("idAspiranteOpcion", String.valueOf(dto.idAspiranteOpcion()))
                    .build();
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@Valid PruebaJornadaAulaAspiranteOpcionExamenPK key, @Context UriInfo uriInfo) {
        try {
            PruebaJornadaAulaAspiranteOpcionExamen found = PruebaJornadaAulaAspiranteOpcionExamenDI.findById(
                    new PruebaJornadaAulaAspiranteOpcionExamenPK(
                            key.getIdPrueba(),
                            key.getIdJornada(),
                            key.getIdAula(),
                            key.getIdAspiranteOpcion())
            );
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPrueba:" + key.getIdPrueba() + ", IdJornada: " + key.getIdJornada()
                                + ", IdAula: " + key.getIdAula()+ ", IdAspiranteOpcion: " + key.getIdAspiranteOpcion(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
            PruebaJornadaAulaAspiranteOpcionExamenDI.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@Valid PruebaJornadaAulaAspiranteOpcionExamenPK key, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaJornadaAulaAspiranteOpcionExamen found = PruebaJornadaAulaAspiranteOpcionExamenDI.findById(key);
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                  "No entity with id: IdPrueba:" + key.getIdPrueba() + ", IdJornada: " + key.getIdJornada()
                                + ", IdAula: " + key.getIdAula()+ ", IdAspiranteOpcion: " + key.getIdAspiranteOpcion(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        ))
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            return Response.ok(PruebaJornadaAulaAspiranteOpcionExamenDI.toDto(found), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<PruebaJornadaAulaAspiranteOpcionExamenDTO> resultList = PruebaJornadaAulaAspiranteOpcionExamenDI.findByRange(params.getOffset(), params.getLimit())
                    .stream()
                    .map(r -> PruebaJornadaAulaAspiranteOpcionExamenDI.toDto(r)).toList();
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid PruebaJornadaAulaAspiranteOpcionExamenDTO dto, @Context UriInfo uriInfo) {
        try {
        PruebaJornadaAulaAspiranteOpcionExamen found = PruebaJornadaAulaAspiranteOpcionExamenDI
                    .findById(new PruebaJornadaAulaAspiranteOpcionExamenPK(dto.idPrueba(),
                            dto.idJornada(),
                            dto.idAula(),
                            dto.idAspiranteOpcion()));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                 "No entity with id: IdPrueba:" + dto.idPrueba() + ", IdJornada: " + dto.idJornada()
                                + ", IdAula: " + dto.idAula()+ ", IdAspiranteOpcion: " + dto.idAspiranteOpcion(),
                                uriInfo.getAbsolutePath().toString(),
                                null
                        )
                        ).build();
            }
           PruebaJornadaAulaAspiranteOpcionExamen entity = PruebaJornadaAulaAspiranteOpcionExamenDI.toEntity(dto);
            PruebaJornadaAulaAspiranteOpcionExamenDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
    
}
