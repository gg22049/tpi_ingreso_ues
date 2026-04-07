/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.DistractorAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimientoPK;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class DistractorAreaConocimientoDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("DistractorAreaConocimientoDAOImpTest.toEntityTest");
        DistractorAreaConocimientoDAOImp cut = new DistractorAreaConocimientoDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        DistractorAreaConocimiento result = cut.toEntity(new DistractorAreaConocimientoDTO(1L, 1, "obs"));
        assertNotNull(result.getDistractorAreaConocimientoPK());
        assertEquals(1L, result.getDistractorAreaConocimientoPK().getIdDistractor());
        assertEquals(1, result.getDistractorAreaConocimientoPK().getIdAreaConocimiento());
    }

    @Test
    public void toDtoTest() {
        System.out.println("DistractorAreaConocimientoDAOImpTest.toDtoTest");
        DistractorAreaConocimientoDAOImp cut = new DistractorAreaConocimientoDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        DistractorAreaConocimientoDTO result = cut.toDto(new DistractorAreaConocimiento(null, "obs"));
        assertNotNull(result);
        assertNull(result.idDistractor());
        assertEquals(0, result.idAreaConocimiento());
        result = cut.toDto(new DistractorAreaConocimiento(new DistractorAreaConocimientoPK(1L, 1), "obs"));
        assertEquals(1L, result.idDistractor());
        assertEquals(1, result.idAreaConocimiento());
    }

}
