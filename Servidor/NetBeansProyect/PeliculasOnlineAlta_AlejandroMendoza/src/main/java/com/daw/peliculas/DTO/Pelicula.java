package com.daw.peliculas.DTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "pelicula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pelicula.findAll", query = "SELECT p FROM Pelicula p"),
    @NamedQuery(name = "Pelicula.findByCodPelicula", query = "SELECT p FROM Pelicula p WHERE p.codPelicula = :codPelicula"),
    @NamedQuery(name = "Pelicula.findByTitulo", query = "SELECT p FROM Pelicula p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Pelicula.findByEstreno", query = "SELECT p FROM Pelicula p WHERE p.estreno = :estreno"),
    @NamedQuery(name = "Pelicula.findByImagen", query = "SELECT p FROM Pelicula p WHERE p.imagen = :imagen"),
    @NamedQuery(name = "Pelicula.findByPuntuacion", query = "SELECT p FROM Pelicula p WHERE p.puntuacion = :puntuacion")})
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codPelicula")
    private Integer codPelicula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estreno")
    private int estreno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "imagen")
    private String imagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntuacion")
    private int puntuacion;
    @JoinColumn(name = "codDirector", referencedColumnName = "codDIrector")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Director codDirector;
    @JoinColumn(name = "codGenero", referencedColumnName = "codGenero")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Genero codGenero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pelicula", fetch = FetchType.LAZY)
    private List<Reparto> repartoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPelicula", fetch = FetchType.LAZY)
    private List<Opinion> opinionList;

    public Pelicula() {
    }

    public Pelicula(Integer codPelicula) {
        this.codPelicula = codPelicula;
    }

    public Pelicula(Integer codPelicula, String titulo, int estreno, String imagen, int puntuacion) {
        this.codPelicula = codPelicula;
        this.titulo = titulo;
        this.estreno = estreno;
        this.imagen = imagen;
        this.puntuacion = puntuacion;
    }

    public Integer getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(Integer codPelicula) {
        this.codPelicula = codPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEstreno() {
        return estreno;
    }

    public void setEstreno(int estreno) {
        this.estreno = estreno;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Director getCodDirector() {
        return codDirector;
    }

    public void setCodDirector(Director codDirector) {
        this.codDirector = codDirector;
    }

    public Genero getCodGenero() {
        return codGenero;
    }

    public void setCodGenero(Genero codGenero) {
        this.codGenero = codGenero;
    }

    @XmlTransient
    public List<Reparto> getRepartoList() {
        return repartoList;
    }

    public void setRepartoList(List<Reparto> repartoList) {
        this.repartoList = repartoList;
    }

    @XmlTransient
    public List<Opinion> getOpinionList() {
        return opinionList;
    }

    public void setOpinionList(List<Opinion> opinionList) {
        this.opinionList = opinionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPelicula != null ? codPelicula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pelicula)) {
            return false;
        }
        Pelicula other = (Pelicula) object;
        if ((this.codPelicula == null && other.codPelicula != null) || (this.codPelicula != null && !this.codPelicula.equals(other.codPelicula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.peliculas.DTO.Pelicula[ codPelicula=" + codPelicula + " ]";
    }

}
