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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.EntityNotFoundInRepositoryExcpetion;

/**
 * "Several Internal Server Exception, Could Not Properly Response."
 *
 * @author caesar
 */
@Provider
public class EntityNotFoundInRepositoryExceptionMapper implements ExceptionMapper<EntityNotFoundInRepositoryExcpetion> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(EntityNotFoundInRepositoryExcpetion e) {
        return Response
                .status(404)
                .entity(new ErrorDetailDTO(
                        null,
                        ErrorType.NO_MATCH_ID.toString(),
                        404,
                        e.getMessage(),
                        uriInfo.getAbsolutePath().toString(),
                        null
                ))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
