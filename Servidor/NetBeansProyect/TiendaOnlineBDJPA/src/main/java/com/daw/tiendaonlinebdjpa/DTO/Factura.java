/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.tiendaonlinebdjpa.DTO;

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
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByNumfactura", query = "SELECT f FROM Factura f WHERE f.numfactura = :numfactura"),
    @NamedQuery(name = "Factura.findByUsuario", query = "SELECT f FROM Factura f WHERE f.usuario = :usuario"),
    @NamedQuery(name = "Factura.findByFecha", query = "SELECT f FROM Factura f WHERE f.fecha = :fecha"),
    @NamedQuery(name = "Factura.findByImporte", query = "SELECT f FROM Factura f WHERE f.importe = :importe"),
    @NamedQuery(name = "Factura.findByPagado", query = "SELECT f FROM Factura f WHERE f.pagado = :pagado")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numfactura")
    private Integer numfactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe")
    private float importe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pagado")
    private boolean pagado;

    public Factura() {
    }

    public Factura(Integer numfactura) {
        this.numfactura = numfactura;
    }

    public Factura(Integer numfactura, String usuario, Date fecha, float importe, boolean pagado) {
        this.numfactura = numfactura;
        this.usuario = usuario;
        this.fecha = fecha;
        this.importe = importe;
        this.pagado = pagado;
    }

    public Integer getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(Integer numfactura) {
        this.numfactura = numfactura;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public boolean getPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numfactura != null ? numfactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.numfactura == null && other.numfactura != null) || (this.numfactura != null && !this.numfactura.equals(other.numfactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.tiendaonlinebdjpa.Factura[ numfactura=" + numfactura + " ]";
    }
    
}
