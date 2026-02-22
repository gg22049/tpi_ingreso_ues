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
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "examen_aspirante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamenAspirante.findAll", query = "SELECT e FROM ExamenAspirante e"),
    @NamedQuery(name = "ExamenAspirante.findByIdExamenAspirante", query = "SELECT e FROM ExamenAspirante e WHERE e.idExamenAspirante = :idExamenAspirante"),
    @NamedQuery(name = "ExamenAspirante.findByRuta", query = "SELECT e FROM ExamenAspirante e WHERE e.ruta = :ruta"),
    @NamedQuery(name = "ExamenAspirante.findByCalificacion", query = "SELECT e FROM ExamenAspirante e WHERE e.calificacion = :calificacion"),
    @NamedQuery(name = "ExamenAspirante.findByObservaciones", query = "SELECT e FROM ExamenAspirante e WHERE e.observaciones = :observaciones")})
public class ExamenAspirante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_examen_aspirante")
    private Long idExamenAspirante;
    @Size(max = 256)
    @Column(name = "ruta")
    private String ruta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "calificacion")
    private Double calificacion;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_aspirante", referencedColumnName = "id_aspirante")
    @ManyToOne(optional = false)
    private Aspirante idAspirante;
    @JoinColumn(name = "id_examen", referencedColumnName = "id_examen")
    @ManyToOne(optional = false)
    private Examen idExamen;

    public ExamenAspirante() {
    }

    public ExamenAspirante(Long idExamenAspirante) {
        this.idExamenAspirante = idExamenAspirante;
    }

    public Long getIdExamenAspirante() {
        return idExamenAspirante;
    }

    public void setIdExamenAspirante(Long idExamenAspirante) {
        this.idExamenAspirante = idExamenAspirante;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Aspirante getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Aspirante idAspirante) {
        this.idAspirante = idAspirante;
    }

    public Examen getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Examen idExamen) {
        this.idExamen = idExamen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamenAspirante != null ? idExamenAspirante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenAspirante)) {
            return false;
        }
        ExamenAspirante other = (ExamenAspirante) object;
        if ((this.idExamenAspirante == null && other.idExamenAspirante != null) || (this.idExamenAspirante != null && !this.idExamenAspirante.equals(other.idExamenAspirante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.ExamenAspirante[ idExamenAspirante=" + idExamenAspirante + " ]";
    }

}
