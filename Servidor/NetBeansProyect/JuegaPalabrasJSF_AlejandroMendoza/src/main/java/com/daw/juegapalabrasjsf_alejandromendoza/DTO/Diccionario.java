package com.daw.juegapalabrasjsf_alejandromendoza.DTO;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "diccionario")
@NamedQueries({
    @NamedQuery(name = "Diccionario.findAll", query = "SELECT d FROM Diccionario d"),
    @NamedQuery(name = "Diccionario.findByCodpalSp", query = "SELECT d FROM Diccionario d WHERE d.diccionarioPK.codpalSp = :codpalSp"),
    @NamedQuery(name = "Diccionario.findByCodpalIn", query = "SELECT d FROM Diccionario d WHERE d.diccionarioPK.codpalIn = :codpalIn")})
public class Diccionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DiccionarioPK diccionarioPK;

    public Diccionario() {
    }

    public Diccionario(DiccionarioPK diccionarioPK) {
        this.diccionarioPK = diccionarioPK;
    }

    public Diccionario(int codpalSp, int codpalIn) {
        this.diccionarioPK = new DiccionarioPK(codpalSp, codpalIn);
    }

    public DiccionarioPK getDiccionarioPK() {
        return diccionarioPK;
    }

    public void setDiccionarioPK(DiccionarioPK diccionarioPK) {
        this.diccionarioPK = diccionarioPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diccionarioPK != null ? diccionarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diccionario)) {
            return false;
        }
        Diccionario other = (Diccionario) object;
        return !((this.diccionarioPK == null && other.diccionarioPK != null) || (this.diccionarioPK != null && !this.diccionarioPK.equals(other.diccionarioPK)));
    }

    @Override
    public String toString() {
        return "com.daw.juegapalabrasjsf_alejandromendoza.DTO.Diccionario[ diccionarioPK=" + diccionarioPK + " ]";
    }

}
