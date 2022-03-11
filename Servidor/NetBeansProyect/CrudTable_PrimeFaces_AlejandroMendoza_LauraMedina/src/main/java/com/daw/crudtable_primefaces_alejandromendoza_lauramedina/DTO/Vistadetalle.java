package com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DTO;

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

@Entity
@Table(name = "vistadetalle")
@NamedQueries({
    @NamedQuery(name = "Vistadetalle.findAll", query = "SELECT v FROM Vistadetalle v"),
    @NamedQuery(name = "Vistadetalle.findByUid", query = "SELECT v FROM Vistadetalle v WHERE v.uid = :uid"),
    @NamedQuery(name = "Vistadetalle.findByCodArticulo", query = "SELECT v FROM Vistadetalle v WHERE v.codArticulo = :codArticulo"),
    @NamedQuery(name = "Vistadetalle.findByDenominacion", query = "SELECT v FROM Vistadetalle v WHERE v.denominacion = :denominacion"),
    @NamedQuery(name = "Vistadetalle.findByPrecio", query = "SELECT v FROM Vistadetalle v WHERE v.precio = :precio"),
    @NamedQuery(name = "Vistadetalle.findByCantidad", query = "SELECT v FROM Vistadetalle v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "Vistadetalle.findByUsuario", query = "SELECT v FROM Vistadetalle v WHERE v.usuario = :usuario")})
public class Vistadetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 36)
    @Column(name = "uid")
    @Id
    private String uid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_articulo")
    private int codArticulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "denominacion")
    private String denominacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private float precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario")
    private String usuario;

    public Vistadetalle() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
