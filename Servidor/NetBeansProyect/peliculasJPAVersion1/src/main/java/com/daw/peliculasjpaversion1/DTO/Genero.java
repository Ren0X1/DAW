/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.peliculasjpaversion1.DTO;

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

/**
 *
 * @author Lola
 */
@Entity
@Table(name = "genero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genero.findAll", query = "SELECT g FROM Genero g"),
    @NamedQuery(name = "Genero.findByCodgenero", query = "SELECT g FROM Genero g WHERE g.codgenero = :codgenero"),
    @NamedQuery(name = "Genero.findByGenero", query = "SELECT g FROM Genero g WHERE g.genero = :genero")})
public class Genero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codgenero")
    private Integer codgenero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "genero")
    private String genero;

    public Genero() {
    }

    public Genero(Integer codgenero) {
        this.codgenero = codgenero;
    }

    public Genero(Integer codgenero, String genero) {
        this.codgenero = codgenero;
        this.genero = genero;
    }

    public Integer getCodgenero() {
        return codgenero;
    }

    public void setCodgenero(Integer codgenero) {
        this.codgenero = codgenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codgenero != null ? codgenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genero)) {
            return false;
        }
        Genero other = (Genero) object;
        if ((this.codgenero == null && other.codgenero != null) || (this.codgenero != null && !this.codgenero.equals(other.codgenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.peliculasjpaversion1.DTO.Genero[ codgenero=" + codgenero + " ]";
    }
    
}
