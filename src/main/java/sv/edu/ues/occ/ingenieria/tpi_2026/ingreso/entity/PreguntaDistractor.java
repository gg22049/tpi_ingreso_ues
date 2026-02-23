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
import java.io.Serializable;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "pregunta_distractor")
@NamedQueries({
    @NamedQuery(name = "PreguntaDistractor.findAll", query = "SELECT p FROM PreguntaDistractor p"),
    @NamedQuery(name = "PreguntaDistractor.findByIdPreguntaDistractor", query = "SELECT p FROM PreguntaDistractor p WHERE p.idPreguntaDistractor = :idPreguntaDistractor"),
    @NamedQuery(name = "PreguntaDistractor.findByObservaciones", query = "SELECT p FROM PreguntaDistractor p WHERE p.observaciones = :observaciones")})
public class PreguntaDistractor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pregunta_distractor")
    private Long idPreguntaDistractor;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_distractor", referencedColumnName = "id_distractor")
    @ManyToOne(optional = false)
    private Distractor idDistractor;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne(optional = false)
    private Pregunta idPregunta;

    public PreguntaDistractor() {
    }

    public PreguntaDistractor(Long idPreguntaDistractor) {
        this.idPreguntaDistractor = idPreguntaDistractor;
    }

    public Long getIdPreguntaDistractor() {
        return idPreguntaDistractor;
    }

    public void setIdPreguntaDistractor(Long idPreguntaDistractor) {
        this.idPreguntaDistractor = idPreguntaDistractor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Distractor getIdDistractor() {
        return idDistractor;
    }

    public void setIdDistractor(Distractor idDistractor) {
        this.idDistractor = idDistractor;
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
        hash += (idPreguntaDistractor != null ? idPreguntaDistractor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaDistractor)) {
            return false;
        }
        PreguntaDistractor other = (PreguntaDistractor) object;
        if ((this.idPreguntaDistractor == null && other.idPreguntaDistractor != null) || (this.idPreguntaDistractor != null && !this.idPreguntaDistractor.equals(other.idPreguntaDistractor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaDistractor[ idPreguntaDistractor=" + idPreguntaDistractor + " ]";
    }

}
