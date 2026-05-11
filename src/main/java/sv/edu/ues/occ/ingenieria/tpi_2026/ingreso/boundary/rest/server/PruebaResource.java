package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.FindRangeDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception.EntityNotFoundInRepositoryExcpetion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AspiranteOpcionDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.DistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaAulaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.JornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PreguntaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoPreguntaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveAreaConocimientoPreguntaDistractorDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaClaveDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaAulaAspiranteOpcionDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaAulaAspiranteOpcionExamenDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.TipoPruebaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteOpcion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAula;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAulaPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClave;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractorPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamen;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamenPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
@Path("prueba")
public class PruebaResource {

    @Inject
    PruebaDAOImp pruebaDI;

    @Inject
    TipoPruebaDAOImp tipoPruebaDI;

    @Inject
    PruebaClaveDAOImp pruebaClaveDI;

    @Inject
    AreaConocimientoDAOImp areaDI;

    @Inject
    PruebaClaveAreaConocimientoDAOImp pruebaClaveAreaConocimientoDI;

    @Inject
    PreguntaDAOImp preguntaDI;

    @Inject
    PruebaClaveAreaConocimientoPreguntaDAOImp pruebaClaveAreaConocimientoPreguntaDI;

    @Inject
    DistractorDAOImp distractorDI;

    @Inject
    PruebaClaveAreaConocimientoPreguntaDistractorDAOImp pruebaClaveAreaConocimientoPreguntaDistractorDI;

    @Inject
    JornadaDAOImp jornadaDI;

    @Inject
    PruebaJornadaDAOImp pruebaJornadaDI;

    @Inject
    JornadaAulaDAOImp jornadaAulaDI;

    @Inject
    AspiranteOpcionDAOImp aspiranteOpcionDI;

    @Inject
    PruebaJornadaAulaAspiranteOpcionDAOImp pruebaJornadaAulaAspiranteOpcionDI;

