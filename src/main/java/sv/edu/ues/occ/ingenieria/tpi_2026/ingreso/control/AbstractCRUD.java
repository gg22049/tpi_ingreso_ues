/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generalizacion de metodos CRUD.
 *
 * @param <T> Parametro generico a suministrar.
 * @author caesar
 */
public abstract class AbstractCRUD<T> implements DefaultDAO<T> {

    private final Class<T> tipoDato;

    /**
     * Metodo para obtener el entity manager para las operaciones basicas.
     *
     * @return Retorna un Entity Manager.
     */
    public abstract EntityManager getEntityManager();

    /**
     * Metodo constructor de la clase abstracta.
     *
     * @param tipoDato Clase de la entidad en la que se opera.
     */
    public AbstractCRUD(Class<T> tipoDato) {
        this.tipoDato = tipoDato;
    }

    /**
     * Metodo para crear nuevos registros en la base de datos.
     *
     * @param entity la entidad que se persistira.
     * @throws IllegalStateException En caso de error en el proceso.
     * @throws IllegalArgumentException si la entidad es invalida.
     */
    @Override
    public void create(final T entity) throws IllegalStateException, IllegalArgumentException {
        EntityManager em = null;
        if (entity == null) {
            throw new IllegalArgumentException("Entidad invalido");
        }
        try {
            em = getEntityManager();
            if (em == null) {
                throw new IllegalStateException("Error accediendo al repositorio");
            }
            em.persist(entity);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException("Error persistiendo la entidad", e);
        }
    }

    /**
     * Metodo para buscar todos los registros de una tabla en la base de datos.
     *
     * @return Retorna los registros de la tabla o una lista vacia en su lugar.
     * @throws IllegalStateException En caso de error en el proceso.
     */
    @Override
    public List<T> findAll() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            if (em == null) {
                throw new IllegalStateException("Error accediendo al repositorio");
            }
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(tipoDato);
            Root<T> raiz = cq.from(tipoDato);
            cq.select(raiz);
            TypedQuery<T> q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException("Error al obtener todos los registros");
        }
    }

    /**
     * Metodo para buscar registros id.
     *
     * @param id Llave para realizar la busqueda.
     * @return Retorna que busques i use to la entidad buscada si se encuentra,
     * sino retorna nulo.
     * @throws IllegalArgumentException En caso de id invalido.
     * @throws IllegalStateException En caso de error en proceso de busqueda por
     * id.
     */
    @Override
    public T findById(final Object id) throws IllegalArgumentException, IllegalStateException {
        EntityManager em = null;
        if (id == null) {
            throw new IllegalArgumentException("Id invalido");
        }
        try {
            em = getEntityManager();
            if (em == null) {
                throw new IllegalStateException("Error accediendo al repositorio");
            }
            return em.find(tipoDato, id);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException("Error durante la busqueda por id", e);
        }
    }

    /**
     * Metodo para obtener registros por rango.
     *
     * @param offset Comienzo de la lista.
     * @param limit Fin de la lista.
     * @return Retorna una lista en el rango estipulado o lista vacia en su
     * lugar.
     * @throws IllegalArgumentException Rango brindado invalido.
     * @throws IllegalStateException Error en el proceso de busqueda por rango.
     */
    @Override
    public List<T> findByRange(int offset, int limit) throws IllegalArgumentException, IllegalStateException {
        EntityManager em = null;
        if (offset < 0 || limit <= 0) {
            throw new IllegalArgumentException("Rango invalido");
        }
        try {
            em = getEntityManager();
            if (em == null) {
                throw new IllegalStateException("Error accediendo al repositorio");
            }
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(tipoDato);
            Root<T> raiz = cq.from(tipoDato);
            cq.select(raiz);
            TypedQuery<T> q = em.createQuery(cq);
            q.setFirstResult(offset);
            q.setMaxResults(limit);
            return q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException("Error al obtener el rango de registros", e);
        }
    }

    /**
     * Metodo para acualizar un registro.
     *
     * @param entity La entidad para actualizar.
     * @return Regresa la entidad actualizada.
     * @throws IllegalArgumentException La entidad a actualizar es invalida.
     * @throws IllegalStateException Error en proceso de actualizacion
     */
    @Override
    public T update(final T entity) throws IllegalArgumentException, IllegalStateException {
        EntityManager em = null;
        if (entity == null) {
            throw new IllegalArgumentException("Entidad invalida");
        }
        try {
            em = getEntityManager();
            if (em == null) {
                throw new IllegalStateException("Error accediendo al repositorio");
            }
            return em.merge(entity);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException("Error actualizando el registro", e);
        }
    }

    /**
     * Metodo para eliminar registros de la base de datos.
     *
     * @param entity La entidad o registro a eliminar.
     * @throws IllegalArgumentException La entidad a eliminar es invalida.
     * @throws IllegalStateException Error eliminando la entidad.
     */
    @Override
    public void delete(T entity) throws IllegalArgumentException, IllegalStateException {
        EntityManager em = null;
        if (entity == null) {
            throw new IllegalArgumentException("Entidad invalida");
        }
        try {
            em = getEntityManager();
            if (em == null) {
                throw new IllegalStateException("Error accediendo al repositorio");
            }
            if (!em.contains(entity)) {
                entity = em.merge(entity);
            }
            em.remove(entity);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException("Error eliminando el registro", e);
        }
    }

    /**
     * Metodo para obtener cantidad de registros en una tabla.
     *
     * @return int Retorna un valor entero.
     * @throws IllegalStateException Error contando los registro.
     */
    @Override
    public int count() throws IllegalStateException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            if (em == null) {
                throw new IllegalStateException("Error accediendo al repositorio");
            }
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<T> raiz = cq.from(tipoDato);
            cq.select(cb.count(raiz));
            TypedQuery<Long> q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new IllegalStateException("Error contando los registros de la tabla", e);
        }
    }

}
