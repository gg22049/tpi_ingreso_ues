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
public class PruebaClaveAreaConocimientoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_prueba_clave")
    private long idPruebaClave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_area_conocimiento")
    private int idAreaConocimiento;

    public PruebaClaveAreaConocimientoPK() {
    }

    public PruebaClaveAreaConocimientoPK(long idPruebaClave, int idAreaConocimiento) {
        this.idPruebaClave = idPruebaClave;
        this.idAreaConocimiento = idAreaConocimiento;
    }

    public long getIdPruebaClave() {
        return idPruebaClave;
    }

    public void setIdPruebaClave(long idPruebaClave) {
        this.idPruebaClave = idPruebaClave;
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
        hash += (int) idPruebaClave;
        hash += (int) idAreaConocimiento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaClaveAreaConocimientoPK)) {
            return false;
        }
        PruebaClaveAreaConocimientoPK other = (PruebaClaveAreaConocimientoPK) object;
        if (this.idPruebaClave != other.idPruebaClave) {
            return false;
        }
        if (this.idAreaConocimiento != other.idAreaConocimiento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaClaveAreaConocimientoPK[ idPruebaClave=" + idPruebaClave + ", idAreaConocimiento=" + idAreaConocimiento + " ]";
    }

}
