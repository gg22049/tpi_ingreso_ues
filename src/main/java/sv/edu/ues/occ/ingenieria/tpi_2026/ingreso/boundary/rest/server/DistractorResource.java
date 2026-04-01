package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO.DistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO.JornadaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.DistractorAreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.DistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaDistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoPreguntaDistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;

/**
 *
 * @author usermein
 */
@Path("distractor")
public class DistractorResource {

    @Inject
    DistractorDAOImp distractorDI;

    @Inject
    PruebaClaveAreaConocimientoPreguntaDistractorDAOImp pruebaClaveAreaConocimientoPreguntaDistractorDI;

    @Inject
    DistractorAreaConocimientoDAOImp distractorAreaConocimientoDI;

    @Inject
    PreguntaDistractorDAOImp preguntaDistractorDI;

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(DistractorDTO distractorDTO, @Context UriInfo uriInfo) {
        if (distractorDTO == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
        try {
            Distractor nuevoDistractor = new Distractor();
            nuevoDistractor.setValor(distractorDTO.valor());
            nuevoDistractor.setActivo(distractorDTO.activo());
            nuevoDistractor.setImagenUrl(distractorDTO.imagenUrl());

            distractorDI.create(nuevoDistractor);
            URI uriCreada = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(nuevoDistractor.getIdDistractor()))
                    .build();
            return Response.created(uriCreada)
                    .entity(distractorDTO)
                    .build();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al crear el Distractor", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el Distractor " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{idPrueba}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idDistractor") Long idDistractor) {
        if (idDistractor != null && idDistractor > 0) {
            try {
                Distractor distractorEncontrado = distractorDI.findById(idDistractor);
                if (distractorEncontrado != null) {
                    distractorDI.delete(distractorEncontrado);
                    return Response.noContent().build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Distractor no encontrado con ID :" + idDistractor)
                            .build();
                }

            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al eliminar el Distractor");
                return Response.serverError()
                        .entity("Error interno " + e.getMessage())
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El ID que se ingreso es invalido")
                    .build();
        }
    }

    @GET
    @Path("/{idDistrator}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("idDistractor") Long idDistractor) {
        if (idDistractor != null && idDistractor > 0) {
            try {
                Distractor distractor = distractorDI.findById(idDistractor);
                if (distractor != null) {

                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Distractor no encontrado")
                            .build();
                }

            } catch (Exception e) {
                Logger.getLogger(getClass().getName())
                        .log(Level.SEVERE, "Error al eliminar el Distractor", e.getMessage());
                return Response.serverError()
                        .entity("Error interno " + e.getMessage())
                        .build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("El ID ingresado es invalido")
                .build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(
            @QueryParam("first") @DefaultValue("0") int first,
            @QueryParam("max") @DefaultValue("50") int max
    ) {

        try {
            if (first >= 0 && max <= 50) {
                List<Distractor> listaDistractores = distractorDI.findByRange(first, max);
                List<DistractorDTO> listaDistractorDTOs = listaDistractores
                        .stream()
                        .map(distractor -> new DistractorDTO(distractor))
                        .collect(Collectors.toList());
                Long total = distractorDI.count();
                return Response
                        .ok(listaDistractorDTOs)
                        .header("TOTAL RECORD", total)
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .header("WRONG_PARAMETER", first + "-" + max)
                        .build();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "ERROR", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{idDistractor}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("idDistractor") Long idDistractor,
            DistractorDTO distractorDTO,
            @Context UriInfo uriInfo) {
        if (idDistractor != null && distractorDTO != null) {
            try {
                Distractor distractorEncontrado = distractorDI.findById(idDistractor);
                if (distractorEncontrado == null) {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Distractor no encontrado con ID: " + idDistractor)
                            .build();
                }
                if (distractorDTO.valor() != null) {
                    distractorEncontrado.setValor(distractorDTO.valor());
                }
                if (distractorDTO.activo()) {
                    distractorEncontrado.setActivo(distractorDTO.activo());
                }
                if (distractorDTO.imagenUrl() != null) {
                    distractorEncontrado.setImagenUrl(distractorDTO.imagenUrl());
                }
                distractorDI.update(distractorEncontrado);
                return Response.ok()
                        .location(uriInfo.getAbsolutePathBuilder().build())
                        .build();

            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al actualizar el distractor", e.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error al actualizar ;" + e.getMessage())
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ID y datos de la Jornada son requeridos")
                    .build();
        }

    }

}
