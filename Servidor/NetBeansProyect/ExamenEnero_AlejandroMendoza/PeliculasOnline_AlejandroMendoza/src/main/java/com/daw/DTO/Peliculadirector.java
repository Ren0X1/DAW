package com.daw.DTO;

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
@Table(name = "peliculadirector")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peliculadirector.findAll", query = "SELECT p FROM Peliculadirector p"),
    @NamedQuery(name = "Peliculadirector.findById", query = "SELECT p FROM Peliculadirector p WHERE p.id = :id"),
    @NamedQuery(name = "Peliculadirector.findByTitulo", query = "SELECT p FROM Peliculadirector p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Peliculadirector.findByNombreDirector", query = "SELECT p FROM Peliculadirector p WHERE p.nombreDirector = :nombreDirector"),
    @NamedQuery(name = "Peliculadirector.findByGenero", query = "SELECT p FROM Peliculadirector p WHERE p.genero = :genero")})
public class Peliculadirector implements Serializable {

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

    public Peliculadirector() {
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
