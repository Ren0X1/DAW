/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.institutorelacionalv2.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lola
 */
@Embeddable
public class MatriculaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int nmatriculaAlumn;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int codasignatura;

    public MatriculaPK() {
    }

    public MatriculaPK(int nmatriculaAlumn, int codasignatura) {
        this.nmatriculaAlumn = nmatriculaAlumn;
        this.codasignatura = codasignatura;
    }

    public int getNmatriculaAlumn() {
        return nmatriculaAlumn;
    }

    public void setNmatriculaAlumn(int nmatriculaAlumn) {
        this.nmatriculaAlumn = nmatriculaAlumn;
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
        hash += (int) nmatriculaAlumn;
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
        if (this.nmatriculaAlumn != other.nmatriculaAlumn) {
            return false;
        }
        if (this.codasignatura != other.codasignatura) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.institutorelacionalv2.DTO2.MatriculaPK[ nmatriculaAlumn=" + nmatriculaAlumn + ", codasignatura=" + codasignatura + " ]";
    }
    
}
