package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.DomainException;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid PruebaJornadaAulaAspiranteOpcionDTO dto, @Context UriInfo uriInfo) throws DomainException {
        try {
            PruebaJornadaAulaAspiranteOpcion newPruebaJornadaAulaAspiranteOpcion = PruebaJornadaAulaAspiranteOpcionDI.toEntity(dto);
            PruebaJornadaAulaAspiranteOpcionDI.create(newPruebaJornadaAulaAspiranteOpcion);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            uriBuilder
                    .path(String.valueOf(dto.idPrueba()))
                    .path(String.valueOf(dto.idJornada()))
                    .path(dto.idAula())
                    .path(String.valueOf(dto.idAspiranteOpcion()))
                    .build();
            return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Path("/{idPrueba:\\d}/{idJornada:\\d+}/{idAula}/{idAspiranteOpcion:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("idPrueba") @Min(1L) @Max(Long.MAX_VALUE) long idPrueba,
            @PathParam("idJornada") @Min(1L) @Max(Long.MAX_VALUE) long idJornada,
            @PathParam("idAula") @NotBlank String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) @Max(Long.MAX_VALUE) long idAspiranteOpcion,
            @Context UriInfo uriInfo
    ) throws DomainException {
        try {
            PruebaJornadaAulaAspiranteOpcion found = PruebaJornadaAulaAspiranteOpcionDI.findById(
                    new PruebaJornadaAulaAspiranteOpcion(
                            idPrueba,
                            idJornada,
                            idAula,
                            idAspiranteOpcion
                    ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPrueba:" + idPrueba + ", IdJornada: " + idJornada
                                + ", IdAula: " + idAula + ", IdAspiranteOpcion: " + idAspiranteOpcion,
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
    @Path("/{idPrueba:\\d}/{idJornada:\\d+}/{idAula}/{idAspiranteOpcion:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(
            @PathParam("idPrueba") @Min(1L) @Max(Long.MAX_VALUE) long idPrueba,
            @PathParam("idJornada") @Min(1L) @Max(Long.MAX_VALUE) long idJornada,
            @PathParam("idAula") @NotBlank String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) @Max(Long.MAX_VALUE) long idAspiranteOpcion,
            @Context UriInfo uriInfo
    ) throws DomainException {
        try {
            PruebaJornadaAulaAspiranteOpcion found = PruebaJornadaAulaAspiranteOpcionDI
                    .findById(new PruebaJornadaAulaAspiranteOpcionPK(
                            idPrueba,
                            idJornada,
                            idAula,
                            idAspiranteOpcion
                    ));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPrueba:" + idPrueba + ", IdJornada: " + idJornada
                                + ", IdAula: " + idAula + ", IdAspiranteOpcion: " + idAspiranteOpcion,
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
                    .findById(new PruebaJornadaAulaAspiranteOpcionPK(dto.idPrueba(), dto.idJornada(), dto.idAula(), dto.idAspiranteOpcion()));
            if (found == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(
                                null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: IdPrueba:" + dto.idPrueba() + ", IdJornada: " + dto.idJornada()
                                + ", IdAula: " + dto.idAula() + ", IdAspiranteOpcion: " + dto.idAspiranteOpcion(),
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
