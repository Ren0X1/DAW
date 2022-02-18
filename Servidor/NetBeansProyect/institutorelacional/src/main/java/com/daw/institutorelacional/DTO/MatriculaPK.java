package com.daw.institutorelacional.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class MatriculaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "nmatriculaAlum")
    private int nmatriculaAlum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codasignatura")
    private int codasignatura;

    public MatriculaPK() {
    }

    public MatriculaPK(int nmatriculaAlum, int codasignatura) {
        this.nmatriculaAlum = nmatriculaAlum;
        this.codasignatura = codasignatura;
    }

    public int getNmatriculaAlum() {
        return nmatriculaAlum;
    }

    public void setNmatriculaAlum(int nmatriculaAlum) {
        this.nmatriculaAlum = nmatriculaAlum;
    }

    public int getCodasignatura() {
        return codasignatura;
    }

    public void setCodasignatura(int codasignatura) {
        this.codasignatura = codasignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nmatriculaAlum;
        hash += (int) codasignatura;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatriculaPK)) {
            return false;
        }
        MatriculaPK other = (MatriculaPK) object;
        if (this.nmatriculaAlum != other.nmatriculaAlum) {
            return false;
        }
        if (this.codasignatura != other.codasignatura) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.institutorelacional.DTO.MatriculaPK[ nmatriculaAlum=" + nmatriculaAlum + ", codasignatura=" + codasignatura + " ]";
    }

}
