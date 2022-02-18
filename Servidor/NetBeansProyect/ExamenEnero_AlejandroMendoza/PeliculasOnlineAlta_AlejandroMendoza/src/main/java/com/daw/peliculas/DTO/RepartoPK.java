package com.daw.peliculas.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class RepartoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "codPelicula")
    private int codPelicula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codActor")
    private int codActor;

    public RepartoPK() {
    }

    public RepartoPK(int codPelicula, int codActor) {
        this.codPelicula = codPelicula;
        this.codActor = codActor;
    }

    public int getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(int codPelicula) {
        this.codPelicula = codPelicula;
    }

    public int getCodActor() {
        return codActor;
    }

    public void setCodActor(int codActor) {
        this.codActor = codActor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codPelicula;
        hash += (int) codActor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepartoPK)) {
            return false;
        }
        RepartoPK other = (RepartoPK) object;
        if (this.codPelicula != other.codPelicula) {
            return false;
        }
        if (this.codActor != other.codActor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.peliculas.DTO.RepartoPK[ codPelicula=" + codPelicula + ", codActor=" + codActor + " ]";
    }

}
