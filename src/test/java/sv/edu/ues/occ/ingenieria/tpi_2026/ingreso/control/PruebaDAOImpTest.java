/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class PruebaDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("PruebaDAOImpTest.toEntityTest");
        PruebaDAOImp cut = new PruebaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        cut.em = emMock;
        when(emMock.find(TipoPrueba.class, 1)).thenReturn(new TipoPrueba(1));
        Prueba result = cut.toEntity(new PruebaDTO(1L, "name", "ind", BigDecimal.ZERO, BigDecimal.ZERO, 60, Date.from(Instant.now()), null));
        assertNull(result.getIdTipoPrueba());
        assertEquals(1L, result.getIdPrueba());
        result = cut.toEntity(new PruebaDTO(1L, "name", "ind", BigDecimal.ZERO, BigDecimal.ZERO, 60, Date.from(Instant.now()), 1));
        assertNotNull(result.getIdTipoPrueba());
        assertEquals(1L, result.getIdPrueba());
        assertEquals(1, result.getIdTipoPrueba().getIdTipoPrueba());
    }

    @Test
    public void toDtoTest() {
        System.out.println("PruebaDAOImpTest.toDtoTest");
        PruebaDAOImp cut = new PruebaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        PruebaDTO result = cut.toDto(new Prueba(1L, "name", "ind", BigDecimal.ZERO, BigDecimal.ZERO, 60, Date.from(Instant.now()), null));
        assertEquals(1L, result.idPrueba());
        assertNull(result.idTipoPrueba());
        result = cut.toDto(new Prueba(1L, "name", "ind", BigDecimal.ZERO, BigDecimal.ZERO, 60, Date.from(Instant.now()), new TipoPrueba(1)));
        assertEquals(1L, result.idPrueba());
        assertNotNull(result.idTipoPrueba());
        assertEquals(1, result.idTipoPrueba());
    }

}
