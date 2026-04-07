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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.DistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class DistractorDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("DistractorDAOImpTest.toEntityTest");
        DistractorDAOImp cut = new DistractorDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        Distractor result = cut.toEntity(new DistractorDTO(1L, "val", Boolean.TRUE, "url"));
        assertEquals(1L, result.getIdDistractor());
    }

    @Test
    public void toDtoTest() {
        System.out.println("DistractorDAOImpTest.toDtoTest");
        DistractorDAOImp cut = new DistractorDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        DistractorDTO result = cut.toDto(new Distractor(1L, "val", Boolean.TRUE, "url"));
        assertEquals(1L, result.idDistractor());
    }

}
