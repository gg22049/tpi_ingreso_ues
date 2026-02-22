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
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
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
    @NamedQuery(name = "Aspirante.findByIdAspiranteOpcion", query = "SELECT a FROM Aspirante a WHERE a.idAspiranteOpcion = :idAspiranteOpcion"),
    @NamedQuery(name = "Aspirante.findByNombres", query = "SELECT a FROM Aspirante a WHERE a.nombres = :nombres"),
    @NamedQuery(name = "Aspirante.findByApellidos", query = "SELECT a FROM Aspirante a WHERE a.apellidos = :apellidos"),
    @NamedQuery(name = "Aspirante.findByObservaciones", query = "SELECT a FROM Aspirante a WHERE a.observaciones = :observaciones")})
public class Aspirante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_aspirante")
    private Long idAspirante;
    @Size(max = 256)
    @Column(name = "id_aspirante_opcion")
    private String idAspiranteOpcion;
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
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAspirante")
    private List<ExamenAspirante> examenAspiranteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAspirante")
    private List<DocumentoIdentificacion> documentoIdentificacionList;
    @JoinColumn(name = "id_tipo_ingreso", referencedColumnName = "id_tipo_ingreso")
    @ManyToOne(optional = false)
    private TipoIngreso idTipoIngreso;

    public Aspirante() {
    }

    public Aspirante(Long idAspirante) {
        this.idAspirante = idAspirante;
    }

    public Aspirante(Long idAspirante, String nombres, String apellidos) {
        this.idAspirante = idAspirante;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Long getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Long idAspirante) {
        this.idAspirante = idAspirante;
    }

    public String getIdAspiranteOpcion() {
        return idAspiranteOpcion;
    }

    public void setIdAspiranteOpcion(String idAspiranteOpcion) {
        this.idAspiranteOpcion = idAspiranteOpcion;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<ExamenAspirante> getExamenAspiranteList() {
        return examenAspiranteList;
    }

    public void setExamenAspiranteList(List<ExamenAspirante> examenAspiranteList) {
        this.examenAspiranteList = examenAspiranteList;
    }

    @XmlTransient
    public List<DocumentoIdentificacion> getDocumentoIdentificacionList() {
        return documentoIdentificacionList;
    }

    public void setDocumentoIdentificacionList(List<DocumentoIdentificacion> documentoIdentificacionList) {
        this.documentoIdentificacionList = documentoIdentificacionList;
    }

    public TipoIngreso getIdTipoIngreso() {
        return idTipoIngreso;
    }

    public void setIdTipoIngreso(TipoIngreso idTipoIngreso) {
        this.idTipoIngreso = idTipoIngreso;
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
