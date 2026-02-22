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
@Table(name = "examen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examen.findAll", query = "SELECT e FROM Examen e"),
    @NamedQuery(name = "Examen.findByIdExamen", query = "SELECT e FROM Examen e WHERE e.idExamen = :idExamen"),
    @NamedQuery(name = "Examen.findByObservaciones", query = "SELECT e FROM Examen e WHERE e.observaciones = :observaciones")})
public class Examen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_examen")
    private Integer idExamen;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExamen")
    private List<ExamenJornada> examenJornadaList;
    @JoinColumn(name = "id_tipo_examen", referencedColumnName = "id_tipo_examen")
    @ManyToOne(optional = false)
    private TipoExamen idTipoExamen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExamen")
    private List<ExamenAspirante> examenAspiranteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idExamen")
    private List<ExamenAreaConocimiento> examenAreaConocimientoList;

    public Examen() {
    }

    public Examen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<ExamenJornada> getExamenJornadaList() {
        return examenJornadaList;
    }

    public void setExamenJornadaList(List<ExamenJornada> examenJornadaList) {
        this.examenJornadaList = examenJornadaList;
    }

    public TipoExamen getIdTipoExamen() {
        return idTipoExamen;
    }

    public void setIdTipoExamen(TipoExamen idTipoExamen) {
        this.idTipoExamen = idTipoExamen;
    }

    @XmlTransient
    public List<ExamenAspirante> getExamenAspiranteList() {
        return examenAspiranteList;
    }

    public void setExamenAspiranteList(List<ExamenAspirante> examenAspiranteList) {
        this.examenAspiranteList = examenAspiranteList;
    }

    @XmlTransient
    public List<ExamenAreaConocimiento> getExamenAreaConocimientoList() {
        return examenAreaConocimientoList;
    }

    public void setExamenAreaConocimientoList(List<ExamenAreaConocimiento> examenAreaConocimientoList) {
        this.examenAreaConocimientoList = examenAreaConocimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExamen != null ? idExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examen)) {
            return false;
        }
        Examen other = (Examen) object;
        if ((this.idExamen == null && other.idExamen != null) || (this.idExamen != null && !this.idExamen.equals(other.idExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Examen[ idExamen=" + idExamen + " ]";
    }

}
