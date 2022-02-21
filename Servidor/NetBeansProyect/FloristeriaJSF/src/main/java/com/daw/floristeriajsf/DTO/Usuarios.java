package com.daw.floristeriajsf.DTO;

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

@Entity
@Table(name = "usuarios")
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByDniusuario", query = "SELECT u FROM Usuarios u WHERE u.dniusuario = :dniusuario"),
    @NamedQuery(name = "Usuarios.findByPassusuario", query = "SELECT u FROM Usuarios u WHERE u.passusuario = :passusuario"),
    @NamedQuery(name = "Usuarios.findByNombreusuario", query = "SELECT u FROM Usuarios u WHERE u.nombreusuario = :nombreusuario")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "dniusuario")
    private String dniusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "passusuario")
    private String passusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nombreusuario")
    private String nombreusuario;

    public Usuarios() {
    }

    public Usuarios(String dniusuario) {
        this.dniusuario = dniusuario;
    }

    public Usuarios(String dniusuario, String passusuario, String nombreusuario) {
        this.dniusuario = dniusuario;
        this.passusuario = passusuario;
        this.nombreusuario = nombreusuario;
    }

    public String getDniusuario() {
        return dniusuario;
    }

    public void setDniusuario(String dniusuario) {
        this.dniusuario = dniusuario;
    }

    public String getPassusuario() {
        return passusuario;
    }

    public void setPassusuario(String passusuario) {
        this.passusuario = passusuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dniusuario != null ? dniusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.dniusuario == null && other.dniusuario != null) || (this.dniusuario != null && !this.dniusuario.equals(other.dniusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.floristeriajsf.DTO.Usuarios[ dniusuario=" + dniusuario + " ]";
    }

}
