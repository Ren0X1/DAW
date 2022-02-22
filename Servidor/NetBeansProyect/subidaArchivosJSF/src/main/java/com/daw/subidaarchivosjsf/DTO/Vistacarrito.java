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
@Table(name = "vistacarrito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vistacarrito.findAll", query = "SELECT v FROM Vistacarrito v"),
    @NamedQuery(name = "Vistacarrito.findByUid", query = "SELECT v FROM Vistacarrito v WHERE v.uid = :uid"),
    @NamedQuery(name = "Vistacarrito.findByDniusuario", query = "SELECT v FROM Vistacarrito v WHERE v.dniusuario = :dniusuario"),
    @NamedQuery(name = "Vistacarrito.findByCodigoFlor", query = "SELECT v FROM Vistacarrito v WHERE v.codigoFlor = :codigoFlor"),
    @NamedQuery(name = "Vistacarrito.findByUnidades", query = "SELECT v FROM Vistacarrito v WHERE v.unidades = :unidades"),
    @NamedQuery(name = "Vistacarrito.findByTotalprecio", query = "SELECT v FROM Vistacarrito v WHERE v.totalprecio = :totalprecio"),
    @NamedQuery(name = "Vistacarrito.findByNombreComun", query = "SELECT v FROM Vistacarrito v WHERE v.nombreComun = :nombreComun"),
    @NamedQuery(name = "Vistacarrito.findByNombreCientifico", query = "SELECT v FROM Vistacarrito v WHERE v.nombreCientifico = :nombreCientifico"),
    @NamedQuery(name = "Vistacarrito.findByPrecio", query = "SELECT v FROM Vistacarrito v WHERE v.precio = :precio"),
    @NamedQuery(name = "Vistacarrito.findByImagen", query = "SELECT v FROM Vistacarrito v WHERE v.imagen = :imagen"),
    @NamedQuery(name = "Vistacarrito.findByTipoLuz", query = "SELECT v FROM Vistacarrito v WHERE v.tipoLuz = :tipoLuz"),
    @NamedQuery(name = "Vistacarrito.findByAgua", query = "SELECT v FROM Vistacarrito v WHERE v.agua = :agua")})
public class Vistacarrito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 36)
    @Column(name = "uid")
    @Id
    private String uid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "dniusuario")
    private String dniusuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoFlor")
    private int codigoFlor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unidades")
    private int unidades;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalprecio")
    private int totalprecio;
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
    @Column(name = "precio")
    private int precio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "imagen")
    private String imagen;
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

    public Vistacarrito() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDniusuario() {
        return dniusuario;
    }

    public void setDniusuario(String dniusuario) {
        this.dniusuario = dniusuario;
    }

    public int getCodigoFlor() {
        return codigoFlor;
    }

    public void setCodigoFlor(int codigoFlor) {
        this.codigoFlor = codigoFlor;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
    
}
