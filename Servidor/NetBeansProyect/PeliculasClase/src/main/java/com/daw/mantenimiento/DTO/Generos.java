package com.daw.mantenimiento.DTO;

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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "generos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Generos.findAll", query = "SELECT g FROM Generos g"),
    @NamedQuery(name = "Generos.findById", query = "SELECT g FROM Generos g WHERE g.id = :id"),
    @NamedQuery(name = "Generos.findByNombre", query = "SELECT g FROM Generos g WHERE g.nombre = :nombre")})
public class Generos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;

    public Generos() {
    }

    public Generos(Integer id) {
        this.id = id;
    }

    public Generos(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Generos)) {
            return false;
        }
        Generos other = (Generos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.mantenimiento.DTO.Generos[ id=" + id + " ]";
    }

}
