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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "area_conocimiento")
@NamedQueries({
    @NamedQuery(name = "AreaConocimiento.findAll", query = "SELECT a FROM AreaConocimiento a"),
    @NamedQuery(name = "AreaConocimiento.findByIdAreaConocimiento", query = "SELECT a FROM AreaConocimiento a WHERE a.idAreaConocimiento = :idAreaConocimiento"),
    @NamedQuery(name = "AreaConocimiento.findByNombre", query = "SELECT a FROM AreaConocimiento a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AreaConocimiento.findByObservaciones", query = "SELECT a FROM AreaConocimiento a WHERE a.observaciones = :observaciones")})
public class AreaConocimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_area_conocimiento")
    private Integer idAreaConocimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAreaConocimiento")
    private List<PreguntaAreaConocimiento> preguntaAreaConocimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAreaConocimiento")
    private List<ExamenAreaConocimiento> examenAreaConocimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAreaConocimiento")
    private List<DistractorAreaConocimiento> distractorAreaConocimientoList;
    @OneToMany(mappedBy = "idAreaConocimientoPadre")
    private List<AreaConocimiento> areaConocimientoList;
    @JoinColumn(name = "id_area_conocimiento_padre", referencedColumnName = "id_area_conocimiento")
    @ManyToOne
    private AreaConocimiento idAreaConocimientoPadre;

    public AreaConocimiento() {
    }

    public AreaConocimiento(Integer idAreaConocimiento) {
        this.idAreaConocimiento = idAreaConocimiento;
    }

    public AreaConocimiento(Integer idAreaConocimiento, String nombre) {
        this.idAreaConocimiento = idAreaConocimiento;
        this.nombre = nombre;
    }

    public Integer getIdAreaConocimiento() {
        return idAreaConocimiento;
    }

    public void setIdAreaConocimiento(Integer idAreaConocimiento) {
        this.idAreaConocimiento = idAreaConocimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @JsonbTransient
    public List<PreguntaAreaConocimiento> getPreguntaAreaConocimientoList() {
        return preguntaAreaConocimientoList;
    }

    public void setPreguntaAreaConocimientoList(List<PreguntaAreaConocimiento> preguntaAreaConocimientoList) {
        this.preguntaAreaConocimientoList = preguntaAreaConocimientoList;
    }

    @JsonbTransient
    public List<ExamenAreaConocimiento> getExamenAreaConocimientoList() {
        return examenAreaConocimientoList;
    }

    public void setExamenAreaConocimientoList(List<ExamenAreaConocimiento> examenAreaConocimientoList) {
        this.examenAreaConocimientoList = examenAreaConocimientoList;
    }

    @JsonbTransient
    public List<DistractorAreaConocimiento> getDistractorAreaConocimientoList() {
        return distractorAreaConocimientoList;
    }

    public void setDistractorAreaConocimientoList(List<DistractorAreaConocimiento> distractorAreaConocimientoList) {
        this.distractorAreaConocimientoList = distractorAreaConocimientoList;
    }

    @JsonbTransient
    public List<AreaConocimiento> getAreaConocimientoList() {
        return areaConocimientoList;
    }

    public void setAreaConocimientoList(List<AreaConocimiento> areaConocimientoList) {
        this.areaConocimientoList = areaConocimientoList;
    }

    public AreaConocimiento getIdAreaConocimientoPadre() {
        return idAreaConocimientoPadre;
    }

    public void setIdAreaConocimientoPadre(AreaConocimiento idAreaConocimientoPadre) {
        this.idAreaConocimientoPadre = idAreaConocimientoPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAreaConocimiento != null ? idAreaConocimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreaConocimiento)) {
            return false;
        }
        AreaConocimiento other = (AreaConocimiento) object;
        if ((this.idAreaConocimiento == null && other.idAreaConocimiento != null) || (this.idAreaConocimiento != null && !this.idAreaConocimiento.equals(other.idAreaConocimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.AreaConocimiento[ idAreaConocimiento=" + idAreaConocimiento + " ]";
    }

}
