/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.institutorelacional.DTO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "matricula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m"),
    @NamedQuery(name = "Matricula.findByNmatriculaAlum", query = "SELECT m FROM Matricula m WHERE m.matriculaPK.nmatriculaAlum = :nmatriculaAlum"),
    @NamedQuery(name = "Matricula.findByCodasignatura", query = "SELECT m FROM Matricula m WHERE m.matriculaPK.codasignatura = :codasignatura"),
    @NamedQuery(name = "Matricula.findByNota1", query = "SELECT m FROM Matricula m WHERE m.nota1 = :nota1"),
    @NamedQuery(name = "Matricula.findByNota2", query = "SELECT m FROM Matricula m WHERE m.nota2 = :nota2"),
    @NamedQuery(name = "Matricula.findByNota3", query = "SELECT m FROM Matricula m WHERE m.nota3 = :nota3"),
    @NamedQuery(name = "Matricula.findByNotafinal", query = "SELECT m FROM Matricula m WHERE m.notafinal = :notafinal")})
public class Matricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MatriculaPK matriculaPK;
    @Column(name = "nota1")
    private Integer nota1;
    @Column(name = "nota2")
    private Integer nota2;
    @Column(name = "nota3")
    private Integer nota3;
    @Column(name = "notafinal")
    private Integer notafinal;
    @JoinColumn(name = "codasignatura", referencedColumnName = "codasignatura", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Asignatura asignatura;
    @JoinColumn(name = "nmatriculaAlum", referencedColumnName = "nmatricula", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Alumno alumno;

    public Matricula() {
    }

    public Matricula(MatriculaPK matriculaPK) {
        this.matriculaPK = matriculaPK;
    }

    public Matricula(int nmatriculaAlum, int codasignatura) {
        this.matriculaPK = new MatriculaPK(nmatriculaAlum, codasignatura);
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

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
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
        return "com.daw.institutorelacional.DTO.Matricula[ matriculaPK=" + matriculaPK + " ]";
    }
    
}
