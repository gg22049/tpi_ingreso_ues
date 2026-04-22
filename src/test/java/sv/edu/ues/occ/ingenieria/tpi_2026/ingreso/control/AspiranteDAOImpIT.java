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
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto.AspiranteDTO;
import sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante;

/**
 *
 * @author usermein
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AspiranteDAOImpIT extends ITAbstract {

    Aspirante newAspirante;

    @BeforeAll
    void init() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);

        newAspirante = new Aspirante(-1L, "TEST", "INTEGRACION", cal.getTime(), "hola@test");

    }

    @Test
    @Order(1)
    void testCreate() {
        System.out.println("Aspirante.testCreate");
        AspiranteDAOImp cut = new AspiranteDAOImp();
        assertThrows(IllegalArgumentException.class, () -> {
            cut.create(null);
        });
        assertThrows(IllegalStateException.class, () -> {
            cut.create(newAspirante);
        });
        cut.em = emf.createEntityManager();
        EntityTransaction tx = cut.em.getTransaction();

        try {
            tx.begin();
            cut.create(newAspirante);
            cut.em.flush();
            cut.em.clear();
            Long id = newAspirante.getIdAspirante();
            assertNotNull(id);
            Aspirante creado = cut.em.find(Aspirante.class, id);
            assertNotNull(creado);
            assertEquals(id, creado.getIdAspirante());
        } finally {
            tx.rollback();
            cut.em.close();
        }

    }

    @Test
    @Order(2)
    void testFindById() {
        System.out.println("Aspirante.testFindById");
        AspiranteDAOImp cut = new AspiranteDAOImp();
        assertThrows(IllegalArgumentException.class, () -> {
            cut.create(null);
        });
        assertThrows(IllegalStateException.class, () -> {
            cut.create(newAspirante);
        });
        
        cut.em=emf.createEntityManager();
        EntityTransaction tx=cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newAspirante);
            cut.em.flush();
            cut.em.clear();
            System.out.println(newAspirante.getIdAspirante());
            Aspirante found=cut.findById(newAspirante.getIdAspirante());
            assertNotNull(found);
            assertEquals(found.getIdAspirante(), newAspirante.getIdAspirante());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }
    
    @Test
    @Order(3)
    void testFindAll() {
        System.out.println("AspiranteDAOImp.testFindAll");
        AspiranteDAOImp cut = new AspiranteDAOImp();
        assertThrows(IllegalStateException.class, () -> {
            cut.findAll();
        });
        cut.em=emf.createEntityManager();
        EntityTransaction tx=cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newAspirante);
            cut.em.flush();
            cut.em.clear();
            List<Aspirante> resultList=cut.findAll();
            assertNotNull(resultList);
            assertTrue(resultList.size()>0);
        } finally {
            tx.rollback();
            cut.em.clear();
        }
       
    }

    @Test
    @Order(4)
    void testFindRange() {
        System.out.println("AspiranteDAOImp.testFindRange");
        AspiranteDAOImp cut = new AspiranteDAOImp();
        int offset = 0;
        int limit = 10;
        assertThrows(IllegalArgumentException.class, ()->{
        cut.findByRange(-limit, limit);
        });
        assertThrows(IllegalArgumentException.class, ()->{
        cut.findByRange(limit, offset);
        });
        assertThrows(IllegalStateException.class, ()->{
        cut.findByRange(offset, limit);
        });
       cut.em=emf.createEntityManager();
       EntityTransaction tx=cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newAspirante);
            cut.em.flush();;
            cut.em.clear();
            List<Aspirante> lista=cut.findByRange(offset, limit);
            assertNotNull(lista);
            assertTrue(lista.size()>0); 
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(5)
    void testUpdate() {
        System.out.println("AspiranteDAOImp.testUpdate");
        String expected = "integracion";
        AspiranteDAOImp cut = new AspiranteDAOImp();
        assertThrows(IllegalArgumentException.class, ()->{
        cut.update(null);
        });
        assertThrows(IllegalStateException.class, ()->{
        cut.update(newAspirante);
        });
        cut.em=emf.createEntityManager();
        EntityTransaction tx=cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newAspirante);
            cut.em.flush();
            cut.em.clear();
            newAspirante.setApellidos(expected);
            Aspirante aspiranteUpdate=cut.update(newAspirante);
            assertEquals(aspiranteUpdate.getApellidos(), expected);
        } finally {
        tx.rollback();
        cut.em.close();
        }
    }

    @Test
    @Order(6)
    void testDelete() {
        System.out.println("AspiranteDAOImp.testDelete");
        AspiranteDAOImp cut = new AspiranteDAOImp();
        assertThrows(IllegalArgumentException.class, () -> {
            cut.delete(null);
        });
        assertThrows(IllegalStateException.class, () -> {
           cut.delete(newAspirante);
        });
        cut.em=emf.createEntityManager();
        EntityTransaction tx=cut.em.getTransaction();
        try {
           tx.begin();
           cut.em.persist(newAspirante);
           cut.em.flush();
           cut.em.clear();
           cut.delete(newAspirante);
           cut.em.flush();
           cut.em.clear();
           Aspirante aspiranteDelete=cut.em.find(Aspirante.class, newAspirante.getIdAspirante());
           assertNull(aspiranteDelete);        
        } finally {
        tx.rollback();
        cut.em.close();
        }
    }

    @Test
    @Order(7)
    void testCount() {
        System.out.println("AspiranteDAOImp.testCount");
        AspiranteDAOImp cut = new AspiranteDAOImp();
        assertThrows(IllegalStateException.class,()->
                {
                cut.count();
                });
        cut.em=emf.createEntityManager();
        EntityTransaction tx=cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newAspirante);
            cut.em.flush();
            cut.em.clear();
            Long cuantos=cut.count();
            assertNotNull(cuantos);
            assertTrue(cuantos>0);
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }

    @Test
    @Order(8)
    void testFindByEmail(){
        System.out.println("AspiranteDAOImp.testFindByEmail");
        AspiranteDAOImp cut = new AspiranteDAOImp();
        
        assertThrows(IllegalArgumentException.class, () -> {
            cut.findByEmail(null);
        });
      
         assertThrows(IllegalArgumentException.class, () -> {
            cut.findByEmail(" ");
        });
        assertThrows(Exception.class, () -> {
            cut.findByEmail("correo@noexiste");
        });
        
        cut.em=emf.createEntityManager();
        EntityTransaction tx=cut.em.getTransaction();
        try {
            tx.begin();
            cut.em.persist(newAspirante);
            cut.em.flush();
            cut.em.clear();
             
            Aspirante correoNoEncontrado =cut.findByEmail("busquemos@gmail");
            assertNull(correoNoEncontrado);
            Aspirante found= cut.findByEmail(newAspirante.getCorreo());
            assertNotNull(found);
            assertEquals(found.getCorreo(), newAspirante.getCorreo());
        } finally {
            tx.rollback();
            cut.em.close();
        }
    }
    
    
    
     @Test
    @Order(8)
    void testToDto(){
        System.out.println("AspiranteDAOImp.testToDto");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        AspiranteDAOImp aspiranteDI=new AspiranteDAOImp();
        Aspirante aspirante1= new Aspirante(12L, "Juan", "chepe", cal.getTime() , "juan@hotmail",cal.getTime(), "No hay");
        AspiranteDTO aspiranteDTO;
        Aspirante aspirante2=null;
        assertThrows(IllegalStateException.class, () -> {
          aspiranteDI.toDto(aspirante2);
           
        });
        
         aspiranteDTO=aspiranteDI.toDto(aspirante1);
         assertNotNull(aspiranteDTO);
    }
    
      @Test
    @Order(9)
    void testToEntity(){
        System.out.println("AspiranteDAOImp.estToEntity");
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 15);
        AspiranteDAOImp aspiranteDI=new AspiranteDAOImp();
        AspiranteDTO aspiranteDTO1= new AspiranteDTO(1L, "Fernando", "Torrento", cal.getTime(), "hola@ues.edu.sv", null, "");
        Aspirante aspirante;
        AspiranteDTO aspirante2=null;
        assertThrows(IllegalStateException.class, () -> {
          aspiranteDI.toEntity(aspirante2);
           
        });
        
         aspirante=aspiranteDI.toEntity(aspiranteDTO1);
         assertNotNull(aspirante);
    }
}
