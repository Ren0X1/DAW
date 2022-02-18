/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.institutorelacionalv2.DTO;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lola
 */
@MappedSuperclass
@Table(catalog = "institutorelacionalcascada", schema = "")
@XmlRootElement
public class Matricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MatriculaPK matriculaPK;
    private Integer nota1;
    private Integer nota2;
    private Integer nota3;
    private Integer notafinal;
    @JoinColumn(name = "nmatriculaAlumn", referencedColumnName = "nmatricula", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;
    @JoinColumn(name = "codasignatura", referencedColumnName = "codasignatura", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Asignatura asignatura;

    public Matricula() {
    }

    public Matricula(MatriculaPK matriculaPK) {
        this.matriculaPK = matriculaPK;
    }

    public Matricula(int nmatriculaAlumn, int codasignatura) {
        this.matriculaPK = new MatriculaPK(nmatriculaAlumn, codasignatura);
    }

    public MatriculaPK getMatriculaPK() {
        return matriculaPK;
    }

    public void setMatriculaPK(MatriculaPK matriculaPK) {
        this.matriculaPK = matriculaPK;
    }

    public Integer getNota1() {
        return nota1;
    }

    public void setNota1(Integer nota1) {
        this.nota1 = nota1;
    }

    public Integer getNota2() {
        return nota2;
    }

    public void setNota2(Integer nota2) {
        this.nota2 = nota2;
    }

    public Integer getNota3() {
        return nota3;
    }

    public void setNota3(Integer nota3) {
        this.nota3 = nota3;
    }

    public Integer getNotafinal() {
        return notafinal;
    }

    public void setNotafinal(Integer notafinal) {
        this.notafinal = notafinal;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculaPK != null ? matriculaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.matriculaPK == null && other.matriculaPK != null) || (this.matriculaPK != null && !this.matriculaPK.equals(other.matriculaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.institutorelacionalv2.DTO2.Matricula[ matriculaPK=" + matriculaPK + " ]";
    }
    
}
