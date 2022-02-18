/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.peliculasjpaversion1.DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lola
 */
@Entity
@Table(name = "peliculas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peliculas.findAll", query = "SELECT p FROM Peliculas p"),
    @NamedQuery(name = "Peliculas.findByCodigoPelicula", query = "SELECT p FROM Peliculas p WHERE p.codigoPelicula = :codigoPelicula"),
    @NamedQuery(name = "Peliculas.findByNombre", query = "SELECT p FROM Peliculas p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Peliculas.findByDirector", query = "SELECT p FROM Peliculas p WHERE p.director = :director"),
    @NamedQuery(name = "Peliculas.findByGenero", query = "SELECT p FROM Peliculas p WHERE p.genero = :genero"),
    @NamedQuery(name = "Peliculas.findByEstreno", query = "SELECT p FROM Peliculas p WHERE p.estreno = :estreno"),
    @NamedQuery(name = "Peliculas.findByPublico", query = "SELECT p FROM Peliculas p WHERE p.publico = :publico"),
    @NamedQuery(name = "Peliculas.findByFechaInsercion", query = "SELECT p FROM Peliculas p WHERE p.fechaInsercion = :fechaInsercion"),
    @NamedQuery(name = "Peliculas.findByImagen", query = "SELECT p FROM Peliculas p WHERE p.imagen = :imagen"),
  @NamedQuery(name = "Peliculas.miConsulta", query = "SELECT p FROM Peliculas p WHERE p.genero = :genero and p.estreno= :estreno")})
public class Peliculas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_pelicula")
    private Integer codigoPelicula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 170)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "director")
    private String director;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "genero")
    private String genero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estreno")
    private boolean estreno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "publico")
    private boolean publico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_insercion")
    @Temporal(TemporalType.DATE)
    private Date fechaInsercion;
    @Size(max = 200)
    @Column(name = "imagen")
    private String imagen;

    public Peliculas() {
    }

    public Peliculas(Integer codigoPelicula) {
        this.codigoPelicula = codigoPelicula;
    }

    public Peliculas(Integer codigoPelicula, String nombre, String director, String genero, boolean estreno, boolean publico, Date fechaInsercion) {
        this.codigoPelicula = codigoPelicula;
        this.nombre = nombre;
        this.director = director;
        this.genero = genero;
        this.estreno = estreno;
        this.publico = publico;
        this.fechaInsercion = fechaInsercion;
    }

    public Integer getCodigoPelicula() {
        return codigoPelicula;
    }

    public void setCodigoPelicula(Integer codigoPelicula) {
        this.codigoPelicula = codigoPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean getEstreno() {
        return estreno;
    }

    public void setEstreno(boolean estreno) {
        this.estreno = estreno;
    }

    public boolean getPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public Date getFechaInsercion() {
        return fechaInsercion;
    }

    public void setFechaInsercion(Date fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPelicula != null ? codigoPelicula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peliculas)) {
            return false;
        }
        Peliculas other = (Peliculas) object;
        if ((this.codigoPelicula == null && other.codigoPelicula != null) || (this.codigoPelicula != null && !this.codigoPelicula.equals(other.codigoPelicula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.peliculasjpaversion1.DTO.Peliculas[ codigoPelicula=" + codigoPelicula + " ]";
    }
    
}
