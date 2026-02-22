/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "examen_area_conocimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamenAreaConocimiento.findAll", query = "SELECT e FROM ExamenAreaConocimiento e"),
    @NamedQuery(name = "ExamenAreaConocimiento.findByIdExamenAreaConocimiento", query = "SELECT e FROM ExamenAreaConocimiento e WHERE e.idExamenAreaConocimiento = :idExamenAreaConocimiento"),
    @NamedQuery(name = "ExamenAreaConocimiento.findByObservaciones", query = "SELECT e FROM ExamenAreaConocimiento e WHERE e.observaciones = :observaciones")})
public class ExamenAreaConocimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_examen_area_conocimiento")
    private Long idExamenAreaConocimiento;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_area_conocimiento", referencedColumnName = "id_area_conocimiento")
    @ManyToOne(optional = false)
    private AreaConocimiento idAreaConocimiento;
    @JoinColumn(name = "id_examen", referencedColumnName = "id_examen")
    @ManyToOne(optional = false)
    private Examen idExamen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExamenAreaConocimiento")
    private List<ExamenAreaConocimientoPregunta> examenAreaConocimientoPreguntaList;

    public ExamenAreaConocimiento() {
    }

    public ExamenAreaConocimiento(Long idExamenAreaConocimiento) {
        this.idExamenAreaConocimiento = idExamenAreaConocimiento;
    }

    public Long getIdExamenAreaConocimiento() {
        return idExamenAreaConocimiento;
    }

    public void setIdExamenAreaConocimiento(Long idExamenAreaConocimiento) {
        this.idExamenAreaConocimiento = idExamenAreaConocimiento;
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

    public Examen getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Examen idExamen) {
        this.idExamen = idExamen;
    }

    @XmlTransient
    public List<ExamenAreaConocimientoPregunta> getExamenAreaConocimientoPreguntaList() {
        return examenAreaConocimientoPreguntaList;
    }

    public void setExamenAreaConocimientoPreguntaList(List<ExamenAreaConocimientoPregunta> examenAreaConocimientoPreguntaList) {
        this.examenAreaConocimientoPreguntaList = examenAreaConocimientoPreguntaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamenAreaConocimiento != null ? idExamenAreaConocimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamenAreaConocimiento)) {
            return false;
        }
        ExamenAreaConocimiento other = (ExamenAreaConocimiento) object;
        if ((this.idExamenAreaConocimiento == null && other.idExamenAreaConocimiento != null) || (this.idExamenAreaConocimiento != null && !this.idExamenAreaConocimiento.equals(other.idExamenAreaConocimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.ExamenAreaConocimiento[ idExamenAreaConocimiento=" + idExamenAreaConocimiento + " ]";
    }

}
