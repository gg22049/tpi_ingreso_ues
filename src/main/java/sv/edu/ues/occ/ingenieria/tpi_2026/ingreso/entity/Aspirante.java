/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author caesar
 */
@Entity
@Table(name = "aspirante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aspirante.findAll", query = "SELECT a FROM Aspirante a"),
    @NamedQuery(name = "Aspirante.findByIdAspirante", query = "SELECT a FROM Aspirante a WHERE a.idAspirante = :idAspirante"),
    @NamedQuery(name = "Aspirante.findByNombres", query = "SELECT a FROM Aspirante a WHERE a.nombres = :nombres"),
    @NamedQuery(name = "Aspirante.findByApellidos", query = "SELECT a FROM Aspirante a WHERE a.apellidos = :apellidos"),
    @NamedQuery(name = "Aspirante.findByFechaNacimiento", query = "SELECT a FROM Aspirante a WHERE a.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Aspirante.findByCorreo", query = "SELECT a FROM Aspirante a WHERE a.correo = :correo"),
    @NamedQuery(name = "Aspirante.findByFechaCreacion", query = "SELECT a FROM Aspirante a WHERE a.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Aspirante.findByObservaciones", query = "SELECT a FROM Aspirante a WHERE a.observaciones = :observaciones")})
public class Aspirante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_aspirante")
    private Long idAspirante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 124)
    @Column(name = "correo")
    private String correo;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aspirante", fetch = FetchType.LAZY)
    private List<AspiranteIdentificacion> aspiranteIdentificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAspirante", fetch = FetchType.LAZY)
    private List<AspiranteOpcion> aspiranteOpcionList;

    public Aspirante() {
    }

    public Aspirante(Long idAspirante) {
        this.idAspirante = idAspirante;
    }

    public Aspirante(Long idAspirante, String nombres, String apellidos, Date fechaNacimiento, String correo) {
        this.idAspirante = idAspirante;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
    }

    public Long getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Long idAspirante) {
        this.idAspirante = idAspirante;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<AspiranteIdentificacion> getAspiranteIdentificacionList() {
        return aspiranteIdentificacionList;
    }

    public void setAspiranteIdentificacionList(List<AspiranteIdentificacion> aspiranteIdentificacionList) {
        this.aspiranteIdentificacionList = aspiranteIdentificacionList;
    }

    @XmlTransient
    public List<AspiranteOpcion> getAspiranteOpcionList() {
        return aspiranteOpcionList;
    }

    public void setAspiranteOpcionList(List<AspiranteOpcion> aspiranteOpcionList) {
        this.aspiranteOpcionList = aspiranteOpcionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAspirante != null ? idAspirante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aspirante)) {
            return false;
        }
        Aspirante other = (Aspirante) object;
        if ((this.idAspirante == null && other.idAspirante != null) || (this.idAspirante != null && !this.idAspirante.equals(other.idAspirante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Aspirante[ idAspirante=" + idAspirante + " ]";
    }

}
