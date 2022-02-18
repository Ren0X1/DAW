/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.tiendaonlinebdjpa.DTO;

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
 * @author Lola
 */
@Entity
@Table(name = "detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalle.findAll", query = "SELECT d FROM Detalle d"),
    @NamedQuery(name = "Detalle.findByUsuario", query = "SELECT d FROM Detalle d WHERE d.detallePK.usuario = :usuario"),
    @NamedQuery(name = "Detalle.findByCodArticulo", query = "SELECT d FROM Detalle d WHERE d.detallePK.codArticulo = :codArticulo"),
    @NamedQuery(name = "Detalle.findByCantidad", query = "SELECT d FROM Detalle d WHERE d.cantidad = :cantidad")})
public class Detalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallePK detallePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;

    public Detalle() {
    }

    public Detalle(DetallePK detallePK) {
        this.detallePK = detallePK;
    }

    public Detalle(DetallePK detallePK, int cantidad) {
        this.detallePK = detallePK;
        this.cantidad = cantidad;
    }

    public Detalle(String usuario, int codArticulo) {
        this.detallePK = new DetallePK(usuario, codArticulo);
    }
  public Detalle(String usuario, int codArticulo, int cantidad) {
        this.detallePK = new DetallePK(usuario, codArticulo);
        this.cantidad=cantidad;
    }

    public DetallePK getDetallePK() {
        return detallePK;
    }

    public void setDetallePK(DetallePK detallePK) {
        this.detallePK = detallePK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallePK != null ? detallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalle)) {
            return false;
        }
        Detalle other = (Detalle) object;
        if ((this.detallePK == null && other.detallePK != null) || (this.detallePK != null && !this.detallePK.equals(other.detallePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.tiendaonlinebdjpa.Detalle[ detallePK=" + detallePK + " ]";
    }
    
}
