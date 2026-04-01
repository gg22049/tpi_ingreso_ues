package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.print.attribute.standard.Media;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO.AspiranteDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO.FindRangeParamDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.DomainException;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.record.ErrorRecord;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AspiranteDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante;

/**
 *
 * @author usermein
 */
@Path("aspirante")
public class AspiranteResource {
@Inject
AspiranteDAOImp aspiranteDI;

@POST
@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public Response create(@NotNull @Valid AspiranteDTO aspiranteDTO, UriInfo uriInfo) throws DomainException{
    try {
        Aspirante nuevoAspirante=aspiranteDTO.toEntity();
        aspiranteDI.create(nuevoAspirante);
        URI uriCreada=uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(nuevoAspirante.getIdAspirante()))
                .build();
        return Response.created(uriCreada).entity(aspiranteDTO).build();
    } catch (Exception e) {
        throw new DomainException(e);
    }
}

@DELETE
@Path("/{isAspirante}")
@Produces(MediaType.APPLICATION_JSON)
public Response delete(@PathParam("idAspirante") @Min(1) @Max(Integer.MAX_VALUE) Long idAspirante, @Context UriInfo uriInfo) throws DomainException{
    try {
        Aspirante aspirante=aspiranteDI.findById(idAspirante);
        if (aspirante==null) {
            return Response
                    .status(404)
                    .entity(new ErrorRecord(null, 
                            ErrorType.NO_MATCH_ID.toString(),
                            404,
                            "No existe entidad con id:"+idAspirante,
                            uriInfo.getAbsolutePath().toString(),
                            null))
                    .build();
        }
        aspiranteDI.delete(aspirante);
        return Response.noContent().build();
    } catch (Exception e) {
        throw new DomainException(e);
    }
    
}


    @GET
    @Path("/{idAspirante}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("idAspirante") @Min(1) @Max(Integer.MAX_VALUE) Long idAspirante, @Context UriInfo uriInfo ) throws DomainException{
            try {
                Aspirante aspirante=aspiranteDI.findById(idAspirante);
                if (aspirante==null) {
                    return Response
                            .status(Response.Status.NOT_FOUND)
                            .entity(new ErrorRecord(
                                    null,
                                    ErrorType.NO_MATCH_ID.toString(),
                                    404, 
                                    "No existe el aspirante con ID:"+idAspirante,
                                    uriInfo.getAbsolutePath().toString(),
                                    null))
                            .type(MediaType.APPLICATION_JSON)
                            .build();
                            
                                   
                }
                AspiranteDTO aspiranteDTO =new AspiranteDTO(aspirante);
                return Response.ok(aspiranteDTO, MediaType.APPLICATION_JSON).build();
            } catch (Exception e) {
                throw new DomainException(e);
            }

    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(
            @Valid @BeanParam FindRangeParamDTO params
    ) throws DomainException{
        try {
            List<Aspirante> listaAspirante=aspiranteDI.findByRange(params.getOffset(), params.getLimit());
            List<AspiranteDTO> listaAspiranteDTO= listaAspirante
                    .stream()
                    .map(aspirante -> new AspiranteDTO(aspirante))
                    .collect(Collectors.toList());
            return Response
                    .ok(listaAspiranteDTO)
                    .header(HeaderName.TOTAL_RECORDS.toString(), listaAspiranteDTO.size())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            
            throw new DomainException(e);

        }

    }

    @PUT
    @Path("/{idAspirante}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("idAspirante") Long idAspirante,
            AspiranteDTO aspiranteDTO,
            @Context UriInfo uriInfo) throws DomainException{
            try {
               Aspirante aspirante=aspiranteDI.findById(idAspirante);
                if (aspirante==null) {
                    return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity(
                                new ErrorRecord(
                                        null,
                                        ErrorType.NO_MATCH_ID.toString(),
                                        404,
                                        "No existe Aspirante con ID: " + idAspirante,
                                        uriInfo.getAbsolutePath().toString(),
                                        null
                                )
                        ).build();
                }
                //AQUI si voy a ocupar if, esto para poder solamente actualizar los datos ue vienen en el dto, habia creado un metodo
                //toEntity para pasar el dto a entidad pero al menos en este caso no me sirve por que rompe la logica de los dto
                if (aspiranteDTO.nombres()!=null) {
                    aspirante.setNombres(aspiranteDTO.nombres());
                }
                if (aspiranteDTO.apellidos()!=null) {
                    aspirante.setApellidos(aspiranteDTO.apellidos());
                }
                if (aspiranteDTO.fechaNacimiento()!=null) {
                    aspirante.setFechaNacimiento(aspiranteDTO.fechaNacimiento());
                }
                if (aspiranteDTO.correo()!=null) {
                    aspirante.setCorreo(aspiranteDTO.correo());
                }
                if (aspiranteDTO.fechaCreacion()!=null) {
                    aspirante.setFechaCreacion(aspiranteDTO.fechaCreacion());
                }
                if (aspiranteDTO.observaciones()!=null) {
                    aspirante.setObservaciones(aspiranteDTO.observaciones());
                }
                
                aspiranteDI.update(aspirante);
                return Response.noContent().build();
            } catch (Exception e) {
                throw new DomainException(e);
            }
      

    }

        
    
}
