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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO.PruebaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaAulaAspiranteOpcionDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
@Path("prueba")
public class PruebaResource {

    @Inject
    PruebaDAOImp pruebaDI;

    @Inject
    PruebaJornadaAulaAspiranteOpcionDAOImp pruebaJornadaAulaAspiranteOpcionDI;

    @Inject
    PruebaClaveDAOImp pruebaClaveDI;

    @Inject
    PruebaJornadaDAOImp pruebaJornadaDI;

    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(PruebaDTO pruebaDTO, @Context UriInfo uriInfo) {
        if (pruebaDTO == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            Prueba nuevaPrueba = new Prueba();
            //vamos a settear todos menos el idPrueba por que lo va a generar la db automaticamente

            nuevaPrueba.setNombre(pruebaDTO.nombre());
            nuevaPrueba.setIndicaciones(pruebaDTO.indicaciones());
            nuevaPrueba.setPuntajeMaximo(pruebaDTO.puntajeMaximo());
            nuevaPrueba.setNotaAprobacion(pruebaDTO.notaAprobacion());
            nuevaPrueba.setDuracion(pruebaDTO.duracion());
            nuevaPrueba.setFechaCreacion(pruebaDTO.fechaCreacion());
            //este if es para comprobar que hay una asignacion correcta del tipo de prueba
            //pero aja en este caso ya deberian de haber varios tipos de pruebas creados
            //o no se si esto se va a manejar de una manera distinta
            if (pruebaDTO.idTipoPrueba() == null || pruebaDTO.idTipoPrueba() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("el ID del tipo de Prueba es obligatorio.")
                        .build();

            }

            TipoPrueba tipoPrueba = new TipoPrueba();
            tipoPrueba.setIdTipoPrueba(pruebaDTO.idTipoPrueba());
            nuevaPrueba.setIdTipoPrueba(tipoPrueba);

            pruebaDI.create(nuevaPrueba);
            URI uriCreada = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(nuevaPrueba.getIdPrueba()))
                    .build();
            return Response.created(uriCreada).entity(pruebaDTO).build();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al crear la prueba/examen", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("error al crear la prueba/examen :" + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{idPrueba}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("idPrueba") Long idPrueba) {
        if (idPrueba != null && idPrueba > 0) {
            try {
                Prueba prueba = pruebaDI.findById(idPrueba);
                if (prueba != null) {
                    pruebaDI.delete(prueba);
                    return Response.noContent().build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity("prueba no encontrada con ID: " + idPrueba).build();
                }
            } catch (Exception e) {
                Logger.getLogger(getClass().getName())
                        .log(Level.SEVERE, "Error al eliminar Prueba", e);
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
    @Path("/{idPrueba}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("idPrueba") Long idPrueba) {
        if (idPrueba != null && idPrueba > 0) {
            try {
                Prueba prueba = pruebaDI.findById(idPrueba);

                if (prueba != null) {
                    //TipoPrueba tipoPrueba=new TipoPrueba(prueba.getIdTipoPrueba().getIdTipoPrueba());
                    PruebaDTO pruebaDTO = new PruebaDTO(prueba);
                    return Response.ok(pruebaDTO).build();
                }
                return Response.status(Response.Status.NOT_FOUND)
                        .header("NOT_FOUNT_ID", idPrueba)
                        .entity("No se encontro el recurso con ID: " + idPrueba)
                        .build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "ERROR", e.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(e.getMessage())
                        .build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .header("WRONG_PARAMETER", idPrueba)
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
                List<Prueba> listaPruebas = pruebaDI.findByRange(first, max);
                List<PruebaDTO> listaPruebasDTO = listaPruebas
                        .stream()
                        .map(prueba -> new PruebaDTO(prueba))
                        .collect(Collectors.toList());
                long total = pruebaDI.count();
                return Response.ok(listaPruebasDTO)
                        .header("TOTAL_RECORD", total)
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .header("WRONG_PARAMETER", first + "-" + max)
                        .build();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName())
                    .log(Level.SEVERE, "ERROR", e.getMessage());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{idPrueba}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("idPrueba") Long idPrueba,
            PruebaDTO pruebaDTO,
            @Context UriInfo uriInfo) {
        if (idPrueba != null && pruebaDTO != null) {
            try {
                Prueba pruebaEncontrada = pruebaDI.findById(idPrueba);
            if (pruebaEncontrada == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Prueba no encontrada con ID " + idPrueba)
                        .build();
            }
            if (pruebaDTO.nombre() != null) {
                pruebaEncontrada.setNombre(pruebaDTO.nombre());
            }
            if (pruebaDTO.indicaciones() != null) {
                pruebaEncontrada.setIndicaciones(pruebaDTO.indicaciones());
            }
            if (pruebaDTO.puntajeMaximo() != null) {
                pruebaEncontrada.setPuntajeMaximo(pruebaDTO.puntajeMaximo());
            }
            if (pruebaDTO.notaAprobacion() != null) {
                pruebaEncontrada.setNotaAprobacion(pruebaDTO.notaAprobacion());
            }
            if (pruebaDTO.duracion() != null) {
                pruebaEncontrada.setDuracion(pruebaDTO.duracion());
            }
            if (pruebaDTO.fechaCreacion() != null) {
                pruebaEncontrada.setFechaCreacion(pruebaDTO.fechaCreacion());
            }
            if (pruebaDTO.idTipoPrueba() != null && pruebaDTO.idTipoPrueba()>0) {
                TipoPrueba nuevoTipoPrueba=new TipoPrueba();
                nuevoTipoPrueba.setIdTipoPrueba(pruebaDTO.idTipoPrueba());
                pruebaEncontrada.setIdTipoPrueba(nuevoTipoPrueba);
            }
            pruebaDI.update(pruebaEncontrada);
            return Response.ok()
                    .location(uriInfo.getAbsolutePathBuilder().build())
                    .build();
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al actualizar", e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error al actualizar :"+e.getMessage())
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ID y datos de la Prueba son requeridos")
                    .build();
        }

    }

}
