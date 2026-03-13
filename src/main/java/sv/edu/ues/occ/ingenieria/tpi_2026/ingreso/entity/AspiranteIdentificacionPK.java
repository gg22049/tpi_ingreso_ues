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
public class AspiranteIdentificacionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_aspirante")
    private int idAspirante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_identificacion")
    private int idTipoIdentificacion;

    public AspiranteIdentificacionPK() {
    }

    public AspiranteIdentificacionPK(int idAspirante, int idTipoIdentificacion) {
        this.idAspirante = idAspirante;
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public int getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(int idAspirante) {
        this.idAspirante = idAspirante;
    }

    public int getIdTipoIdentificacion() {
        return idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(int idTipoIdentificacion) {
        this.idTipoIdentificacion = idTipoIdentificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAspirante;
        hash += (int) idTipoIdentificacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AspiranteIdentificacionPK)) {
            return false;
        }
        AspiranteIdentificacionPK other = (AspiranteIdentificacionPK) object;
        if (this.idAspirante != other.idAspirante) {
            return false;
        }
        if (this.idTipoIdentificacion != other.idTipoIdentificacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AspiranteIdentificacionPK[ idAspirante=" + idAspirante + ", idTipoIdentificacion=" + idTipoIdentificacion + " ]";
    }

}
