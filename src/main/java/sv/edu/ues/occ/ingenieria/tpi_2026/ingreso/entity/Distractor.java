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
@Table(name = "distractor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distractor.findAll", query = "SELECT d FROM Distractor d"),
    @NamedQuery(name = "Distractor.findByIdDistractor", query = "SELECT d FROM Distractor d WHERE d.idDistractor = :idDistractor"),
    @NamedQuery(name = "Distractor.findByValor", query = "SELECT d FROM Distractor d WHERE d.valor = :valor"),
    @NamedQuery(name = "Distractor.findByActivo", query = "SELECT d FROM Distractor d WHERE d.activo = :activo"),
    @NamedQuery(name = "Distractor.findByImagenUrl", query = "SELECT d FROM Distractor d WHERE d.imagenUrl = :imagenUrl")})
public class Distractor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_distractor")
    private Long idDistractor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "valor")
    private String valor;
    @NotNull
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 64)
    @Column(name = "imagen_url")
    private String imagenUrl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "distractor", fetch = FetchType.LAZY)
    private List<PruebaClaveAreaConocimientoPreguntaDistractor> pruebaClaveAreaConocimientoPreguntaDistractorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "distractor", fetch = FetchType.LAZY)
    private List<DistractorAreaConocimiento> distractorAreaConocimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "distractor", fetch = FetchType.LAZY)
    private List<PreguntaDistractor> preguntaDistractorList;

    public Distractor() {
    }

    public Distractor(Long idDistractor) {
        this.idDistractor = idDistractor;
    }

    public Distractor(Long idDistractor, String valor) {
        this.idDistractor = idDistractor;
        this.valor = valor;
    }

    public Distractor(Long idDistractor, String valor, Boolean activo, String imagenUrl) {
        this.idDistractor = idDistractor;
        this.valor = valor;
        this.activo = activo;
        this.imagenUrl = imagenUrl;
    }

    public Long getIdDistractor() {
        return idDistractor;
    }

    public void setIdDistractor(Long idDistractor) {
        this.idDistractor = idDistractor;
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

    @XmlTransient
    public List<PruebaClaveAreaConocimientoPreguntaDistractor> getPruebaClaveAreaConocimientoPreguntaDistractorList() {
        return pruebaClaveAreaConocimientoPreguntaDistractorList;
    }

    public void setPruebaClaveAreaConocimientoPreguntaDistractorList(List<PruebaClaveAreaConocimientoPreguntaDistractor> pruebaClaveAreaConocimientoPreguntaDistractorList) {
        this.pruebaClaveAreaConocimientoPreguntaDistractorList = pruebaClaveAreaConocimientoPreguntaDistractorList;
    }

    @XmlTransient
    public List<DistractorAreaConocimiento> getDistractorAreaConocimientoList() {
        return distractorAreaConocimientoList;
    }

    public void setDistractorAreaConocimientoList(List<DistractorAreaConocimiento> distractorAreaConocimientoList) {
        this.distractorAreaConocimientoList = distractorAreaConocimientoList;
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
        hash += (idDistractor != null ? idDistractor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distractor)) {
            return false;
        }
        Distractor other = (Distractor) object;
        if ((this.idDistractor == null && other.idDistractor != null) || (this.idDistractor != null && !this.idDistractor.equals(other.idDistractor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.entity.Distractor[ idDistractor=" + idDistractor + " ]";
    }

}
