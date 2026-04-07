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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.TipoIdentificacionDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.TipoIdentificacionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoIdentificacion;

/**
 *
 * @author usermein
 */
@Path("tipoIdentificacion")
public class TipoIdentificacionResource {

    @Inject
    TipoIdentificacionDAOImp tipoIdentificacionDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@NotNull @Valid TipoIdentificacionDTO tipoIdentificacionDTO, UriInfo uriInfo) throws DomainException {
        try {

            //TipoIdentificacion nuevoTipoIdentificacion = new TipoIdentificacion();
            TipoIdentificacion nuevoTipoIdentificacion = tipoIdentificacionDI.toEntity(tipoIdentificacionDTO);
            //nuevoTipoIdentificacion.setNombre(tipoIdentificacionDTO.nombre());
            // nuevoTipoIdentificacion.setObservaciones(tipoIdentificacionDTO.observaciones());
            tipoIdentificacionDI.create(nuevoTipoIdentificacion);
            URI uriCreada = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(nuevoTipoIdentificacion.getIdTipoIdentificacion()))
                    .build();
            return Response.created(uriCreada)
                    .entity(tipoIdentificacionDTO)
                    .build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Path("idTipoIdentificacion:\\d+")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idTipoIdentificacion") @Min(1) @Max(Integer.MAX_VALUE) Integer idTipoIdentificacion, @Context UriInfo uriInfo) throws DomainException {
        try {
            TipoIdentificacion tipoIdentificacion = tipoIdentificacionDI.findById(idTipoIdentificacion);
            if (tipoIdentificacion == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe TipoPrueba con ID: " + idTipoIdentificacion,
                                 uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            tipoIdentificacionDI.delete(tipoIdentificacion);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Path("idTipoIdentificacion:\\d+")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("idTipoIdentificacion") @Min(1) @Max(Integer.MAX_VALUE) Integer idTipoIdentificacion,
            @Context UriInfo uriInfo
    ) throws DomainException {
        try {
            TipoIdentificacion tipoIdentificacion = tipoIdentificacionDI.findById(idTipoIdentificacion);
            if (tipoIdentificacion == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe TipoPrueba con ID: " + idTipoIdentificacion,
                                 uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            //Por mientras voy a ocupar el constructor normal ya que vamos a borrar los otros
            TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO(idTipoIdentificacion,
                    tipoIdentificacion.getNombre(),
                    tipoIdentificacion.getObservaciones());
            return Response.ok(tipoIdentificacionDTO, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<TipoIdentificacion> listaAspirante = tipoIdentificacionDI.findByRange(params.getOffset(), params.getLimit());
            List<TipoIdentificacionDTO> listaTipoIdentificacionDTOs = listaAspirante
                    .stream()
                    .map(tipoIdentificacion -> new TipoIdentificacionDTO(tipoIdentificacion.getIdTipoIdentificacion(),
                    tipoIdentificacion.getNombre(),
                    tipoIdentificacion.getObservaciones()))
                    .collect(Collectors.toList());
            return Response
                    .ok(listaTipoIdentificacionDTOs)
                    .header(HeaderName.TOTAL_RECORDS.toString(), listaTipoIdentificacionDTOs.size())
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @PUT
    @Path("/{idTipoIdentificacion:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("idTipoIdentificacion") Integer idTipoIdentificacion,
            TipoIdentificacionDTO tipoIdentificacionDTO,
            @Context UriInfo uriInfo) throws DomainException {
        try {
            TipoIdentificacion tipoIdentificacion = tipoIdentificacionDI.findById(idTipoIdentificacion);
            if (tipoIdentificacion == null) {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity(
                                new ErrorDetailDTO(
                                        null,
                                        ErrorType.NO_MATCH_ID.toString(),
                                        404,
                                        "No existe TipoIdentificacion con ID: " + idTipoIdentificacion,
                                        uriInfo.getAbsolutePath().toString(),
                                        null
                                )
                        ).build();
            }
            if (tipoIdentificacionDTO.nombre() != null) {
                tipoIdentificacion.setNombre(tipoIdentificacionDTO.nombre());

            }
            if (tipoIdentificacionDTO.observaciones() != null) {
                tipoIdentificacion.setObservaciones(tipoIdentificacionDTO.observaciones());
            }
            tipoIdentificacionDI.update(tipoIdentificacion);
            return Response.noContent().build();

        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
}
