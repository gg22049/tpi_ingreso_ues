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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.TipoPruebaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class TipoPruebaDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("TipoPruebaDAOImpTest.toEntityTest");
        TipoPruebaDAOImp cut = new TipoPruebaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        TipoPrueba result = cut.toEntity(new TipoPruebaDTO(1, "val", Boolean.TRUE, "obs"));
        assertNotNull(result);
        assertEquals(1, result.getIdTipoPrueba());
        assertTrue(result.getActivo());
    }

    @Test
    public void toDtoTest() {
        System.out.println("TipoPruebaDAOImpTest.toDtoTest");
        TipoPruebaDAOImp cut = new TipoPruebaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        TipoPruebaDTO result = cut.toDto(new TipoPrueba(1, "val", Boolean.TRUE, "url"));
        assertNotNull(result);
        assertEquals(1, result.idTipoPrueba());
        assertTrue(result.activo());
    }

}
