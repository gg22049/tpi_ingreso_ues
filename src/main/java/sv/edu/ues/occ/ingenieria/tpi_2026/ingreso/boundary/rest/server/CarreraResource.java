package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.CarreraDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.dto.FindRangeDTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author caesar
 */
@Path("carreras")
public class CarreraResource {

    static final List<CarreraDTO> burnedResponse = Arrays.asList(
            new CarreraDTO("Ingenieria Civil", "Diseña, construye y mantiene infraestructuras básicas", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwDnoYcFAx5MQG0OiulRKcPmQe415MF_iGwg&s"),
            new CarreraDTO("Ingenieria Electrica", "Se enfoca en el diseño, desarrollo y mantenimiento de sistemas de generación, transmisión, distribución y uso de energía eléctrica para suplir las necesidades de industrias, comercios y hogares.", "https://ube.edu.ec/img/carreras/1712079657_95ff7639b4c719c236f5.jpg"),
            new CarreraDTO("Ingenieria Agronoma", "Aplica la ciencia y la tecnología para optimizar la producción agrícola, garantizar la seguridad alimentaria y promover el desarrollo sostenible.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGx7sucDVYct5_q6DKGgbiiR1RIo0QtmqcvQ&s"),
            new CarreraDTO("Ingenieria Sistemas Informaticos", "Diseña, programa y optimiza sistemas tecnológicos complejos", "https://www.espaciodigital.com.co/wp-content/uploads/2021/04/servidores-web.jpg")
    );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCarreras(
            @Valid @BeanParam FindRangeDTO params
    ) {
        return Response
                .ok(burnedResponse, MediaType.APPLICATION_JSON)
                .header(HeaderName.TOTAL_RECORDS.toString(), burnedResponse.size())
                .build();
    }

}
