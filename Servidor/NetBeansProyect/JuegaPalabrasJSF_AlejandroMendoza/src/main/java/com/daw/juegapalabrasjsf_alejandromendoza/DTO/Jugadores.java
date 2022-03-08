package com.daw.juegapalabrasjsf_alejandromendoza.DTO;

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
@Table(name = "jugadores")
@NamedQueries({
    @NamedQuery(name = "Jugadores.findAll", query = "SELECT j FROM Jugadores j"),
    @NamedQuery(name = "Jugadores.findByNif", query = "SELECT j FROM Jugadores j WHERE j.nif = :nif"),
    @NamedQuery(name = "Jugadores.findByClave", query = "SELECT j FROM Jugadores j WHERE j.clave = :clave"),
    @NamedQuery(name = "Jugadores.findByNombre", query = "SELECT j FROM Jugadores j WHERE j.nombre = :nombre"),
    @NamedQuery(name = "Jugadores.findByLocalidad", query = "SELECT j FROM Jugadores j WHERE j.localidad = :localidad"),
    @NamedQuery(name = "Jugadores.findByPuntosAcum", query = "SELECT j FROM Jugadores j WHERE j.puntosAcum = :puntosAcum"),
    @NamedQuery(name = "Jugadores.findByNivelSuperado", query = "SELECT j FROM Jugadores j WHERE j.nivelSuperado = :nivelSuperado"),
    @NamedQuery(name = "Jugadores.findByPass", query = "SELECT j FROM Jugadores j WHERE j.pass = :pass"),
    @NamedQuery(name = "Jugadores.findByImgPerfil", query = "SELECT j FROM Jugadores j WHERE j.imgPerfil = :imgPerfil")})
public class Jugadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "nif")
    private String nif;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "localidad")
    private String localidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntosAcum")
    private int puntosAcum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nivelSuperado")
    private int nivelSuperado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "pass")
    private String pass;
    @Size(max = 15)
    @Column(name = "img_perfil")
    private String imgPerfil;

    public Jugadores() {
    }

    public Jugadores(String nif) {
        this.nif = nif;
    }

    public Jugadores(String nif, String clave, String nombre, String localidad, int puntosAcum, int nivelSuperado, String pass) {
        this.nif = nif;
        this.clave = clave;
        this.nombre = nombre;
        this.localidad = localidad;
        this.puntosAcum = puntosAcum;
        this.nivelSuperado = nivelSuperado;
        this.pass = pass;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getPuntosAcum() {
        return puntosAcum;
    }

    public void setPuntosAcum(int puntosAcum) {
        this.puntosAcum = puntosAcum;
    }

    public int getNivelSuperado() {
        return nivelSuperado;
    }

    public void setNivelSuperado(int nivelSuperado) {
        this.nivelSuperado = nivelSuperado;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getImgPerfil() {
        return imgPerfil;
    }

    public void setImgPerfil(String imgPerfil) {
        this.imgPerfil = imgPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nif != null ? nif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jugadores)) {
            return false;
        }
        Jugadores other = (Jugadores) object;
        return !((this.nif == null && other.nif != null) || (this.nif != null && !this.nif.equals(other.nif)));
    }

    @Override
    public String toString() {
        return "com.daw.juegapalabrasjsf_alejandromendoza.DTO.Jugadores[ nif=" + nif + " ]";
    }

}
