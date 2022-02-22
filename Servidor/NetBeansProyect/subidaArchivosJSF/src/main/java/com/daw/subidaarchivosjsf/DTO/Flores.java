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
@Table(name = "flores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flores.findAll", query = "SELECT f FROM Flores f"),
    @NamedQuery(name = "Flores.findByCodigoFlor", query = "SELECT f FROM Flores f WHERE f.codigoFlor = :codigoFlor"),
    @NamedQuery(name = "Flores.findByNombreComun", query = "SELECT f FROM Flores f WHERE f.nombreComun = :nombreComun"),
    @NamedQuery(name = "Flores.findByNombreCientifico", query = "SELECT f FROM Flores f WHERE f.nombreCientifico = :nombreCientifico"),
    @NamedQuery(name = "Flores.findByCodigoLuz", query = "SELECT f FROM Flores f WHERE f.codigoLuz = :codigoLuz"),
    @NamedQuery(name = "Flores.findByPrecio", query = "SELECT f FROM Flores f WHERE f.precio = :precio"),
    @NamedQuery(name = "Flores.findByUnidadesVendidas", query = "SELECT f FROM Flores f WHERE f.unidadesVendidas = :unidadesVendidas"),
    @NamedQuery(name = "Flores.findByImagen", query = "SELECT f FROM Flores f WHERE f.imagen = :imagen")})
public class Flores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoFlor")
    private Integer codigoFlor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombreComun")
    private String nombreComun;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombreCientifico")
    private String nombreCientifico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoLuz")
    private int codigoLuz;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private int precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unidadesVendidas")
    private int unidadesVendidas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "imagen")
    private String imagen;

    public Flores() {
    }

    public Flores(Integer codigoFlor) {
        this.codigoFlor = codigoFlor;
    }

    public Flores(Integer codigoFlor, String nombreComun, String nombreCientifico, int codigoLuz, int precio, int unidadesVendidas, String imagen) {
        this.codigoFlor = codigoFlor;
        this.nombreComun = nombreComun;
        this.nombreCientifico = nombreCientifico;
        this.codigoLuz = codigoLuz;
        this.precio = precio;
        this.unidadesVendidas = unidadesVendidas;
        this.imagen = imagen;
    }

    public Integer getCodigoFlor() {
        return codigoFlor;
    }

    public void setCodigoFlor(Integer codigoFlor) {
        this.codigoFlor = codigoFlor;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public int getCodigoLuz() {
        return codigoLuz;
    }

    public void setCodigoLuz(int codigoLuz) {
        this.codigoLuz = codigoLuz;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(int unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
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
        hash += (codigoFlor != null ? codigoFlor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flores)) {
            return false;
        }
        Flores other = (Flores) object;
        if ((this.codigoFlor == null && other.codigoFlor != null) || (this.codigoFlor != null && !this.codigoFlor.equals(other.codigoFlor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.subidaarchivosjsf.DTO.Flores[ codigoFlor=" + codigoFlor + " ]";
    }

    public void aumentarVentas() {
        this.unidadesVendidas++;
    }
}
