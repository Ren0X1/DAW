/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.tiendaonlinebdjpa.DTO;

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
@Table(name = "articulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a"),
    @NamedQuery(name = "Articulo.findByCodArticulo", query = "SELECT a FROM Articulo a WHERE a.codArticulo = :codArticulo"),
    @NamedQuery(name = "Articulo.findByDenominacion", query = "SELECT a FROM Articulo a WHERE a.denominacion = :denominacion"),
    @NamedQuery(name = "Articulo.findByCategoria", query = "SELECT a FROM Articulo a WHERE a.categoria = :categoria"),
    @NamedQuery(name = "Articulo.findByPrecio", query = "SELECT a FROM Articulo a WHERE a.precio = :precio"),
    @NamedQuery(name = "Articulo.findByExistencias", query = "SELECT a FROM Articulo a WHERE a.existencias = :existencias"),
    @NamedQuery(name = "Articulo.findByCodproveedor", query = "SELECT a FROM Articulo a WHERE a.codproveedor = :codproveedor"),
    @NamedQuery(name = "Articulo.findByImagen", query = "SELECT a FROM Articulo a WHERE a.imagen = :imagen")})
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_articulo")
    private Integer codArticulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "denominacion")
    private String denominacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "categoria")
    private String categoria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private float precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existencias")
    private int existencias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codproveedor")
    private int codproveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "imagen")
    private String imagen;

    public Articulo() {
    }

    public Articulo(Integer codArticulo) {
        this.codArticulo = codArticulo;
    }

    public Articulo(Integer codArticulo, String denominacion, String categoria, float precio, int existencias, int codproveedor, String imagen) {
        this.codArticulo = codArticulo;
        this.denominacion = denominacion;
        this.categoria = categoria;
        this.precio = precio;
        this.existencias = existencias;
        this.codproveedor = codproveedor;
        this.imagen = imagen;
    }

    public Integer getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(Integer codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public int getCodproveedor() {
        return codproveedor;
    }

    public void setCodproveedor(int codproveedor) {
        this.codproveedor = codproveedor;
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
        hash += (codArticulo != null ? codArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.codArticulo == null && other.codArticulo != null) || (this.codArticulo != null && !this.codArticulo.equals(other.codArticulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.tiendaonlinebdjpa.Articulo[ codArticulo=" + codArticulo + " ]";
    }
    
}
