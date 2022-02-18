/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.institutorelacionalv2.DTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lola
 */
@MappedSuperclass
@Table(catalog = "institutorelacionalcascada", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"dni"})})
@XmlRootElement
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer nmatricula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(nullable = false, length = 9)
    private String dni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(nullable = false, length = 40)
    private String nombrealumno;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private List<Matricula> matriculaList;

    public Alumno() {
    }

    public Alumno(Integer nmatricula) {
        this.nmatricula = nmatricula;
    }

    public Alumno(Integer nmatricula, String dni, String nombrealumno, String email) {
        this.nmatricula = nmatricula;
        this.dni = dni;
        this.nombrealumno = nombrealumno;
        this.email = email;
    }

    public Integer getNmatricula() {
        return nmatricula;
    }

    public void setNmatricula(Integer nmatricula) {
        this.nmatricula = nmatricula;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombrealumno() {
        return nombrealumno;
    }

    public void setNombrealumno(String nombrealumno) {
        this.nombrealumno = nombrealumno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nmatricula != null ? nmatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.nmatricula == null && other.nmatricula != null) || (this.nmatricula != null && !this.nmatricula.equals(other.nmatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.institutorelacionalv2.DTO2.Alumno[ nmatricula=" + nmatricula + " ]";
    }
    
}
