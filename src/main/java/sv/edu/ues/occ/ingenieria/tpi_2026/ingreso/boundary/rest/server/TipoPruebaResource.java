/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.TipoPruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.TipoPruebaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
@Path("tipoPrueba")
public class TipoPruebaResource {

    @Inject
    TipoPruebaDAOImp tipoPruebaDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@NotNull @Valid TipoPruebaDTO tipoPruebaDTO, UriInfo uriInfo) throws DomainException {
        try {
            TipoPrueba nuevaTipoPrueba = new TipoPrueba();
            nuevaTipoPrueba.setValor(tipoPruebaDTO.valor());
            nuevaTipoPrueba.setActivo(tipoPruebaDTO.activo());
            nuevaTipoPrueba.setObservaciones(tipoPruebaDTO.observaciones());
            tipoPruebaDI.create(nuevaTipoPrueba);
            URI uriCreada = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(nuevaTipoPrueba.getIdTipoPrueba()))
                    .build();
            return Response.created(uriCreada)
                    .entity(tipoPruebaDTO)
                    .build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @DELETE
    @Path("idTipoPrueba:\\d+")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idTipoPrueba") @Min(1) @Max(Integer.MAX_VALUE) Integer idTipoPrueba, @Context UriInfo uriInfo) throws DomainException {
        try {
            TipoPrueba tipoPrueba = tipoPruebaDI.findById(idTipoPrueba);
            if (tipoPrueba == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe TipoPrueba con ID: " + idTipoPrueba,
                                 uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            tipoPruebaDI.delete(tipoPrueba);
            return Response.noContent().build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Path("idTipoPrueba:\\d+")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("idTipoPrueba") @Min(1) @Max(Integer.MAX_VALUE) Integer idTipoPrueba,
            @Context UriInfo uriInfo
    ) throws DomainException {
        try {
            TipoPrueba tipoPrueba = tipoPruebaDI.findById(idTipoPrueba);
            if (tipoPrueba == null) {
                return Response
                        .status(404)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe TipoPrueba con ID: " + idTipoPrueba,
                                 uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }
            TipoPruebaDTO tipoPruebaDTO = tipoPruebaDI.toDto(tipoPrueba);
            return Response.ok(tipoPruebaDTO, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            throw new DomainException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@Valid @BeanParam FindRangeParamDTO params) throws DomainException {
        try {
            List<TipoPrueba> listaAspirante = tipoPruebaDI.findByRange(params.getOffset(), params.getLimit());
            List<TipoPruebaDTO> listaTipoPruebaDTOs = listaAspirante
                    .stream()
                    .map(tipoPrueba -> tipoPruebaDI.toDto(tipoPrueba))
                    .collect(Collectors.toList());
            return Response
                    .ok(listaTipoPruebaDTOs)
                    .header(HeaderName.TOTAL_RECORDS.toString(), listaTipoPruebaDTOs.size())
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
    public Response update(@PathParam("idTipoPrueba") Integer idTipoPrueba,
            TipoPruebaDTO tipoPruebaDTO,
            @Context UriInfo uriInfo) throws DomainException {
        try {
            TipoPrueba tipoPrueba = tipoPruebaDI.findById(idTipoPrueba);
            if (tipoPrueba == null) {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity(new ErrorDetailDTO(null,
                                ErrorType.NO_MATCH_ID.toString(),
                                404,
                                "No existe TipoPrueba con ID: " + idTipoPrueba,
                                 uriInfo.getAbsolutePath().toString(),
                                null))
                        .build();
            }

            tipoPrueba.setValor(tipoPruebaDTO.valor());
            tipoPrueba.setActivo(tipoPruebaDTO.activo());

            if (tipoPruebaDTO.observaciones() != null) {
                tipoPrueba.setObservaciones(tipoPruebaDTO.observaciones());
            }
            tipoPruebaDI.update(tipoPrueba);
            return Response.noContent().build();

        } catch (Exception e) {
            throw new DomainException(e);
        }
    }
}
