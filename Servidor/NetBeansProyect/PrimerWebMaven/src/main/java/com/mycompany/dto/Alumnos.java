/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto;

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
@Table(name = "alumnos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumnos.findAll", query = "SELECT a FROM Alumnos a"),
    @NamedQuery(name = "Alumnos.findById", query = "SELECT a FROM Alumnos a WHERE a.id = :id"),
    @NamedQuery(name = "Alumnos.findByNombre", query = "SELECT a FROM Alumnos a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Alumnos.findByApellidos", query = "SELECT a FROM Alumnos a WHERE a.apellidos = :apellidos"),
    @NamedQuery(name = "Alumnos.findByNotasfinales", query = "SELECT a FROM Alumnos a WHERE a.notasfinales = :notasfinales")})
public class Alumnos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "notasfinales")
    private String notasfinales;

    public Alumnos() {
    }

    public Alumnos(Integer id) {
        this.id = id;
    }

    public Alumnos(Integer id, String nombre, String apellidos, String notasfinales) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.notasfinales = notasfinales;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNotasfinales() {
        return notasfinales;
    }

    public void setNotasfinales(String notasfinales) {
        this.notasfinales = notasfinales;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumnos)) {
            return false;
        }
        Alumnos other = (Alumnos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dto.Alumnos[ id=" + id + " ]";
    }
    
}
