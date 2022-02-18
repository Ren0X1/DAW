package com.daw.peliculas.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsu", query = "SELECT u FROM Usuario u WHERE u.usu = :usu"),
    @NamedQuery(name = "Usuario.findByPass", query = "SELECT u FROM Usuario u WHERE u.pass = :pass"),
    @NamedQuery(name = "Usuario.findByAdmin", query = "SELECT u FROM Usuario u WHERE u.admin = :admin")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "usu")
    private String usu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pass")
    private int pass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "admin")
    private int admin;

    public Usuario() {
    }

    public Usuario(String usu) {
        this.usu = usu;
    }

    public Usuario(String usu, int pass, int admin) {
        this.usu = usu;
        this.pass = pass;
        this.admin = admin;
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usu != null ? usu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usu == null && other.usu != null) || (this.usu != null && !this.usu.equals(other.usu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.peliculas.DTO.Usuario[ usu=" + usu + " ]";
    }

}
