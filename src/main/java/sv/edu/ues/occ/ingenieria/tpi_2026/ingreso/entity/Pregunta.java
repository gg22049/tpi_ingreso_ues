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
@Table(name = "pregunta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findByIdPregunta", query = "SELECT p FROM Pregunta p WHERE p.idPregunta = :idPregunta"),
    @NamedQuery(name = "Pregunta.findByValor", query = "SELECT p FROM Pregunta p WHERE p.valor = :valor"),
    @NamedQuery(name = "Pregunta.findByActivo", query = "SELECT p FROM Pregunta p WHERE p.activo = :activo"),
    @NamedQuery(name = "Pregunta.findByImagenUrl", query = "SELECT p FROM Pregunta p WHERE p.imagenUrl = :imagenUrl"),
    @NamedQuery(name = "Pregunta.findByObservaciones", query = "SELECT p FROM Pregunta p WHERE p.observaciones = :observaciones")})
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pregunta")
    private Long idPregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "valor")
    private String valor;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 64)
    @Column(name = "imagen_url")
    private String imagenUrl;
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregunta", fetch = FetchType.LAZY)
    private List<PreguntaAreaConocimiento> preguntaAreaConocimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregunta", fetch = FetchType.LAZY)
    private List<PruebaClaveAreaConocimientoPregunta> pruebaClaveAreaConocimientoPreguntaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregunta", fetch = FetchType.LAZY)
    private List<PreguntaDistractor> preguntaDistractorList;

    public Pregunta() {
    }

    public Pregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Pregunta(Long idPregunta, String valor) {
        this.idPregunta = idPregunta;
        this.valor = valor;
    }

    public Pregunta(Long idPregunta, String valor, Boolean activo, String imagenUrl, String observaciones) {
        this.idPregunta = idPregunta;
        this.valor = valor;
        this.activo = activo;
        this.imagenUrl = imagenUrl;
        this.observaciones = observaciones;
    }

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<PreguntaAreaConocimiento> getPreguntaAreaConocimientoList() {
        return preguntaAreaConocimientoList;
    }

    public void setPreguntaAreaConocimientoList(List<PreguntaAreaConocimiento> preguntaAreaConocimientoList) {
        this.preguntaAreaConocimientoList = preguntaAreaConocimientoList;
    }

    @XmlTransient
    public List<PruebaClaveAreaConocimientoPregunta> getPruebaClaveAreaConocimientoPreguntaList() {
        return pruebaClaveAreaConocimientoPreguntaList;
    }

    public void setPruebaClaveAreaConocimientoPreguntaList(List<PruebaClaveAreaConocimientoPregunta> pruebaClaveAreaConocimientoPreguntaList) {
        this.pruebaClaveAreaConocimientoPreguntaList = pruebaClaveAreaConocimientoPreguntaList;
    }

    @XmlTransient
    public List<PreguntaDistractor> getPreguntaDistractorList() {
        return preguntaDistractorList;
    }

    public void setPreguntaDistractorList(List<PreguntaDistractor> preguntaDistractorList) {
        this.preguntaDistractorList = preguntaDistractorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPregunta != null ? idPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.idPregunta == null && other.idPregunta != null) || (this.idPregunta != null && !this.idPregunta.equals(other.idPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Pregunta[ idPregunta=" + idPregunta + " ]";
    }

}
