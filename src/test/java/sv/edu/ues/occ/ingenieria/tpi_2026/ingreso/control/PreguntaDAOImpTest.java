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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class PreguntaDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("PreguntaDAOImpTest.toEntityTest");
        PreguntaDAOImp cut = new PreguntaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        Pregunta result = cut.toEntity(new PreguntaDTO(1L, "val", Boolean.TRUE, "url", "obs"));
        assertEquals(1L, result.getIdPregunta());
    }

    @Test
    public void toDtoTest() {
        System.out.println("PreguntaDAOImpTest.toDtoTest");
        PreguntaDAOImp cut = new PreguntaDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        PreguntaDTO result = cut.toDto(new Pregunta(1L, "val", Boolean.TRUE, "url", "obs"));
        assertEquals(1L, result.idPregunta());
    }

}
