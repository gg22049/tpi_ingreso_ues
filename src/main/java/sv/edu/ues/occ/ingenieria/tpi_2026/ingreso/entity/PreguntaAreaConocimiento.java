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
@Table(name = "pregunta_area_conocimiento")
@NamedQueries({
    @NamedQuery(name = "PreguntaAreaConocimiento.findAll", query = "SELECT p FROM PreguntaAreaConocimiento p"),
    @NamedQuery(name = "PreguntaAreaConocimiento.findByIdPreguntaAreaConocimiento", query = "SELECT p FROM PreguntaAreaConocimiento p WHERE p.idPreguntaAreaConocimiento = :idPreguntaAreaConocimiento"),
    @NamedQuery(name = "PreguntaAreaConocimiento.findByObservaciones", query = "SELECT p FROM PreguntaAreaConocimiento p WHERE p.observaciones = :observaciones")})
public class PreguntaAreaConocimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pregunta_area_conocimiento")
    private Long idPreguntaAreaConocimiento;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_area_conocimiento", referencedColumnName = "id_area_conocimiento")
    @ManyToOne(optional = false)
    private AreaConocimiento idAreaConocimiento;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne(optional = false)
    private Pregunta idPregunta;

    public PreguntaAreaConocimiento() {
    }

    public PreguntaAreaConocimiento(Long idPreguntaAreaConocimiento) {
        this.idPreguntaAreaConocimiento = idPreguntaAreaConocimiento;
    }

    public Long getIdPreguntaAreaConocimiento() {
        return idPreguntaAreaConocimiento;
    }

    public void setIdPreguntaAreaConocimiento(Long idPreguntaAreaConocimiento) {
        this.idPreguntaAreaConocimiento = idPreguntaAreaConocimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public AreaConocimiento getIdAreaConocimiento() {
        return idAreaConocimiento;
    }

    public void setIdAreaConocimiento(AreaConocimiento idAreaConocimiento) {
        this.idAreaConocimiento = idAreaConocimiento;
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
        hash += (idPreguntaAreaConocimiento != null ? idPreguntaAreaConocimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaAreaConocimiento)) {
            return false;
        }
        PreguntaAreaConocimiento other = (PreguntaAreaConocimiento) object;
        if ((this.idPreguntaAreaConocimiento == null && other.idPreguntaAreaConocimiento != null) || (this.idPreguntaAreaConocimiento != null && !this.idPreguntaAreaConocimiento.equals(other.idPreguntaAreaConocimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.PreguntaAreaConocimiento[ idPreguntaAreaConocimiento=" + idPreguntaAreaConocimiento + " ]";
    }

}
