package com.daw.peliculas.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "peliculasdetalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peliculasdetalle.findAll", query = "SELECT p FROM Peliculasdetalle p"),
    @NamedQuery(name = "Peliculasdetalle.findById", query = "SELECT p FROM Peliculasdetalle p WHERE p.id = :id"),
    @NamedQuery(name = "Peliculasdetalle.findByTitulo", query = "SELECT p FROM Peliculasdetalle p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Peliculasdetalle.findByNombreDirector", query = "SELECT p FROM Peliculasdetalle p WHERE p.nombreDirector = :nombreDirector"),
    @NamedQuery(name = "Peliculasdetalle.findByGenero", query = "SELECT p FROM Peliculasdetalle p WHERE p.genero = :genero")})
public class Peliculasdetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 36)
    @Column(name = "id")
    @Id
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombreDirector")
    private String nombreDirector;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "genero")
    private String genero;

    public Peliculasdetalle() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
