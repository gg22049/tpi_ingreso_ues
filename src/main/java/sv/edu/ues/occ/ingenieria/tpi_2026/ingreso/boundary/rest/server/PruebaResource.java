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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;

/**
 *
 * @author usermein
 */
@Path("prueba")
public class PruebaResource {

    @Inject
    PruebaDAOImp pruebaDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@NotNull @Valid PruebaDTO pruebaDTO, UriInfo uriInfo) throws DomainException {
        try {
            Prueba nuevaPrueba = pruebaDI.toEntity(pruebaDTO);
            pruebaDI.create(nuevaPrueba);
            URI uriCreada = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(nuevaPrueba.getIdPrueba()))
                    .build();
            return Response.created(uriCreada)
                    .entity(pruebaDTO)
                    .build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Path("/{idPrueba:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idPrueba") @Min(1) @Max(Integer.MAX_VALUE) Integer idPrueba, @Context UriInfo uriInfo) throws DomainException {
        try {
            Prueba prueba = pruebaDI.findById(idPrueba);
            if (prueba == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe Prueba con ID: " + idPrueba,
                                uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            pruebaDI.delete(prueba);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Path("/{idPrueba:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(
            @PathParam("idPrueba") @Min(1) @Max(Integer.MAX_VALUE) Integer idPrueba,
            @Context UriInfo uriInfo
    ) throws DomainException {
        try {
            Prueba prueba = pruebaDI.findById(idPrueba);
            if (prueba == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe Prueba con ID: " + idPrueba,
                                uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            PruebaDTO pruebaDTO = pruebaDI.toDto(prueba);
            return Response.ok(pruebaDTO, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<Prueba> listaPruebas = pruebaDI.findByRange(params.getOffset(), params.getLimit());
            List<PruebaDTO> listaPruebasDTO = listaPruebas
                    .stream()
                    .map(distractor -> pruebaDI.toDto(distractor))
                    .collect(Collectors.toList());
            return Response
                    .ok(listaPruebasDTO)
                    .header(HeaderName.TOTAL_RECORDS.toString(), listaPruebasDTO.size())
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Path("/{idPrueba:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("idPrueba") @Min(1L) @Max(Long.MAX_VALUE) Long idPrueba,
            @Valid PruebaDTO pruebaDTO,
            @Context UriInfo uriInfo) throws DomainException {
        try {
            Prueba prueba = pruebaDI.findById(idPrueba);
            if (prueba == null) {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe Prueba con ID: " + idPrueba,
                                uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            Prueba entity = pruebaDI.toEntity(pruebaDTO);
            entity.setIdPrueba(idPrueba);
            pruebaDI.update(prueba);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
}
