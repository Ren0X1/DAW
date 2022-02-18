package com.daw.traductorjsf.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class DiccionarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_palSp")
    private int codpalSp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_palIn")
    private int codpalIn;

    public DiccionarioPK() {
    }

    public DiccionarioPK(int codpalSp, int codpalIn) {
        this.codpalSp = codpalSp;
        this.codpalIn = codpalIn;
    }

    public int getCodpalSp() {
        return codpalSp;
    }

    public void setCodpalSp(int codpalSp) {
        this.codpalSp = codpalSp;
    }

    public int getCodpalIn() {
        return codpalIn;
    }

    public void setCodpalIn(int codpalIn) {
        this.codpalIn = codpalIn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codpalSp;
        hash += (int) codpalIn;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiccionarioPK)) {
            return false;
        }
        DiccionarioPK other = (DiccionarioPK) object;
        if (this.codpalSp != other.codpalSp) {
            return false;
        }
        if (this.codpalIn != other.codpalIn) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.traductorjsf.DAO.DiccionarioPK[ codpalSp=" + codpalSp + ", codpalIn=" + codpalIn + " ]";
    }

}
