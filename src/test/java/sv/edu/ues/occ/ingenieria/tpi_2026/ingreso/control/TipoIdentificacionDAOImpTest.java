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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.TipoIdentificacionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoIdentificacion;

/**
 *
 * @author caesar
 */
@ExtendWith(MockitoExtension.class)
public class TipoIdentificacionDAOImpTest {

    @Mock
    EntityManager emMock;

    @Test
    public void toEntityTest() {
        System.out.println("TipoIdentificacionDAOImpTest.toEntityTest");
        TipoIdentificacionDAOImp cut = new TipoIdentificacionDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toEntity(null);
                });
        TipoIdentificacion result = cut.toEntity(new TipoIdentificacionDTO(1, "name", "obs"));
        assertNotNull(result);
        assertEquals(1, result.getIdTipoIdentificacion());
    }

    @Test
    public void toDtoTest() {
        System.out.println("TipoIdentificacionDAOImpTest.toDtoTest");
        TipoIdentificacionDAOImp cut = new TipoIdentificacionDAOImp();
        assertThrows(IllegalStateException.class,
                () -> {
                    cut.toDto(null);
                });
        TipoIdentificacionDTO result = cut.toDto(new TipoIdentificacion(1, "name", "obs"));
        assertNotNull(result);
        assertEquals(1, result.idTipoIdentificacion());
    }

}
