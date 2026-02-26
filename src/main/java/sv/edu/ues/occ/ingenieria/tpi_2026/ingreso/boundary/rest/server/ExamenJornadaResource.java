/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.ExamenDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.ExamenJornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Examen;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.ExamenJornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;

/**
 *
 * @author caesar
 */
@Path("examen-jornada")
public class ExamenJornadaResource implements Serializable {

    @Inject
    ExamenJornadaDAOImp examenJornadaDI;

    @Inject
    ExamenDAOImp examenDI;

    @Inject
    JornadaDAOImp jornadaDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createExamenJornada(ExamenJornada ej, @Context UriInfo uriInfo) {
        if (ej != null && ej.getIdExamen() != null && ej.getIdJornada() != null) {
            Examen eEncontrado = examenDI.findById(ej.getIdExamen());
            Jornada jEncontrado = jornadaDI.findById(ej.getIdJornada());
            if (eEncontrado != null && jEncontrado != null) {
                ej.setIdExamen(eEncontrado);
                ej.setIdJornada(jEncontrado);
                try {
                    examenJornadaDI.create(ej);
                    UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
                    uriBuilder.path(String.valueOf(ej));
                    return Response.created(uriBuilder.build()).type(MediaType.APPLICATION_JSON).build();
                } catch (Exception e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                    return Response.status(500).entity(RestError.INTERNAL_EXCEPTION).build();
                }
            }
            return Response.status(422).entity(RestError.MALFORMED_ENTITY).build();
        }
        return Response.status(400).entity(RestError.NULL_PAYLOAD).build();
    }

}
