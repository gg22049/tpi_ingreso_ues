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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteIdentificacionDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimientoPK;
/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PreguntaAreaConocimientoDAOImpIT extends ITAbstract{
    
  PreguntaAreaConocimiento preguntaAreaConocimiento;
   Pregunta pregunta;
    AreaConocimiento areaConocimiento;
    PreguntaAreaConocimientoPK preAreaPK;

    @BeforeAll
    void init() {
        

        pregunta = new Pregunta(null, "PREGUNTAME, PREGUNTAME", true, "IMAGEN","No hay observaciones");
        areaConocimiento = new AreaConocimiento(null, "PROGRAMACION", "PREGUNTAS SOBRE JAVA", true, null);
       
        preguntaAreaConocimiento = new PreguntaAreaConocimiento(null, "En verdad esta pregunta es de esta area?");

        preguntaAreaConocimiento.setPregunta(pregunta);
        preguntaAreaConocimiento.setAreaConocimiento(areaConocimiento);
       
    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("PreguntaAreaConocimientoDAOImp.testCreate");
        PreguntaAreaConocimientoDAOImp cut = new PreguntaAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {
            tx.begin();
            cut.em.persist(pregunta);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            preAreaPK = new PreguntaAreaConocimientoPK(pregunta.getIdPregunta(), areaConocimiento.getIdAreaConocimiento());
            preguntaAreaConocimiento.setPreguntaAreaConocimientoPK(preAreaPK);
            cut.em.clear();
            cut.create(preguntaAreaConocimiento);
            PreguntaAreaConocimientoPK pk = preguntaAreaConocimiento.getPreguntaAreaConocimientoPK();
            assertNotNull(pk);
            PreguntaAreaConocimiento creado = cut.em.find(PreguntaAreaConocimiento.class, pk);
            assertNotNull(creado);
            assertEquals("En verdad esta pregunta es de esta area?", creado.getObservaciones());
            assertEquals(preguntaAreaConocimiento.getPreguntaAreaConocimientoPK(), creado.getPreguntaAreaConocimientoPK());
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("PreguntaAreaConocimientoDAOImp.testFindById");
        PreguntaAreaConocimientoDAOImp cut = new PreguntaAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();

            cut.em.persist(pregunta);
            cut.em.persist(areaConocimiento);
            cut.em.flush();

            PreguntaAreaConocimientoPK pk = new PreguntaAreaConocimientoPK(
                    pregunta.getIdPregunta(), areaConocimiento.getIdAreaConocimiento()
            );
            preguntaAreaConocimiento.setPreguntaAreaConocimientoPK(pk);
            cut.em.persist(preguntaAreaConocimiento);
            cut.em.flush();
            cut.em.clear();

            PreguntaAreaConocimiento found = cut.em.find(PreguntaAreaConocimiento.class, pk);
            assertNotNull(found, "aspirante esta null");
            assertEquals(preguntaAreaConocimiento.getPreguntaAreaConocimientoPK(), found.getPreguntaAreaConocimientoPK());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("PreguntaAreaConocimientoDAOImp.testFindAll");
        PreguntaAreaConocimientoDAOImp cut = new PreguntaAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(pregunta);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            PreguntaAreaConocimientoPK pk = new PreguntaAreaConocimientoPK(
                    pregunta.getIdPregunta(), areaConocimiento.getIdAreaConocimiento()
            );
            preguntaAreaConocimiento.setPreguntaAreaConocimientoPK(pk);
            cut.em.persist(preguntaAreaConocimiento);
            cut.em.flush();
            cut.em.clear();
            List<PreguntaAreaConocimiento> resultList = cut.findAll();
            assertNotNull(resultList);
            assertTrue(resultList.size() ==2);
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(4)
    void testFindRange() {
        System.out.println("PreguntaAreaConocimientoDAOImp.testFindRange");
        PreguntaAreaConocimientoDAOImp cut = new PreguntaAreaConocimientoDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(pregunta);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            PreguntaAreaConocimientoPK pk = new PreguntaAreaConocimientoPK(
                    pregunta.getIdPregunta(), areaConocimiento.getIdAreaConocimiento()
            );
            preguntaAreaConocimiento.setPreguntaAreaConocimientoPK(pk);
            cut.em.persist(preguntaAreaConocimiento);
            cut.em.flush();
            cut.em.clear();
            List<PreguntaAreaConocimiento> resultList = cut.findByRange(offset, limit);
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
        System.out.println("PreguntaAreaConocimientoDAOImp.testUpdate");
        String expected = "TEST ACTUALIZADO";
        PreguntaAreaConocimientoDAOImp cut = new PreguntaAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(pregunta);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            PreguntaAreaConocimientoPK pk = new PreguntaAreaConocimientoPK(
                    pregunta.getIdPregunta(), areaConocimiento.getIdAreaConocimiento()
            );
            preguntaAreaConocimiento.setPreguntaAreaConocimientoPK(pk);
            cut.em.persist(preguntaAreaConocimiento);
            cut.em.flush();
            preguntaAreaConocimiento.setObservaciones(expected);
            PreguntaAreaConocimiento entidad = cut.update(preguntaAreaConocimiento);
            assertNotNull(entidad);
            assertEquals(expected, entidad.getObservaciones());
            System.out.println(entidad.getObservaciones());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("PreguntaAreaConocimientoDAOImp.testDelete");
        PreguntaAreaConocimientoDAOImp cut = new PreguntaAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();

            cut.em.persist(pregunta);
            cut.em.persist(areaConocimiento);
            cut.em.flush();

            PreguntaAreaConocimientoPK pk = new PreguntaAreaConocimientoPK(
                    pregunta.getIdPregunta(), areaConocimiento.getIdAreaConocimiento()
            );
            preguntaAreaConocimiento.setPreguntaAreaConocimientoPK(pk);
            cut.em.persist(preguntaAreaConocimiento);
            cut.em.flush();
            cut.em.clear();

            cut.delete(preguntaAreaConocimiento);
            cut.em.flush();
            cut.em.clear();
            PreguntaAreaConocimiento deleted = cut.findById(pk);
            assertNull(deleted);
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos == 1);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(7)
    void testCount() {
        System.out.println("PreguntaAreaConocimientoDAOImp.testCount");
        PreguntaAreaConocimientoDAOImp cut = new PreguntaAreaConocimientoDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(pregunta);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            cut.em.clear();
            PreguntaAreaConocimientoPK pk = new PreguntaAreaConocimientoPK(
                    pregunta.getIdPregunta(), areaConocimiento.getIdAreaConocimiento()
            );
            preguntaAreaConocimiento.setPreguntaAreaConocimientoPK(pk);
            cut.em.persist(preguntaAreaConocimiento);
            cut.em.flush();
            cut.em.clear();
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos== 2);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }
    
    @Test
    @Order(8)
    void testToDto() {
        System.out.println("PreguntaAreaConocimientoDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        PreguntaAreaConocimientoDAOImp cut = new PreguntaAreaConocimientoDAOImp();
        PreguntaAreaConocimiento pac = null;
        PreguntaAreaConocimientoDTO pacDTO;
        PreguntaAreaConocimientoDTO pacDTO1;
        
        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(pac);
        });

        pacDTO = cut.toDto(new PreguntaAreaConocimiento(new PreguntaAreaConocimientoPK(1l, 1), ""));
        assertNotNull(pacDTO);
        assertEquals(1l, pacDTO.idPregunta());
        pacDTO1 = cut.toDto(new PreguntaAreaConocimiento(null, ""));
        assertNotNull(pacDTO1);
        assertEquals(0, pacDTO1.idAreaConocimiento());
        assertEquals(0, pacDTO1.idPregunta());
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("PreguntaAreaConocimientoDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        PreguntaAreaConocimientoDAOImp cut = new PreguntaAreaConocimientoDAOImp();
      

        PreguntaAreaConocimiento pac;
        PreguntaAreaConocimientoDTO pacDTO = null;
        assertThrows(IllegalStateException.class, () -> {
            cut.toEntity(pacDTO);

        });

        pac = cut.toEntity(new PreguntaAreaConocimientoDTO(1l, 1, ""));
        assertNotNull(pac);
        assertNotNull(pac.getPreguntaAreaConocimientoPK());
        assertEquals(1, pac.getPreguntaAreaConocimientoPK().getIdAreaConocimiento());
        assertEquals(1, pac.getPreguntaAreaConocimientoPK().getIdPregunta());
    }
}
