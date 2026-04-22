/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.TipoIdentificacionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoIdentificacion;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TipoIdentificacionDAOImpIT extends ITAbstract {

    TipoIdentificacion newTipoIdentificacion;

    @BeforeAll
    void init() {

        newTipoIdentificacion = new TipoIdentificacion(null, "NIT", "Numero De Identificacion Tributaria");

    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("TipoIdentificacionDAOImp.testCreate");
        TipoIdentificacionDAOImp cut = new TipoIdentificacionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newTipoIdentificacion);
            cut.create(newTipoIdentificacion);
            cut.em.flush();
            cut.em.clear();
            TipoIdentificacion creado = cut.em.find(TipoIdentificacion.class, newTipoIdentificacion.getIdTipoIdentificacion());
            assertNotNull(creado);
            assertEquals("NIT", creado.getNombre());
            assertEquals(newTipoIdentificacion.getIdTipoIdentificacion(), creado.getIdTipoIdentificacion());
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("TipoIdentificacionDAOImp.testFindById");
        TipoIdentificacionDAOImp cut = new TipoIdentificacionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newTipoIdentificacion);
            cut.em.flush();
            cut.em.clear();
            TipoIdentificacion found = cut.em.find(TipoIdentificacion.class, newTipoIdentificacion.getIdTipoIdentificacion());
            assertNotNull(found);
            assertEquals(newTipoIdentificacion.getIdTipoIdentificacion(), found.getIdTipoIdentificacion());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("JornadaImp.testFindAll");
        JornadaDAOImp cut = new JornadaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newTipoIdentificacion);
            cut.em.flush();
            cut.em.clear();
            List<Jornada> resultList = cut.findAll();
            assertNotNull(resultList);
            assertTrue(resultList.size() > 0);
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(4)
    void testFindRange() {
        System.out.println("TipoIdentificacionDAOImp.testFindRange");
        TipoIdentificacionDAOImp cut = new TipoIdentificacionDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newTipoIdentificacion);
            cut.em.flush();
            cut.em.clear();
            List<TipoIdentificacion> resultList = cut.findByRange(offset, limit);
            assertNotNull(resultList);
            assertTrue(resultList.size() == 2);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(5)
    void testUpdate() {
        System.out.println("TipoIdentificacionDAOImp.testUpdate");
        String expected = "Numero Trubutario.";
        TipoIdentificacionDAOImp cut = new TipoIdentificacionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newTipoIdentificacion);
            cut.em.flush();
            newTipoIdentificacion.setObservaciones(expected);
            cut.update(newTipoIdentificacion);
            TipoIdentificacion probar = cut.findById(newTipoIdentificacion.getIdTipoIdentificacion());
            assertNotNull(newTipoIdentificacion);
            assertEquals(expected, probar.getObservaciones());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("TipoIdentificacionDAOImp.testDelete");
        TipoIdentificacionDAOImp cut = new TipoIdentificacionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newTipoIdentificacion);
            cut.em.flush();
            cut.em.clear();
            cut.delete(newTipoIdentificacion);
            TipoIdentificacion deleted = cut.findById(newTipoIdentificacion.getIdTipoIdentificacion());
            assertNull(deleted);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }
    
    @Test
    @Order(7)
    void testCount() {
        System.out.println("TipoIdentificacionDAOImp.testCount");
        TipoIdentificacionDAOImp cut = new TipoIdentificacionDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newTipoIdentificacion);
            cut.em.flush();
            cut.em.clear();
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos ==2 );
        } finally {
            tx.rollback();
            cut.em.close();
        }
 
    }
    
    
    
     @Test
    @Order(8)
    void testToDto() {
        System.out.println("TipoIdentificacionDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("34");
        TipoIdentificacionDAOImp cut = new TipoIdentificacionDAOImp();
        TipoIdentificacion ti = null;
        TipoIdentificacionDTO tiDTO;
        
        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(ti);
//PreguntaDistractorDAOImp
        });
        tiDTO = cut.toDto(new TipoIdentificacion(1, "DUI", "Deben presentar imagen"));
        assertNotNull(tiDTO);
        assertEquals(1, tiDTO.idTipoIdentificacion());
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("TipoIdentificacionDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
      
        TipoIdentificacionDAOImp cut = new TipoIdentificacionDAOImp();
        TipoIdentificacionDTO tiDTO = null;
        TipoIdentificacion ti1;
        TipoIdentificacion ti2;

        assertThrows(IllegalStateException.class, () -> {
            cut.toEntity(tiDTO);

        });
        ti1 = cut.toEntity(new TipoIdentificacionDTO(1, "DUI", "Deben presentar imagen"));
        assertNotNull(ti1);
        assertEquals(1,ti1.getIdTipoIdentificacion() );
       //TipoIdentificacionDAOImp
    }
}
