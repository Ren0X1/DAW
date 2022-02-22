/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.subidaarchivosjsf.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "carrito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrito.findAll", query = "SELECT c FROM Carrito c"),
    @NamedQuery(name = "Carrito.findByDniusuario", query = "SELECT c FROM Carrito c WHERE c.carritoPK.dniusuario = :dniusuario"),
    @NamedQuery(name = "Carrito.findByCodigoFlor", query = "SELECT c FROM Carrito c WHERE c.carritoPK.codigoFlor = :codigoFlor"),
    @NamedQuery(name = "Carrito.findByUnidades", query = "SELECT c FROM Carrito c WHERE c.unidades = :unidades"),
    @NamedQuery(name = "Carrito.findByTotalprecio", query = "SELECT c FROM Carrito c WHERE c.totalprecio = :totalprecio")})
public class Carrito implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CarritoPK carritoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unidades")
    private int unidades;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalprecio")
    private int totalprecio;

    public Carrito() {
    }

    public Carrito(CarritoPK carritoPK) {
        this.carritoPK = carritoPK;
    }

    public Carrito(CarritoPK carritoPK, int unidades, int totalprecio) {
        this.carritoPK = carritoPK;
        this.unidades = unidades;
        this.totalprecio = totalprecio;
    }

    public Carrito(String dniusuario, int codigoFlor) {
        this.carritoPK = new CarritoPK(dniusuario, codigoFlor);
    }

    public CarritoPK getCarritoPK() {
        return carritoPK;
    }

    public void setCarritoPK(CarritoPK carritoPK) {
        this.carritoPK = carritoPK;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public int getTotalprecio() {
        return totalprecio;
    }

    public void setTotalprecio(int totalprecio) {
        this.totalprecio = totalprecio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carritoPK != null ? carritoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrito)) {
            return false;
        }
        Carrito other = (Carrito) object;
        if ((this.carritoPK == null && other.carritoPK != null) || (this.carritoPK != null && !this.carritoPK.equals(other.carritoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.subidaarchivosjsf.DTO.Carrito[ carritoPK=" + carritoPK + " ]";
    }

    public void disminuirUnds() {
        this.unidades--;
    }

    public void aumentarUnds() {
        this.unidades++;
    }

    public void establecerPrecio(int precio) {
        this.totalprecio = this.unidades * precio;
    }
}
