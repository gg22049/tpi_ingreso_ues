/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPK;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class PruebaClaveAreaConocimientoTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("PruebaClaveAreaConocimientoDAOImpTest.toEntityTest");
        PruebaClaveAreaConocimientoDAOImp cut = new PruebaClaveAreaConocimientoDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        PruebaClaveAreaConocimiento result = cut.toEntity(new PruebaClaveAreaConocimientoDTO(1L, 1, 3, BigDecimal.ONE));
        assertNotNull(result.getPruebaClaveAreaConocimientoPK());
        assertEquals(1L, result.getPruebaClaveAreaConocimientoPK().getIdPruebaClave());
        assertEquals(1, result.getPruebaClaveAreaConocimientoPK().getIdAreaConocimiento());
    }

    @Test
    public void toDtoTest() {
        System.out.println("PruebaClaveAreaConocimientoDAOImpTest.toDtoTest");
        PruebaClaveAreaConocimientoDAOImp cut = new PruebaClaveAreaConocimientoDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        PruebaClaveAreaConocimientoDTO result = cut.toDto(new PruebaClaveAreaConocimiento(null, 3, BigDecimal.ONE));
        assertEquals(0L, result.idPruebaClave());
        assertEquals(0, result.idAreaConocimiento());
        result = cut.toDto(new PruebaClaveAreaConocimiento(new PruebaClaveAreaConocimientoPK(1L, 1), 3, BigDecimal.ONE));
        assertEquals(1L, result.idPruebaClave());
        assertEquals(1, result.idAreaConocimiento());
        assertEquals(BigDecimal.ONE, result.porcentaje());
    }

}
