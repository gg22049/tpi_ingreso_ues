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
import jakarta.ws.rs.ext.Provider;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.ErrorType;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.record.ErrorRecord;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.record.IssueRecord;

/**
 *
 * @author caesar
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ConstraintViolationException e) {

        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        List<IssueRecord> issues = e
                .getConstraintViolations()
                .stream()
                .map(v -> {
                    String field = v.getPropertyPath().toString();
                    field = field.substring(field.lastIndexOf(".") + 1);
                    return new IssueRecord(field, v.getMessage());
                })
                .toList();

        ErrorRecord error = new ErrorRecord(
                null,
                ErrorType.VALIDATION_ERROR.toString(),
                400,
                "Constraint Violation in Resource: " + uriInfo.getPath().toString(),
                uriInfo.getAbsolutePath().toString(),
                issues
        );

        return Response
                .status(400)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();

    }

}
