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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaDistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractorPK;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class PreguntaDistractorDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("PreguntaDistractorDAOImpTest.toEntityTest");
        PreguntaDistractorDAOImp cut = new PreguntaDistractorDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        PreguntaDistractor result = cut.toEntity(new PreguntaDistractorDTO(1L, 1L, Boolean.TRUE, "obs"));
        assertNotNull(result.getPreguntaDistractorPK());
        assertEquals(1L, result.getPreguntaDistractorPK().getIdDistractor());
        assertEquals(1L, result.getPreguntaDistractorPK().getIdPregunta());
    }

    @Test
    public void toDtoTest() {
        System.out.println("PreguntaDistractorDAOImpTest.toDtoTest");
        PreguntaDistractorDAOImp cut = new PreguntaDistractorDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        PreguntaDistractorDTO result = cut.toDto(new PreguntaDistractor(null, true, "obs"));
        assertEquals(0L, result.idDistractor());
        assertEquals(0L, result.idPregunta());
        result = cut.toDto(new PreguntaDistractor(new PreguntaDistractorPK(1L, 1L), true, "obs"));
        assertEquals(1L, result.idDistractor());
        assertEquals(1L, result.idPregunta());
    }

}
