package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.DomainException;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.DistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.DistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;

/**
 *
 * @author usermein
 */
@Path("distractor")
public class DistractorResource {

    @Inject
    DistractorDAOImp distractorDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@NotNull @Valid DistractorDTO distractorDTO, @Context UriInfo uriInfo) throws DomainException {
        try {
            Distractor nuevoDistractor = distractorDI.toEntity(distractorDTO);
            distractorDI.create(nuevoDistractor);
            URI uriCreada = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(nuevoDistractor.getIdDistractor()))
                    .build();
            return Response
                    .created(uriCreada)
                    .entity(distractorDTO)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Path("/{idDistractor:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idDistractor") @Min(1) @Max(Long.MAX_VALUE) Long idDistractor, @Context UriInfo uriInfo) throws DomainException {
        try {
            Distractor distractor = distractorDI.findById(idDistractor);
            if (distractor == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe TipoPrueba con ID: " + idDistractor,
                                uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            distractorDI.delete(distractor);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Path("/{idDistractor:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("idDistractor") @Min(1) @Max(Long.MAX_VALUE) Long idDistractor,
            @Context UriInfo uriInfo
    ) throws DomainException {
        try {
            Distractor distractor = distractorDI.findById(idDistractor);
            if (distractor == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe TipoPrueba con ID: " + idDistractor,
                                uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            DistractorDTO distractorDTO = distractorDI.toDto(distractor);
            return Response.ok(distractorDTO, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<Distractor> listaDistractores = distractorDI.findByRange(params.getOffset(), params.getLimit());
            List<DistractorDTO> listaDistracoresDTO = listaDistractores
                    .stream()
                    .map(distractor -> distractorDI.toDto(distractor))
                    .collect(Collectors.toList());
            return Response
                    .ok(listaDistracoresDTO)
                    .header(HeaderName.TOTAL_RECORDS.toString(), listaDistracoresDTO.size())
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Path("/{idDistractor:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("idDistractor") @Min(1) Long idDistractor,
            DistractorDTO distractorDTO,
            @Context UriInfo uriInfo) throws DomainException {
        try {
            Distractor distractor = distractorDI.findById(idDistractor);
            if (distractor == null) {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No entity with id: " + idDistractor,
                                uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            distractor.setValor(distractorDTO.valor());
            distractor.setActivo(distractorDTO.activo());
            distractor.setImagenUrl(distractorDTO.imagenUrl());

            distractorDI.update(distractor);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

}
