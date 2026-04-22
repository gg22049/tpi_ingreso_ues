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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.TipoPruebaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TipoPruebaDAOImpIT extends ITAbstract{
TipoPrueba newTipoPrueba;

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);

        //parentAspirante = new Aspirante(null, "TEST", "RELACION", cal.getTime(), "relacion@test.com");
        //aspIdenPK = new AspiranteIdentificacionPK();
        newTipoPrueba = new TipoPrueba(null, "Examen Presencial", true, "Debera presentarse a las instalaciones que se le indique");
        //newAspiranteIdentificacion.setAspiranteIdentificacionPK(aspIdenPK);

    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("TipoPruebaDAOImp.testCreate");
        TipoPruebaDAOImp cut = new TipoPruebaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newTipoPrueba);
            cut.create(newTipoPrueba);
            cut.em.flush();
            cut.em.clear();
            TipoPrueba creado = cut.em.find(TipoPrueba.class, newTipoPrueba.getIdTipoPrueba());
            assertNotNull(creado);
            assertEquals("Examen Presencial", creado.getValor());
            assertEquals(newTipoPrueba.getIdTipoPrueba(), creado.getIdTipoPrueba());
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("TipoPruebaDAOImp.testFindById");
        TipoPruebaDAOImp cut = new TipoPruebaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newTipoPrueba);
            cut.em.flush();
            cut.em.clear();
            TipoPrueba found = cut.em.find(TipoPrueba.class, newTipoPrueba.getIdTipoPrueba());
            assertNotNull(found);
            assertEquals(newTipoPrueba.getIdTipoPrueba(), found.getIdTipoPrueba());
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
            cut.em.persist(newTipoPrueba);
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
        System.out.println("TipoPruebaDAOImp.testFindRange");
        TipoPruebaDAOImp cut = new TipoPruebaDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newTipoPrueba);
            cut.em.flush();
            cut.em.clear();
            List<TipoPrueba> resultList = cut.findByRange(offset, limit);
            assertNotNull(resultList);
            assertTrue(resultList.size() > 0);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(5)
    void testUpdate() {
        System.out.println("TipoPruebaDAOImp.testUpdate");
        String expected = "A lapiz y papel.";
        TipoPruebaDAOImp cut = new TipoPruebaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newTipoPrueba);
            cut.em.flush();
            newTipoPrueba.setObservaciones(expected);
            newTipoPrueba = cut.update(newTipoPrueba);
            assertNotNull(newTipoPrueba);
            assertEquals(expected, newTipoPrueba.getObservaciones());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("TipoPruebaDAOImp.testDelete");
        TipoPruebaDAOImp cut = new TipoPruebaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newTipoPrueba);
            cut.em.flush();
            cut.em.clear();
            cut.delete(newTipoPrueba);
            TipoPrueba deleted = cut.findById(newTipoPrueba.getIdTipoPrueba());
            assertNull(deleted);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(7)
    void testCount() {
        System.out.println("TipoPruebaDAOImp.testCount");
        TipoPruebaDAOImp cut = new TipoPruebaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newTipoPrueba);
            cut.em.flush();
            cut.em.clear();
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos > 1);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }
    
    
    @Test
    @Order(8)
    void testToDto() {
        System.out.println("TipoPruebaDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("34");
        TipoPruebaDAOImp cut = new TipoPruebaDAOImp();
        TipoPrueba tp = null;
        TipoPruebaDTO pdDTO;
        
        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(tp);
//PreguntaDistractorDAOImp
        });
        pdDTO = cut.toDto(new TipoPrueba(1, "examen presencial", true, "NADA"));
        assertNotNull(pdDTO);
        assertEquals(1, pdDTO.idTipoPrueba());
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("TipoPruebaDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("34");
        TipoPruebaDAOImp cut = new TipoPruebaDAOImp();
        TipoPruebaDTO tpDTO = null;
        TipoPrueba tp1;
        TipoPrueba tp2;

        assertThrows(IllegalStateException.class, () -> {
            cut.toEntity(tpDTO);

        });
        tp1 = cut.toEntity(new TipoPruebaDTO(1, "Examen Presencial", true, "nada"));
        assertNotNull(tp1);
        assertEquals(1, tp1.getIdTipoPrueba());
       
    }
//TipoPruebaDAOImp
}
