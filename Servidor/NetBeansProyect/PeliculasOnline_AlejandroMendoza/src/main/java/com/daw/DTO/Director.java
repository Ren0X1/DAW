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
@Table(name = "director")
@NamedQueries({
    @NamedQuery(name = "Director.findAll", query = "SELECT d FROM Director d"),
    @NamedQuery(name = "Director.findByCodDIrector", query = "SELECT d FROM Director d WHERE d.codDIrector = :codDIrector"),
    @NamedQuery(name = "Director.findByNombreDirector", query = "SELECT d FROM Director d WHERE d.nombreDirector = :nombreDirector"),
    @NamedQuery(name = "Director.findByNacionalidad", query = "SELECT d FROM Director d WHERE d.nacionalidad = :nacionalidad")})
public class Director implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codDIrector")
    private Integer codDIrector;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombreDirector")
    private String nombreDirector;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nacionalidad")
    private String nacionalidad;

    public Director() {
    }

    public Director(Integer codDIrector) {
        this.codDIrector = codDIrector;
    }

    public Director(Integer codDIrector, String nombreDirector, String nacionalidad) {
        this.codDIrector = codDIrector;
        this.nombreDirector = nombreDirector;
        this.nacionalidad = nacionalidad;
    }

    public Integer getCodDIrector() {
        return codDIrector;
    }

    public void setCodDIrector(Integer codDIrector) {
        this.codDIrector = codDIrector;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDIrector != null ? codDIrector.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Director)) {
            return false;
        }
        Director other = (Director) object;
        if ((this.codDIrector == null && other.codDIrector != null) || (this.codDIrector != null && !this.codDIrector.equals(other.codDIrector))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.DTO.Director[ codDIrector=" + codDIrector + " ]";
    }

}
