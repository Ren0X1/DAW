/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.subidaarchivosjsf.DTO;

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
 * @author Usuario
 */
@Entity
@Table(name = "luz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Luz.findAll", query = "SELECT l FROM Luz l"),
    @NamedQuery(name = "Luz.findByCodigoLuz", query = "SELECT l FROM Luz l WHERE l.codigoLuz = :codigoLuz"),
    @NamedQuery(name = "Luz.findByTipoLuz", query = "SELECT l FROM Luz l WHERE l.tipoLuz = :tipoLuz"),
    @NamedQuery(name = "Luz.findByAgua", query = "SELECT l FROM Luz l WHERE l.agua = :agua")})
public class Luz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoLuz")
    private Integer codigoLuz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tipoLuz")
    private String tipoLuz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "agua")
    private String agua;

    public Luz() {
    }

    public Luz(Integer codigoLuz) {
        this.codigoLuz = codigoLuz;
    }

    public Luz(Integer codigoLuz, String tipoLuz, String agua) {
        this.codigoLuz = codigoLuz;
        this.tipoLuz = tipoLuz;
        this.agua = agua;
    }

    public Integer getCodigoLuz() {
        return codigoLuz;
    }

    public void setCodigoLuz(Integer codigoLuz) {
        this.codigoLuz = codigoLuz;
    }

    public String getTipoLuz() {
        return tipoLuz;
    }

    public void setTipoLuz(String tipoLuz) {
        this.tipoLuz = tipoLuz;
    }

    public String getAgua() {
        return agua;
    }

    public void setAgua(String agua) {
        this.agua = agua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoLuz != null ? codigoLuz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Luz)) {
            return false;
        }
        Luz other = (Luz) object;
        if ((this.codigoLuz == null && other.codigoLuz != null) || (this.codigoLuz != null && !this.codigoLuz.equals(other.codigoLuz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.subidaarchivosjsf.DTO.Luz[ codigoLuz=" + codigoLuz + " ]";
    }
    
}
