/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception;

/**
 * Conflict for existent entity.
 *
 * @author caesar
 */
public class ExistentEntityException extends RuntimeException {

    private final Object id;

    public ExistentEntityException(Object id) {
        super("Entity already exist: " + id);
        this.id = id;
    }

}
