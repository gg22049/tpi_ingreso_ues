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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.DistractorDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DistractorDAOImpIT extends ITAbstract {

    Distractor newDistractor;

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);

        //parentAspirante = new Aspirante(null, "TEST", "RELACION", cal.getTime(), "relacion@test.com");
        //aspIdenPK = new AspiranteIdentificacionPK();
        newDistractor = new Distractor(null, "Vas a pasar la materia?", true, "IMAGEN");
        //newAspiranteIdentificacion.setAspiranteIdentificacionPK(aspIdenPK);

    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("DistractorDAOImp.testCreate");
        DistractorDAOImp cut = new DistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {
            tx.begin();

            cut.em.persist(newDistractor);

          

            cut.create(newDistractor);
  cut.em.flush();
            cut.em.clear();
            Distractor creado = cut.em.find(Distractor.class, newDistractor.getIdDistractor());
            assertNotNull(creado);
            assertEquals("Vas a pasar la materia?", creado.getValor());
            assertEquals(newDistractor, creado);
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }
   
    @Test
    @Order(2)
    void testFindById() {
        System.out.println("DistractorDAOImp.testFindById");
        DistractorDAOImp cut = new DistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();

           
            cut.em.persist(newDistractor);
            cut.em.flush();
            cut.em.clear();

            Distractor found = cut.em.find(Distractor.class, newDistractor.getIdDistractor());

            
            assertNotNull(found);
            assertEquals(newDistractor.getIdDistractor(), found.getIdDistractor());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }
 
    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("DistractorImp.testFindAll");
        DistractorDAOImp cut = new DistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
        
            cut.em.persist(newDistractor);
            cut.em.flush();
            cut.em.clear();
            List<Distractor> resultList = cut.findAll();
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
        System.out.println("DistractorDAOImp.testFindRange");
        DistractorDAOImp cut = new DistractorDAOImp();
        int offset = 0;
        int limit = 10;
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newDistractor);
            cut.em.flush();
            cut.em.clear();
            List<Distractor> resultList = cut.findByRange(offset, limit);
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
        System.out.println("DistractorDAOImp.testUpdate");
        String expected = "IMAGENNN";
        DistractorDAOImp cut = new DistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newDistractor);
            cut.em.flush();

            newDistractor.setImagenUrl(expected);
            newDistractor = cut.update(newDistractor);
            assertNotNull(newDistractor);
            assertEquals(expected, newDistractor.getImagenUrl());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }
 
    @Test
    @Order(6)
    void testDelete() {
        System.out.println("DistractorDAOImp.testDelete");
        DistractorDAOImp cut = new DistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
        try {
            tx.begin();

            cut.em.persist(newDistractor);
            cut.em.flush();
            cut.em.clear();
            cut.delete(newDistractor);
         
            Distractor deleted=cut.findById(newDistractor.getIdDistractor());
            assertNull(deleted);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }
 
    @Test
    @Order(7)
    void testCount() {
        System.out.println("DistractorDAOImp.testCount");
        DistractorDAOImp cut = new DistractorDAOImp();
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();
  try {
            tx.begin();
            cut.em.persist(newDistractor);
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
        System.out.println("DistractorDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        DistractorDAOImp cut = new DistractorDAOImp();
        Distractor distractor1= new Distractor(1l, "100", true, "imagen");
        DistractorDTO distractorDTO;
        Distractor distractor2=null;
        assertThrows(IllegalStateException.class, () -> {
          cut.toDto(distractor2);
           
        });
        
         distractorDTO=cut.toDto(distractor1);
         assertNotNull(distractorDTO);
    }
    
      @Test
    @Order(9)
    void testToEntity(){
        System.out.println("DistractorDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
       DistractorDAOImp cut = new DistractorDAOImp();
        DistractorDTO distractorDTO1= new DistractorDTO(1l, "100kM", true, "imagen");
        Distractor distractor;
        DistractorDTO distractor2=null;
        assertThrows(IllegalStateException.class, () -> {
          cut.toEntity(distractor2);
           
        });
        
         distractor=cut.toEntity(distractorDTO1);
         assertNotNull(distractor);
    }
}
