package com.daw.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reparto")
@NamedQueries({
    @NamedQuery(name = "Reparto.findAll", query = "SELECT r FROM Reparto r"),
    @NamedQuery(name = "Reparto.findByCodPelicula", query = "SELECT r FROM Reparto r WHERE r.repartoPK.codPelicula = :codPelicula"),
    @NamedQuery(name = "Reparto.findByCodActor", query = "SELECT r FROM Reparto r WHERE r.repartoPK.codActor = :codActor"),
    @NamedQuery(name = "Reparto.findByMinutos", query = "SELECT r FROM Reparto r WHERE r.minutos = :minutos")})
public class Reparto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RepartoPK repartoPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "minutos")
    private int minutos;

    public Reparto() {
    }

    public Reparto(RepartoPK repartoPK) {
        this.repartoPK = repartoPK;
    }

    public Reparto(RepartoPK repartoPK, int minutos) {
        this.repartoPK = repartoPK;
        this.minutos = minutos;
    }

    public Reparto(int codPelicula, int codActor) {
        this.repartoPK = new RepartoPK(codPelicula, codActor);
    }

    public RepartoPK getRepartoPK() {
        return repartoPK;
    }

    public void setRepartoPK(RepartoPK repartoPK) {
        this.repartoPK = repartoPK;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (repartoPK != null ? repartoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reparto)) {
            return false;
        }
        Reparto other = (Reparto) object;
        if ((this.repartoPK == null && other.repartoPK != null) || (this.repartoPK != null && !this.repartoPK.equals(other.repartoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.DTO.Reparto[ repartoPK=" + repartoPK + " ]";
    }

}
