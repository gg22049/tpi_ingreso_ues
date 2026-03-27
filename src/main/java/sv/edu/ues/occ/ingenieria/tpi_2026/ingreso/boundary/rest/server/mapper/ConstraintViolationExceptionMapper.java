/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.mapper;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.ErrorTitle;

/**
 *
 * @author caesar
 */
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ConstraintViolationException e) {
        String errorId = java.util.UUID.randomUUID().toString();
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error UUID: " + errorId, e);
        List<Map<String, String>> issues = e
                .getConstraintViolations()
                .stream()
                .map(v -> Map.of("field", v.getPropertyPath().toString(), "message", v.getMessage()))
                .toList();

        ErrorDetailDTO error = new ErrorDetailDTO(
                ErrorTitle.VALIDATION_ERROR.toString(),
                400,
                "Invalid Request Parameters. Error UUID: " + errorId,
                uriInfo.getPath(),
                issues
        );

        return Response
                .status(400)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
