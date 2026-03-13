/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author caesar
 */
@Embeddable
public class PruebaJornadaAulaAspiranteOpcionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_prueba")
    private long idPrueba;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_jornada")
    private long idJornada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 124)
    @Column(name = "id_aula")
    private String idAula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_aspirante_opcion")
    private long idAspiranteOpcion;

    public PruebaJornadaAulaAspiranteOpcionPK() {
    }

    public PruebaJornadaAulaAspiranteOpcionPK(long idPrueba, long idJornada, String idAula, long idAspiranteOpcion) {
        this.idPrueba = idPrueba;
        this.idJornada = idJornada;
        this.idAula = idAula;
        this.idAspiranteOpcion = idAspiranteOpcion;
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

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    public long getIdAspiranteOpcion() {
        return idAspiranteOpcion;
    }

    public void setIdAspiranteOpcion(long idAspiranteOpcion) {
        this.idAspiranteOpcion = idAspiranteOpcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPrueba;
        hash += (int) idJornada;
        hash += (idAula != null ? idAula.hashCode() : 0);
        hash += (int) idAspiranteOpcion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaJornadaAulaAspiranteOpcionPK)) {
            return false;
        }
        PruebaJornadaAulaAspiranteOpcionPK other = (PruebaJornadaAulaAspiranteOpcionPK) object;
        if (this.idPrueba != other.idPrueba) {
            return false;
        }
        if (this.idJornada != other.idJornada) {
            return false;
        }
        if ((this.idAula == null && other.idAula != null) || (this.idAula != null && !this.idAula.equals(other.idAula))) {
            return false;
        }
        if (this.idAspiranteOpcion != other.idAspiranteOpcion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PruebaJornadaAulaAspiranteOpcionPK[ idPrueba=" + idPrueba + ", idJornada=" + idJornada + ", idAula=" + idAula + ", idAspiranteOpcion=" + idAspiranteOpcion + " ]";
    }

}
