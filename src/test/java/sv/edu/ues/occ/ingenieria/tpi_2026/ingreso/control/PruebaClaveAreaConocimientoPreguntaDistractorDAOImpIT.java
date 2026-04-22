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
import static sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control.ITAbstract.emf;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.DistractorAreaConocimientoDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaClaveAreaConocimientoPreguntaDistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimientoPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClave;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimiento;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPregunta;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractor;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaDistractorPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPreguntaPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PruebaClaveAreaConocimientoPreguntaDistractorDAOImpIT {

    PruebaClaveAreaConocimientoPregunta pcacP;
    PruebaClaveAreaConocimientoPreguntaPK pcacPPK;

    PruebaClaveAreaConocimiento pcac;
    PruebaClaveAreaConocimientoPK pcacPK;

    Prueba prueba;
    PruebaClave pruebaClave;
    TipoPrueba tipoPrueba;
    AreaConocimiento areaConocimiento;

    Pregunta pregunta;

    Distractor distractor;

    PruebaClaveAreaConocimientoPreguntaDistractor pcacpD;
    PruebaClaveAreaConocimientoPreguntaDistractorPK pcacpDPK;

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal = new BigDecimal("100");
        distractor = new Distractor(null, "Si, pero pagando", true, "IMAGEN");
        pregunta = new Pregunta(null, "Vamos a llegar al mundial alguna vez?", true, "imagen", "NUNCA");

        areaConocimiento = new AreaConocimiento(null, "MATE", "matematica avanzada", true, null);

        prueba = new Prueba(null, "Prueba 2026", "Trabajar sin ayuda", decimal, decimal, 100, null, null);
        pruebaClave = new PruebaClave(null, "Clave A");
        tipoPrueba = new TipoPrueba(null, "En linea", true, "Suerte");
        pcac = new PruebaClaveAreaConocimiento(null, 25, decimal);

        pcacP = new PruebaClaveAreaConocimientoPregunta(null, decimal);

        pcacpD = new PruebaClaveAreaConocimientoPreguntaDistractor(null, null, "NO se que poner");
    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDIstractorImp.testCreate");
        PruebaClaveAreaConocimientoPreguntaDistractorDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {

            tx.begin();
            cut.em.persist(distractor);
            cut.em.persist(pregunta);
            cut.em.persist(tipoPrueba);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            cut.em.flush();
            pruebaClave.setIdPrueba(prueba);
            cut.em.persist(pruebaClave);
            cut.em.flush();

            pcacPK = new PruebaClaveAreaConocimientoPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento());
            pcac.setPruebaClaveAreaConocimientoPK(pcacPK);
            cut.em.persist(pcac);
            cut.em.flush();

            pcacPPK = new PruebaClaveAreaConocimientoPreguntaPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento(),
                    pregunta.getIdPregunta());
            pcacP.setPruebaClaveAreaConocimiento(pcac);
            pcacP.setPruebaClaveAreaConocimientoPreguntaPK(pcacPPK);
            cut.em.persist(pcacP);
            cut.em.flush();
            pcacpD.setPruebaClaveAreaConocimientoPregunta(pcacP);
            pcacpDPK = new PruebaClaveAreaConocimientoPreguntaDistractorPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento(),
                    pregunta.getIdPregunta(), distractor.getIdDistractor());
            pcacpD.setPruebaClaveAreaConocimientoPreguntaDistractorPK(pcacpDPK);
            cut.create(pcacpD);
            cut.em.flush();
            cut.em.clear();
            PruebaClaveAreaConocimientoPreguntaDistractor entidad = cut.findById(pcacpDPK);
            assertNotNull(entidad);
            assertEquals(pcacpDPK, entidad.getPruebaClaveAreaConocimientoPreguntaDistractorPK());
            System.out.println("-----------------------------------");
            System.out.println(entidad.getObservaciones());
        } finally {
            tx.rollback();
            cut.em.close();

        }
    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDistractorDAOImp.testFindById");
        PruebaClaveAreaConocimientoPreguntaDistractorDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(distractor);
            cut.em.persist(pregunta);
            cut.em.persist(tipoPrueba);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            cut.em.flush();
            pruebaClave.setIdPrueba(prueba);
            cut.em.persist(pruebaClave);
            cut.em.flush();

            pcacPK = new PruebaClaveAreaConocimientoPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento());
            pcac.setPruebaClaveAreaConocimientoPK(pcacPK);
            cut.em.persist(pcac);
            cut.em.flush();

            pcacPPK = new PruebaClaveAreaConocimientoPreguntaPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento(),
                    pregunta.getIdPregunta());
            pcacP.setPruebaClaveAreaConocimiento(pcac);
            pcacP.setPruebaClaveAreaConocimientoPreguntaPK(pcacPPK);
            cut.em.persist(pcacP);
            cut.em.flush();
            pcacpD.setPruebaClaveAreaConocimientoPregunta(pcacP);
            pcacpDPK = new PruebaClaveAreaConocimientoPreguntaDistractorPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento(),
                    pregunta.getIdPregunta(), distractor.getIdDistractor());
            pcacpD.setPruebaClaveAreaConocimientoPreguntaDistractorPK(pcacpDPK);
            cut.em.persist(pcacpD);
            cut.em.flush();
            cut.em.clear();
            PruebaClaveAreaConocimientoPreguntaDistractor entidad = cut.findById(pcacpDPK);
            assertNotNull(entidad);
            assertEquals(pcacpDPK, entidad.getPruebaClaveAreaConocimientoPreguntaDistractorPK());

        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDistractorDAOImp.testFindAll");
        PruebaClaveAreaConocimientoPreguntaDistractorDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();

            List<PruebaClaveAreaConocimientoPreguntaDistractor> resultList = cut.findAll();
            assertNotNull(resultList);
            assertTrue(resultList.size() == 1);

        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(4)
    void testFindRange() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDistractorDAOImp.testFindRange");
        PruebaClaveAreaConocimientoPreguntaDistractorDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDistractorDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            List<PruebaClaveAreaConocimientoPreguntaDistractor> resultList = cut.findByRange(offset, limit);
            assertNotNull(resultList);
            assertTrue(resultList.size() == 1);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(5)
    void testUpdate() {
        System.out.println("PruebaClaveAreaConocimientoPreguntaDistractorDAOImp.testUpdate");
        String expected = "ACTUALIZADO";

        PruebaClaveAreaConocimientoPreguntaDistractorDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(distractor);
            cut.em.persist(pregunta);
            cut.em.persist(tipoPrueba);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            cut.em.flush();
            pruebaClave.setIdPrueba(prueba);
            cut.em.persist(pruebaClave);
            cut.em.flush();

            pcacPK = new PruebaClaveAreaConocimientoPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento());
            pcac.setPruebaClaveAreaConocimientoPK(pcacPK);
            cut.em.persist(pcac);
            cut.em.flush();

            pcacPPK = new PruebaClaveAreaConocimientoPreguntaPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento(),
                    pregunta.getIdPregunta());
            pcacP.setPruebaClaveAreaConocimiento(pcac);
            pcacP.setPruebaClaveAreaConocimientoPreguntaPK(pcacPPK);
            cut.em.persist(pcacP);
            cut.em.flush();
            pcacpD.setPruebaClaveAreaConocimientoPregunta(pcacP);
            pcacpDPK = new PruebaClaveAreaConocimientoPreguntaDistractorPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento(),
                    pregunta.getIdPregunta(), distractor.getIdDistractor());
            pcacpD.setPruebaClaveAreaConocimientoPreguntaDistractorPK(pcacpDPK);
            cut.em.persist(pcacpD);
            cut.em.flush();
            cut.em.clear();
            pcacpD.setObservaciones(expected);
            PruebaClaveAreaConocimientoPreguntaDistractor entidad = cut.update(pcacpD);
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
        System.out.println("PruebaClaveAreaConocimientoPreguntaDAOImp.testDelete");
        PruebaClaveAreaConocimientoPreguntaDistractorDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {

            tx.begin();
            cut.em.persist(distractor);
            cut.em.persist(pregunta);
            cut.em.persist(tipoPrueba);
            cut.em.persist(areaConocimiento);
            cut.em.flush();
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            cut.em.flush();
            pruebaClave.setIdPrueba(prueba);
            cut.em.persist(pruebaClave);
            cut.em.flush();

            pcacPK = new PruebaClaveAreaConocimientoPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento());
            pcac.setPruebaClaveAreaConocimientoPK(pcacPK);
            cut.em.persist(pcac);
            cut.em.flush();

            pcacPPK = new PruebaClaveAreaConocimientoPreguntaPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento(),
                    pregunta.getIdPregunta());
            pcacP.setPruebaClaveAreaConocimiento(pcac);
            pcacP.setPruebaClaveAreaConocimientoPreguntaPK(pcacPPK);
            cut.em.persist(pcacP);
            cut.em.flush();
            pcacpD.setPruebaClaveAreaConocimientoPregunta(pcacP);
            pcacpDPK = new PruebaClaveAreaConocimientoPreguntaDistractorPK(pruebaClave.getIdPruebaClave(), areaConocimiento.getIdAreaConocimiento(),
                    pregunta.getIdPregunta(), distractor.getIdDistractor());
            pcacpD.setPruebaClaveAreaConocimientoPreguntaDistractorPK(pcacpDPK);
            cut.em.persist(pcacpD);
            cut.em.flush();
            cut.em.clear();
            cut.delete(pcacpD);
            PruebaClaveAreaConocimientoPreguntaDistractor deleted = cut.findById(pcacpDPK);
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
        System.out.println("PruebaClaveAreaConocimientoPreguntaDistractorDAOImp.testCount");
        PruebaClaveAreaConocimientoPreguntaDistractorDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos == 1);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(8)
    void testToDto() {
        System.out.println("PruebaClaveAreaConocimeintoPreguntaDistractorDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        PruebaClaveAreaConocimientoPreguntaDistractorDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDistractorDAOImp();
        PruebaClaveAreaConocimientoPreguntaDistractor pcacpd = null;
        PruebaClaveAreaConocimientoPreguntaDistractorDTO pcacpdDTO;
        PruebaClaveAreaConocimientoPreguntaDistractorDTO pcacpdDTO1;
        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(pcacpd);

        });
        pcacpdDTO = cut.toDto(new PruebaClaveAreaConocimientoPreguntaDistractor(
                new PruebaClaveAreaConocimientoPreguntaDistractorPK(1l, 1, 1l, 1l),
                cal.getTime(), "nada"
        ));
        pcacpdDTO1=cut.toDto(new PruebaClaveAreaConocimientoPreguntaDistractor(null, cal.getTime(), "nada"));
        
        assertNotNull(pcacpdDTO);
        assertNotNull(pcacpdDTO.idDistractor());
        assertNotNull(pcacpdDTO1);
        assertNotNull(pcacpdDTO1.idDistractor());
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("PruebaClaveAreaConocimeintoPreguntaDistractorDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        PruebaClaveAreaConocimientoPreguntaDistractorDAOImp cut = new PruebaClaveAreaConocimientoPreguntaDistractorDAOImp();
        PruebaClaveAreaConocimientoPreguntaDistractorDTO pcacpdDTO = null;
        PruebaClaveAreaConocimientoPreguntaDistractor pcacpd;
        PruebaClaveAreaConocimientoPreguntaDistractor pcacpd1;

        assertThrows(IllegalStateException.class, () -> {
            cut.toEntity(pcacpdDTO);

        });
        pcacpd = cut.toEntity(new PruebaClaveAreaConocimientoPreguntaDistractorDTO(1l, 1, 1l, 1, cal.getTime(), "nada"));
        assertNotNull(pcacpd);
        //pcacpd1 = cut.toEntity(new PruebaClaveAreaConocimientoPreguntaDistractorDTO(0, 0, 0, 0, cal.getTime(), "nada"));
        //assertNotNull(pcacpd1);
        //assertNull(pcacpd1.getDistractor());

    }
}
