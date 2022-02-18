package com.daw.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pelicula")
@NamedQueries({
    @NamedQuery(name = "Pelicula.findAll", query = "SELECT p FROM Pelicula p"),
    @NamedQuery(name = "Pelicula.findByCodPelicula", query = "SELECT p FROM Pelicula p WHERE p.codPelicula = :codPelicula"),
    @NamedQuery(name = "Pelicula.findByTitulo", query = "SELECT p FROM Pelicula p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Pelicula.findByCodDirector", query = "SELECT p FROM Pelicula p WHERE p.codDirector = :codDirector"),
    @NamedQuery(name = "Pelicula.findByCodGenero", query = "SELECT p FROM Pelicula p WHERE p.codGenero = :codGenero"),
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
    @Column(name = "codDirector")
    private int codDirector;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codGenero")
    private int codGenero;
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

    public Pelicula() {
    }

    public Pelicula(Integer codPelicula) {
        this.codPelicula = codPelicula;
    }

    public Pelicula(Integer codPelicula, String titulo, int codDirector, int codGenero, int estreno, String imagen, int puntuacion) {
        this.codPelicula = codPelicula;
        this.titulo = titulo;
        this.codDirector = codDirector;
        this.codGenero = codGenero;
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

    public int getCodDirector() {
        return codDirector;
    }

    public void setCodDirector(int codDirector) {
        this.codDirector = codDirector;
    }

    public int getCodGenero() {
        return codGenero;
    }

    public void setCodGenero(int codGenero) {
        this.codGenero = codGenero;
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
        return "com.daw.DTO.Pelicula[ codPelicula=" + codPelicula + " ]";
    }

}
