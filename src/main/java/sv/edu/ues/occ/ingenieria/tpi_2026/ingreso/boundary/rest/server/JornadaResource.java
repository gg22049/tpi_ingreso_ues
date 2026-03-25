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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaAulaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO.JornadaDTO;

/**
 *
 * @author usermein
 */
@Path("jornada")
public class JornadaResource implements Serializable {

    @Inject
    JornadaDAOImp jornadaDI;
    @Inject
    JornadaAulaDAOImp jornadaAulaDI;
    @Inject
    PruebaJornadaDAOImp pruebaJornadaDI;

    /**
     * Metodo para crear una jornada
     *
     * @param jornadaDTO
     * @param uriInfo
     * @return
     */
    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(JornadaDTO jornadaDTO, @Context UriInfo uriInfo) {
        if (jornadaDTO == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            //no hay que hacer set en el id de nueva jornada por que la db lo va a generar
            //aqui creo que deberiamos validar que ninguna de estos datos sea null
            Jornada nuevaJornada = new Jornada();
            nuevaJornada.setNombre(jornadaDTO.nombre());
            nuevaJornada.setFechaInicio(jornadaDTO.fechaInicio());
            nuevaJornada.setFechaFin(jornadaDTO.fechaFin());
            nuevaJornada.setObservaciones(jornadaDTO.observaciones());
            //creamos la nueva jornada, en teoria se deberia de guardar en la db
            jornadaDI.create(nuevaJornada);
            //
            URI uriCreada = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(nuevaJornada.getIdJornada()))
                    .build();

            return Response.created(uriCreada).entity(jornadaDTO).build();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al crear la jornada", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear la jornada" + e.getMessage())
                    .build();
        }

    }

    @DELETE
    @Path("/{idJornada}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idJornada") Long idJornada) {
        if (idJornada != null && idJornada > 0) {
            try {
                Jornada jornada = jornadaDI.findById(idJornada);
                if (jornada != null) {
                    jornadaDI.delete(jornada);
                    return Response.noContent().build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Jornada no encontrada con ID " + idJornada).build();
                }
            } catch (Exception e) {
                Logger.getLogger(getClass().getName())
                        .log(Level.SEVERE, "Error al eliminar Jornada", e);
                return Response.serverError()
                        .entity("Error interno " + e.getMessage())
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{idJornada}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("idJornada") Long idJornada) {
        if (idJornada != null && idJornada > 0) {
            try {
                Jornada jornada = jornadaDI.findById(idJornada);
                if (jornada != null) {
                    JornadaDTO jornadaDTO = new JornadaDTO(jornada.getIdJornada(),
                            jornada.getNombre(),
                            jornada.getFechaInicio(),
                            jornada.getFechaFin(),
                            jornada.getObservaciones());
                    return Response.ok(jornadaDTO).build();
                }
                return Response.status(Response.Status.NOT_FOUND).header("NOT_FOUNT_ID", idJornada)
                        .entity("No se encontro el recurso con ID: " + idJornada)
                        .build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "ERROR", e.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }

        }
        return Response.status(Response.Status.BAD_REQUEST).header("WRONG_PARAMETER", idJornada).build();
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
                List<Jornada> lista = jornadaDI.findByRange(first, max);

                List<JornadaDTO> listaDTO = lista.stream()
                        .map(jornada -> new JornadaDTO(jornada.getIdJornada(),
                        jornada.getNombre(),
                        jornada.getFechaInicio(),
                        jornada.getFechaFin(),
                        jornada.getObservaciones()))
                        .collect(Collectors.toList());
                long total = jornadaDI.count();
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
    @Path("/{idJornada}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("idJornada") Long idJornada,
            JornadaDTO jornadaDTO,
            @Context UriInfo uriInfo) {

        if (idJornada != null && jornadaDTO != null) {
            try {
                Jornada jornadaEncontrada = jornadaDI.findById(idJornada);
                if (jornadaEncontrada == null) {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("Jornada no encontrada con ID: " + idJornada)
                            .build();
                }
                if (jornadaDTO.nombre() != null) {
                    jornadaEncontrada.setNombre(jornadaDTO.nombre());
                }
                if (jornadaDTO.fechaInicio() != null) {
                    jornadaEncontrada.setFechaInicio(jornadaDTO.fechaInicio());
                }
                if (jornadaDTO.fechaFin() != null) {
                    jornadaEncontrada.setFechaFin(jornadaDTO.fechaFin());
                }
                if (jornadaDTO.observaciones() != null) {
                    jornadaEncontrada.setObservaciones(jornadaDTO.observaciones());
                }

                jornadaDI.update(jornadaEncontrada);
                return Response.ok()
                        .location(uriInfo.getAbsolutePathBuilder().build())
                        .build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al actualizar ", e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("error al actualzar :" + e.getMessage())
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ID y datos de la Jornada son requeridos")
                    .build();
        }

    }
    
            
    
}
