package com.daw.senecaexa22_alejandromendoza.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p"),
    @NamedQuery(name = "Profesor.findByCodProfesor", query = "SELECT p FROM Profesor p WHERE p.codProfesor = :codProfesor"),
    @NamedQuery(name = "Profesor.findByNombreProfesor", query = "SELECT p FROM Profesor p WHERE p.nombreProfesor = :nombreProfesor"),
    @NamedQuery(name = "Profesor.findByUsuProfesor", query = "SELECT p FROM Profesor p WHERE p.usuProfesor = :usuProfesor"),
    @NamedQuery(name = "Profesor.findByPassProfesor", query = "SELECT p FROM Profesor p WHERE p.passProfesor = :passProfesor")})
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codProfesor")
    private Integer codProfesor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombreProfesor")
    private String nombreProfesor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "usuProfesor")
    private String usuProfesor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "passProfesor")
    private String passProfesor;

    public Profesor() {
    }

    public Profesor(Integer codProfesor) {
        this.codProfesor = codProfesor;
    }

    public Profesor(Integer codProfesor, String nombreProfesor, String usuProfesor, String passProfesor) {
        this.codProfesor = codProfesor;
        this.nombreProfesor = nombreProfesor;
        this.usuProfesor = usuProfesor;
        this.passProfesor = passProfesor;
    }

    public Integer getCodProfesor() {
        return codProfesor;
    }

    public void setCodProfesor(Integer codProfesor) {
        this.codProfesor = codProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getUsuProfesor() {
        return usuProfesor;
    }

    public void setUsuProfesor(String usuProfesor) {
        this.usuProfesor = usuProfesor;
    }

    public String getPassProfesor() {
        return passProfesor;
    }

    public void setPassProfesor(String passProfesor) {
        this.passProfesor = passProfesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codProfesor != null ? codProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.codProfesor == null && other.codProfesor != null) || (this.codProfesor != null && !this.codProfesor.equals(other.codProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.senecaexa22_alejandromendoza.DTO.Profesor[ codProfesor=" + codProfesor + " ]";
    }

}
