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
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO.PreguntaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaAreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaDistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoPreguntaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
/**
 *
 * @author usermein
 */
@Path("pregunta")
public class PreguntaResource {
    @Inject
    PreguntaDAOImp preguntaDI;
    @Inject
    PreguntaAreaConocimientoDAOImp preguntaAreaconocimientoDI;
    @Inject
    PruebaClaveAreaConocimientoPreguntaDAOImp pruebaClaveAreaConocimientoPreguntaDI;
    @Inject
    PreguntaDistractorDAOImp preguntaDistractorDAOImp;
    
    
    
     @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(PreguntaDTO preguntaDTO, @Context UriInfo uriInfo) {
        if (preguntaDTO == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            Pregunta nuevaPregunta = new Pregunta();
            nuevaPregunta.setValor(preguntaDTO.valor());
            nuevaPregunta.setActivo(preguntaDTO.activo());
            nuevaPregunta.setImagenUrl(preguntaDTO.imageUrl());
            nuevaPregunta.setObservaciones(preguntaDTO.observaciones());
            //creamos la nueva jornada, en teoria se deberia de guardar en la db
            preguntaDI.create(nuevaPregunta);
            URI uriCreada = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(nuevaPregunta.getIdPregunta()))
                    .build();

            return Response.created(uriCreada).entity(preguntaDTO).build();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al crear la pregunta", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear la pregunta" + e.getMessage())
                    .build();
        }

    }

    @DELETE
    @Path("/{idPregunta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idPregunta") Long idPregunta) {
        if (idPregunta != null && idPregunta > 0) {
            try {
                Pregunta pregunta = preguntaDI.findById(idPregunta);
                if (pregunta != null) {
                    preguntaDI.delete(pregunta);
                    return Response.noContent().build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Pregunta no encontrada con ID " + idPregunta).build();
                }
            } catch (Exception e) {
                Logger.getLogger(getClass().getName())
                        .log(Level.SEVERE, "Error al eliminar Pregunta", e);
                return Response.serverError()
                        .entity("Error interno " + e.getMessage())
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El ID que se ingreso no es valido.")
                    .build();
        }
    }

    @GET
    @Path("/{idPregunta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("idPregunta") Long idPregunta) {
        if (idPregunta != null && idPregunta > 0) {
            try {
                Pregunta pregunta = preguntaDI.findById(idPregunta);
                if (pregunta != null) {
                    PreguntaDTO preguntaDTO = new PreguntaDTO(pregunta);
                    return Response.ok(preguntaDTO).build();
                }
                return Response.status(Response.Status.NOT_FOUND).header("NOT_FOUNT_ID", idPregunta)
                        .entity("No se encontro el recurso con ID: " + idPregunta)
                        .build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "ERROR", e.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }

        }
        return Response.status(Response.Status.BAD_REQUEST).header("WRONG_PARAMETER", idPregunta).build();
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
                List<Pregunta> lista = preguntaDI.findByRange(first, max);

                List<PreguntaDTO> listaDTO = lista
                        .stream()
                        .map(pregunta -> new PreguntaDTO(pregunta))
                        .collect(Collectors.toList());
                long total = preguntaDI.count();
                return Response.ok(listaDTO).header("TOTAL_RECORD", total)
                        .build();

            } else {
                return Response.status(Response.Status.BAD_REQUEST).header("WRONG_PARAMETER", first + "-" + max).build();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "ERROR", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

        }

    }

    @PUT
    @Path("/{idPregunta}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("idPregunta") Long idPregunta,
            PreguntaDTO preguntaDTO,
            @Context UriInfo uriInfo) {

        if (idPregunta != null && preguntaDTO != null) {
            try {
                Pregunta preguntaEncontrada = preguntaDI.findById(idPregunta);
                if (preguntaEncontrada == null) {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Pregunta no encontrada con ID: " + idPregunta)
                            .build();
                }
                //Si el campo es null o no está en el combo, el campo no se actualizará en la base de datos.
                if (preguntaDTO.valor() != null) {
                    preguntaEncontrada.setValor(preguntaDTO.valor());
                }
                if (preguntaDTO.activo() != null) {
                   preguntaEncontrada.setActivo(preguntaDTO.activo());
                }
                if (preguntaDTO.imageUrl() != null) {
                  preguntaEncontrada.setImagenUrl(preguntaDTO.imageUrl());
                }
                if (preguntaDTO.observaciones() != null) {
                    preguntaEncontrada.setObservaciones(preguntaDTO.observaciones());
                }

                preguntaDI.update(preguntaEncontrada);
                return Response.ok()
                        .location(uriInfo.getAbsolutePathBuilder().build())
                        .build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al actualizar ", e.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("error al actualzar :" + e.getMessage())
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ID y datos del Distractor son requeridos")
                    .build();
        }

    }

            
    
}
    

