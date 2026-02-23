/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "examen_jornada")
@NamedQueries({
    @NamedQuery(name = "ExamenJornada.findAll", query = "SELECT e FROM ExamenJornada e"),
    @NamedQuery(name = "ExamenJornada.findByIdExamenJornada", query = "SELECT e FROM ExamenJornada e WHERE e.idExamenJornada = :idExamenJornada"),
    @NamedQuery(name = "ExamenJornada.findByInicio", query = "SELECT e FROM ExamenJornada e WHERE e.inicio = :inicio"),
    @NamedQuery(name = "ExamenJornada.findByFin", query = "SELECT e FROM ExamenJornada e WHERE e.fin = :fin"),
    @NamedQuery(name = "ExamenJornada.findByObservaciones", query = "SELECT e FROM ExamenJornada e WHERE e.observaciones = :observaciones")})
public class ExamenJornada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_examen_jornada")
    private Integer idExamenJornada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fin;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_examen", referencedColumnName = "id_examen")
    @ManyToOne(optional = false)
    private Examen idExamen;
    @JoinColumn(name = "id_jornada", referencedColumnName = "id_jornada")
    @ManyToOne(optional = false)
    private Jornada idJornada;

    public ExamenJornada() {
    }

    public ExamenJornada(Integer idExamenJornada) {
        this.idExamenJornada = idExamenJornada;
    }

    public ExamenJornada(Integer idExamenJornada, Date inicio, Date fin) {
        this.idExamenJornada = idExamenJornada;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Integer getIdExamenJornada() {
        return idExamenJornada;
    }

    public void setIdExamenJornada(Integer idExamenJornada) {
        this.idExamenJornada = idExamenJornada;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Examen getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Examen idExamen) {
        this.idExamen = idExamen;
    }

    public Jornada getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Jornada idJornada) {
        this.idJornada = idJornada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamenJornada != null ? idExamenJornada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenJornada)) {
            return false;
        }
        ExamenJornada other = (ExamenJornada) object;
        if ((this.idExamenJornada == null && other.idExamenJornada != null) || (this.idExamenJornada != null && !this.idExamenJornada.equals(other.idExamenJornada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.ExamenJornada[ idExamenJornada=" + idExamenJornada + " ]";
    }

}
