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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "examen_area_conocimiento_pregunta")
@NamedQueries({
    @NamedQuery(name = "ExamenAreaConocimientoPregunta.findAll", query = "SELECT e FROM ExamenAreaConocimientoPregunta e"),
    @NamedQuery(name = "ExamenAreaConocimientoPregunta.findByIdExamenAreaConocimientoPregunta", query = "SELECT e FROM ExamenAreaConocimientoPregunta e WHERE e.idExamenAreaConocimientoPregunta = :idExamenAreaConocimientoPregunta"),
    @NamedQuery(name = "ExamenAreaConocimientoPregunta.findByPonderacion", query = "SELECT e FROM ExamenAreaConocimientoPregunta e WHERE e.ponderacion = :ponderacion"),
    @NamedQuery(name = "ExamenAreaConocimientoPregunta.findByObservaciones", query = "SELECT e FROM ExamenAreaConocimientoPregunta e WHERE e.observaciones = :observaciones")})
public class ExamenAreaConocimientoPregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_examen_area_conocimiento_pregunta")
    private Long idExamenAreaConocimientoPregunta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ponderacion")
    private double ponderacion;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_examen_area_conocimiento", referencedColumnName = "id_examen_area_conocimiento")
    @ManyToOne(optional = false)
    private ExamenAreaConocimiento idExamenAreaConocimiento;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne(optional = false)
    private Pregunta idPregunta;

    public ExamenAreaConocimientoPregunta() {
    }

    public ExamenAreaConocimientoPregunta(Long idExamenAreaConocimientoPregunta) {
        this.idExamenAreaConocimientoPregunta = idExamenAreaConocimientoPregunta;
    }

    public ExamenAreaConocimientoPregunta(Long idExamenAreaConocimientoPregunta, double ponderacion) {
        this.idExamenAreaConocimientoPregunta = idExamenAreaConocimientoPregunta;
        this.ponderacion = ponderacion;
    }

    public Long getIdExamenAreaConocimientoPregunta() {
        return idExamenAreaConocimientoPregunta;
    }

    public void setIdExamenAreaConocimientoPregunta(Long idExamenAreaConocimientoPregunta) {
        this.idExamenAreaConocimientoPregunta = idExamenAreaConocimientoPregunta;
    }

    public double getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(double ponderacion) {
        this.ponderacion = ponderacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ExamenAreaConocimiento getIdExamenAreaConocimiento() {
        return idExamenAreaConocimiento;
    }

    public void setIdExamenAreaConocimiento(ExamenAreaConocimiento idExamenAreaConocimiento) {
        this.idExamenAreaConocimiento = idExamenAreaConocimiento;
    }

    public Pregunta getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Pregunta idPregunta) {
        this.idPregunta = idPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamenAreaConocimientoPregunta != null ? idExamenAreaConocimientoPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenAreaConocimientoPregunta)) {
            return false;
        }
        ExamenAreaConocimientoPregunta other = (ExamenAreaConocimientoPregunta) object;
        if ((this.idExamenAreaConocimientoPregunta == null && other.idExamenAreaConocimientoPregunta != null) || (this.idExamenAreaConocimientoPregunta != null && !this.idExamenAreaConocimientoPregunta.equals(other.idExamenAreaConocimientoPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.ExamenAreaConocimientoPregunta[ idExamenAreaConocimientoPregunta=" + idExamenAreaConocimientoPregunta + " ]";
    }

}
