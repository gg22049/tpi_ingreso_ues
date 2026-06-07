package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ArbolAreaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.FindRangeDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ProcesoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ResultadoExamenDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.AreaConocimientoDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaAulaAspiranteOpcionDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaDAOImp;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author caesar
 */
@Path("procesos")
public class ProcesoResource {

    @Inject
    PruebaJornadaDAOImp pruebaJornadaDI;

    @Inject
    AreaConocimientoDAOImp areaDI;

    @Inject
    PruebaJornadaAulaAspiranteOpcionDAOImp pjaaoDI;

    @GET
    public Response findByFilteredRangeProcesos(
            @Valid @BeanParam FindRangeDTO range,
            @QueryParam("filter") String filter
    ) {
        List<ProcesoDTO> resultList = pruebaJornadaDI.findByFilteredRangeProcesos(range.getOffset(), range.getLimit(), filter);
        return Response
                .ok(resultList, MediaType.APPLICATION_JSON)
                .header(HeaderName.TOTAL_RECORDS.toString(), resultList.size())
                .build();
    }

    @GET
    @Path("{idPrueba}/areas")
    public Response findByIdArbolAreas(
            @PathParam("idPrueba") Long idPrueba
    ) {
        List<AreaConocimiento> resultList = areaDI.findByIdArbolAreas(idPrueba);

        List<AreaConocimiento> areas = areaDI.findByIdArbolAreas(idPrueba);

        Map<Integer, AreaConocimiento> filterMap = new LinkedHashMap<>();

        for (AreaConocimiento area : areas) {
            AreaConocimiento actual = area;

            while (actual != null) {
                filterMap.putIfAbsent(
                        actual.getIdAreaConocimiento(),
                        actual
                );
                actual = actual.getIdAreaConocimientoPadre();
            }
        }

        List<ArbolAreaDTO> responseList = filterMap
                .values()
                .stream()
                .map(a -> new ArbolAreaDTO(
                a.getIdAreaConocimiento(),
                a.getIdAreaConocimientoPadre() == null ? null : a.getIdAreaConocimientoPadre().getIdAreaConocimiento(),
                a.getNombre()
        )).toList();
        return Response
                .ok(responseList, MediaType.APPLICATION_JSON)
                .header(HeaderName.TOTAL_RECORDS.toString(), responseList.size())
                .build();
    }

    @GET
    @Path("resultados")
    public Response findExamenByCorreo(
            @QueryParam("email") @NotBlank String correo
    ) {
        List<ResultadoExamenDTO> resultList = pjaaoDI.findExamenByCorreo(correo);

        return Response
                .ok(resultList, MediaType.APPLICATION_JSON)
                .header(HeaderName.TOTAL_RECORDS.toString(), resultList.size())
                .build();
    }

}
