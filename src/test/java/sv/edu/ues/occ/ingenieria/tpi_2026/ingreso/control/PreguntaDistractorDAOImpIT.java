/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PreguntaDistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractorPK;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PreguntaDistractorDAOImpIT extends ITAbstract {

    PreguntaDistractor preguntaDistractor;
    Pregunta pregunta;
    Distractor distractor;
    PreguntaDistractorPK preDisPK;
    
    private void persistirEscenarioCompleto(EntityManager em) {
            em.persist(pregunta);
            em.persist(distractor);
            em.flush();
            preDisPK = new PreguntaDistractorPK(
                    pregunta.getIdPregunta(), distractor.getIdDistractor()
            );
            preguntaDistractor.setPreguntaDistractorPK(preDisPK);
            em.persist(preguntaDistractor);
            em.flush();
    }

    @BeforeAll
    void init() {

        pregunta = new Pregunta(null, "A que velocidad corre el atleta?", true, "IMAGEN", "saludos");
        distractor = new Distractor(null, "120KM/h", true, "Imagen");
        preguntaDistractor = new PreguntaDistractor(null, false, "aportar mas detalles");

        preguntaDistractor.setPregunta(pregunta);
        preguntaDistractor.setDistractor(distractor);

    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("PreguntaDistractorDAOImp.testCreate");
        PreguntaDistractorDAOImp cut = new PreguntaDistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {
            tx.begin();

            cut.em.persist(pregunta);
            cut.em.persist(distractor);

            cut.em.flush();

            preDisPK = new PreguntaDistractorPK(pregunta.getIdPregunta(), distractor.getIdDistractor());
            preguntaDistractor.setPreguntaDistractorPK(preDisPK);

            cut.em.clear();
            cut.create(preguntaDistractor);
            PreguntaDistractorPK pk = preguntaDistractor.getPreguntaDistractorPK();
            assertNotNull(pk);
            PreguntaDistractor creado = cut.em.find(PreguntaDistractor.class, pk);
            assertNotNull(creado);
            assertEquals(false, creado.getCorrecto());
            assertEquals(preguntaDistractor.getPreguntaDistractorPK(), creado.getPreguntaDistractorPK());
        } finally {
            tx.rollback();
            cut.em.close();

        }
    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("PreguntaDistractorDAOImp.testFindById");
        PreguntaDistractorDAOImp cut = new PreguntaDistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            PreguntaDistractor found = cut.em.find(PreguntaDistractor.class, preDisPK);
            assertNotNull(found);
            assertEquals(preguntaDistractor.getPreguntaDistractorPK(), found.getPreguntaDistractorPK());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("PreguntaDistractorDAOImp.testFindAll");
        PreguntaDistractorDAOImp cut = new PreguntaDistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            List<PreguntaDistractor> resultList = cut.findAll();
            assertNotNull(resultList);
            assertTrue(resultList.size()>0);
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(4)
    void testFindRange() {
        System.out.println("PreguntaDistractorDAOImp.testFindRange");
        PreguntaDistractorDAOImp cut = new PreguntaDistractorDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            List<PreguntaDistractor> resultList = cut.findByRange(offset, limit);
            assertNotNull(resultList);
            assertTrue(resultList.size() >0);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(5)
    void testUpdate() {
        System.out.println("PreguntaDistractorDAOImp.testUpdate");
        String expected = "SUFICINTES DETALLES";
        PreguntaDistractorDAOImp cut = new PreguntaDistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            preguntaDistractor.setObservaciones(expected);
            PreguntaDistractor entidad = cut.update(preguntaDistractor);
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
        System.out.println("PreguntaDistractorDAOImp.testDelete");
        PreguntaDistractorDAOImp cut = new PreguntaDistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();

            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            cut.delete(preguntaDistractor);
            PreguntaDistractor deleted = cut.findById(preDisPK);
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
        System.out.println("PreguntaDistractorDAOImp.testCount");
        PreguntaDistractorDAOImp cut = new PreguntaDistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            persistirEscenarioCompleto(cut.em);
            cut.em.clear();
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos>0);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(8)
    void testToDto() {
        System.out.println("PreguntaDistractorDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("34");
        PreguntaDistractorDAOImp cut = new PreguntaDistractorDAOImp();
        PreguntaDistractor preDistractor = null;
        PreguntaDistractorDTO pdDTO;
        PreguntaDistractorDTO pdDTO1;
        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(preDistractor);
        });
        pdDTO = cut.toDto(new PreguntaDistractor(new PreguntaDistractorPK(1l, 1l), true, "no hay"));
        pdDTO1 = cut.toDto(new PreguntaDistractor(null, true, "no hay"));

        assertNotNull(pdDTO);
        assertEquals(1, pdDTO.idPregunta());
        assertEquals(1, pdDTO.idDistractor());
        assertNotNull(pdDTO1);
        assertEquals(0, pdDTO1.idPregunta());
        assertEquals(0, pdDTO1.idDistractor());
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("PreguntaDistractorDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("34");
        PreguntaDistractorDAOImp cut = new PreguntaDistractorDAOImp();
        PreguntaDistractorDTO pdDTO = null;
        PreguntaDistractor pd;
        PreguntaDistractor pd1;

        assertThrows(IllegalStateException.class, () -> {
            cut.toEntity(pdDTO);

        });
        pd = cut.toEntity(new PreguntaDistractorDTO(1, 1, true, "no hay"));
        assertNotNull(pd);
        assertNotNull(pd.getPreguntaDistractorPK());
       
    }

}
