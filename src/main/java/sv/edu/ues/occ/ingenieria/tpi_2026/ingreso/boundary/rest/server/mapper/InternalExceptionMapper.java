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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO.ErrorDetailDTO;

/**
 *
 * @author caesar
 */
@Provider
public class InternalExceptionMapper implements ExceptionMapper<Exception> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(Exception e) {
        ErrorDetailDTO error = new ErrorDetailDTO(
                "INTERNAL_EXCEPTION",
                500,
                "Several Internal Server Exception, Could Not Properly Response.",
                uriInfo.getPath(),
                null
        );

        return Response
                .status(500)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
