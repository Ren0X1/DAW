package com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class DetallePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_articulo")
    private int codArticulo;

    public DetallePK() {
    }

    public DetallePK(String usuario, int codArticulo) {
        this.usuario = usuario;
        this.codArticulo = codArticulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(int codArticulo) {
        this.codArticulo = codArticulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuario != null ? usuario.hashCode() : 0);
        hash += (int) codArticulo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePK)) {
            return false;
        }
        DetallePK other = (DetallePK) object;
        if ((this.usuario == null && other.usuario != null) || (this.usuario != null && !this.usuario.equals(other.usuario))) {
            return false;
        }
        if (this.codArticulo != other.codArticulo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.crudtable_primefaces_alejandromendoza_lauramedina.DTO.DetallePK[ usuario=" + usuario + ", codArticulo=" + codArticulo + " ]";
    }

}
