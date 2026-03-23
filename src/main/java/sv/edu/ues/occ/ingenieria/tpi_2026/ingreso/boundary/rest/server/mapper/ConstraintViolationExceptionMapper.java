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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO.ErrorDetailDTO;

/**
 *
 * @author caesar
 */
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<Map<String, String>> issues = e
                .getConstraintViolations()
                .stream()
                .map(v -> Map.of("field", v.getPropertyPath().toString(), "message", v.getMessage()))
                .toList();

        ErrorDetailDTO error = new ErrorDetailDTO(
                "VALIDATION_ERROR",
                400,
                "Invalid Request Parameters",
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
