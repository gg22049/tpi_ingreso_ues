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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.PruebaJornadaAulaAspiranteOpcionExamenDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteOpcion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Jornada;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAula;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.JornadaAulaPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Prueba;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcion;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamen;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionExamenPK;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionPK;

import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.TipoPrueba;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PruebaJornadaAulaAspiranteOpcionExamenDAOImpIT extends ITAbstract {

    PruebaJornadaAulaAspiranteOpcionExamen pjaaE;
    PruebaJornadaAulaAspiranteOpcionExamenPK pjaaEPK;

    PruebaJornadaAulaAspiranteOpcion pjaao;
    Prueba prueba;
    JornadaAula jornadaAula;
    AspiranteOpcion aspiranteOpcion;
    PruebaJornadaAulaAspiranteOpcionPK pjaaoPK;

    Jornada jornada;
    JornadaAulaPK jorAulaPK;

    TipoPrueba tipoPrueba;

    Aspirante aspirante;

    @BeforeAll
    void init() {

        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        //aspiranteOpcion
        aspirante = new Aspirante(-1L, "TEST", "INTEGRACION", cal.getTime(), "hola@test");
        aspiranteOpcion = new AspiranteOpcion(null);
        aspiranteOpcion.setIdOpcion("I300515");
        //creacion jornadaAula
        jornada = new Jornada(null, "Primera Jornada Nuevo Ingreso 2027", cal.getTime(), cal.getTime(), "Llegar 30 minutos antes.");
        jornadaAula = new JornadaAula();
        jornadaAula.setJornada(jornada);
        jornadaAula.setObservaciones("Buena Aula");
        //prueba
        BigDecimal decimal = new BigDecimal("100");
        tipoPrueba = new TipoPrueba(null, "Examen Presencial", true, "Prueba presencial, no puede ser realizada en otro "
                + "lugar que no se la UES");
        prueba = new Prueba(null, "prueba 2027", decimal, decimal, null);
        prueba.setIndicaciones("trabajar de forma ordenada");
        prueba.setDuracion(100);

        pjaao = new PruebaJornadaAulaAspiranteOpcion(null, true, cal.getTime());

        pjaaE = new PruebaJornadaAulaAspiranteOpcionExamen(null, decimal, cal.getTime(), "IMAGEN", "Felicitaciones");

    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenImp.testCreate");
        PruebaJornadaAulaAspiranteOpcionExamenDAOImp cut = new PruebaJornadaAulaAspiranteOpcionExamenDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {
            tx.begin();
            //persistimos lo necesario para crear todas las relaciones
            cut.em.persist(tipoPrueba);
            cut.em.persist(jornada);
            cut.em.persist(aspirante);
            cut.em.flush();
            cut.em.persist(aspiranteOpcion);
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            jorAulaPK = new JornadaAulaPK(jornada.getIdJornada(), "A3");
            jornadaAula.setJornadaAulaPK(jorAulaPK);
            cut.em.persist(jornadaAula);
            aspiranteOpcion.setIdAspirante(aspirante);
            cut.em.persist(aspiranteOpcion);
            cut.em.flush();

            pjaaoPK = new PruebaJornadaAulaAspiranteOpcionPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaao.setPruebaJornadaAulaAspiranteOpcionPK(pjaaoPK);

            cut.em.persist(pjaao);
            cut.em.flush();
            pjaaE.setPruebaJornadaAulaAspiranteOpcion(pjaao);
            pjaaEPK = new PruebaJornadaAulaAspiranteOpcionExamenPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaaE.setPruebaJornadaAulaAspiranteOpcionExamenPK(pjaaEPK);
            cut.create(pjaaE);
            cut.em.flush();
            PruebaJornadaAulaAspiranteOpcionExamen entidad = cut.findById(pjaaEPK);
            assertNotNull(entidad);
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
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenDAOImp.testFindById");
        PruebaJornadaAulaAspiranteOpcionExamenDAOImp cut = new PruebaJornadaAulaAspiranteOpcionExamenDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            //persistimos lo necesario para crear todas las relaciones
            cut.em.persist(tipoPrueba);
            cut.em.persist(jornada);
            cut.em.persist(aspirante);
            cut.em.flush();
            cut.em.persist(aspiranteOpcion);
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            jorAulaPK = new JornadaAulaPK(jornada.getIdJornada(), "A3");
            jornadaAula.setJornadaAulaPK(jorAulaPK);
            cut.em.persist(jornadaAula);
            aspiranteOpcion.setIdAspirante(aspirante);
            cut.em.persist(aspiranteOpcion);
            cut.em.flush();
            pjaaoPK = new PruebaJornadaAulaAspiranteOpcionPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaao.setPruebaJornadaAulaAspiranteOpcionPK(pjaaoPK);
            cut.em.persist(pjaao);
            cut.em.flush();
            pjaaE.setPruebaJornadaAulaAspiranteOpcion(pjaao);
            pjaaEPK = new PruebaJornadaAulaAspiranteOpcionExamenPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaaE.setPruebaJornadaAulaAspiranteOpcionExamenPK(pjaaEPK);
            cut.em.persist(pjaaE);
            cut.em.flush();
            cut.em.clear();
            PruebaJornadaAulaAspiranteOpcionExamen found = cut.em.find(PruebaJornadaAulaAspiranteOpcionExamen.class, pjaaEPK);

            assertNotNull(found);
            assertEquals(pjaaE.getPruebaJornadaAulaAspiranteOpcionExamenPK(), found.getPruebaJornadaAulaAspiranteOpcionExamenPK());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenDAOImp.testFindAll");
        PruebaJornadaAulaAspiranteOpcionExamenDAOImp cut = new PruebaJornadaAulaAspiranteOpcionExamenDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            //persistimos lo necesario para crear todas las relaciones
            cut.em.persist(tipoPrueba);
            cut.em.persist(jornada);
            cut.em.persist(aspirante);
            cut.em.flush();
            cut.em.persist(aspiranteOpcion);
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            jorAulaPK = new JornadaAulaPK(jornada.getIdJornada(), "A3");
            jornadaAula.setJornadaAulaPK(jorAulaPK);
            cut.em.persist(jornadaAula);
            aspiranteOpcion.setIdAspirante(aspirante);
            cut.em.persist(aspiranteOpcion);
            cut.em.flush();
            pjaaoPK = new PruebaJornadaAulaAspiranteOpcionPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaao.setPruebaJornadaAulaAspiranteOpcionPK(pjaaoPK);
            cut.em.persist(pjaao);
            cut.em.flush();
            pjaaE.setPruebaJornadaAulaAspiranteOpcion(pjaao);
            pjaaEPK = new PruebaJornadaAulaAspiranteOpcionExamenPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaaE.setPruebaJornadaAulaAspiranteOpcionExamenPK(pjaaEPK);
            cut.em.persist(pjaaE);
            cut.em.flush();
            cut.em.clear();
            List<PruebaJornadaAulaAspiranteOpcionExamen> resultList = cut.findAll();
            assertNotNull(resultList);
            assertTrue(resultList.size() == 2);
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(4)
    void testFindRange() {
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenDAOImp.testFindRange");
        PruebaJornadaAulaAspiranteOpcionExamenDAOImp cut = new PruebaJornadaAulaAspiranteOpcionExamenDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            //persistimos lo necesario para crear todas las relaciones
            cut.em.persist(tipoPrueba);
            cut.em.persist(jornada);
            cut.em.persist(aspirante);
            cut.em.flush();
            cut.em.persist(aspiranteOpcion);
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            jorAulaPK = new JornadaAulaPK(jornada.getIdJornada(), "A3");
            jornadaAula.setJornadaAulaPK(jorAulaPK);
            cut.em.persist(jornadaAula);
            aspiranteOpcion.setIdAspirante(aspirante);
            cut.em.persist(aspiranteOpcion);
            cut.em.flush();
            pjaaoPK = new PruebaJornadaAulaAspiranteOpcionPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaao.setPruebaJornadaAulaAspiranteOpcionPK(pjaaoPK);
            cut.em.persist(pjaao);
            cut.em.flush();
            pjaaE.setPruebaJornadaAulaAspiranteOpcion(pjaao);
            pjaaEPK = new PruebaJornadaAulaAspiranteOpcionExamenPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaaE.setPruebaJornadaAulaAspiranteOpcionExamenPK(pjaaEPK);
            cut.em.persist(pjaaE);
            cut.em.flush();
            cut.em.clear();
            List<PruebaJornadaAulaAspiranteOpcionExamen> resultList = cut.findByRange(offset, limit);
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
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenDAOImp.testUpdate");
       String expected = "Estudiar mas para la proxima";
        PruebaJornadaAulaAspiranteOpcionExamenDAOImp cut = new PruebaJornadaAulaAspiranteOpcionExamenDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
             tx.begin();
            //persistimos lo necesario para crear todas las relaciones
            cut.em.persist(tipoPrueba);
            cut.em.persist(jornada);
            cut.em.persist(aspirante);
            cut.em.flush();
            cut.em.persist(aspiranteOpcion);
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            jorAulaPK = new JornadaAulaPK(jornada.getIdJornada(), "A3");
            jornadaAula.setJornadaAulaPK(jorAulaPK);
            cut.em.persist(jornadaAula);
            aspiranteOpcion.setIdAspirante(aspirante);
            cut.em.persist(aspiranteOpcion);
            cut.em.flush();
            pjaaoPK = new PruebaJornadaAulaAspiranteOpcionPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaao.setPruebaJornadaAulaAspiranteOpcionPK(pjaaoPK);
            cut.em.persist(pjaao);
            cut.em.flush();
            pjaaE.setPruebaJornadaAulaAspiranteOpcion(pjaao);
            pjaaEPK = new PruebaJornadaAulaAspiranteOpcionExamenPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaaE.setPruebaJornadaAulaAspiranteOpcionExamenPK(pjaaEPK);
            cut.em.persist(pjaaE);
            cut.em.flush();
            pjaaE.setObservaciones(expected);
            PruebaJornadaAulaAspiranteOpcionExamen entidad = cut.update(pjaaE);
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
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenDAOImp.testDelete");
        PruebaJornadaAulaAspiranteOpcionExamenDAOImp cut = new PruebaJornadaAulaAspiranteOpcionExamenDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
             tx.begin();
            //persistimos lo necesario para crear todas las relaciones
            cut.em.persist(tipoPrueba);
            cut.em.persist(jornada);
            cut.em.persist(aspirante);
            cut.em.flush();
            cut.em.persist(aspiranteOpcion);
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            jorAulaPK = new JornadaAulaPK(jornada.getIdJornada(), "A3");
            jornadaAula.setJornadaAulaPK(jorAulaPK);
            cut.em.persist(jornadaAula);
            aspiranteOpcion.setIdAspirante(aspirante);
            cut.em.persist(aspiranteOpcion);
            cut.em.flush();
            pjaaoPK = new PruebaJornadaAulaAspiranteOpcionPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaao.setPruebaJornadaAulaAspiranteOpcionPK(pjaaoPK);
            cut.em.persist(pjaao);
            cut.em.flush();
            pjaaE.setPruebaJornadaAulaAspiranteOpcion(pjaao);
            pjaaEPK = new PruebaJornadaAulaAspiranteOpcionExamenPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaaE.setPruebaJornadaAulaAspiranteOpcionExamenPK(pjaaEPK);
            cut.em.persist(pjaaE);
            cut.em.flush();
            PruebaJornadaAulaAspiranteOpcionExamen pruebaAntes = cut.findById(pjaaEPK);
            assertNotNull(pruebaAntes);
            cut.delete(pjaaE);
            cut.em.flush();
            cut.em.clear();
            PruebaJornadaAulaAspiranteOpcionExamen deleted = cut.findById(pjaaEPK);
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
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenDAOImp.testCount");
        PruebaJornadaAulaAspiranteOpcionExamenDAOImp cut = new PruebaJornadaAulaAspiranteOpcionExamenDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
             tx.begin();
            //persistimos lo necesario para crear todas las relaciones
            cut.em.persist(tipoPrueba);
            cut.em.persist(jornada);
            cut.em.persist(aspirante);
            cut.em.flush();
            cut.em.persist(aspiranteOpcion);
            prueba.setIdTipoPrueba(tipoPrueba);
            cut.em.persist(prueba);
            jorAulaPK = new JornadaAulaPK(jornada.getIdJornada(), "A3");
            jornadaAula.setJornadaAulaPK(jorAulaPK);
            cut.em.persist(jornadaAula);
            aspiranteOpcion.setIdAspirante(aspirante);
            cut.em.persist(aspiranteOpcion);
            cut.em.flush();
            pjaaoPK = new PruebaJornadaAulaAspiranteOpcionPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaao.setPruebaJornadaAulaAspiranteOpcionPK(pjaaoPK);
            cut.em.persist(pjaao);
            cut.em.flush();
            pjaaE.setPruebaJornadaAulaAspiranteOpcion(pjaao);
            pjaaEPK = new PruebaJornadaAulaAspiranteOpcionExamenPK(prueba.getIdPrueba(), jornadaAula.getJornadaAulaPK().getIdJornada(),
                    jornadaAula.getJornadaAulaPK().getIdAula(), aspiranteOpcion.getIdAspiranteOpcion());
            pjaaE.setPruebaJornadaAulaAspiranteOpcionExamenPK(pjaaEPK);
            cut.em.persist(pjaaE);
            cut.em.flush();
            cut.em.clear();
            Long cuantos = cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos == 2);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

     
 @Test
    @Order(8)
    void testToDto() {
        System.out.println("PruebaClaveAreaConocimeintoPreguntaDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal=new BigDecimal("31");
        PruebaJornadaAulaAspiranteOpcionExamenDAOImp cut = new PruebaJornadaAulaAspiranteOpcionExamenDAOImp();
        PruebaJornadaAulaAspiranteOpcionExamen pjaaoE = null;
        PruebaJornadaAulaAspiranteOpcionExamenDTO pjaaoEDTO;
        PruebaJornadaAulaAspiranteOpcionExamenDTO pjaaoEDTO1;
        PruebaJornadaAulaAspiranteOpcionExamenPK pk=new PruebaJornadaAulaAspiranteOpcionExamenPK(1l, 1l, "A3", 1l);
        assertThrows(IllegalStateException.class, () -> {
            cut.toDto(pjaaoE);

        });
        pjaaoEDTO = cut.toDto(new PruebaJornadaAulaAspiranteOpcionExamen(pk, decimal, cal.getTime(), "imagen", "ninguna"));
        //pcacpDTO1=cut.toDto(new PruebaClaveAreaConocimientoPregunta(null, cal.getTime(), "nada"));
        
        assertNotNull(pjaaoEDTO);
        assertEquals("A3", pjaaoEDTO.idAula());
        //assertNotNull(pcacpDTO1);
       // assertNotNull(pcacpDTO1.idDistractor());
    }

    @Test
    @Order(9)
    void testToEntity() {
        System.out.println("PruebaJornadaAulaAspiranteOpcionExamenDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        BigDecimal decimal=new BigDecimal("21");
        PruebaJornadaAulaAspiranteOpcionExamenDAOImp cut = new PruebaJornadaAulaAspiranteOpcionExamenDAOImp();
        PruebaJornadaAulaAspiranteOpcionExamenDTO pjaaoEDTO = null;
        PruebaJornadaAulaAspiranteOpcionExamen pjaaoE;
        PruebaJornadaAulaAspiranteOpcionExamenDTO pjaaoE1;

        assertThrows(IllegalStateException.class, () -> {
            cut.toEntity(pjaaoEDTO);

        });
        pjaaoE = cut.toEntity(new PruebaJornadaAulaAspiranteOpcionExamenDTO(1l, 1l, "I300515", 1l, decimal, cal.getTime(), "img", "ninguna"));
        assertNotNull(pjaaoE);
        //pcacpd1 = cut.toEntity(new PruebaClaveAreaConocimientoPreguntaDistractorDTO(0, 0, 0, 0, cal.getTime(), "nada"));
        //assertNotNull(pcacpd1);
        //assertNull(pcacpd1.getDistractor());

    }
}
