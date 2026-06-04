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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.ExistentEntityException;

/**
 *
 * @author caesar
 */
@Provider
public class ExistentEntityExceptionMapper implements ExceptionMapper<ExistentEntityException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ExistentEntityException e) {
        return Response
                .status(409)
                .entity(new ErrorDetailDTO(
                        null,
                        ErrorType.UNPROCESSABLE.toString(),
                        409,
                        e.getMessage(),
                        uriInfo.getAbsolutePath().toString(),
                        null
                ))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
