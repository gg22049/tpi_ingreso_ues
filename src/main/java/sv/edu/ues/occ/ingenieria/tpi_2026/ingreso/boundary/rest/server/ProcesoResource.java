package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.FindRangeDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.ProcesoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.PruebaJornadaDAOImp;

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

}
