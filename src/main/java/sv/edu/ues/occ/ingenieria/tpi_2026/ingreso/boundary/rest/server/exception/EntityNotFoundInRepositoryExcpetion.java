/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.boundary.rest.server.exception;

/**
 * No entity found for the given id.
 *
 * @author caesar
 */
public class EntityNotFoundInRepositoryExcpetion extends RuntimeException {

    private final Object id;

    public EntityNotFoundInRepositoryExcpetion(Object id) {
        super("No entity with id: " + id);
        this.id = id;
    }

}
