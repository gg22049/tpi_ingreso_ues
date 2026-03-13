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
public class DistractorAreaConocimientoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_distractor")
    private long idDistractor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_area_conocimiento")
    private int idAreaConocimiento;

    public DistractorAreaConocimientoPK() {
    }

    public DistractorAreaConocimientoPK(long idDistractor, int idAreaConocimiento) {
        this.idDistractor = idDistractor;
        this.idAreaConocimiento = idAreaConocimiento;
    }

    public long getIdDistractor() {
        return idDistractor;
    }

    public void setIdDistractor(long idDistractor) {
        this.idDistractor = idDistractor;
    }

    public int getIdAreaConocimiento() {
        return idAreaConocimiento;
    }

    public void setIdAreaConocimiento(int idAreaConocimiento) {
        this.idAreaConocimiento = idAreaConocimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDistractor;
        hash += (int) idAreaConocimiento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DistractorAreaConocimientoPK)) {
            return false;
        }
        DistractorAreaConocimientoPK other = (DistractorAreaConocimientoPK) object;
        if (this.idDistractor != other.idDistractor) {
            return false;
        }
        if (this.idAreaConocimiento != other.idAreaConocimiento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimientoPK[ idDistractor=" + idDistractor + ", idAreaConocimiento=" + idAreaConocimiento + " ]";
    }

}
