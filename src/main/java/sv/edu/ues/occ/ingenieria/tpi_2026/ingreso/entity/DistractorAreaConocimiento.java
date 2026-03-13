/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @NamedQuery(name = "DistractorAreaConocimiento.findByIdDistractor", query = "SELECT d FROM DistractorAreaConocimiento d WHERE d.distractorAreaConocimientoPK.idDistractor = :idDistractor"),
    @NamedQuery(name = "DistractorAreaConocimiento.findByIdAreaConocimiento", query = "SELECT d FROM DistractorAreaConocimiento d WHERE d.distractorAreaConocimientoPK.idAreaConocimiento = :idAreaConocimiento"),
    @NamedQuery(name = "DistractorAreaConocimiento.findByObservaciones", query = "SELECT d FROM DistractorAreaConocimiento d WHERE d.observaciones = :observaciones")})
public class DistractorAreaConocimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DistractorAreaConocimientoPK distractorAreaConocimientoPK;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_area_conocimiento", referencedColumnName = "id_area_conocimiento", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AreaConocimiento areaConocimiento;
    @JoinColumn(name = "id_distractor", referencedColumnName = "id_distractor", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Distractor distractor;

    public DistractorAreaConocimiento() {
    }

    public DistractorAreaConocimiento(DistractorAreaConocimientoPK distractorAreaConocimientoPK) {
        this.distractorAreaConocimientoPK = distractorAreaConocimientoPK;
    }

    public DistractorAreaConocimiento(long idDistractor, int idAreaConocimiento) {
        this.distractorAreaConocimientoPK = new DistractorAreaConocimientoPK(idDistractor, idAreaConocimiento);
    }

    public DistractorAreaConocimientoPK getDistractorAreaConocimientoPK() {
        return distractorAreaConocimientoPK;
    }

    public void setDistractorAreaConocimientoPK(DistractorAreaConocimientoPK distractorAreaConocimientoPK) {
        this.distractorAreaConocimientoPK = distractorAreaConocimientoPK;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public AreaConocimiento getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(AreaConocimiento areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    public Distractor getDistractor() {
        return distractor;
    }

    public void setDistractor(Distractor distractor) {
        this.distractor = distractor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (distractorAreaConocimientoPK != null ? distractorAreaConocimientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DistractorAreaConocimiento)) {
            return false;
        }
        DistractorAreaConocimiento other = (DistractorAreaConocimiento) object;
        if ((this.distractorAreaConocimientoPK == null && other.distractorAreaConocimientoPK != null) || (this.distractorAreaConocimientoPK != null && !this.distractorAreaConocimientoPK.equals(other.distractorAreaConocimientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.DistractorAreaConocimiento[ distractorAreaConocimientoPK=" + distractorAreaConocimientoPK + " ]";
    }

}
