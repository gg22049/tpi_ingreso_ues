/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author usermein
 */
@Provider
public class CorsResponseFilter implements ContainerResponseFilter {

    public static final String METODOS_PERMITIDOS = "GET, POST, PUT, DELETE, OPTIONS, HEAD";
    public static final int MAXIMO_CACHE = 30 * 60 * 60;
    public static final String CABECERAS_PERMITIDAS = "Origin, Accept, Content-Type";
    public static final String CABECERAS_EXPUESTAS = "Location, Info";

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        headers.putSingle("Access-Control-Allow-Origin", "*");
        headers.putSingle("Access-Control-Allow-Headers", getRequestedAllowedHeaders(requestContext));
        headers.putSingle("Access-Control-Expose-Headers", CABECERAS_EXPUESTAS);
        headers.putSingle("Access-Control-Allow-Credentials", "true");
        headers.putSingle("Access-Control-Allow-Methods", METODOS_PERMITIDOS);
        headers.putSingle("Access-Control-Max-Age", String.valueOf(MAXIMO_CACHE));
        headers.putSingle("x-responded-by", "cors-response-filter");
    }

    String getRequestedAllowedHeaders(ContainerRequestContext requestContext) {
        List<String> headers = requestContext.getHeaders().get("Access-Control-Allow-Headers");
        if (headers == null || headers.isEmpty()) {
            return CABECERAS_PERMITIDAS;
        }
        StringBuilder sb = new StringBuilder();
        for (Object header : headers) {
            sb.append(header);
            sb.append(", ");
        }
        sb.append(CABECERAS_PERMITIDAS);
        return sb.toString();
    }
}
