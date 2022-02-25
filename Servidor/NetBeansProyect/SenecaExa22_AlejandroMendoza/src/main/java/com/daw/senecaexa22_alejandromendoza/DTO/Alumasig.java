package com.daw.senecaexa22_alejandromendoza.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "alumasig")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumasig.findAll", query = "SELECT a FROM Alumasig a"),
    @NamedQuery(name = "Alumasig.findByIdAlumno", query = "SELECT a FROM Alumasig a WHERE a.alumasigPK.idAlumno = :idAlumno"),
    @NamedQuery(name = "Alumasig.findByIdAsig", query = "SELECT a FROM Alumasig a WHERE a.alumasigPK.idAsig = :idAsig"),
    @NamedQuery(name = "Alumasig.findByNota1", query = "SELECT a FROM Alumasig a WHERE a.nota1 = :nota1"),
    @NamedQuery(name = "Alumasig.findByNota2", query = "SELECT a FROM Alumasig a WHERE a.nota2 = :nota2"),
    @NamedQuery(name = "Alumasig.findByNota3", query = "SELECT a FROM Alumasig a WHERE a.nota3 = :nota3")})
public class Alumasig implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AlumasigPK alumasigPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota1")
    private int nota1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota2")
    private int nota2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota3")
    private int nota3;

    public Alumasig() {
    }

    public Alumasig(AlumasigPK alumasigPK) {
        this.alumasigPK = alumasigPK;
    }

    public Alumasig(AlumasigPK alumasigPK, int nota1, int nota2, int nota3) {
        this.alumasigPK = alumasigPK;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public Alumasig(int idAlumno, int idAsig) {
        this.alumasigPK = new AlumasigPK(idAlumno, idAsig);
    }

    public AlumasigPK getAlumasigPK() {
        return alumasigPK;
    }

    public void setAlumasigPK(AlumasigPK alumasigPK) {
        this.alumasigPK = alumasigPK;
    }

    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public int getNota3() {
        return nota3;
    }

    public void setNota3(int nota3) {
        this.nota3 = nota3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (alumasigPK != null ? alumasigPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumasig)) {
            return false;
        }
        Alumasig other = (Alumasig) object;
        if ((this.alumasigPK == null && other.alumasigPK != null) || (this.alumasigPK != null && !this.alumasigPK.equals(other.alumasigPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.senecaexa22_alejandromendoza.DTO.Alumasig[ alumasigPK=" + alumasigPK + " ]";
    }

}
