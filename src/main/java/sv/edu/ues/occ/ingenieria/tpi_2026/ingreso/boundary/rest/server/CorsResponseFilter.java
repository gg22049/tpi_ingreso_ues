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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usermein
 */
@Provider
public class CorsResponseFilter implements ContainerResponseFilter{

    public static final String METODOS_PERMITIDOS="GET, POST, PUT, DELETE, OPTIONS, HEAD";
    public static final int MAXIMO_CACHE=30*60*60;
    public static final String CABECERAS_PERMITIDAS="origin,accept,content-type";
    public static final String CABECERAS_EXPUESTAS="location,info";
    
    @Override
    public void filter(ContainerRequestContext crc, ContainerResponseContext responseContext) throws IOException {
        MultivaluedMap<String, Object> headers=responseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin","*");
         headers.add("Access-Control-Allow-Headers",getResquestedAllowedHeaders(crc));
          headers.add("Access-Control-Expose-Origin",getResquestedExposedHeaders(crc));
           headers.add("Access-Control-Allow-Credentials","true");
           headers.add("Access-Control-Allow-Methods",METODOS_PERMITIDOS);
           headers.add("Access-Control-MAx-Age",MAXIMO_CACHE);
           headers.add("x-responded-by","cors-response-filter");
    }
    
    String getResquestedAllowedHeaders(ContainerRequestContext responseContext){
        List<String> headers=responseContext.getHeaders().get("Access-Control-Allow-Headers");
        return crearCabeceras(headers, CABECERAS_PERMITIDAS);
    }
    
    String getResquestedExposedHeaders(ContainerRequestContext responseContext){
        List<String> headers=responseContext.getHeaders().get("Access-Control-Expose-Headers");
        return crearCabeceras(headers, CABECERAS_EXPUESTAS);
    }
    
    String crearCabeceras(List<String> cabeceras, String cabecerasPorDefecto){
        if (cabeceras==null || cabeceras.isEmpty()) {
            return cabecerasPorDefecto;
        }
        List<String> salida=new ArrayList<>();
        StringBuilder sb= new StringBuilder();
        for (Object cabecera : cabeceras) {
            sb.append(cabecera);
            sb.append(";");
        }
        sb.append(cabecerasPorDefecto);
        return  sb.toString();
    }
}
