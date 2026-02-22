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
@Table(name = "distractor_area_conocimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DistractorAreaConocimiento.findAll", query = "SELECT d FROM DistractorAreaConocimiento d"),
    @NamedQuery(name = "DistractorAreaConocimiento.findByIdDistractorAreaConocimiento", query = "SELECT d FROM DistractorAreaConocimiento d WHERE d.idDistractorAreaConocimiento = :idDistractorAreaConocimiento"),
    @NamedQuery(name = "DistractorAreaConocimiento.findByObservaciones", query = "SELECT d FROM DistractorAreaConocimiento d WHERE d.observaciones = :observaciones")})
public class DistractorAreaConocimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_distractor_area_conocimiento")
    private Long idDistractorAreaConocimiento;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_area_conocimiento", referencedColumnName = "id_area_conocimiento")
    @ManyToOne(optional = false)
    private AreaConocimiento idAreaConocimiento;
    @JoinColumn(name = "id_distractor", referencedColumnName = "id_distractor")
    @ManyToOne(optional = false)
    private Distractor idDistractor;

    public DistractorAreaConocimiento() {
    }

    public DistractorAreaConocimiento(Long idDistractorAreaConocimiento) {
        this.idDistractorAreaConocimiento = idDistractorAreaConocimiento;
    }

    public Long getIdDistractorAreaConocimiento() {
        return idDistractorAreaConocimiento;
    }

    public void setIdDistractorAreaConocimiento(Long idDistractorAreaConocimiento) {
        this.idDistractorAreaConocimiento = idDistractorAreaConocimiento;
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

    public Distractor getIdDistractor() {
        return idDistractor;
    }

    public void setIdDistractor(Distractor idDistractor) {
        this.idDistractor = idDistractor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDistractorAreaConocimiento != null ? idDistractorAreaConocimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DistractorAreaConocimiento)) {
            return false;
        }
        DistractorAreaConocimiento other = (DistractorAreaConocimiento) object;
        if ((this.idDistractorAreaConocimiento == null && other.idDistractorAreaConocimiento != null) || (this.idDistractorAreaConocimiento != null && !this.idDistractorAreaConocimiento.equals(other.idDistractorAreaConocimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimiento[ idDistractorAreaConocimiento=" + idDistractorAreaConocimiento + " ]";
    }

}
