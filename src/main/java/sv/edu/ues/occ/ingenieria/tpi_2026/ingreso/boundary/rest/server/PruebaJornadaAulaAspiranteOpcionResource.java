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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AspiranteOpcionDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaAulaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaAulaAspiranteOpcionDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaJornadaAulaAspiranteOpcionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionPK;
/**
 *
 * @author usermein
 */
@Path("prueba-jornada-aula-aspirante-opcion")
public class PruebaJornadaAulaAspiranteOpcionResource {
    @Inject
   PruebaJornadaAulaAspiranteOpcionDAOImp PruebaJornadaAulaAspiranteOpcionDI;
    @Inject
    JornadaAulaDAOImp jornadaAulaDI;
    @Inject
    PruebaDAOImp pruebaDI;
    @Inject 
    AspiranteOpcionDAOImp aspiranteOpcionDI ;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid PruebaJornadaAulaAspiranteOpcionDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaJornadaAulaAspiranteOpcion newPruebaJornadaAulaAspiranteOpcion = PruebaJornadaAulaAspiranteOpcionDI.toEntity(dto);
            //Aqui tengo la duda sobre lo que ibamos a consumir desde fuera, que son las aulas entonces me parece algo ilogico comprobar con el
            //findById si no tenemos una tabla directamente en eso
            if (PruebaJornadaAulaAspiranteOpcionDI.findById(dto.idPrueba()) == null
                    && PruebaJornadaAulaAspiranteOpcionDI.findById(dto.idJornada()) == null
                    && PruebaJornadaAulaAspiranteOpcionDI.findById(dto.idAula())==null
                    && PruebaJornadaAulaAspiranteOpcionDI.findById(dto.idAspiranteOpcion())==null) {
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
            PruebaJornadaAulaAspiranteOpcionDI.create(newPruebaJornadaAulaAspiranteOpcion);
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
    public Response delete(@Valid PruebaJornadaAulaAspiranteOpcionPK key, @Context UriInfo uriInfo) {
        try {
            PruebaJornadaAulaAspiranteOpcion found = PruebaJornadaAulaAspiranteOpcionDI.findById(
                    new PruebaJornadaAulaAspiranteOpcionPK(
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
            PruebaJornadaAulaAspiranteOpcionDI.delete(found);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@Valid PruebaJornadaAulaAspiranteOpcionPK key, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaJornadaAulaAspiranteOpcion found = PruebaJornadaAulaAspiranteOpcionDI.findById(key);
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
            return Response.ok(PruebaJornadaAulaAspiranteOpcionDI.toDto(found), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<PruebaJornadaAulaAspiranteOpcionDTO> resultList = PruebaJornadaAulaAspiranteOpcionDI.findByRange(params.getOffset(), params.getLimit())
                    .stream()
                    .map(r -> PruebaJornadaAulaAspiranteOpcionDI.toDto(r)).toList();
            return Response.ok(resultList).header(HeaderName.TOTAL_RECORDS.toString(), resultList.size()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid PruebaJornadaAulaAspiranteOpcionDTO dto, @Context UriInfo uriInfo) {
        try {
        PruebaJornadaAulaAspiranteOpcion found = PruebaJornadaAulaAspiranteOpcionDI
                    .findById(new PruebaJornadaAulaAspiranteOpcionPK(dto.idPrueba(), dto.idJornada(),dto.idAula(), dto.idAspiranteOpcion()));
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
           PruebaJornadaAulaAspiranteOpcion entity = PruebaJornadaAulaAspiranteOpcionDI.toEntity(dto);
            PruebaJornadaAulaAspiranteOpcionDI.update(entity);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
    
    
}
