/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.control;

import java.util.List;

/**
 *
 * @author caesar
 */
public interface DefaultDAO<T> {

    /**
     * Permite crea una nueva entidad.
     * <p>
     * Precondiciones:
     * <ul>
     * <li>{@code entity} no debe ser {@code null}.</li>
     * <li>La entidad no debe existir previamente, no debe tener
     * identificador.</li>
     * </ul>
     * </p>
     * <p>
     * Postcondiciones:
     * <ul>
     * <li>La entidad queda almacenada de forma persistente.</li>
     * <li>El identificador es generado al momento de persistirse</li>
     * </ul>
     * </p>
     *
     * @param entity Entidad a persistir; No nula.
     * @throws IllegalArgumentException En caso de entidad nula.
     * @throws IllegalStateException En caso falle la persistencia.
     */
    public void create(T entity) throws RuntimeException;

    /**
     * Permite recuperar una lista de registros de entidad.
     * <p>
     * Precondiciones:
     * <ul>
     * <li>Deben existir pocos registros de la entidad.</li>
     * </ul>
     * </p>
     * <p>
     * Postcondiciones:
     * <ul>
     * <li>Devuelve los registros disponibles en ese momento.</li>
     * </ul>
     * </p>
     *
     * @throws IllegalStateException En caso falle la recuperacion de
     * informacion.
     * @return Retorna una lista los elementos encontrados; lista vacia en su
     * defecto.
     */
    public List<T> findAll() throws RuntimeException;

    /**
     * Permite buscar un registro por id.
     * <p>
     * Precondiciones:
     * <ul>
     * <li>{@code id} no debe ser {@code null}.</li>
     * <li>La entidad debe existir previamente, debe tener identificador.</li>
     * </ul>
     * </p>
     * <p>
     * Postcondiciones:
     * <ul>
     * <li>La entidad esta sujeta a cambios posteriores a su consulta.</li>
     * </ul>
     * </p>
     *
     * @param id Objeto que sirve de identificador; No nulo.
     * @throws IllegalArgumentException En caso de identificador nulo o
     * invalido.
     * @throws IllegalStateException En caso falle la consulta.
     * @return Retorna el registro encontrado; {@code null} en su defecto.
     */
    public T findById(Object id) throws RuntimeException;

    /**
     * Permite buscar una lista de registros por rango.
     * <p>
     * Precondiciones:
     * <ul>
     * <li>{@code offset} no debe ser {@code null}.</li>
     * <li>{@code limit} no debe ser {@code null}.</li>
     * <li>{@code offset} debe ser menor {@code limit}.</li>
     * </ul>
     * </p>
     * <p>
     * Postcondiciones:
     * <ul>
     * <li>Devuelve los registros disponibles en ese momento.</li>
     * </ul>
     * </p>
     *
     * @param {@code offset} Desplazamiento desde el que se inicia la busqueda;
     * No nulo.
     * @param {@code limit} Limite hasta el que se realiza la busqueda; No nulo.
     * @throws IllegalArgumentException En caso de identificadores nulos o
     * invalidos.
     * @throws IllegalStateException En caso falle la consulta.
     * @return Retorna una lista los elementos encontrados; lista vacia en su
     * defecto.
     */
    public List<T> findByRange(int offset, int limit) throws RuntimeException;

    /**
     * Permite actualizar una entidad.
     * <p>
     * Precondiciones:
     * <ul>
     * <li>{@code entity} debe existir previamente.</li>
     * <li>{@code entity} no debe ser {@code null}.</li>
     * </ul>
     * </p>
     * <p>
     * Postcondiciones:
     * <ul>
     * <li>Los datos actulizados quedan persistidos permanentemente.</li>
     * </ul>
     * </p>
     *
     * @param {@code entity} Entidad con datos actualizados; No nula.
     * @throws IllegalArgumentException En caso de {@code entity} {@code null}.
     * @throws IllegalStateException En caso de que falle la actualizacion.
     * @return Retorna la entidad actualizada.
     */
    public T update(T entity) throws RuntimeException;

    /**
     * Permite eliminar una entidad.
     * <p>
     * Precondiciones:
     * <ul>
     * <li>{@code entity} debe existir previamente.</li>
     * <li>{@code entity} no debe ser {@code null}.</li>
     * </ul>
     * </p>
     * <p>
     * Postcondiciones:
     * <ul>
     * <li>Los datos quedan irreversible y permanentemente eliminados.</li>
     * </ul>
     * </p>
     *
     * @param {@code entity} Entidad a eliminar; No nula.
     * @throws IllegalArgumentException En caso de {@code entity} {@code null}.
     * @throws IllegalStateException En caso de que falle la eliminacion.
     */
    public void delete(T entity) throws RuntimeException;

    /**
     * Permite los registros de una tabla.
     * <p>
     * Precondiciones:
     * <ul>
     * <li>La tabla debe poseer registros.</li>
     * </ul>
     * </p>
     * <p>
     * Postcondiciones:
     * <ul>
     * <li>Se recupera la cantidad en un momento dado por lo que esta sujeto a
     * cambios.</li>
     * </ul>
     * </p>
     *
     * @throws IllegalStateException En caso de que falle el proceso de contar.
     */
    public int count() throws RuntimeException;

}
