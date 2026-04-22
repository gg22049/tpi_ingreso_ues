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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta;
/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PreguntaDAOImpIT extends ITAbstract{
    
    Pregunta newPregunta;

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);

        //parentAspirante = new Aspirante(null, "TEST", "RELACION", cal.getTime(), "relacion@test.com");
        //aspIdenPK = new AspiranteIdentificacionPK();
        newPregunta = new Pregunta(null, "¿Puede salirse para fuera de la casa?", true,"IMAGEN", "La pregunta esta mal planteada");
        //newAspiranteIdentificacion.setAspiranteIdentificacionPK(aspIdenPK);

    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("PreguntaDAOImp.testCreate");
        PreguntaDAOImp cut = new PreguntaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newPregunta);
            cut.create(newPregunta);
            cut.em.flush();
            cut.em.clear();
            Pregunta creado = cut.em.find(Pregunta.class, newPregunta.getIdPregunta());
            assertNotNull(creado);
            assertEquals("¿Puede salirse para fuera de la casa?", creado.getValor());
            assertEquals(newPregunta.getIdPregunta(), creado.getIdPregunta());
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("PreguntaDAOImp.testFindById");
        PreguntaDAOImp cut = new PreguntaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newPregunta);
            cut.em.flush();
            cut.em.clear();
            Pregunta found = cut.em.find(Pregunta.class, newPregunta.getIdPregunta());
            assertNotNull(found);
            assertEquals(newPregunta.getIdPregunta(), found.getIdPregunta());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("PreguntaImp.testFindAll");
        PreguntaDAOImp cut = new PreguntaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newPregunta);
            cut.em.flush();
            cut.em.clear();
            List<Pregunta> resultList = cut.findAll();
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
        System.out.println("PreguntaDAOImp.testFindRange");
        PreguntaDAOImp cut = new PreguntaDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newPregunta);
            cut.em.flush();
            cut.em.clear();
            List<Pregunta> resultList = cut.findByRange(offset, limit);
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
        System.out.println("PreguntaDAOImp.testUpdate");
        String expected = "¿Puede salirse de la casa?";
        PreguntaDAOImp cut = new PreguntaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newPregunta);
            cut.em.flush();
            newPregunta.setObservaciones(expected);
            newPregunta = cut.update(newPregunta);
            assertNotNull(newPregunta);
            assertEquals(expected, newPregunta.getObservaciones());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("PreguntaDAOImp.testDelete");
        PreguntaDAOImp cut = new PreguntaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newPregunta);
            cut.em.flush();
            cut.em.clear();
            cut.delete(newPregunta);
            Pregunta deleted = cut.findById(newPregunta.getIdPregunta());
            assertNull(deleted);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(7)
    void testCount() {
        System.out.println("PreguntaDAOImp.testCount");
        PreguntaDAOImp cut = new PreguntaDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newPregunta);
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
        System.out.println("PreguntaDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("34");
        PreguntaDAOImp cut = new PreguntaDAOImp();
        Pregunta p = null;
        PreguntaDTO pDTO;
        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(p);
        });
        pDTO = cut.toDto(new Pregunta(1l, "Que hace?", true, "imagen", ""));
        assertNotNull(pDTO);
        assertEquals(1, pDTO.idPregunta());
        assertEquals("Que hace?", pDTO.valor());
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("PreguntaDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        PreguntaDAOImp cut = new PreguntaDAOImp();
        PreguntaDTO pDTO = null;
        Pregunta p1;
        assertThrows(IllegalStateException.class, () -> {
            cut.toEntity(pDTO);
        });
        p1 = cut.toEntity(new PreguntaDTO(1l, "Que es TDD?", true, "IMAGEN", ""));
        assertNotNull(p1);
        assertEquals("Que es TDD?", p1.getValor());
        
    }

}
