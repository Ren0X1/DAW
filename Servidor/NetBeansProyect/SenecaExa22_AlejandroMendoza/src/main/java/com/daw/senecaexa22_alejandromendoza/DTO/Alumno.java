package com.daw.senecaexa22_alejandromendoza.DTO;

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

@Entity
@Table(name = "alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
    @NamedQuery(name = "Alumno.findByIdAlumno", query = "SELECT a FROM Alumno a WHERE a.idAlumno = :idAlumno"),
    @NamedQuery(name = "Alumno.findByDni", query = "SELECT a FROM Alumno a WHERE a.dni = :dni"),
    @NamedQuery(name = "Alumno.findByNomApell", query = "SELECT a FROM Alumno a WHERE a.nomApell = :nomApell"),
    @NamedQuery(name = "Alumno.findByLocalidad", query = "SELECT a FROM Alumno a WHERE a.localidad = :localidad"),
    @NamedQuery(name = "Alumno.findByIdCurso", query = "SELECT a FROM Alumno a WHERE a.idCurso = :idCurso"),
    @NamedQuery(name = "Alumno.findByImgPerfil", query = "SELECT a FROM Alumno a WHERE a.imgPerfil = :imgPerfil")})
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAlumno")
    private Integer idAlumno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "dni")
    private String dni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nomApell")
    private String nomApell;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "localidad")
    private String localidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCurso")
    private int idCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "imgPerfil")
    private String imgPerfil;

    public Alumno() {
    }

    public Alumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Alumno(Integer idAlumno, String dni, String nomApell, String localidad, int idCurso, String imgPerfil) {
        this.idAlumno = idAlumno;
        this.dni = dni;
        this.nomApell = nomApell;
        this.localidad = localidad;
        this.idCurso = idCurso;
        this.imgPerfil = imgPerfil;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNomApell() {
        return nomApell;
    }

    public void setNomApell(String nomApell) {
        this.nomApell = nomApell;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
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
        hash += (idAlumno != null ? idAlumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.idAlumno == null && other.idAlumno != null) || (this.idAlumno != null && !this.idAlumno.equals(other.idAlumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.senecaexa22_alejandromendoza.DTO.Alumno[ idAlumno=" + idAlumno + " ]";
    }

}
