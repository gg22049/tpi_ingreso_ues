/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityTransaction;
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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.JornadaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JornadaDAOImpIT extends ITAbstract {

    Jornada newJornada;

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);

    
        newJornada = new Jornada(null, "Primera Jornada Nuevo Ingreso 2027", cal.getTime(), cal.getTime(), "Llegar 30 minutos antes.");
  

    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("JornadaDAOImp.testCreate");
        JornadaDAOImp cut = new JornadaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newJornada);
            cut.create(newJornada);
            cut.em.flush();
            cut.em.clear();
            Jornada creado = cut.em.find(Jornada.class, newJornada.getIdJornada());
            assertNotNull(creado);
            assertEquals("Primera Jornada Nuevo Ingreso 2027", creado.getNombre());
            assertEquals(newJornada.getIdJornada(), creado.getIdJornada());
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("JornadaDAOImp.testFindById");
        JornadaDAOImp cut = new JornadaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newJornada);
            cut.em.flush();
            cut.em.clear();
            Jornada found = cut.em.find(Jornada.class, newJornada.getIdJornada());
            assertNotNull(found);
            assertEquals(newJornada.getIdJornada(), found.getIdJornada());
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
            cut.em.persist(newJornada);
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
        System.out.println("JornadaDAOImp.testFindRange");
        JornadaDAOImp cut = new JornadaDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newJornada);
            cut.em.flush();
            cut.em.clear();
            List<Jornada> resultList = cut.findByRange(offset, limit);
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
        System.out.println("JornadaDAOImp.testUpdate");
        String expected = "Llegar 45 minutos antes.";
        JornadaDAOImp cut = new JornadaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newJornada);
            cut.em.flush();
            newJornada.setObservaciones(expected);
            newJornada = cut.update(newJornada);
            assertNotNull(newJornada);
            assertEquals(expected, newJornada.getObservaciones());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("JornadaDAOImp.testDelete");
        JornadaDAOImp cut = new JornadaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newJornada);
            cut.em.flush();
            cut.em.clear();
            cut.delete(newJornada);
            Jornada deleted = cut.findById(newJornada.getIdJornada());
            assertNull(deleted);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(7)
    void testCount() {
        System.out.println("JornadaDAOImp.testCount");
        JornadaDAOImp cut = new JornadaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newJornada);
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
    void testToDto(){
        System.out.println("JornadaDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        JornadaDAOImp cut = new JornadaDAOImp();
        Jornada jornada1= new Jornada(1l, "2028", cal.getTime(), cal.getTime(), "no hay");
        JornadaDTO jornadaDTO;
        Jornada jornada2=null;
        assertThrows(IllegalStateException.class, () -> {
          cut.toDto(jornada2);
           
        });
        
         jornadaDTO=cut.toDto(jornada1);
         assertNotNull(jornadaDTO);
    }
    
      @Test
    @Order(9)
    void testToEntity(){
        System.out.println("JornadaDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
       JornadaDAOImp cut = new JornadaDAOImp();
        JornadaDTO jornadaDTO1= new JornadaDTO(1l, "jornada 2028", cal.getTime(), cal.getTime(), "no hay");
        Jornada jornada;
        JornadaDTO jornada2=null;
        assertThrows(IllegalStateException.class, () -> {
          cut.toEntity(jornada2);
           
        });
        
         jornada=cut.toEntity(jornadaDTO1);
         assertNotNull(jornada);
    }
}
