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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.ErrorType;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;

/**
 *
 * @author caesar
 */
@Provider
public class IllegalStateExceptionMapper implements ExceptionMapper<IllegalStateException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(IllegalStateException e) {

        String errorId = java.util.UUID.randomUUID().toString();
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error UUID: " + errorId, e);

        Throwable rootCause = e;
        String result;

        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }

        if (rootCause instanceof SQLIntegrityConstraintViolationException) {
            result = "Operation violates database constraints.";
        } else {
            result = "Unexpected internal server error.";
        }

        ErrorDetailDTO error = new ErrorDetailDTO(
                errorId,
                ErrorType.INTERNAL_EXCEPTION.toString(),
                500,
                result,
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
