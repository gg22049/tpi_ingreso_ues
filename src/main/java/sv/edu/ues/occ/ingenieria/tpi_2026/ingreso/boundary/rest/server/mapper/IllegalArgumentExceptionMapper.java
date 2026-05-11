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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.ErrorType;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ErrorDetailDTO;

/**
 *
 * @author caesar
 */
@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(IllegalArgumentException e) {

        ErrorDetailDTO error = new ErrorDetailDTO(
                null,
                ErrorType.UNPROCESSABLE.toString(),
                409,
                e.getMessage(),
                uriInfo.getAbsolutePath().toString(),
                null
        );

        return Response
                .status(409)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();

    }
}
