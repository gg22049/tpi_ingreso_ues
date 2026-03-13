/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author caesar
 */
@Embeddable
public class PruebaJornadaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_prueba")
    private long idPrueba;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_jornada")
    private long idJornada;

    public PruebaJornadaPK() {
    }

    public PruebaJornadaPK(long idPrueba, long idJornada) {
        this.idPrueba = idPrueba;
        this.idJornada = idJornada;
    }

    public long getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(long idPrueba) {
        this.idPrueba = idPrueba;
    }

    public long getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(long idJornada) {
        this.idJornada = idJornada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPrueba;
        hash += (int) idJornada;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaJornadaPK)) {
            return false;
        }
        PruebaJornadaPK other = (PruebaJornadaPK) object;
        if (this.idPrueba != other.idPrueba) {
            return false;
        }
        if (this.idJornada != other.idJornada) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaPK[ idPrueba=" + idPrueba + ", idJornada=" + idJornada + " ]";
    }

}
