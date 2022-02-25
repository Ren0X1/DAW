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
@Table(name = "asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a"),
    @NamedQuery(name = "Asignatura.findByIdAsig", query = "SELECT a FROM Asignatura a WHERE a.idAsig = :idAsig"),
    @NamedQuery(name = "Asignatura.findByNomAsig", query = "SELECT a FROM Asignatura a WHERE a.nomAsig = :nomAsig"),
    @NamedQuery(name = "Asignatura.findByIdCurso", query = "SELECT a FROM Asignatura a WHERE a.idCurso = :idCurso"),
    @NamedQuery(name = "Asignatura.findByCodProfesor", query = "SELECT a FROM Asignatura a WHERE a.codProfesor = :codProfesor")})
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAsig")
    private Integer idAsig;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nomAsig")
    private String nomAsig;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCurso")
    private int idCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codProfesor")
    private String codProfesor;

    public Asignatura() {
    }

    public Asignatura(Integer idAsig) {
        this.idAsig = idAsig;
    }

    public Asignatura(Integer idAsig, String nomAsig, int idCurso, String codProfesor) {
        this.idAsig = idAsig;
        this.nomAsig = nomAsig;
        this.idCurso = idCurso;
        this.codProfesor = codProfesor;
    }

    public Integer getIdAsig() {
        return idAsig;
    }

    public void setIdAsig(Integer idAsig) {
        this.idAsig = idAsig;
    }

    public String getNomAsig() {
        return nomAsig;
    }

    public void setNomAsig(String nomAsig) {
        this.nomAsig = nomAsig;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getCodProfesor() {
        return codProfesor;
    }

    public void setCodProfesor(String codProfesor) {
        this.codProfesor = codProfesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsig != null ? idAsig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.idAsig == null && other.idAsig != null) || (this.idAsig != null && !this.idAsig.equals(other.idAsig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.senecaexa22_alejandromendoza.DTO.Asignatura[ idAsig=" + idAsig + " ]";
    }

}