    @Inject
    PruebaJornadaAulaAspiranteOpcionExamenDAOImp pruebaJornadaAulaAspiranteOpcionExamenDI;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid Prueba entity, @Context UriInfo uriInfo) {

        pruebaDI.create(entity);
        URI uriCreada = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(entity.getIdPrueba()))
                .build();
        return Response.created(uriCreada).build();

    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") @Min(1L) Long id) {

        Prueba found = pruebaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        pruebaDI.delete(found);
        return Response.noContent().build();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByID(@PathParam("id") @Min(1L) Long id) {

        Prueba found = pruebaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRange(@Valid @BeanParam FindRangeDTO params) {

        List<Prueba> resultList = pruebaDI.findByRange(params.getOffset(), params.getLimit());
        return Response
                .ok(resultList)
                .header(HeaderName.TOTAL_RECORDS.toString(), resultList.size())
                .type(MediaType.APPLICATION_JSON)
                .build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") @Min(1L) Long id, @Valid Prueba entity) {

        Prueba found = pruebaDI.findById(id);
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(id);
        }

        entity.setIdPrueba(id);
        pruebaDI.update(entity);
        return Response.noContent().build();

    }

    @PUT
    @Path("/{idPrueba}/tipo/{idTipoPrueba}")
    public Response setTipoPrueba(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idTipoPrueba") @Min(1) Integer idTipoPrueba
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPrueba);
        }

        TipoPrueba tipoPruebaFound = tipoPruebaDI.findById(idTipoPrueba);
        if (tipoPruebaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(tipoPruebaFound);
        }

        pruebafound.setIdTipoPrueba(tipoPruebaFound);
        pruebaDI.update(pruebafound);
        return Response.noContent().build();

    }

    @DELETE
    @Path("/{idPrueba}/tipo")
    public Response unsetTipoPrueba(
            @PathParam("idPrueba") @Min(1L) Long idPrueba
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPrueba);
        }

        pruebafound.setIdTipoPrueba(null);
        pruebaDI.update(pruebafound);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idPrueba}/clave")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPruebaClave(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @Valid PruebaClave entity,
            @Context UriInfo uriInfo
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPrueba);
        }

        entity.setIdPrueba(pruebafound);
        pruebaClaveDI.create(entity);
        URI uriCreada = uriInfo.getAbsolutePathBuilder()
                .path(String.valueOf(entity.getIdPruebaClave()))
                .build();
        return Response.created(uriCreada).build();

    }

    @GET
    @Path("/{idPrueba}/clave/{idPruebaClave}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPruebaClave(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idPruebaClave") @Min(1L) Long idPruebaClave
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPrueba);
        }

        PruebaClave pruebaClavefound = pruebaClaveDI.findById(idPruebaClave);
        if (pruebaClavefound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPruebaClave);
        }

        return Response.ok(pruebaClavefound, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idPrueba}/clave/{idPruebaClave}")
    public Response deletePruebaClave(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idPruebaClave") @Min(1L) Long idPruebaClave
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPrueba);
        }

        PruebaClave pruebaClavefound = pruebaClaveDI.findById(idPruebaClave);
        if (pruebaClavefound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPruebaClave);
        }

        pruebaClaveDI.delete(pruebaClavefound);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idPrueba}/clave/{idClave}/area/{idArea}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPruebaClaveAreaConocimiento(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @Valid PruebaClaveAreaConocimiento entity,
            @Context UriInfo uriInfo
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClave pruebaClavefound = pruebaClaveDI.findById(idClave);
        if (pruebaClavefound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Clave: " + idClave);
        }

        AreaConocimiento areaFound = areaDI.findById(idArea);
        if (pruebaClavefound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Area: " + idArea);
        }

        entity.setPruebaClaveAreaConocimientoPK(new PruebaClaveAreaConocimientoPK(idClave, idArea));
        pruebaClaveAreaConocimientoDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();

    }

    @GET
    @Path("/{idPrueba}/clave/{idClave}/area/{idArea}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPruebaClaveAreaConocimiento(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimiento pruebaClaveAreaConocimientoFound = pruebaClaveAreaConocimientoDI.findById(new PruebaClaveAreaConocimientoPK(idClave, idArea));
        if (pruebaClaveAreaConocimientoFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
            );
        }

        return Response.ok(pruebaClaveAreaConocimientoFound, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idPrueba}/clave/{idClave}/area/{idArea}")
    public Response deletePruebaClaveAreaConocimiento(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimiento pruebaClaveAreaConocimientoFound = pruebaClaveAreaConocimientoDI.findById(new PruebaClaveAreaConocimientoPK(idClave, idArea));
        if (pruebaClaveAreaConocimientoFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
            );
        }

        pruebaClaveAreaConocimientoDI.delete(pruebaClaveAreaConocimientoFound);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idPrueba}/clave/{idClave}/area/{idArea}/pregunta/{idPregunta}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPruebaClaveAreaConocimientoPregunta(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @Valid PruebaClaveAreaConocimientoPregunta entity,
            @Context UriInfo uriInfo
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimiento pruebaClaveAreaConocimientoFound = pruebaClaveAreaConocimientoDI.findById(new PruebaClaveAreaConocimientoPK(idClave, idArea));
        if (pruebaClaveAreaConocimientoFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
            );
        }

        Pregunta preguntaFound = preguntaDI.findById(idPregunta);
        if (preguntaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Pregunta: " + idPregunta);
        }

        entity.setPruebaClaveAreaConocimientoPreguntaPK(new PruebaClaveAreaConocimientoPreguntaPK(idClave, idArea, idPregunta));
        pruebaClaveAreaConocimientoPreguntaDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();

    }

    @GET
    @Path("/{idPrueba}/clave/{idClave}/area/{idArea}/pregunta/{idPregunta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPruebaClaveAreaConocimientoPregunta(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimientoPregunta pruebaClaveAreaConocimientoPreguntaFound = pruebaClaveAreaConocimientoPreguntaDI.findById(new PruebaClaveAreaConocimientoPreguntaPK(idClave, idArea, idPregunta));
        if (pruebaClaveAreaConocimientoPreguntaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
                    + " Pregunta: " + idPregunta
            );
        }

        return Response.ok(pruebaClaveAreaConocimientoPreguntaFound, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idPrueba}/clave/{idClave}/area/{idArea}/pregunta/{idPregunta}")
    public Response deletePruebaClaveAreaConocimiento(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimientoPregunta pruebaClaveAreaConocimientoPreguntaFound = pruebaClaveAreaConocimientoPreguntaDI.findById(new PruebaClaveAreaConocimientoPreguntaPK(idClave, idArea, idPregunta));
        if (pruebaClaveAreaConocimientoPreguntaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
                    + " Pregunta: " + idPregunta
            );
        }

        pruebaClaveAreaConocimientoPreguntaDI.delete(pruebaClaveAreaConocimientoPreguntaFound);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idPrueba}/clave/{idClave}/area/{idArea}/pregunta/{idPregunta}/distractor/{idDistractor}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPruebaClaveAreaConocimientoPreguntaDistractor(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @PathParam("idDistractor") @Min(1L) Long idDistractor,
            @Valid PruebaClaveAreaConocimientoPreguntaDistractor entity,
            @Context UriInfo uriInfo
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimientoPregunta pruebaClaveAreaConocimientoPreguntaFound = pruebaClaveAreaConocimientoPreguntaDI.findById(new PruebaClaveAreaConocimientoPreguntaPK(idClave, idArea, idPregunta));
        if (pruebaClaveAreaConocimientoPreguntaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
                    + " Pregunta: " + idPregunta
            );
        }

        Distractor distractorFound = distractorDI.findById(idDistractor);
        if (distractorFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Distractor: " + idDistractor);
        }

        entity.setPruebaClaveAreaConocimientoPreguntaDistractorPK(
                new PruebaClaveAreaConocimientoPreguntaDistractorPK(idClave, idArea, idPregunta, idDistractor)
        );
        pruebaClaveAreaConocimientoPreguntaDistractorDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();

    }

    @GET
    @Path("/{idPrueba}/clave/{idClave}/area/{idArea}/pregunta/{idPregunta}/distractor/{idDistractor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPruebaClaveAreaConocimientoPreguntaDistractor(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @PathParam("idDistractor") @Min(1L) Long idDistractor
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimientoPreguntaDistractor pruebaClaveAreaConocimientoPreguntaDistractorFound
                = pruebaClaveAreaConocimientoPreguntaDistractorDI
                        .findById(new PruebaClaveAreaConocimientoPreguntaDistractorPK(idClave, idArea, idPregunta, idDistractor));
        if (pruebaClaveAreaConocimientoPreguntaDistractorFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
                    + " Pregunta: " + idPregunta
                    + " Distractor: " + idDistractor
            );
        }

        return Response.ok(pruebaClaveAreaConocimientoPreguntaDistractorFound, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idPrueba}/clave/{idClave}/area/{idArea}/pregunta/{idPregunta}/distractor/{idDistractor}")
    public Response deletePruebaClaveAreaConocimientoPreguntaDistractor(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idClave") @Min(1L) Long idClave,
            @PathParam("idArea") @Min(1) Integer idArea,
            @PathParam("idPregunta") @Min(1L) Long idPregunta,
            @PathParam("idDistractor") @Min(1L) Long idDistractor
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaClaveAreaConocimientoPreguntaDistractor pruebaClaveAreaConocimientoPreguntaDistractorFound
                = pruebaClaveAreaConocimientoPreguntaDistractorDI
                        .findById(new PruebaClaveAreaConocimientoPreguntaDistractorPK(idClave, idArea, idPregunta, idDistractor));
        if (pruebaClaveAreaConocimientoPreguntaDistractorFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Clave: " + idClave
                    + " Area: " + idArea
                    + " Pregunta: " + idPregunta
                    + " Distractor: " + idDistractor
            );
        }

        pruebaClaveAreaConocimientoPreguntaDistractorDI.delete(pruebaClaveAreaConocimientoPreguntaDistractorFound);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idPrueba}/jornada/{idJornada}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPruebaJornada(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @Valid PruebaJornada entity,
            @Context UriInfo uriInfo
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idPrueba);
        }

        Jornada jornadaFound = jornadaDI.findById(idJornada);
        if (jornadaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idJornada);
        }

        entity.setPruebaJornadaPK(new PruebaJornadaPK(idPrueba, idJornada));
        pruebaJornadaDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();

    }

    @GET
    @Path("/{idPrueba}/jornada/{idJornada}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPruebaJornada(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada
    ) {

        PruebaJornada found = pruebaJornadaDI.findById(new PruebaJornadaPK(idPrueba, idJornada));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idJornada);
        }

        return Response.ok(found, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idPrueba}/jornada/{idJornada}")
    public Response deletePruebaJornada(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada
    ) {

        PruebaJornada found = pruebaJornadaDI.findById(new PruebaJornadaPK(idPrueba, idJornada));
        if (found == null) {
            throw new EntityNotFoundInRepositoryExcpetion(idJornada);
        }

        pruebaJornadaDI.delete(found);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idPrueba}/jornada/{idJornada}/aula/{idAula}/opcion/{idAspiranteOpcion}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPruebaJornadaAspiranteOpcion(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion,
            @Valid PruebaJornadaAulaAspiranteOpcion entity,
            @Context UriInfo uriInfo
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        JornadaAula jornadaAulaFound = jornadaAulaDI.findById(new JornadaAulaPK(idJornada, idAula));
        if (jornadaAulaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Jornada: " + idJornada + " Aula: " + idAula);
        }

        AspiranteOpcion aspiranteOpcionFound = aspiranteOpcionDI.findById(idAspiranteOpcion);
        if (aspiranteOpcionFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("AspiranteOpcion: " + idAspiranteOpcion);
        }

        entity.setPruebaJornadaAulaAspiranteOpcionPK(new PruebaJornadaAulaAspiranteOpcionPK(idPrueba, idJornada, idAula, idAspiranteOpcion));
        pruebaJornadaAulaAspiranteOpcionDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();

    }

    @GET
    @Path("/{idPrueba}/jornada/{idJornada}/aula/{idAula}/opcion/{idAspiranteOpcion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPruebaJornadaAspiranteOpcion(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaJornadaAulaAspiranteOpcion pruebaJornadaAulaAspiranteOpcionFound = pruebaJornadaAulaAspiranteOpcionDI.findById(
                new PruebaJornadaAulaAspiranteOpcionPK(idPrueba, idJornada, idAula, idAspiranteOpcion)
        );
        if (pruebaJornadaAulaAspiranteOpcionFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Jornada: " + idJornada
                    + " Aula: " + idAula
                    + " Opcion: " + idAspiranteOpcion);
        }

        return Response.ok(pruebaJornadaAulaAspiranteOpcionFound, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idPrueba}/jornada/{idJornada}/aula/{idAula}/opcion/{idAspiranteOpcion}")
    public Response deletePruebaJornadaAspiranteOpcion(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaJornadaAulaAspiranteOpcion pruebaJornadaAulaAspiranteOpcionFound = pruebaJornadaAulaAspiranteOpcionDI.findById(
                new PruebaJornadaAulaAspiranteOpcionPK(idPrueba, idJornada, idAula, idAspiranteOpcion)
        );
        if (pruebaJornadaAulaAspiranteOpcionFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Jornada: " + idJornada
                    + " Aula: " + idAula
                    + " Opcion: " + idAspiranteOpcion);
        }
        pruebaJornadaAulaAspiranteOpcionDI.delete(pruebaJornadaAulaAspiranteOpcionFound);
        return Response.noContent().build();

    }

    @POST
    @Path("/{idPrueba}/jornada/{idJornada}/aula/{idAula}/opcion/{idAspiranteOpcion}/examen")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPruebaJornadaAspiranteOpcionExamen(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion,
            @Valid PruebaJornadaAulaAspiranteOpcionExamen entity,
            @Context UriInfo uriInfo
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        JornadaAula jornadaAulaFound = jornadaAulaDI.findById(new JornadaAulaPK(idJornada, idAula));
        if (jornadaAulaFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Jornada: " + idJornada + " Aula: " + idAula);
        }

        AspiranteOpcion aspiranteOpcionFound = aspiranteOpcionDI.findById(idAspiranteOpcion);
        if (aspiranteOpcionFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("AspiranteOpcion: " + idAspiranteOpcion);
        }

        entity.setPruebaJornadaAulaAspiranteOpcionExamenPK(new PruebaJornadaAulaAspiranteOpcionExamenPK(idPrueba, idJornada, idAula, idAspiranteOpcion));
        pruebaJornadaAulaAspiranteOpcionExamenDI.create(entity);
        return Response.created(uriInfo.getAbsolutePath()).build();

    }

    @GET
    @Path("/{idPrueba}/jornada/{idJornada}/aula/{idAula}/opcion/{idAspiranteOpcion}/examen")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPruebaJornadaAspiranteOpcionExamen(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaJornadaAulaAspiranteOpcionExamen pruebaJornadaAulaAspiranteOpcionExamenFound = pruebaJornadaAulaAspiranteOpcionExamenDI.findById(
                new PruebaJornadaAulaAspiranteOpcionExamenPK(idPrueba, idJornada, idAula, idAspiranteOpcion)
        );
        if (pruebaJornadaAulaAspiranteOpcionExamenFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Jornada: " + idJornada
                    + " Aula: " + idAula
                    + " Opcion: " + idAspiranteOpcion);
        }

        return Response.ok(pruebaJornadaAulaAspiranteOpcionExamenFound, MediaType.APPLICATION_JSON).build();

    }

    @DELETE
    @Path("/{idPrueba}/jornada/{idJornada}/aula/{idAula}/opcion/{idAspiranteOpcion}/examen")
    public Response deletePruebaJornadaAspiranteOpcionExamen(
            @PathParam("idPrueba") @Min(1L) Long idPrueba,
            @PathParam("idJornada") @Min(1L) Long idJornada,
            @PathParam("idAula") String idAula,
            @PathParam("idAspiranteOpcion") @Min(1L) Long idAspiranteOpcion
    ) {

        Prueba pruebafound = pruebaDI.findById(idPrueba);
        if (pruebafound == null) {
            throw new EntityNotFoundInRepositoryExcpetion("Prueba: " + idPrueba);
        }

        PruebaJornadaAulaAspiranteOpcionExamen pruebaJornadaAulaAspiranteOpcionExamenFound = pruebaJornadaAulaAspiranteOpcionExamenDI.findById(
                new PruebaJornadaAulaAspiranteOpcionExamenPK(idPrueba, idJornada, idAula, idAspiranteOpcion)
        );
        if (pruebaJornadaAulaAspiranteOpcionExamenFound == null) {
            throw new EntityNotFoundInRepositoryExcpetion(
                    "Prueba: " + idPrueba
                    + " Jornada: " + idJornada
                    + " Aula: " + idAula
                    + " Opcion: " + idAspiranteOpcion);
        }
        pruebaJornadaAulaAspiranteOpcionExamenDI.delete(pruebaJornadaAulaAspiranteOpcionExamenFound);
        return Response.noContent().build();

    }

}
