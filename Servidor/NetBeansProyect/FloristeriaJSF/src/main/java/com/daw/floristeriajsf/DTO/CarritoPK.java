package com.daw.floristeriajsf.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class CarritoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "dniusuario")
    private String dniusuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoFlor")
    private int codigoFlor;

    public CarritoPK() {
    }

    public CarritoPK(String dniusuario, int codigoFlor) {
        this.dniusuario = dniusuario;
        this.codigoFlor = codigoFlor;
    }

    public String getDniusuario() {
        return dniusuario;
    }

    public void setDniusuario(String dniusuario) {
        this.dniusuario = dniusuario;
    }

    public int getCodigoFlor() {
        return codigoFlor;
    }

    public void setCodigoFlor(int codigoFlor) {
        this.codigoFlor = codigoFlor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dniusuario != null ? dniusuario.hashCode() : 0);
        hash += (int) codigoFlor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarritoPK)) {
            return false;
        }
        CarritoPK other = (CarritoPK) object;
        if ((this.dniusuario == null && other.dniusuario != null) || (this.dniusuario != null && !this.dniusuario.equals(other.dniusuario))) {
            return false;
        }
        if (this.codigoFlor != other.codigoFlor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.floristeriajsf.DTO.CarritoPK[ dniusuario=" + dniusuario + ", codigoFlor=" + codigoFlor + " ]";
    }

}
