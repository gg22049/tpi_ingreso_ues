/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.mapper;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.ErrorType;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;

/**
 * "Several Internal Server Exception, Could Not Properly Response."
 *
 * @author caesar
 */
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(RuntimeException e) {
        String errorId = java.util.UUID.randomUUID().toString();
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error UUID: " + errorId, e);
        ErrorDetailDTO error = new ErrorDetailDTO(
                errorId,
                ErrorType.INTERNAL_EXCEPTION.toString(),
                500,
                "Unexpected Error in Resource " + uriInfo.getPath().toString(),
                uriInfo.getAbsolutePath().toString(),
                null
        );

        return Response
                .status(500)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
